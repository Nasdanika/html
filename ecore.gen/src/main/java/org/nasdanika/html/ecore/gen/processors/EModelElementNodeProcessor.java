package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.DynamicTableBuilder;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;
import org.nasdanika.ncore.util.NcoreUtil;

public class EModelElementNodeProcessor<T extends EModelElement> extends EObjectNodeProcessor<T> {
	
	public EModelElementNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, 
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	@Override
	protected void configureLabel(EObject eObject, Label label, ProgressMonitor progressMonitor) {
		if (eObject instanceof EModelElement) {
			if (Util.isBlank(label.getIcon())) {
				String defaultIcon = "https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/" + eObject.eClass().getName() + ".gif";
				label.setIcon(NcoreUtil.getNasdanikaAnnotationDetail((EModelElement) eObject, "icon", defaultIcon));
			}
			if (Util.isBlank(label.getTooltip())) {
				label.setTooltip(NcoreUtil.getNasdanikaAnnotationDetail((EModelElement) eObject, "description", null));
			}			
		}
		super.configureLabel(eObject, label, progressMonitor);
	}
	
	@Override
	protected Action newAction(EObject eObject, ProgressMonitor progressMonitor) {
		Action newAction = super.newAction(eObject, progressMonitor);
		if (eObject instanceof EModelElement && Util.isBlank(newAction.getText())) {
			newAction.setText(NcoreUtil.getNasdanikaAnnotationDetail((EModelElement) eObject, "label", null));
		}		
		return newAction;
	}
	
	@Override
	protected Label createAction(ProgressMonitor progressMonitor) {
		Label action = super.createAction(progressMonitor);
		EModelElementDocumentation documentation = EmfUtil.getDocumentation((EModelElement) node.getTarget());
		if (documentation != null) {
			action.getContent().add(interpolatedMarkdown(context.interpolateToString(documentation.documentation()), documentation.location(), progressMonitor));			
		}
		return action;
	}
	
	/**
	 * @param markdown Markdown text
	 * @return Spec for interpolating markdown and then converting to HTML. 
	 */
	protected Markdown interpolatedMarkdown(String markdown, URI location, ProgressMonitor progressMonitor) {
		if (Util.isBlank(markdown)) {
			return null;
		}
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(markdown);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		// Creating a marker with EObject resource location for resource resolution in Markdown
		if (location != null) {
			org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
			ret.getMarkers().add(marker); 
		}
		
		return ret;
	}
	
	/**
	 * Suppressing default behavior, explicit specification of how to build.
	 */
	@Override
	protected void buildOutgoingReference(EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, ProgressMonitor progressMonitor) {
		// TODO EAnnotations
	}
	
	@Override
	protected boolean isCallOutgoingReferenceLabelsSuppliers(EReference eReference) {
		// TODO - EAnnotations...
		return false;
	}
	
	protected static String cardinality(ETypedElement typedElement) {
		int lowerBound = typedElement.getLowerBound();
		int upperBound = typedElement.getUpperBound();
		String cardinality;
		if (lowerBound == upperBound) {
			cardinality = String.valueOf(lowerBound);
		} else {
			cardinality = lowerBound + ".." + (upperBound == -1 ? "*" : String.valueOf(upperBound));
		}
		if (typedElement instanceof EReference && ((EReference) typedElement).isContainment()) {
			cardinality = "<B>"+cardinality+"</B>";
		}
		return cardinality;
	}
	
	// --- Reusable methods ---

	/**
	 * Creates a link to a typed element type. Defaults to typed element name.
	 * @param connection
	 * @param widgetFactory
	 * @param withIcon
	 * @param progressMonitor
	 * @return
	 */
	protected String typeLink(EReferenceConnection connection, WidgetFactory widgetFactory, ProgressMonitor progressMonitor) {		
		// TODO - Generic type
		EClassifier eType = ((ETypedElement) connection.getTarget().getTarget()).getEType();
		if (eType == null) {
			return null;
		}
		String typeName = eType.getName();
		String typeNameComment = "<!-- " + typeName + "--> ";
		String linkStr = widgetFactory.createWidgetString(EcorePackage.Literals.ETYPED_ELEMENT__ETYPE, progressMonitor); // TODO - Generic type		
		return typeNameComment + (Util.isBlank(linkStr) ? typeName : linkStr);
	}
	
	protected String nameLink(EReferenceConnection connection, WidgetFactory widgetFactory, ProgressMonitor progressMonitor) {
		boolean isInherited = false;
		EObject tt = connection.getTarget().getTarget();
		if (tt instanceof EStructuralFeature) {
			isInherited = ((EStructuralFeature) tt).getEContainingClass() != getTarget();
		} else if (tt instanceof EOperation) {
			isInherited = ((EOperation) tt).getEContainingClass() != getTarget();
		}
		String linkStr = widgetFactory.createLinkString(progressMonitor);
		String name = Util.isBlank(linkStr) ? ((ENamedElement) connection.getTarget().getTarget()).getName() : linkStr;
		return isInherited ? TagName.i.create(name).toString() : name;
	}
		
	protected String description(EReferenceConnection connection, WidgetFactory widgetFactory, ProgressMonitor progressMonitor) {
		Object label = widgetFactory.createLabel(progressMonitor);
		return label instanceof Label ? ((Label) label).getTooltip() : null;
	}	
	
	protected String declaringClassLink(EReferenceConnection connection, WidgetFactory widgetFactory, ProgressMonitor progressMonitor) {
		String declaringClassName;
		String linkStr;
		
		EObject tt = connection.getTarget().getTarget();
		if (tt instanceof EStructuralFeature) {
			declaringClassName = ((EStructuralFeature) connection.getTarget().getTarget()).getEContainingClass().getName();
			linkStr = widgetFactory.createWidgetString(EcorePackage.Literals.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS, progressMonitor);
		} else if (tt instanceof EOperation) {
			declaringClassName = ((EOperation) connection.getTarget().getTarget()).getEContainingClass().getName();
			linkStr = widgetFactory.createWidgetString(EcorePackage.Literals.EOPERATION__ECONTAINING_CLASS, progressMonitor);
		} else {
			throw new IllegalArgumentException("Should be EStructuralOperation or EOperation: " + tt);
		}
		
		String declaringClassNameComment = "<!-- " + declaringClassName + "--> ";
		return declaringClassNameComment + (Util.isBlank(linkStr) ? declaringClassName : linkStr);
	}

	/**
	 * Builds columns for {@link ENamedElement}
	 * @param tableBuilder
	 * @param progressMonitor
	 */
	protected void buildNamedElementColumns(DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> tableBuilder, ProgressMonitor progressMonitor) {
		tableBuilder
			.addStringColumnBuilder("name", true, false, "Name", endpoint -> nameLink(endpoint.getKey(), endpoint.getValue(), progressMonitor)) 
			.addStringColumnBuilder("description", true, false, "Description", endpoint -> description(endpoint.getKey(), endpoint.getValue(), progressMonitor));
	}
	
	/**
	 * Builds columns for {@link ETypedElement} including {@link ENamedElement} columns
	 * @param tableBuilder
	 * @param progressMonitor
	 */
	protected void buildTypedElementColumns(DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> tableBuilder, ProgressMonitor progressMonitor) {
		buildNamedElementColumns(tableBuilder, progressMonitor);
		tableBuilder
			.addStringColumnBuilder("type", true, true, "Type", endpoint -> typeLink(endpoint.getKey(), endpoint.getValue(), progressMonitor))  
			.addStringColumnBuilder("cardinality", true, true, "Cardinality", endpoint -> cardinality((ETypedElement) endpoint.getKey().getTarget().getTarget()));
	}
	
	/**
	 * Builds columns for {@link ETypedElement} including {@link ENamedElement} columns
	 * @param tableBuilder
	 * @param progressMonitor
	 */
	protected void buildStructuralFeatureColumns(DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> tableBuilder, ProgressMonitor progressMonitor) {
		buildTypedElementColumns(tableBuilder, progressMonitor);
		tableBuilder
			.addStringColumnBuilder("declaring-class", true, true, "Declaring Class", endpoint -> declaringClassLink(endpoint.getKey(), endpoint.getValue(), progressMonitor))
			.addBooleanColumnBuilder("changeable", true, true, "Changeable", endpoint -> ((EStructuralFeature) endpoint.getKey().getTarget().getTarget()).isChangeable())
			.addBooleanColumnBuilder("derived", true, true, "Derived", endpoint -> ((EStructuralFeature) endpoint.getKey().getTarget().getTarget()).isDerived());
		
		// TODO - unique, ...
	}
	
}
