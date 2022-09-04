package org.nasdanika.html.model.app.drawio;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;

public class ConnectionProcessor extends ModelElementProcessor {
	
	@Override
	public Connection getElement() {
		return (Connection) super.getElement();
	}

	public ConnectionProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	@Override
	public String getText() {
		String text = (getElement()).getLabel();
		if (Util.isBlank(text) && hasDocumentation()) {
			Node source = getElement().getSource();
			String sourceText = source == null ? null : ((NodeProcessor) registry.get(source).getProcessor()).getText();
			
			Node target = getElement().getTarget();
			String targetText = target == null ? null : ((NodeProcessor) registry.get(target).getProcessor()).getText();
			
			if (Util.isBlank(sourceText) && Util.isBlank(targetText)) {
				return null;
			}
			
			StringBuilder textBuilder = new StringBuilder();
			if (!Util.isBlank(sourceText)) {
				textBuilder.append(sourceText).append(" ");
			}
			textBuilder.append("->");
			if (!Util.isBlank(targetText)) {
				textBuilder.append(" ").append(targetText);
			}
			return textBuilder.toString();
		}
		return text;
	}
	
	@Override
	public void build() {
		EObject se = getSemanticElement();
		if (se instanceof Action) {
			Node source = getElement().getSource();
			Node target = getElement().getTarget();
			if (source != null || target != null) {
				Action action = (Action) se;
				// Source and target
				Table table = BootstrapFactory.eINSTANCE.createTable();
				table.getAttributes().put("style", createText("width:auto"));
				
				if (source != null) {
					TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(sourceRow);
					
					TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
					sourceHeader.setHeader(true);
					sourceRow.getCells().add(sourceHeader);
					sourceHeader.getContent().add(createText("Source"));
										
					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
					sourceRow.getCells().add(sourceCell);
	
					NodeProcessor sourceProcessor = (NodeProcessor) registry.get(source).getProcessor();
					String sourceLabel = sourceProcessor.getText();
					if (Util.isBlank(sourceLabel)) {
						sourceLabel = "(unlabeled)";
					} 
					String sourceLink = getModelElementLink(source);
					if (sourceLink == null) {
						sourceCell.getContent().add(createText(sourceLabel));						
					} else {
						sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
					}
				}
				
				if (target != null) {
					TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(targetRow);
					
					TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
					targetHeader.setHeader(true);
					targetRow.getCells().add(targetHeader);
					targetHeader.getContent().add(createText("Target"));
										
					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
					targetRow.getCells().add(targetCell);
					
					NodeProcessor targetProcessor = (NodeProcessor) registry.get(target).getProcessor();
					String targetLabel = targetProcessor.getText();
					if (Util.isBlank(targetLabel)) {
						targetLabel = "(unlabeled)";
					} 
					String targetLink = getModelElementLink(target);
					if (targetLink == null) {
						targetCell.getContent().add(createText(targetLabel));						
					} else {
						targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
					}
				}
		
				action.getContent().add(table);
			}
		}
		super.build();
	}
	
	private String defaultConnectionTargetRoleName;

	public void setDefaultTargetRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, String defaultConnectionTargetRoleName, Predicate<Connection> traversed) {
		if (traversed.test(getElement())) {
			this.defaultConnectionTargetRoleName = defaultConnectionTargetRoleName;
			Node target = getElement().getTarget();
			if (target != null) {
				NodeProcessor targetProcessor = (NodeProcessor) registry.get(target).getProcessor();
				targetProcessor.setDefaultTargetConnectionRole(registry, defaultConnectionTargetRoleName, traversed);
			}
		}
	}
	
	public EReference getTargetRole() {
		String targetRoleProperty = resourceFactory.getTargetRoleProperty();
		if (!Util.isBlank(targetRoleProperty)) {
			String targetRolePropertyName = getElement().getProperty(targetRoleProperty);
			if (!Util.isBlank(targetRolePropertyName)) {
				return resourceFactory.resolveRole(targetRolePropertyName);
			}
		}
		return Util.isBlank(defaultConnectionTargetRoleName) ? null : resourceFactory.resolveRole(defaultConnectionTargetRoleName);
	}
	
	@Override
	public Map<ProcessorInfo<ElementProcessor>, EReference> setSemanticParentInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		Node target = getElement().getTarget();
		if (target == null) {
			return super.setSemanticParentInfo(semanticParentInfo);			
		}
		EReference targetRole = getTargetRole();
		if (targetRole == null) {
			return super.setSemanticParentInfo(semanticParentInfo);
		}
		Map<ProcessorInfo<ElementProcessor>, EReference> ret = new LinkedHashMap<>();
		((ModelElementProcessor) registry.get(target).getProcessor()).setSemanticParentInfo(semanticParentInfo).entrySet().forEach(e -> ret.put(e.getKey(), targetRole));
		return ret;
	}

}
