package org.nasdanika.html.ecore.gen.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypeParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

public class EGenericTypeNodeProcessor extends EObjectNodeProcessor<EGenericType> {

	public EGenericTypeNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}
	
	//	getEClassifier()
	private WidgetFactory eClassifierWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eClassifier'")
	public final void setEClassifierEndpoint(WidgetFactory eClassifierWidgetFactory) {
		this.eClassifierWidgetFactory = eClassifierWidgetFactory;
	}
	
	//	getETypeParameter()
	private WidgetFactory eTypeParameterWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eTypeParameter'")
	public final void setETypeParameterEndpoint(WidgetFactory eTypeParameterWidgetFactory) {
		this.eTypeParameterWidgetFactory = eTypeParameterWidgetFactory;
	}
	
	// getELowerBound()
	private WidgetFactory eLowerBoundWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eLowerBound'")
	public final void setELowerBoundEndpoint(WidgetFactory eLowerBoundWidgetFactory) {
		this.eLowerBoundWidgetFactory = eLowerBoundWidgetFactory;
	}
	
	// getETypeArguments()
	// TODO - two arguments, insert at position or tree map
//	private List<WidgetFactory> eUpperBoundWidgetFactory;
//	
//	@OutgoingEndpoint("reference.name == 'eUpperBound'")
//	public final void setEUpperBoundEndpoint(WidgetFactory eUpperBoundWidgetFactory) {
//		this.eUpperBoundWidgetFactory = eUpperBoundWidgetFactory;
//	}
	
	// getEUpperBound()
	private WidgetFactory eUpperBoundWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eUpperBound'")
	public final void setEUpperBoundEndpoint(WidgetFactory eUpperBoundWidgetFactory) {
		this.eUpperBoundWidgetFactory = eUpperBoundWidgetFactory;
	}

	@Override
	public Object createLink(URI base, ProgressMonitor progressMonitor) {
		List<Object> ret = new ArrayList<>();
				
		if (eTypeParameterWidgetFactory != null) {
			ret.add(eTypeParameterWidgetFactory.createLink(base, progressMonitor));
		} else if (eClassifierWidgetFactory != null) {
			ret.add(eClassifierWidgetFactory.createLink(base, progressMonitor));
			// TODO - genericTypeArguments(eGenericType, contextClassifier, accumulator, monitor);
		} else {
			ret.add("?");
			if (eLowerBoundWidgetFactory != null) {
				ret.add(" super ");
				// TODO - genericType(eGenericType.getELowerBound(), contextClassifier, accumulator, monitor);
			} else if (eUpperBoundWidgetFactory != null) {
				ret.add(" extends ");
				// TODO - genericType(eGenericType.getEUpperBound(), contextClassifier, accumulator, monitor);
			}
		}
		return ret;
	}
	
//	/**
//	 * @param eClassifier
//	 * @return Type parameters string.
//	 */
//	protected String typeParameters(EClassifier eClassifier) {
//		if (eClassifier.getETypeParameters().isEmpty()) {
//			return "";
//		}
//		StringBuilder typeParameters = new StringBuilder();
//		for (ETypeParameter typeParameter: eClassifier.getETypeParameters()) {
//			if (typeParameters.length() > 0) {
//				typeParameters.append(",");
//			}
//			typeParameters.append(genericName(typeParameter));
//		}		
//		
//		return "&lt;" + typeParameters +"&gt;";
//	}	
//	
//	protected String genericName(ETypeParameter typeParameter) {
//		StringBuilder ret = new StringBuilder(labelProvider.apply(typeParameter, typeParameter.getName()));
//		for (EGenericType bound : typeParameter.getEBounds()) {
//			if (bound.getEUpperBound() != null) {
//				ret.append(" extends ").append(genericName(bound.getEUpperBound()));
//			}
//			if (bound.getELowerBound() != null) {
//				ret.append(" super ").append(genericName(bound.getELowerBound()));
//			}
//		}
//		
//		return ret.toString();
//	}
//	
//	protected String genericName(EGenericType eGenericType) {
//		StringBuilder ret = new StringBuilder();
//		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
//		if (eTypeParameter != null) {			
//			ret.append(labelProvider.apply(eTypeParameter, eTypeParameter.getName()));
//		} else {
//			EClassifier eClassifier = eGenericType.getEClassifier();
//			if (eClassifier != null) {
//				ret.append(labelProvider.apply(eClassifier, eClassifier.getName()));			
//			}
//		}
//		ret.append(genericTypeArguments(eGenericType));
//		return ret.toString();
//	}
//
//	protected String genericTypeArguments(EGenericType eGenericType) {
//		StringBuilder ret = new StringBuilder();
//		Iterator<EGenericType> it = eGenericType.getETypeArguments().iterator();
//		if (it.hasNext()) {
//			ret.append("<");
//			while (it.hasNext()) {
//				ret.append(genericName(it.next()));
//				if (it.hasNext()) {
//					ret.append(",");
//				}
//			}
//			ret.append(">");
//		}
//		return ret.toString();
//	}
//

}
