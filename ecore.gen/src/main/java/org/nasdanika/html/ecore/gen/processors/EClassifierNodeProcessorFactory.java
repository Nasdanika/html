package org.nasdanika.html.ecore.gen.processors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

/**
 * Annotated method shall have the following signature: TODO
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface EClassifierNodeProcessorFactory {
	
	/**
	 * {@link EClassifier} name.
	 * @return
	 */
	String value();
	
	/**
	 * NS URI of containing {@link EPackage}. Optional if the method's class is annotated with {@link EPackageNodeProcessorFactory}.  
	 */
	String nsURI() default "";

	/**
	 * URI of an action prototype resource resolved relative to the annotated method's class.
	 * @return
	 */
	String actionPrototype() default "";

}
