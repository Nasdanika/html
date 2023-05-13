package org.nasdanika.html.ecore.gen.processors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EGenericType;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EReferenceConnection;
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
	private Map<Integer,WidgetFactory> eTypeArgumentWidgetFactories = new TreeMap<>();
	
	@OutgoingEndpoint("reference.name == 'eTypeArguments'")
	public final void setETypeArgumentEndpoint(EReferenceConnection connection, WidgetFactory eTypeArgumentWidgetFactory) {
		eTypeArgumentWidgetFactories.put(connection.getIndex(), eTypeArgumentWidgetFactory);
	}
	
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
			ret.addAll(genericTypeArguments(base, progressMonitor));
		} else {
			ret.add("?");
			if (eLowerBoundWidgetFactory != null) {
				ret.add(" super ");
				ret.add(eLowerBoundWidgetFactory.createLink(base, progressMonitor));
			} else if (eUpperBoundWidgetFactory != null) {
				ret.add(" extends ");
				ret.add(eUpperBoundWidgetFactory.createLink(base, progressMonitor));
			}
		}
		return ret;
	}
	
	protected List<Object> genericTypeArguments(URI base, ProgressMonitor progressMonitor) {
		List<Object> ret = new ArrayList<>();
		Iterator<WidgetFactory> it = eTypeArgumentWidgetFactories.values().iterator();
		if (it.hasNext()) {
			ret.add("<");
			while (it.hasNext()) {
				WidgetFactory typeArgumentWidgetFactory = it.next();
				ret.add(typeArgumentWidgetFactory.createLink(base, progressMonitor));
				if (it.hasNext()) {
					ret.add(",");
				}
			}
			ret.add(">");
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
//

}
