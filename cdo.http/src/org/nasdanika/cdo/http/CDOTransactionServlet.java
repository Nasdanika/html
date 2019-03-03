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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;
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
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionProvider;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.transaction.CDOTransactionHandlerBase;
import org.eclipse.emf.cdo.util.ObjectNotFoundException;
import org.eclipse.emf.ecore.EObject;
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
			Principal principal = getPrincipal(req, transaction);
			long lastModified = Math.max(getLastModified(req, transaction), principal.timestamp());
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
				String homeUrl = principal.getHomeUrl();
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
				CDOObject target = principal.find(idStr);
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
			
			// Unauthenticated principal - return authorized or redirect to auth url
			if (result == Result.FORBIDDEN && !principal.isAuthenticated()) {
				String au = principal.getAuthenticationUrl();
				if (Util.isBlank(au)) {
					resp.sendRedirect(au);
					result = null;
				} else {
					result = Result.UNAUTHORIZED;
				}
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
		} catch (AuthenticationException e) {
			log("Authentication exception: " + e, e);
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.toString());			
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
	protected Principal getPrincipal(HttpServletRequest req, CDOTransaction transaction) {
		// Caching in the request for optimization.
		Principal ret = (Principal) req.getAttribute(SUBJECT_KEY);
		if (ret == null) {
			ret = doGetPrincipal(req, transaction);
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
	protected Principal doGetPrincipal(HttpServletRequest req, CDOTransaction transaction) {				
		// Remote user
		String forwardedUserHeader = getForwardedUserHeader();
		if (forwardedUserHeader != null) {
			String principalName = req.getHeader(forwardedUserHeader);
			if (principalName != null) {
				String remoteAddr = req.getRemoteAddr();
				if (authorizeForwardingHost(remoteAddr)) {
					return createForwardedUserPrincipal(req, transaction, principalName);
				}
				
				throw new AuthenticationException("Unauthorized forwarding host: "+remoteAddr);
			}
		}		
		
		// Auth header is present
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null) {
			if (authHeader.startsWith(AUTHORIZATION_BEARER)) {		
				String token = authHeader.substring(AUTHORIZATION_BEARER.length());
				return createTokenPrincipal(req, transaction, token);				
			} 
			
			if (authHeader.startsWith(AUTHORIZATION_BASIC)) {
				// Basic authorization
				String decoded = new String(Base64.getDecoder().decode(authHeader.substring(AUTHORIZATION_BASIC.length())));
				int idx = decoded.indexOf(":");
				if (idx == -1) {
					throw new AuthenticationException("Invalid credentials format: "+decoded);
				}
				return createUserPasswordPrincipal(req, transaction, decoded.substring(0, idx), decoded.substring(idx+1));
			}
		}
		
		// Principals are present in the session.
		HttpSession session = req.getSession(false);
		if (session != null) {
			List<BiFunction<HttpServletRequest, CDOTransaction, Principal>> principalProviders = getSessionSubject(req, transaction);
			if (principalProviders != null) {
				List<Principal> principals = new ArrayList<>();
				for (BiFunction<HttpServletRequest, CDOTransaction, Principal> pp: principalProviders) {
					principals.add(pp.apply(req, transaction));
				}
				if (principals.size() == 1) {
					return principals.get(0);
				}
				return new Principal() {
	
					@Override
					public AccessDecision authorize(Object target, String action, String qualifier, Map<?, ?> context) {
						for (Principal principal: principals) {
							AccessDecision ad = principal.authorize(target, action, qualifier, context);
							if (ad != AccessDecision.ABSTAIN) {
								return ad;
							}
						}
						return AccessDecision.ABSTAIN;
					}
	
					@Override
					public boolean isAuthenticated() {
						return true;
					}
					
					@Override
					public String getHomeUrl() {
						for (Principal principal: principals) {
							String hu = principal.getHomeUrl();
							if (!Util.isBlank(hu)) {
								return hu;
							}
						}
						return null;
					}
					
					@Override
					public String getAuthenticationUrl() {
						for (Principal principal: principals) {
							String au = principal.getAuthenticationUrl();
							if (!Util.isBlank(au)) {
								return au;
							}
						}
						return Principal.super.getAuthenticationUrl();
					}
	
					// Delegates to the first principal
					@Override
					public CDOObject find(String id) {
						return principals.get(0).find(id);
					}
	
					@Override
					public long timestamp() {
						return principals.stream().mapToLong(p -> p.timestamp()).max().getAsLong();
					}
					
				};
			}
		}
		
		// Unauthenticated subject		
		return createUnauthenticatedPrincipal(req, transaction);
	}

	/**
	 * Retrieves a subject (a list of principal providers) from the session. 
	 * @param req
	 * @param transaction
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<BiFunction<HttpServletRequest, CDOTransaction, Principal>> getSessionSubject(HttpServletRequest req, CDOTransaction transaction) {
		HttpSession session = req.getSession(false);
		return session == null ? null : (List<BiFunction<HttpServletRequest, CDOTransaction, Principal>>) session.getAttribute(SUBJECT_KEY);
	}
	
	/**
	 * Retrieves a subject (a list of functions creating principals) from the session. 
	 * @param req
	 * @param transaction
	 * @return
	 */
	protected void setSessionSubject(HttpServletRequest req, CDOTransaction transaction, List<BiFunction<HttpServletRequest, CDOTransaction, Principal>> principalProviders) {
		req.getSession().setAttribute(SUBJECT_KEY, principalProviders);
	}

	/**
	 * Creates a principal for user name and password. Override as needed. This method returns null.
	 * @param req
	 * @param transaction
	 * @param user
	 * @param password
	 * @return
	 */
	protected Principal createUserPasswordPrincipal(
			HttpServletRequest req, 
			CDOTransaction transaction, 
			String user,
			String password) {
		throw new AuthenticationException("Invalid credentials");
	}

	/**
	 * Creates a principal for a forwarded user. This implementation returns null. 
	 * Override to provide support of user forwarding. 
	 * @param forwardedUser
	 * @return
	 */
	protected Principal createForwardedUserPrincipal(HttpServletRequest req, CDOTransaction transaction, String forwardedUser) {
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
	protected Principal createTokenPrincipal(HttpServletRequest req, CDOTransaction transaction, String token) {
		SigningKeyResolver signingKeyResolver = getJwtTokenSignatureKeyResolver(req, transaction);
		if (signingKeyResolver == null) {
			throw new AuthenticationException("No signing key resolver");
		}
		try {
			JwtParser parser = Jwts.parser()
					.setSigningKeyResolver(getJwtTokenSignatureKeyResolver(req, transaction))
					.setAllowedClockSkewSeconds(60 * 5); // 5 minutes.
			validateTokenRequirements(parser);
			
			Jws<Claims> jws = parser.parseClaimsJws(token);			
			return createJwtPrincipal(req, transaction, jws);
		} catch (JwtException jwtException) {
			throw new AuthenticationException(jwtException);
		}
	};
	
	/**
	 * Creates JWT subject. This implementation uses subject field as forwarded user field to create a principal.
	 * @param req
	 * @param transaction
	 * @param jws
	 * @return
	 */
	protected Principal createJwtPrincipal(HttpServletRequest req, CDOTransaction transaction, Jws<Claims> jws) {
		String subject = jws.getBody().getSubject();
		return Util.isBlank(subject) ? null : createForwardedUserPrincipal(req, transaction, subject);
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
	 * Creates unauthenticated principal (guest).
	 * @param req
	 * @param transaction
	 * @return
	 */
	protected Principal createUnauthenticatedPrincipal(HttpServletRequest req, CDOTransaction transaction) {
		throw new AuthenticationException("Unauthenticated principal is not defined");
	}
		
	/**
	 * Returns application wide read-write lock.
	 * @return
	 */
	public ReentrantReadWriteLock getApplicationLock() {
		return (ReentrantReadWriteLock) getServletContext().getAttribute(APP_LOCK_KEY);
	}
	
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
			private boolean authorize(
					HttpServletRequest req, 
					CDOTransaction transaction, 
					EObject target, 
					String action, 
					String qualifier, 
					Map<?,?> context) {
				
				return getPrincipal(req, transaction).authorize(target, action, qualifier, context) == AccessDecision.ALLOW;
			} 	
			
		};
	}
		
}
