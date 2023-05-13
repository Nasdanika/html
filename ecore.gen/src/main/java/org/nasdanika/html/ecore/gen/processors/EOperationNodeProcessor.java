package org.nasdanika.html.ecore.gen.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class EOperationNodeProcessor extends ETypedElementNodeProcessor<EOperation> {

	public EOperationNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	/**
	 * Creating a link only if the action has content 
	 */
	@Override
	public Object createLink(URI base, ProgressMonitor progressMonitor) {
		Label action = createAction(progressMonitor);
		if (action instanceof Action && !((Action) action).getContent().isEmpty()) {
			return super.createLink(base, progressMonitor);
		}
		return createLabel(progressMonitor);
	}
	
	private WidgetFactory declaringClassWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eContainingClass'")
	public final void setDeclaringClassEndpoint(WidgetFactory declaringClassWidgetFactory) {
		this.declaringClassWidgetFactory = declaringClassWidgetFactory;
	}
	
	private Map<Integer,WidgetFactory> eParametersWidgetFactories = new TreeMap<>();
	
	@OutgoingEndpoint("reference.name == 'eParameters'")
	public final void setEParameterEndpoint(EReferenceConnection connection, WidgetFactory eParameterWidgetFactory) {
		eParametersWidgetFactories.put(connection.getIndex(), eParameterWidgetFactory);
	}	
	
	private Map<Integer,WidgetFactory> eTypeParametersWidgetFactories = new TreeMap<>();
	
	@OutgoingEndpoint("reference.name == 'eTypeParameters'")
	public final void setETypeParameterEndpoint(EReferenceConnection connection, WidgetFactory eTypeParameterWidgetFactory) {
		eParametersWidgetFactories.put(connection.getIndex(), eTypeParameterWidgetFactory);
	}	
	
	private Map<Integer,WidgetFactory> eGenericExceptionsWidgetFactories = new TreeMap<>();
	
	@OutgoingEndpoint("reference.name == 'eGenericExceptions'")
	public final void setEGenericExceptionsEndpoint(EReferenceConnection connection, WidgetFactory eGenericExceptionWidgetFactory) {
		eParametersWidgetFactories.put(connection.getIndex(), eGenericExceptionWidgetFactory);
	}	
	
	
	@Override
	public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.EOPERATION__ECONTAINING_CLASS && declaringClassWidgetFactory != null) {
			return declaringClassWidgetFactory.createLink(base, progressMonitor);
		}
		if (selector == EcorePackage.Literals.EOPERATION__EPARAMETERS) {
			if (eParametersWidgetFactories.isEmpty()) {
				return null;
			}
			
			if (eParametersWidgetFactories.size() == 1) {
				List<Object> ret = new ArrayList<>();
				WidgetFactory pwf = eParametersWidgetFactories.get(0);
				ret.add(pwf.createLink(base, progressMonitor));
				ret.add(" : ");
				ret.add(pwf.createWidget(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, base, progressMonitor));
				return ret;
			}
			
			List<Object> ret = new ArrayList<>();
			ret.add("<ol>");
			for (WidgetFactory pwf: eParametersWidgetFactories.values()) {
				ret.add("<li>");
				ret.add(pwf.createLink(base, progressMonitor));
				ret.add(" : ");
				ret.add(pwf.createWidget(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE, base, progressMonitor));
				ret.add("</li>");
			}						
			ret.add("</ol>");
			
			return ret;			
		}		
		if (selector == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS) {
			if (eGenericExceptionsWidgetFactories.isEmpty()) {
				return null;
			}
			
			if (eGenericExceptionsWidgetFactories.size() == 1) {
				return eGenericExceptionsWidgetFactories.get(0).createLink(base, progressMonitor);
			}
			
			List<Object> ret = new ArrayList<>();
			ret.add("<ol>");
			for (WidgetFactory gewf: eGenericExceptionsWidgetFactories.values()) {
				ret.add("<li>");
				ret.add(gewf.createLink(base, progressMonitor));
				ret.add("</li>");
			}						
			ret.add("</ol>");
			
			return ret;			
		}
		if (selector == EcorePackage.Literals.EOPERATION__ETYPE_PARAMETERS) {
			if (eTypeParametersWidgetFactories.isEmpty()) {
				return null;
			}
			
			if (eTypeParametersWidgetFactories.size() == 1) {
				return eTypeParametersWidgetFactories.get(0).createLink(base, progressMonitor);
			}
			
			List<Object> ret = new ArrayList<>();
			ret.add("<ol>");
			for (WidgetFactory tpwf: eTypeParametersWidgetFactories.values()) {
				ret.add("<li>");
				ret.add(tpwf.createLink(base, progressMonitor));
				ret.add("</li>");
			}						
			ret.add("</ol>");
			
			return ret;			
		}
		return super.createWidget(selector, base, progressMonitor);
	}	

}
