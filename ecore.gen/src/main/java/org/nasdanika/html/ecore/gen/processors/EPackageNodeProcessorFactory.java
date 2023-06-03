package org.nasdanika.html.ecore.gen.processors;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.eclipse.emf.ecore.EPackage;

/**
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
@Target(METHOD)
public @interface EPackageNodeProcessorFactory {
	
	/**
	 * {@link EPackage} namespace URI.
	 * @return
	 */
	String value();

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
	 * Description is used for action tooltips, overrides action prototype setting. 
	 * @return
	 */
	String description() default "";
		
	/**
	 * Action icon, overrides action prototype setting. 
	 * @return
	 */
	String icon() default "";
	
	/**
	 * Documentation in markdown, added to action content. 
	 * @return
	 */
	String documentation() default "";

}
