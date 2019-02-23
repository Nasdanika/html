package org.nasdanika.cdo.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.emf.cdo.CDOObject;
import org.nasdanika.html.emf.EObjectAdaptable;

/**
 * Annotation to pass the context entity ({@link CDOObject}) to a method. 
 * If the entity is not an instance of the parameter type it gets adapted to the parameter type with {@link EObjectAdaptable}.adaptTo(). 
 * @author Pavel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface EntityParameter {
	
	
}
