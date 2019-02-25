package org.nasdanika.cdo.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.osgi.framework.BundleContext;

public abstract class ReflectiveRouter extends Router {

	protected Object target;

	protected BundleContext bundleContext;
	protected CDOObject entity;


	public ReflectiveRouter(BundleContext bundleContext, CDOObject entity, Object target) {
		this.bundleContext = bundleContext;
		this.entity = entity;
		this.target = target;
	}

	@Override
	protected List<Target> getTargets(HttpServletRequest request) throws Exception {
		List<Target> ret = new ArrayList<>();
		for (Method m: target.getClass().getMethods()) {
			if (m.getAnnotation(Route.class) != null) {
				ret.add(new RouteMethodTarget(bundleContext, entity, m, target) {

					@Override
					protected Object processBodyParameter(HttpServletRequest request, Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {
						return ReflectiveRouter.this.processBodyParameter(m, request, parameterType, parameterAnnotations);
					}

					@Override
					protected <T> T convert(Object obj, Class<T> type) throws Exception {
						return ReflectiveRouter.this.convert(obj, type);
					}
					
				});
			}
		}
		return ret;
	}

	/**
	 * Converts request body to parameter type. This implementation uses JSON parser for json content type 
	 * and calls convert(request.getInputStream(), parameterType) otherwise.
	 * @param context
	 * @param parameterType
	 * @return
	 * @throws Exception
	 */
	protected Object processBodyParameter(Method method, HttpServletRequest request, Class<?> parameterType, Annotation[] parameterAnnotations) throws Exception {
		// Explicit JSON conversion
		if (Util.JSON_CONTENT_TYPE.equals(request.getContentType())) {
			if (parameterType == JSONArray.class) {
				return new JSONArray(new JSONTokener(request.getReader()));
			}
			
			if (parameterType == JSONObject.class) {
				return new JSONObject(new JSONTokener(request.getReader()));
			}			
		}
		
		return convert(request.getInputStream(), parameterType);
	}

	/**
	 * Override to customize conversion, e.g. to convert from String to {@link CDOID}. 
	 * @param obj
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T convert(Object obj, Class<T> type) throws Exception {
		if (obj == null || type.isInstance(obj)) {
			return (T) obj;
		}
		if (CDOObject.class.isAssignableFrom(type)) {
			if (obj instanceof CDOID) {
				return (T) entity.cdoView().getObject(((CDOID) obj));
			}
			if (obj instanceof String) {
				return convert(convert(obj, CDOID.class), type);
			}
		}				
		
		Converter converter = EObjectAdaptable.adaptTo(entity,  Converter.class);		
		if (converter == null) {
			converter = ReflectiveConverter.INSTANCE;
		}
		return converter.convert(obj, type);
	}
	
	
}
