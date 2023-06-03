package org.nasdanika.html.ecore.gen.processors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.EPackage;

/**
 * Annotation for a class or a method.
 * 
 * Annotated method shall have the following signature: 
 * <PRE>
 * NodeProcessorConfig&lt;Object, WidgetFactory, WidgetFactory, Registry&lt;URI&gt;&gt; config, 
 * java.util.function.Function&lt;ProgressMonitor, Action&gt; prototypeProvider,
 * ProgressMonitor progressMonitor
 * </PRE>
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
@Inherited
public @interface EPackageNodeProcessorFactory {
	
	// Selector
	
	/**
	 * {@link EPackage} namespace URI. Required on a class. On a method is inherited from the class.
	 * @return
	 */
	String nsURI() default "";
	
	// Action prototype

	/**
	 * YAML specification of the action prototype. 
	 * @return
	 */
	String actionPrototype() default "";
	
	/**
	 * URI of an action prototype resource resolved relative to the annotated method's class.
	 * @return
	 */
	String actionPrototypeRef() default "";
	
	/**
	 * Element label is used for action text, overrides action prototype setting. 
	 * @return
	 */
	String label() default "";
	
	/**
	 * Action icon, overrides action prototype setting. 
	 * @return
	 */
	String icon() default "";
	
	/**
	 * Description is used for action tooltips, overrides action prototype setting. 
	 * @return
	 */
	String description() default "";
	
	/**
	 * Documentation in markdown, added to action content. 
	 * @return
	 */
	String documentation() default "";

}
