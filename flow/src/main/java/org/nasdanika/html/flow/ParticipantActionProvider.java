package org.nasdanika.html.flow;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.flow.ArtifactParticipantResponsibility;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.ParticipantResponsibility;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.ncore.util.NamedElementComparator;

public class ParticipantActionProvider extends ServiceProviderActionProvider<Participant> {
	
	public ParticipantActionProvider(Participant value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); 
		for (Participant element: getTarget().getChildren().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList())) {
			children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		
		return action;
	}	
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.PARTICIPANT__ARTIFACTS);
		properties.add(FlowPackage.Literals.PARTICIPANT__PARTICIPATES);
		properties.add(FlowPackage.Literals.PARTICIPANT__RESOURCES);
		properties.add(FlowPackage.Literals.PARTICIPANT__BASES);
		properties.add(FlowPackage.Literals.PARTICIPANT__SPECIALIZATIONS);
		return properties;
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		super.resolve(action, context, progressMonitor);
		
		// Responsibilities
		EList<Action> sections = action.getSections();
		
		Set<EClass> groupKeys = new HashSet<>(); // For sections		

		Map<EClass, List<ParticipantResponsibility<?>>> responsibleGroup = Util.groupBy(getTarget().getResponsible(), EObject::eClass);
		groupKeys.addAll(responsibleGroup.keySet());

		Map<EClass, List<ParticipantResponsibility<?>>> accountableGroup = Util.groupBy(getTarget().getAccountable(), EObject::eClass);
		groupKeys.addAll(accountableGroup.keySet());

		Map<EClass, List<ParticipantResponsibility<?>>> consultedGroup = Util.groupBy(getTarget().getConsulted(), EObject::eClass);
		groupKeys.addAll(consultedGroup.keySet());

		Map<EClass, List<ParticipantResponsibility<?>>> informedGroup = Util.groupBy(getTarget().getInformed(), EObject::eClass);
		groupKeys.addAll(informedGroup.keySet());
		
		if (!groupKeys.isEmpty()) {
			Action responsibilitiesAction = AppFactory.eINSTANCE.createAction(); 			
			responsibilitiesAction.setText("Responsible");
			responsibilitiesAction.setSectionStyle(SectionStyle.HEADER);
			sections.add(responsibilitiesAction);
			EList<Action> rSections = responsibilitiesAction.getSections();
			for (EClass groupKey: groupKeys.stream().sorted(EmfUtil.ENAMED_ELEMENT_COMPARATOR).collect(Collectors.toList())) {
				rSections.add(createResponsivilityGroupAction(
						groupKey,
						responsibleGroup.get(groupKey),
						accountableGroup.get(groupKey),
						consultedGroup.get(groupKey),
						informedGroup.get(groupKey),
						action,
						context, 
						progressMonitor));			
			}
		}
		
	}

	private Action createResponsivilityGroupAction(
			EClass typeKey, 
			List<ParticipantResponsibility<?>> responsible,
			List<ParticipantResponsibility<?>> accountable, 
			List<ParticipantResponsibility<?>> consulted,
			List<ParticipantResponsibility<?>> informed, 
			Action base,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {

		Action groupAction = AppFactory.eINSTANCE.createAction(); 			
		String groupName = typeKey.getName();
		if ("Activity".equals(groupName)) {
			groupAction.setText("Activities");
		} else {
			groupAction.setText(groupName + "s");
		}
		
		if (typeKey == FlowPackage.Literals.ARTIFACT_PARTICIPANT_RESPONSIBILITY) {
			groupAction.setText("Activity artifacts");
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
			Set<EObject> flowElements = new HashSet<>(); // For sections		

			Map<EObject, List<ParticipantResponsibility<?>>> responsibleGroup = responsible == null ? Collections.emptyMap() : Util.groupBy(responsible, EObject::eContainer);
			flowElements.addAll(responsibleGroup.keySet());

			Map<EObject, List<ParticipantResponsibility<?>>> accountableGroup = accountable == null ? Collections.emptyMap() : Util.groupBy(accountable, EObject::eContainer);
			flowElements.addAll(accountableGroup.keySet());

			Map<EObject, List<ParticipantResponsibility<?>>> consultedGroup = consulted == null ? Collections.emptyMap() : Util.groupBy(consulted, EObject::eContainer);
			flowElements.addAll(consultedGroup.keySet());

			Map<EObject, List<ParticipantResponsibility<?>>> informedGroup = informed == null ? Collections.emptyMap() : Util.groupBy(informed, EObject::eContainer);
			flowElements.addAll(informedGroup.keySet());

			TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
			responsibilitiesTable.setBody(body);
			EList<TableRow> bodyRows = body.getRows();
			
			for (FlowElement<?> container: flowElements.stream().map(e -> (FlowElement<?>) e).sorted(FlowActionProvider::compareFlowElements).collect(Collectors.toList())) {
				TableRow containerRow = BootstrapFactory.eINSTANCE.createTableRow();
				bodyRows.add(containerRow);

				TableCell containerCell = BootstrapFactory.eINSTANCE.createTableCell();
				containerRow.getCells().add(containerCell);
				containerCell.getContent().add(renderValue(base, null, container, context, progressMonitor));
				
				TableCell responsibleCell = BootstrapFactory.eINSTANCE.createTableCell();
				containerRow.getCells().add(responsibleCell);
				List<ParticipantResponsibility<?>> containerResponsible = responsibleGroup.get(container);
				if (containerResponsible != null) {
					buildResponsibilityCell(containerResponsible.stream().map(e -> ((ArtifactParticipantResponsibility) e).getArtifact()).sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList()), responsibleCell, base, context, progressMonitor);
				}
				
				TableCell accountableCell = BootstrapFactory.eINSTANCE.createTableCell();
				containerRow.getCells().add(accountableCell);
				List<ParticipantResponsibility<?>> containerAccountable = accountableGroup.get(container);
				if (containerAccountable != null) {
					buildResponsibilityCell(containerAccountable.stream().map(e -> ((ArtifactParticipantResponsibility) e).getArtifact()).sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList()), accountableCell, base, context, progressMonitor);
				}
				
				TableCell consultedCell = BootstrapFactory.eINSTANCE.createTableCell();
				containerRow.getCells().add(consultedCell);
				List<ParticipantResponsibility<?>> containerConsulted = consultedGroup.get(container);
				if (containerConsulted != null) {
					buildResponsibilityCell(containerConsulted.stream().map(e -> ((ArtifactParticipantResponsibility) e).getArtifact()).sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList()), consultedCell, base, context, progressMonitor);
				}
				
				TableCell informedCell = BootstrapFactory.eINSTANCE.createTableCell();
				containerRow.getCells().add(informedCell);
				List<ParticipantResponsibility<?>> containerInformed = informedGroup.get(container);
				if (containerInformed != null) {
					buildResponsibilityCell(containerInformed.stream().map(e -> ((ArtifactParticipantResponsibility) e).getArtifact()).sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList()), informedCell, base, context, progressMonitor);
				}
				
			}
			
			groupAction.getContent().add(responsibilitiesTable);
		} else {
			Table responsibilitiesTable = BootstrapFactory.eINSTANCE.createTable();
			TableHeader header = BootstrapFactory.eINSTANCE.createTableHeader();
			responsibilitiesTable.setHeader(header);
			TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
			header.getRows().add(headerRow);
			EList<TableCell> headerRowCells = headerRow.getCells();
			for (String title: new String[] {"Responsible", "Accountable", "Consulted", "Informed"}) {
				TableCell headerCell = BootstrapFactory.eINSTANCE.createTableCell();
				headerCell.setHeader(true);
				headerRowCells.add(headerCell);
				headerCell.getContent().add(createText(title));
			}
			
			TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
			responsibilitiesTable.setBody(body);
			EList<TableRow> bodyRows = body.getRows();
			
			TableRow responsibilitiesRow = BootstrapFactory.eINSTANCE.createTableRow();
			bodyRows.add(responsibilitiesRow);
			
			TableCell responsibleCell = BootstrapFactory.eINSTANCE.createTableCell();
			responsibilitiesRow.getCells().add(responsibleCell);
			buildResponsibilityCell(responsible, responsibleCell, base, context, progressMonitor);
			
			TableCell accountableCell = BootstrapFactory.eINSTANCE.createTableCell();
			responsibilitiesRow.getCells().add(accountableCell);
			buildResponsibilityCell(accountable, accountableCell, base, context, progressMonitor);
			
			TableCell consultedCell = BootstrapFactory.eINSTANCE.createTableCell();
			responsibilitiesRow.getCells().add(consultedCell);
			buildResponsibilityCell(consulted, consultedCell, base, context, progressMonitor);
			
			TableCell informedCell = BootstrapFactory.eINSTANCE.createTableCell();
			responsibilitiesRow.getCells().add(informedCell);
			buildResponsibilityCell(informed, informedCell, base, context, progressMonitor);
			
			groupAction.getContent().add(responsibilitiesTable);
		}
		
		return groupAction;
	}
	
	private void buildResponsibilityCell(
			List<ParticipantResponsibility<?>> elements,			
			TableCell cell,
			Action base,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		if (elements != null && !elements.isEmpty()) {
			cell.getContent().add(renderValue(base, null, elements, context, progressMonitor));
		}
			
	}

}
