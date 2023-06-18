package org.nasdanika.html.model.app.graph.emf;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Annotation for an icoming reference builder method. 
 * The method shall have 4 or 5 parameters compatible with parameters of <p/>
 * 
 *  <code>EObjectNodeProcessor.buildIncomingReference(
 * <br/>&nbsp;&nbsp;&nbsp;&nbsp;			{@link EReference} eReference,
 * <br/>&nbsp;&nbsp;&nbsp;&nbsp;			{@link List}&lt;{@link Entry}&lt;{{@link EReferenceConnection}, {@link WidgetFactory}&gt;&gt; referenceIncomingEndpoints,
 * <br/>&nbsp;&nbsp;&nbsp;&nbsp;			{@link Collection}&lt;{@link Label}&gt; labels,
 * <br/>&nbsp;&nbsp;&nbsp;&nbsp;			{@link Map}&lt;{@link EReferenceConnection}, {@link Collection}&lt;{@link Label}&gt;&gt incomingLabels,
 * <br/>&nbsp;&nbsp;&nbsp;&nbsp;			{@link ProgressMonitor} progressMonitor)</code>
 * <p/>			
 * In the case of 4 parameters it is the last 4 parameters because the reference is already bound by the annotation.			 
 * @author Pavel
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface IncomingReferenceBuilder {
	
	/**
	 * {@link EPackage} namespace URI obtained from constants, e.g. <code>{@link EcorePackage}.eNS_URI</code>
	 * @return
	 */
	String nsURI();
	
	/**
	 * Reference ID, obtained from generated {@link EPackage} constants, e.g. <code>{@link EcorePackage}.ECLASS</code>.
	 * @return
	 */
	int classID();

	/**
	 * Reference ID, obtained from generated {@link EPackage} constants, e.g. <code>{@link EcorePackage}.ECLASS__EALL_ATTRIBUTES</code>.
	 * @return
	 */
	int referenceID();
	
}

