package org.nasdanika.html.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Relationship;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.ArtifactComponentDiagramGenerator;
import org.nasdanika.html.emf.ColumnBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NamedElementComparator;

public class ArtifactActionBuilder extends ParticipantResponsibilityActionBuilder<Artifact> {
	
	public ArtifactActionBuilder(Artifact value, Context context) {
		super(value, context);
	}
		
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) {
		
		action = super.buildAction(action, registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); 
		for (Artifact element: getTarget().getChildren().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList())) {
			children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		
		return action;
	}	
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.ARTIFACT__INPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__OUTPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__REPOSITORIES);
		properties.add(FlowPackage.Literals.ARTIFACT__USED_BY);
		properties.add(FlowPackage.Literals.ARTIFACT__TEMPLATES);
		properties.add(FlowPackage.Literals.ARTIFACT__INSTANCES);
		return properties;
	}
		
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		
		super.resolve(action, context, progressMonitor);
		
		EList<Action> sections = action.getSections();
		Artifact semanticElement = getTarget();
		if (!semanticElement.getPayloadFor().isEmpty()) {
			sections.add(createEReferenceAction(action, FlowPackage.Literals.ARTIFACT__PAYLOAD_FOR, context, progressMonitor));
		}
		
		if (!semanticElement.getResponseFor().isEmpty()) {
			sections.add(createEReferenceAction(action, FlowPackage.Literals.ARTIFACT__RESPONSE_FOR, context, progressMonitor));
		}
		
		
		// Inbound
		EList<Relationship> inboundRelationships = semanticElement.getInboundRelationships();
		EMap<String, Relationship> outboundRelationships = semanticElement.getOutboundRelationships();
		if (!(inboundRelationships.isEmpty() && outboundRelationships.isEmpty())) {
			Action rAction = AppFactory.eINSTANCE.createAction();
			rAction.setSectionStyle(SectionStyle.HEADER);
			rAction.setText("Relationships");
			sections.add(rAction);
			EList<Action> rSections = rAction.getSections();
			if (!inboundRelationships.isEmpty()) {
				rSections.add(createInboundRelationshipsAction(action, context, progressMonitor));
			}
			
			// Outbound
			if (!outboundRelationships.isEmpty()) {
				rSections.add(createOutboundRelationshipsAction(action, context, progressMonitor));
			}
		}
		
		// Activity level responsibilities
		EList<ArtifactParticipantResponsibility> responsibilities = semanticElement.getResponsibilities();
		
		if (!responsibilities.isEmpty()) {
			sections.add(createActivityResponsibilitiesAction(responsibilities, action, context, progressMonitor));
		}
		
	}

	private Action createActivityResponsibilitiesAction(
			EList<ArtifactParticipantResponsibility> responsibilities,
			Action base,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {

		Action responsibilitiesAction = AppFactory.eINSTANCE.createAction(); 			
		responsibilitiesAction.setText("Activity responsibilities");
		
		Table responsibilitiesTable = BootstrapFactory.eINSTANCE.createTable();
		TableHeader header = BootstrapFactory.eINSTANCE.createTableHeader();
		responsibilitiesTable.setHeader(header);
		TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
		header.getRows().add(headerRow);
		EList<TableCell> headerRowCells = headerRow.getCells();
		for (String title: new String[] {"Activity", "Responsible", "Accountable", "Consulted", "Informed"}) {
			TableCell headerCell = BootstrapFactory.eINSTANCE.createTableCell();
			headerCell.setHeader(true);
			headerRowCells.add(headerCell);
			headerCell.getContent().add(createText(title));
		}
		
		// Group all by container. Sort containers For each container create a row		
		Map<EObject, List<ArtifactParticipantResponsibility>> flowElementsMap = Util.groupBy(responsibilities, EObject::eContainer);

		TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
		responsibilitiesTable.setBody(body);
		EList<TableRow> bodyRows = body.getRows();
		
		for (FlowElement<?> container: flowElementsMap.keySet().stream().map(e -> (FlowElement<?>) e).sorted(FlowActionBuilder::compareFlowElements).collect(Collectors.toList())) {
			TableRow containerRow = BootstrapFactory.eINSTANCE.createTableRow();
			bodyRows.add(containerRow);

			TableCell containerCell = BootstrapFactory.eINSTANCE.createTableCell();
			containerRow.getCells().add(containerCell);
			containerCell.getContent().add(renderValue(base, null, container, context, progressMonitor));
			
			TableCell responsibleCell = BootstrapFactory.eINSTANCE.createTableCell();
			containerRow.getCells().add(responsibleCell);
			List<ArtifactParticipantResponsibility> containerResponsibilities = flowElementsMap.get(container);
			
			buildResponsibilityCell(
					containerResponsibilities
						.stream()
						.map(ArtifactParticipantResponsibility::getResponsible)
						.flatMap(Collection::stream)
						.collect(Collectors.toSet()) // To remove duplicates
						.stream()
						.sorted(NamedElementComparator.INSTANCE)
						.collect(Collectors.toList()),
					responsibleCell,
					base, 
					context, 
					progressMonitor);
			
			TableCell accountableCell = BootstrapFactory.eINSTANCE.createTableCell();
			containerRow.getCells().add(accountableCell);
			buildResponsibilityCell(
					containerResponsibilities
						.stream()
						.map(ArtifactParticipantResponsibility::getAccountable)
						.flatMap(Collection::stream)
						.collect(Collectors.toSet()) // To remove duplicates
						.stream()
						.sorted(NamedElementComparator.INSTANCE)
						.collect(Collectors.toList()),
					accountableCell,
					base, 
					context, 
					progressMonitor);
			
			TableCell consultedCell = BootstrapFactory.eINSTANCE.createTableCell();
			containerRow.getCells().add(consultedCell);
			buildResponsibilityCell(
					containerResponsibilities
						.stream()
						.map(ArtifactParticipantResponsibility::getConsulted)
						.flatMap(Collection::stream)
						.collect(Collectors.toSet()) // To remove duplicates
						.stream()
						.sorted(NamedElementComparator.INSTANCE)
						.collect(Collectors.toList()),
					consultedCell,
					base, 
					context, 
					progressMonitor);
			
			TableCell informedCell = BootstrapFactory.eINSTANCE.createTableCell();
			containerRow.getCells().add(informedCell);
			buildResponsibilityCell(
					containerResponsibilities
						.stream()
						.map(ArtifactParticipantResponsibility::getInformed)
						.flatMap(Collection::stream)
						.collect(Collectors.toSet()) // To remove duplicates
						.stream()
						.sorted(NamedElementComparator.INSTANCE)
						.collect(Collectors.toList()),
					informedCell,
					base, 
					context, 
					progressMonitor);
		}
		
		responsibilitiesAction.getContent().add(responsibilitiesTable);
		
		return responsibilitiesAction;
	}
	
	private void buildResponsibilityCell(
			List<Participant> elements,			
			TableCell cell,
			Action base,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		
		if (elements != null && !elements.isEmpty()) {
			cell.getContent().add(renderValue(base, null, elements, context, progressMonitor));
		}
			
	}
	
	// TODO - Sort by source 
	private Action createEReferenceAction(
			Action action, 
			EReference eReference,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		Collection<ColumnBuilder<? super EObject>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					{
				header.getContent().add(createText("Source"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				cell.getContent().add(renderValue(base, typedElement, rowElement.eContainer().eContainer(), context, progressMonitor));				
			}
		});
		
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Key"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				EObject renderedValue = renderValue(base, typedElement, ((Map.Entry<?,?>) rowElement.eContainer()).getKey(), context, progressMonitor);
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
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Target"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getTarget(), context, progressMonitor);
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
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Name"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getName(), context, progressMonitor);
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
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Documentation"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				EObject renderedValue = renderValue(base, typedElement, ((Transition) rowElement).getDocumentation(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		return createTableAction(
				eReference,
				columnBuilders,
				action, 
				context, 
				progressMonitor);
	}
	
	@Override
	protected void populateRepresentation(
			Diagram representation, 
			Action action,
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor) {

		ArtifactComponentDiagramGenerator artifactComponentDiagramGenerator = new ArtifactComponentDiagramGenerator() {
		
			@Override
			protected String getArtifactLocation(Artifact semanticElement) {
				Action elementAction = context.getAction(semanticElement);
				if (elementAction == null) {
					return null;
				}
				
				URI uri = context.resolve(elementAction, action);
				return uri == null ? null : uri.toString();
			}
			
			@Override
			protected String getArtifactTooltip(Artifact semanticElement) {
				Action elementAction = context.getAction(semanticElement);
				return elementAction == null ? null : elementAction.getDescription();
			}

			@Override
			protected DiagramElement createDiagramElement(
					Artifact semanticElement,
					Map<Artifact, DiagramElement> semanticMap, 
					Artifact contextElement,
					int depth) {
				
				DiagramElement ret = super.createDiagramElement(semanticElement, semanticMap, contextElement, depth);
				String text = ret.getText();
				int initialLineLength = 25;
				if (text != null && text.length() > initialLineLength) {
					ret.setText(Util.wrap(text, initialLineLength, 2, "\\n"));
				}
				return ret;
			}
						
		};
		
		populateRepresentation(representation, artifactComponentDiagramGenerator);
	}
	
	protected void populateRepresentation(Diagram representation, ArtifactComponentDiagramGenerator artifactComponentDiagramGenerator) {
		artifactComponentDiagramGenerator.generateDiagram(getTarget(), representation);
	}

	private Action createInboundRelationshipsAction(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		Collection<ColumnBuilder<? super EObject>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Source"));
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				cell.getContent().add(renderValue(base, typedElement, rowElement.eContainer().eContainer(), context, progressMonitor));				
			}
		});
		
		columnBuilders.add(createColumnBuilder(NcorePackage.Literals.NAMED_ELEMENT__NAME));		
		columnBuilders.add(createColumnBuilder(FlowPackage.Literals.PACKAGE_ELEMENT__DOCUMENTATION));		
		
		Action ret = AppFactory.eINSTANCE.createAction();
		ret.setText("Inbound");
		
		Table table = buildTable(
				getTarget().getInboundRelationships(), 
				columnBuilders, 
				action, 
				FlowPackage.Literals.ARTIFACT__INBOUND_RELATIONSHIPS, 
				context, 
				progressMonitor);
		
		ret.getContent().add(table);
		return ret;		
	}
	
	private Action createOutboundRelationshipsAction(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		Collection<ColumnBuilder<? super Map.Entry<String, Relationship>>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<Map.Entry<String, Relationship>>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Key"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				EObject renderedValue = renderValue(base, typedElement, rowElement.getKey(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		columnBuilders.add(new ColumnBuilder<Map.Entry<String, Relationship>>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Target"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				Relationship relationship = rowElement.getValue();
				EObject renderedValue = renderValue(base, typedElement, relationship.getTarget(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<Map.Entry<String, Relationship>>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Name"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				Relationship value = rowElement.getValue();
				EObject renderedValue = renderValue(base, typedElement, value.getName(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});

		columnBuilders.add(new ColumnBuilder<Map.Entry<String, Relationship>>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				header.getContent().add(createText("Documentation"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor) {
				Relationship value = rowElement.getValue();
				EObject renderedValue = renderValue(base, typedElement, value.getDocumentation(), context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		});
		
		Action ret = AppFactory.eINSTANCE.createAction();
		ret.setText("Outbound");
		
		Table table = buildTable(
				getTarget().getOutboundRelationships(), 
				columnBuilders, 
				action, 
				FlowPackage.Literals.ARTIFACT__OUTBOUND_RELATIONSHIPS, 
				context, 
				progressMonitor);
		
		ret.getContent().add(table);
		return ret;		
	}
	
	
}
