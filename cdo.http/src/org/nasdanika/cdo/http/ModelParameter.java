package org.nasdanika.cdo.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.EObject;

/**
 * Arguments for parameters with this annotation are injected with values from request parameters.
 
 * Parameter type must be a subtype of {@link EObject}. Argument value is provided in the following way:
 * 
 * * Session package registry is searched for a package/factory which can instantiate the parameter type.
 * * The factory is used to instantiate the type.
 * * Request parameters are injected into features with names matching request parameters names.
 *  
 * If a feature is a non-containing reference, then values shall be strings representing encoded CDOID's. 
 * For containing references parameter values shall be in dotted notation hierarchy form, e.g. accounts.number.
 * 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ModelParameter {
		
}
