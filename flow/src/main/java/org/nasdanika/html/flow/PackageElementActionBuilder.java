package org.nasdanika.html.flow;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.gen.Generator;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.html.emf.EObjectActionBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.ncore.util.NamedElementComparator;
import org.nasdanika.ncore.util.NcoreUtil;

public class PackageElementActionBuilder<T extends PackageElement<?>> extends EObjectActionBuilder<T> {
	
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500;
		
	public PackageElementActionBuilder(T value, Context context) {
		super(value, context);		
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Action ret = super.buildAction(action, registry, resolveConsumer, progressMonitor);		
		T eObj = getTarget();
		URI uri = NcoreUtil.getUri(eObj);
		String id = uri == null ? eObj.getUuid() : uri.toString();
		String digest = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(id.getBytes(StandardCharsets.UTF_8)));
		ret.setId(digest);
		
		String description = eObj.getDescription();
		addContent(ret, description);
		ret.setDescription(description);

		BiSupplier<EObject, String> cPath = NcoreUtil.containmentPath(eObj);
		if (cPath == null || Util.isBlank(cPath.getSecond())) {
			ret.setLocation("${base-uri}index.html");
		} else {
			ret.setLocation(cPath.getSecond() + "/index.html");
		}
		
		ret.setText(eObj.getName()); // Escape?
		ret.setSectionStyle(SectionStyle.HEADER);
		return ret;
	}

	@Override
	public String name() {
		return getTarget().getName();
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> propertiew = super.getProperties();
		propertiew.add(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENDS);
		propertiew.add(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENSIONS);
		propertiew.add(FlowPackage.Literals.PACKAGE_ELEMENT__MODIFIERS);
		return propertiew;
	}
	
	@Override
	protected Table createPropertiesTable(
			Action action,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		Table propertiesTable = super.createPropertiesTable(action, context, progressMonitor);
		propertiesTable.getAttributes().put("style", createText("width:auto"));
		return propertiesTable;
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {

		super.resolve(action, context, progressMonitor);
		T semanticElement = getTarget();

		// Representations
		for (Diagram representation: semanticElement.getRepresentations().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList())) {
			if (representation.getElements().isEmpty()) {
				populateRepresentation(representation, action, context, progressMonitor);
			}			
			Action representationAction;
			if (Util.isBlank(representation.getName())) {
				representationAction = action;
			} else {
				representationAction = AppFactory.eINSTANCE.createAction();
				representationAction.setText(representation.getName());
				action.getSections().add(representationAction); // TODO - support of navigation/navigation-modal - get from properties.
			}
			String rDescr = representation.getDescription();
			if (Util.isBlank(rDescr)) {
				addContent(representationAction, createGenerator().generate(representation));				
			} else {
				Table table = BootstrapFactory.eINSTANCE.createTable();
				representationAction.getContent().add(table);
				table.setBordered(true);
				TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
				table.setBody(body);
				table.getAttributes().put("style", createText("width:auto"));				
				
				TableRow diagramRow = BootstrapFactory.eINSTANCE.createTableRow();
				body.getRows().add(diagramRow);
				TableCell diagramCell = BootstrapFactory.eINSTANCE.createTableCell();
				diagramRow.getCells().add(diagramCell);				
				diagramCell.getContent().add(createText(createGenerator().generate(representation)));
				
				TableRow descriptionRow = BootstrapFactory.eINSTANCE.createTableRow();
				body.getRows().add(descriptionRow);
				TableCell descriptionCell = BootstrapFactory.eINSTANCE.createTableCell();
				descriptionRow.getCells().add(descriptionCell);				
				descriptionCell.getContent().add(createText(rDescr));								
			}
		}
		
		// Adding documentation here so it appears under the properties table
		action.getContent().addAll(EcoreUtil.copyAll(semanticElement.getDocumentation()));				
	}
	
	/**
	 * Creates a diagram {@link Generator}.
	 * @return
	 */
	protected Generator createGenerator() {
		return new Generator() {
			
			@Override
			protected DiagramGenerator getDiagramGenerator() {
				return context == null ? super.getDiagramGenerator() : context.get(DiagramGenerator.class, super.getDiagramGenerator()); 
			}
			
		};
	}
	
	/**
	 * Populates empty representations. An empty representation indicates that it has to be auto-populated. 
	 * Non-empty representations indicate that they were pre-populated, e.g. manually, and should not be auto-populated. 
	 * @param representation
	 */
	protected void populateRepresentation(
			Diagram representation, 
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
	}
	
}