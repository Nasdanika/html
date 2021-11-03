package org.nasdanika.html.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.FlowStateDiagramGenerator;
import org.nasdanika.html.emf.ColumnBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.ncore.NcorePackage;

public class FlowElementActionProvider<T extends FlowElement<?>> extends ParticipantResponsibilityActionProvider<T> {
	
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
		if (!getTarget().getInputs().isEmpty()) {
			sections.add(createInputsAction(action, context, progressMonitor));
		}
		
		// Outputs
		if (!getTarget().getOutputs().isEmpty()) {
			sections.add(createOutputsAction(action, context, progressMonitor));
		}
		
		// Invocations
		if (!getTarget().getInvocations().isEmpty()) {
			sections.add(createInvocationsAction(action, context, progressMonitor));
		}
		
		// Calls
		if (!getTarget().getCalls().isEmpty()) {
			sections.add(createCallsAction(action, context, progressMonitor));
		}
		
		// Artifact participant responsibilities
		if (!getTarget().getArtifactResponsibilities().isEmpty()) {
			sections.add(createArtifactResponsibilitiesAction(action, context, progressMonitor));			
		}
	}
	
	@Override
	protected void populateRepresentation(
			Diagram representation, 
			Action action,
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor)
			throws Exception {
		
		FlowStateDiagramGenerator flowStateDiagramGenerator = new FlowStateDiagramGenerator() {
		
			@Override
			protected String getFlowElementLocation(FlowElement<?> flowElement) {
				Action elementAction = context.getAction(flowElement);
				if (elementAction == null) {
					return null;
				}
				
				URI uri = context.resolve(elementAction, action);
				return uri == null ? null : uri.toString();
			}
			
			@Override
			protected String getFlowElementTooltip(FlowElement<?> flowElement) {
				Action elementAction = context.getAction(flowElement);
				return elementAction == null ? null : elementAction.getDescription();
			}
			
			@Override
			protected String getParticipantLocation(Participant participant) {
				Action elementAction = context.getAction(participant);
				if (elementAction == null) {
					return null;
				}
				
				URI uri = context.resolve(elementAction, action);
				return uri == null ? null : uri.toString();
			}
			
			@Override
			protected String getParticipantTooltip(Participant participant) {
				Action elementAction = context.getAction(participant);
				return elementAction == null ? null : elementAction.getDescription();
			}
			
			/**
			 * Wraps text
			 */
			@Override
			protected DiagramElement createDiagramElement(
					FlowElement<?> flowElement,
					Map<FlowElement<?>, DiagramElement> semanticMap, 
					FlowElement<?> contextElement,
					int depth) {
				
				DiagramElement ret = super.createDiagramElement(flowElement, semanticMap, contextElement, depth);
				String text = ret.getText();
				int initialLineLength = 25;
				if (text != null && text.length() > initialLineLength) {
					ret.setText(Util.wrap(text, initialLineLength, 2, "\\n"));
				}
				return ret;
			}
			
		};
		
		populateRepresentation(representation, flowStateDiagramGenerator);
	}

	protected void populateRepresentation(Diagram representation, FlowStateDiagramGenerator flowStateDiagramGenerator) {
		flowStateDiagramGenerator.generateDiagram(getTarget(), representation);
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
		
		columnBuilders.add(createColumnBuilder(NcorePackage.Literals.NAMED_ELEMENT__NAME));		
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.PACKAGE_ELEMENT__DOCUMENTATION));		
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
				header.getContent().add(createText("Documentation"));
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
				EObject renderedValue = renderValue(base, typedElement, value.getDocumentation(), context, progressMonitor);
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

	private Action createInvocationsAction(
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
		
		columnBuilders.add(createColumnBuilder(NcorePackage.Literals.NAMED_ELEMENT__NAME));		
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.PACKAGE_ELEMENT__DOCUMENTATION));		
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.TRANSITION__PAYLOAD));
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.CALL__RESPONSE));
		
		return createTableAction(
				FlowPackage.Literals.FLOW_ELEMENT__INVOCATIONS,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
	private Action createCallsAction(
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
				header.getContent().add(createText("Documentation"));
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
				EObject renderedValue = renderValue(base, typedElement, value.getDocumentation(), context, progressMonitor);
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
				header.getContent().add(createText("Request"));
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

		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Response"));
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
				Call value = (Call) ((Map.Entry<?,?>) rowElement).getValue();
				EObject renderedValue = renderValue(base, typedElement, value.getResponse(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);				
				}
			}
		});
		
		return createTableAction(
				FlowPackage.Literals.FLOW_ELEMENT__CALLS,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
	
	private Action createArtifactResponsibilitiesAction(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		return createTableAction(
				FlowPackage.Literals.FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES,
				action, 
				context, 
				progressMonitor,
				FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT,
				FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE,
				FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE,
				FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__CONSULTED,
				FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__INFORMED,
				NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION);
	}
	
}
