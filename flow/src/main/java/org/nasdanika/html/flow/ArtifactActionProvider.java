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
import org.nasdanika.diagram.gen.PlantumlGenerator;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Relationship;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.ArtifactComponentDiagramGenerator;
import org.nasdanika.html.emf.ColumnBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NamedElementComparator;

public class ArtifactActionProvider extends ParticipantResponsibilityActionProvider<Artifact> {
	
	public ArtifactActionProvider(Artifact value, Context context) {
		super(value, context);
	}
		
	@Override
	protected Action createAction(
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
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
		return properties;
	}
		
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		super.resolve(action, context, progressMonitor);
		
		addContent(action, createDiagram(action, context));
	
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
		
	}
	
	// TODO - Sort by source 
	private Action createEReferenceAction(
			Action action, 
			EReference eReference,
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
	
	protected String createDiagram(Action action, org.nasdanika.html.emf.EObjectActionResolver.Context context) throws Exception {
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
					Artifact contextElement) {
				
				DiagramElement ret = super.createDiagramElement(semanticElement, semanticMap, contextElement);
				String text = ret.getText();
				int initialLineLength = 25;
				if (text != null && text.length() > initialLineLength) {
					ret.setText(Util.wrap(text, initialLineLength, 2, "\\n"));
				}
				return ret;
			}
						
		};
		
		Diagram diagram = generateDiagram(artifactComponentDiagramGenerator);
		if (diagram == null) {
			return null; 
		}
		PlantumlGenerator generator = new PlantumlGenerator();
		String diagramHTML = generator.generateUmlDiagram(diagram);
		return diagramHTML;
	}

	protected Diagram generateDiagram(ArtifactComponentDiagramGenerator artifactComponentDiagramGenerator) {
		Artifact semanticElement = getTarget();
		if (semanticElement.eContainmentFeature() == FlowPackage.Literals.ARTIFACT_ENTRY__VALUE && semanticElement.eContainer().eContainmentFeature() == FlowPackage.Literals.ARTIFACT__CHILDREN) {
			return artifactComponentDiagramGenerator.generateContextDiagram(semanticElement);
		}
		return artifactComponentDiagramGenerator.generateDiagram(semanticElement.getChildren().values());
	}

	private Action createInboundRelationshipsAction(
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
			ProgressMonitor progressMonitor) throws Exception {
		Collection<ColumnBuilder<Map.Entry<String, Relationship>>> columnBuilders = new ArrayList<>();
		columnBuilders.add(new ColumnBuilder<Map.Entry<String, Relationship>>() {
			
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
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
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
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Target"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
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
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Name"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
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
					ProgressMonitor progressMonitor)
					throws Exception {
				header.getContent().add(createText("Documentation"));
			}
			
			@Override
			public void buildCell(
					Map.Entry<String, Relationship> rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement typedElement,
					org.nasdanika.html.emf.EObjectActionResolver.Context context, 
					ProgressMonitor progressMonitor)
					throws Exception {
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
