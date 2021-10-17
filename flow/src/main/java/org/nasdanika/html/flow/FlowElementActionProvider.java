package org.nasdanika.html.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Transition;
import org.nasdanika.html.emf.ColumnBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.ncore.NcorePackage;

public class FlowElementActionProvider<T extends FlowElement<?>> extends PackageElementActionProvider<T> {
	
	public FlowElementActionProvider(T value, Context context) {
		super(value, context);
	}
		
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__CALLS);
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__INPUT_ARTIFACTS);
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__INVOCATIONS);
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__OUTPUT_ARTIFACTS);
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__PARTICIPANTS);
		properties.add(FlowPackage.Literals.FLOW_ELEMENT__RESOURCES);
		return properties;
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		super.resolve(action, context, progressMonitor);
	
		// Inputs
		EList<Action> sections = action.getSections();
		sections.add(createInputsAction(action, context, progressMonitor));
		
		// Outputs
		sections.add(createOutputsAction(action, context, progressMonitor));
		
		// Invocations
		
		// Calls
	}

	private Action createInputsAction(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		Collection<ColumnBuilder<EObject>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Source"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				cell.getContent().add(renderValue(base, typedElement, rowElement.eContainer().eContainer(), context, progressMonitor));				
			}
		});
		
//		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.TRANSITION__TARGET));		
		columnBuilders.add(createColumnBuilder(NcorePackage.Literals.NAMED_ELEMENT__NAME));		
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.TRANSITION__PAYLOAD));
		
		return createTableAction(
				FlowPackage.Literals.FLOW_ELEMENT__INPUTS,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
	private Action createOutputsAction(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		Collection<ColumnBuilder<EObject>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Key"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				EObject renderedValue = renderValue(base, typedElement, ((Map.Entry<?,?>) rowElement).getKey(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Target"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				Transition transition = (Transition) ((Map.Entry<?,?>) rowElement).getValue();
				EObject renderedValue = renderValue(base, typedElement, transition.getTarget(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Name"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				Transition value = (Transition) ((Map.Entry<?,?>) rowElement).getValue();
				EObject renderedValue = renderValue(base, typedElement, value.getName(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Payload"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				Transition value = (Transition) ((Map.Entry<?,?>) rowElement).getValue();
				EObject renderedValue = renderValue(base, typedElement, value.getPayload(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);				
				}
			}
		});
		
		return createTableAction(
				FlowPackage.Literals.FLOW_ELEMENT__OUTPUTS,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
}
