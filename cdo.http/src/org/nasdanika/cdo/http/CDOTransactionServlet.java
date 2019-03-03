package org.nasdanika.cdo.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

//import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.cdo.common.id.CDOIDUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionProvider;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.transaction.CDOTransactionHandlerBase;
import org.eclipse.emf.cdo.util.ObjectNotFoundException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.cdo.http.Principal.AccessDecision;
import org.nasdanika.html.Producer;
import org.nasdanika.html.emf.AccessController;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.util.tracker.ServiceTracker;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SigningKeyResolver;

@SuppressWarnings("serial")
/**
 * 
 * @author Pavel
 *
 * @param <P> Principal type.
 */
public class CDOTransactionServlet extends HttpServlet {
	
	
	protected ServiceTracker<CDOSessionProvider, CDOSessionProvider> cdoSessionProviderServiceTracker;
	
//	public static final MimetypesFileTypeMap MIME_TYPES_MAP = new MimetypesFileTypeMap(CDOTransactionServlet.class.getResourceAsStream("mime.types"));	
	
	/**
	 * A key to store subject in a request and a list of subject principals in a session.
	 */
	protected static final String SUBJECT_KEY = "org.nasdanika.cdo.web:subject";
	
	protected static final String AUTH_CACHE_KEY = "org.nasdanika.cdo.web:auth-cache";
		
	public static final String APP_LOCK_KEY = "org.nasdanika.cdo.web:application-lock";
	
	public static final String AUTHORIZATION_BEARER = "Bearer ";
	
	private static final String AUTHORIZATION_BASIC = "Basic ";
		
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";		
		
	protected boolean jsonPrettyPrint = true;

	private BundleContext bundleContext;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		bundleContext = FrameworkUtil.getBundle(getClass()).getBundleContext();
		if (bundleContext == null) {
			throw new IllegalStateException("Bundle context is not available, make sure that bundle "
					+ FrameworkUtil.getBundle(getClass()).getSymbolicName() + " is activated");
		}

		String serviceFilter = config.getInitParameter("cdo-session-provider-service-filter");
		// TODO - bundle is still null???
		if (serviceFilter == null || serviceFilter.trim().length() == 0) {
			cdoSessionProviderServiceTracker = new ServiceTracker<>(bundleContext, CDOSessionProvider.class.getName(), null);
		} else {
			String theFilter = "(&(" + Constants.OBJECTCLASS + "=" + CDOSessionProvider.class.getName() + ")" + serviceFilter + ")";
			try {
				cdoSessionProviderServiceTracker = new ServiceTracker<>(bundleContext, bundleContext.createFilter(theFilter), null);
			} catch (InvalidSyntaxException e) {
				throw new ServletException("Invalid service filter (" + e + "): " + serviceFilter, e);
			}
		}
		cdoSessionProviderServiceTracker.open();
		
		ReentrantReadWriteLock applicationLock = (ReentrantReadWriteLock) getServletContext().getAttribute(APP_LOCK_KEY);
		if (applicationLock == null) {
			applicationLock = new ReentrantReadWriteLock(true);
			getServletContext().setAttribute(APP_LOCK_KEY, applicationLock);
		}	
		
	}	
	
	protected BundleContext getBundleContext() {
		return bundleContext;
	}

	@Override
	public void destroy() {
		if (cdoSessionProviderServiceTracker != null) {
			cdoSessionProviderServiceTracker.close();
		}
		super.destroy();
	}
	
	/**
	 * Builds url including scheme and port.
	 * @param req
	 * @return
	 */
	protected StringBuilder buildUrl(HttpServletRequest req) {
		StringBuilder urlBuilder = new StringBuilder(req.getScheme())
		        .append("://")
		        .append(req.getServerName());

		int port = req.getServerPort();
		if ((req.getScheme().equals("http") && port != 80) || (req.getScheme().equals("https") && port != 443)) {
			urlBuilder.append(':').append(port);
		}
		urlBuilder.append(req.getContextPath()).append(req.getServletPath());			
		
		return urlBuilder;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CDOSessionProvider sessionProvider = cdoSessionProviderServiceTracker.getService();
		if (sessionProvider == null) {
			throw new ServletException("Session provider not found");
		}
		
		CDOSession session = sessionProvider.getSession();
		CDOTransaction transaction = session.openTransaction();
		Result result = null;
		try {
			// No subject - it means that authentication information was presented, but was not accepted.			
			Subject subject = getSubject(req, transaction);
			if (subject == null) {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
			
			long lastModified = getLastModified(req, transaction);
			if (RequestMethod.valueOf(req.getMethod()) == RequestMethod.GET && lastModified != -1) {
			    long ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
			    if (ifModifiedSince > 0 && lastModified > 0) {
			        if (lastModified - ifModifiedSince < 1000) { // Seconds precision.
			        	resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			        	return;
			        }
			    }
			}
			
			String pathInfo = req.getPathInfo();
			if (pathInfo == null || "/".equals(pathInfo)) {
				String homeUrl = subject.getHomeUrl();
				if (Util.isBlank(homeUrl)) { 
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				} else {
					resp.sendRedirect(buildUrl(req).append("/").append(homeUrl).toString());
				}
			} else {
				String[] path = pathInfo.split("/");
				String firstSegment = path[1];
				int dotIdx = firstSegment.lastIndexOf('.');
				String idStr = dotIdx == -1 ? firstSegment : firstSegment.substring(0, dotIdx);
				CDOID cdoId = decodeCDOID(req, idStr);				
				CDOObject target = transaction.getObject(cdoId);

				configureTransaction(req, transaction);
				
				Processor processor = EObjectAdaptable.adaptTo(target, Processor.class);
				if (processor == null) {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				} else {
					HttpServletRequestWrapper wReq = new HttpServletRequestWrapper(req) {
						
						@Override
						public String getPathInfo() {
							return super.getPathInfo().substring(dotIdx+1);
						}
						
						@Override
						public String getServletPath() {
							return super.getServletPath()+super.getPathInfo().substring(0, dotIdx +1);
						}
						
					};
					
					Map<String,String> pathVariables = new HashMap<>();
					pathVariables.put(Util.PATH_VARIABLE_PATH_INFO, wReq.getPathInfo());
					pathVariables.put(Util.PATH_VARIABLE_ROUTE_PATH, wReq.getContextPath()+wReq.getServletPath());
					
					pathVariables.put(Util.PATH_VARIABLE_ROUTE_URL, buildUrl(wReq).toString()); 
					
					result = processor.process(wReq, resp, pathVariables::get);
					if (result instanceof CDOTransactionHandlerBase) {
						transaction.addTransactionHandler((CDOTransactionHandlerBase) result);
					}
				}				
			}
			if (result != null && result.isRollback()) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
			if (result != null) {
				String contentType = result.getContentType();
				if (contentType != null) {
					resp.setContentType(contentType);
				}
				resultToResponse(result.getValue(), resp);
			}
		} catch (ObjectNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);			
		} catch (Exception e) {
			log(e.toString(), e);
			transaction.rollback();
			if (e instanceof ServletException) {
				throw (ServletException) e;
			}
			if (e instanceof IOException) {
				throw (IOException) e;
			}
			throw new ServletException(e);
		} finally {
			long lastModified = getLastModified(req, transaction);
			if (RequestMethod.valueOf(req.getMethod()) == RequestMethod.GET && lastModified != -1 && !resp.containsHeader(HEADER_LASTMOD)) {
		            resp.setDateHeader(HEADER_LASTMOD, lastModified);        	
			}

			transaction.close();
			if (result != null) {
				try {
					result.close();
				} catch (Exception e) {
					log("Result closing failed: "+e.toString(), e);
				}
			}
		}
	}
	
	protected void resultToResponse(Object result, HttpServletResponse resp) throws Exception {
		if (result instanceof ProcessingError) {
			ProcessingError processingError = (ProcessingError) result;
			if (processingError.getMessage()==null) {
				resp.sendError(processingError.getCode());
			} else {
				resp.sendError(processingError.getCode(), processingError.getMessage());
			}
		} else if (result instanceof Producer) {
			resultToResponse(((Producer) result).produce(4), resp);
		} else if (result instanceof JSONArray) {
			resp.setContentType("application/json");
			if (jsonPrettyPrint) {
				resp.getWriter().write(((JSONArray) result).toString(4));
			} else {
				((JSONArray) result).write(resp.getWriter());
			}
		} else if (result instanceof JSONObject) {
			resp.setContentType("application/json");
			if (jsonPrettyPrint) {
				resp.getWriter().write(((JSONObject) result).toString(4));
			} else {
				((JSONObject) result).write(resp.getWriter());
			}
		} else if (result instanceof String) {
			resp.getWriter().write((String) result);
		} else if (result instanceof char[]) {
			resp.getWriter().write((char[]) result);
		} else if (result instanceof byte[]) {
			resp.getOutputStream().write((byte[]) result);
		} else if (result instanceof Reader) {
			try {
				Writer writer = resp.getWriter();
				char[] cbuf = new char[4096];
				int l;
				while ((l=((Reader) result).read(cbuf))!=-1) {
					writer.write(cbuf, 0, l);
				}
			} finally {
				((Reader) result).close();
			}
		} else if (result instanceof InputStream) {
			try (InputStream in = (InputStream) result; OutputStream out = resp.getOutputStream()) {
				byte[] buf = new byte[4096];
				int l;
				while ((l=((InputStream) result).read(buf))!=-1) {
					out.write(buf, 0, l);
				}
			}
		} else if (result instanceof URL) {
			resultToResponse(((URL) result).openStream(), resp);
		} else if (result!=null) {
			resp.getWriter().write(result.toString());
		}
	}	
	
	protected long getLastModified(HttpServletRequest req, CDOTransaction transaction) {
		return transaction.getLastUpdateTime();
	}	

	/**
	 * Override to register adapter factories and perform other transaction configuration
	 * before request processing. 
	 * @param req
	 * @param transaction
	 */
	protected void configureTransaction(HttpServletRequest req, CDOTransaction transaction) {
		
	}
	
	/**
	 * Request header containing forwarded user name.
	 * @return
	 */
	protected String getForwardedUserHeader() {
		return "X-Forwarded-User";
	}
	
	/**
	 * Authorizes address for forwarding user name. This method authorizes only the localhost.
	 * @param remoteAddress
	 * @return
	 */
	protected boolean authorizeForwardingHost(String remoteAddress) {
		try {
			return InetAddress.getLocalHost().equals(InetAddress.getByName(remoteAddress));
		} catch (UnknownHostException e) {
			log("Cannot obtain localhost or invalid remote address", e);
			return false;
		}
	}
	
	/**
	 * Caches return value of doGetSubject in the request for optimization.
	 * @param req
	 * @return
	 */
	protected Subject getSubject(HttpServletRequest req, CDOTransaction transaction) {
		// Caching in the request for optimization.
		Subject ret = (Subject) req.getAttribute(SUBJECT_KEY);
		if (ret == null) {
			ret = doGetSubject(req, transaction);
			req.setAttribute(SUBJECT_KEY, ret);
		}
		
		return ret;
	}
	
	/**
	 * Subject is a list of principals associated with a request.
	 * @param req
	 * @param transaction
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Subject doGetSubject(HttpServletRequest req, CDOTransaction transaction) {				
		// Remote user
		String remoteUserHeader = getForwardedUserHeader();
		if (remoteUserHeader != null) {
			String principalName = req.getHeader(remoteUserHeader);
			if (principalName != null) {
				if (authorizeForwardingHost(req.getRemoteAddr())) {
					return createForwardedUserSubject(req, transaction, principalName);
				}
				
				return null;
			}
		}		
		
		// Auth header is present
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null) {
			if (authHeader.startsWith(AUTHORIZATION_BEARER)) {		
				String token = authHeader.substring(AUTHORIZATION_BEARER.length());
				return createTokenSubject(req, transaction, token);				
			} 
			
			if (authHeader.startsWith(AUTHORIZATION_BASIC)) {
				// Basic authorization
				String decoded = new String(Base64.getDecoder().decode(authHeader.substring(AUTHORIZATION_BASIC.length())));
				int idx = decoded.indexOf(":");
				if (idx == -1) {
					return null; // Bad basic auth format.
				}
				return createUserPasswordSubject(req, transaction, decoded.substring(0, idx), decoded.substring(idx+1));
			}
		}
		
		// Principals are present in the session.
		HttpSession session = req.getSession(false);
		if (session != null) {
			List<Principal> principals = (List<Principal>) session.getAttribute(SUBJECT_KEY);
			if (principals != null) {
				return 
				List<P> subject = new ArrayList<>();
				for (CDOID subjectId: subjectIds) {
					subject.add((P) transaction.getObject(subjectId));
				}
				return Collections.unmodifiableList(subject);
			}
		}
		
		// Unauthenticated subject		
		return createUnauthenticatedSubject(req, transaction);
	}
	
	protected Subject 

	/**
	 * Creates a subject for user name and password. Override as needed. This method returns null.
	 * @param req
	 * @param transaction
	 * @param user
	 * @param password
	 * @return
	 */
	protected Subject createUserPasswordSubject(
			HttpServletRequest req, 
			CDOTransaction transaction, 
			String user,
			String password) {
		return null;
	}

	/**
	 * Builds subject for a forwarded user. This implementation returns null. 
	 * Override to provide support of user forwarding. 
	 * @param forwardedUser
	 * @return
	 */
	protected Subject createForwardedUserSubject(HttpServletRequest req, CDOTransaction transaction, String forwardedUser) {
		return null;
	};
	
	protected Key getJwtTokenIssuerKey(HttpServletRequest req, CDOTransaction transaction, String key) {
		return null;
	}
	
	protected SigningKeyResolver getJwtTokenSignatureKeyResolver(HttpServletRequest req, CDOTransaction transaction) {
		return new SigningKeyResolver() {
			
			@Override
			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, String plainText) {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims) {
				return getJwtTokenIssuerKey(req, transaction, claims.getIssuer());
			}
		};
	}
	
	/**
	 * Builds token subject. This creates a JWT token subject. 
	 * Override to provide support of user forwarding. 
	 * @param forwardedUser
	 * @return
	 */
	protected Subject createTokenSubject(HttpServletRequest req, CDOTransaction transaction, String token) {
		SigningKeyResolver signingKeyResolver = getJwtTokenSignatureKeyResolver(req, transaction);
		if (signingKeyResolver == null) {
			return null;
		}
		try {
			JwtParser parser = Jwts.parser()
					.setSigningKeyResolver(getJwtTokenSignatureKeyResolver(req, transaction))
					.setAllowedClockSkewSeconds(60 * 5); // 5 minutes.
			validateTokenRequirements(parser);
			
			Jws<Claims> jws = parser.parseClaimsJws(token);			
			return createJwtSubject(req, transaction, jws);
		} catch (JwtException jwtException) {
			log("JWT Token authentication failed: "+ jwtException + " " + token, jwtException);
			return null;
		}
	};
	
	/**
	 * Creates JWT subject. This implementation returns null;
	 * @param req
	 * @param transaction
	 * @param jws
	 * @return
	 */
	protected Subject createJwtSubject(HttpServletRequest req, CDOTransaction transaction, Jws<Claims> jws) {
		return null;
	}
	
	/**
	 * This method requires not before and expiration. 
	 * Override to implement additional checks. 
	 * @param parser
	 */
	protected void validateTokenRequirements(JwtParser parser) {
		Date now = new Date();
		parser.requireNotBefore(now).requireExpiration(now);
	}
		
	
	/**
	 * Builds a subject for the primary principal. For example, if user group membership is retrieved from
	 * LDAP or active directory and permissions in the system are not granted to users, but to groups this method
	 * shall lookup user's groups and add them as secondary principals in the subject. This implementation returns a singleton list
	 * containing just the primary principal.
	 * @param req
	 * @param transaction
	 * @param principal
	 * @return
	 */
	protected List<P> buildSubject(HttpServletRequest req, CDOTransaction transaction, P principal) {
		return Collections.singletonList(principal);
	}
	
	/**
	 * Stores subject to the session. This method can be invoked by authentication processing code.
	 * It can be made available to the model via some authentication adapter. 
	 * @param req
	 * @param transaction
	 * @param subject
	 */
	protected void setSubject(HttpServletRequest req, CDOTransaction transaction, List<P> subject) {
		HttpSession session = req.getSession();
		List<CDOID> subjectIds = new ArrayList<CDOID>(); ;
		for (P principal: subject) {
			subjectIds.add(principal.cdoID());
		}
		session.setAttribute(SUBJECT_KEY, subjectIds);
	}
	
	/**
	 * Looks up principal by name. Used for remote/forwarded users.
	 * @param req
	 * @param transaction
	 * @param name
	 * @return
	 */
	protected P getPrincipalByName(HttpServletRequest req, CDOTransaction transaction, String name) {
		return null;
	}

	/**
	 * For basic authentication
	 * @param req
	 * @param transaction
	 * @param user
	 * @param password
	 * @return
	 */
	protected P authenticate(HttpServletRequest req, CDOTransaction transaction, String user, String password) {
		return null;
	}
	
	/**
	 * For token authentication - part after ".".
	 * @param req
	 * @param transaction
	 * @param user
	 * @param password
	 * @return
	 */
	protected boolean authenticate(HttpServletRequest req, CDOTransaction transaction, P principal, String token) {
		return false;
	}
	
	/**
	 * Unauthenticated subject (guest).
	 * @param req
	 * @param transaction
	 * @return
	 */
	protected Subject createUnauthenticatedSubject(HttpServletRequest req, CDOTransaction transaction) {
		return null;
	}
	
	protected String encodeCDOID(HttpServletRequest req, CDOID cdoID) {
		StringBuilder builder = new StringBuilder();
		CDOIDUtil.write(builder, cdoID);
		return builder.toString();
	}
	
	protected CDOID decodeCDOID(HttpServletRequest req, String idStr) {
		return CDOIDUtil.read(idStr);
	}
		
	protected Converter getConverter(HttpServletRequest req) {
		return new Converter() {

			@SuppressWarnings("unchecked")
			@Override
			public <T> T convert(Object source, Class<T> type) throws Exception {
				if (source instanceof String && CDOID.class.equals(type)) {
					return (T) decodeCDOID(req, (String) source);
				}
				
				if (source instanceof CDOID && String.class.equals(type)) {
					return (T) encodeCDOID(req, (CDOID) source);
				}
				
				return ReflectiveConverter.INSTANCE.convert(source, type);
			}
			
		};
	}
		
	/**
	 * Returns application wide read-write lock.
	 * @return
	 */
	public ReentrantReadWriteLock getApplicationLock() {
		return (ReentrantReadWriteLock) getServletContext().getAttribute(APP_LOCK_KEY);
	}
	
// ---------------
	
	
	protected static class CacheKey {
		String action;
		String qualifier;
		
		CacheKey(String action, String qualifier) {
			super();
			this.action = action;
			this.qualifier = qualifier;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((action == null) ? 0 : action.hashCode());
			result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CacheKey other = (CacheKey) obj;
			if (action == null) {
				if (other.action != null)
					return false;
			} else if (!action.equals(other.action))
				return false;
			if (qualifier == null) {
				if (other.qualifier != null)
					return false;
			} else if (!qualifier.equals(other.qualifier))
				return false;
			return true;
		}		
		
	}
	
	/**
	 * Creates access controller which calls authorize() and caches permission check results with empty contexts.
	 * @param req
	 * @param transaction
	 * @param target
	 * @param context
	 * @return
	 */
	protected AccessController createAccessController(HttpServletRequest req, CDOTransaction transaction, EObject target) {
		return new AccessController() {
						
			private Map<CacheKey, Boolean> cache = new ConcurrentHashMap<>();
			
			@Override
			public boolean hasPermission(String action, String qualifier, Map<?,?> context) {
				if (context.isEmpty()) {
					Function<? super CacheKey, ? extends Boolean> cf = key -> authorize(req, transaction, target, key.action, key.qualifier, context);
					return cache.computeIfAbsent(new CacheKey(action, qualifier), cf);
				}
				return authorize(req, transaction, target, action, qualifier, context);
			}
			
		};
	}
	
	/**
	 * Authorizes a principal. This implementation uses object containment for authorization - principal is authorized
	 * for any action on targets it contains.
	 * @param req
	 * @param transaction
	 * @param principal
	 * @param target
	 * @param action
	 * @param qualifier
	 * @param context
	 * @return
	 */
	protected AccessDecision authorize(
			HttpServletRequest req, 
			CDOTransaction transaction, 
			P principal, 
			EObject target, 
			String action, 
			String qualifier, 
			Map<?,?> context) {
		
		return principal != null && target != null && EcoreUtil.isAncestor(principal, target) ? AccessDecision.ALLOW : AccessDecision.DENY;
	}
	
	/**
	 * Checks request authorization cache first, calls doAuthorize if there is no entry in the cache.
	 * @param req
	 * @param transaction
	 * @param target
	 * @param action
	 * @param qualifier
	 * @param context
	 * @return
	 */
	protected boolean authorize(
			HttpServletRequest req, 
			CDOTransaction transaction, 
			EObject target, 
			String action, 
			String qualifier, 
			Map<?,?> context) {
		
		for (P principal: getSubject(req, transaction)) {
			switch (authorize(req, transaction, principal, target, action, qualifier, context)) {
			case ABSTAIN:
				continue;
			case ALLOW:
				return true;
			case DENY:
				return false;
			}
		}
		
		return false;
	} 	
	
}
