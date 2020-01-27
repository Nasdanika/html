package org.nasdanika.html.ecore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.localization.PropertyKeys;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.ecore.PlantUmlTextGenerator.RelationshipDirection;
import org.nasdanika.html.emf.ViewAction;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class EPackageViewAction extends ENamedElementViewAction<EPackage> {

	static final String PACKAGE_SUMMARY_SUFFIX = "/package-summary";
	private Action topLevelPackageParent;

	public EPackageViewAction(EPackage value, Action topLevelPackageParent) {
		super(value);
		this.topLevelPackageParent = topLevelPackageParent;
	}
	
	/**
	 * Encodes package NS URI as HEX.
	 */
	@Override
	public Object getId() {
		return Hex.encodeHexString(target.getNsURI().getBytes(StandardCharsets.UTF_8))+PACKAGE_SUMMARY_SUFFIX;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		contentContainer.row().col("<B>"+getResourceContext().getString(PropertyKeys.UI_NAMESPACE_URI, "NameNamespace URI")+":</B> "+target.getNsURI())
			.width(Breakpoint.DEFAULT, Size.NONE)
			.padding().bottom(Breakpoint.DEFAULT, Size.S3);
		String description = getDescription();
		if (!Util.isBlank(description) && description.length() < descriptionTabLengthThreshold) {
			contentContainer.row().col(description).width(Breakpoint.DEFAULT, Size.NONE);
		}
		
		Navs tabs = bootstrapFactory.navs().tabs();
		contentContainer.row().col(tabs).width(Breakpoint.DEFAULT, Size.NONE);
		
		if (!Util.isBlank(description) && description.length() >= descriptionTabLengthThreshold) {
			tabs.item(getResourceContext().getString(PropertyKeys.UI_DESCRIPTION, "Description"), description);
		}		

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ProgressMonitor imageMonitor = progressMonitor.split("Writing package diagram for "+target.getName(), 100, target)) {
			String diagramCMap = generateDiagram(false, null, 0, RelationshipDirection.both, true, true, baos);
			baos.close();
			String imagePath = getId()+".png";
			org.nasdanika.common.resources.Container<Object> resourceContainer = viewGenerator.get(org.nasdanika.common.resources.Container.class);
			resourceContainer.put(imagePath, baos.toByteArray(), imageMonitor);				
			HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
			Tag diagramImage = htmlFactory.tag(TagName.img).attribute("src", viewGenerator.get("image-path", "")+imagePath).attribute("usemap", "#plantuml_map");
			tabs.item(getResourceContext().getString(PropertyKeys.UI_DIAGRAM, "Diagram"), htmlFactory.fragment(diagramImage, diagramCMap));				
		} catch (IOException e) {
			tabs.item(getResourceContext().getString(PropertyKeys.UI_DIAGRAM, "Diagram"), bootstrapFactory.alert(Color.DANGER, "Error generating package diagram: "+e));
			e.printStackTrace();
		}
		
		Table table = bootstrapFactory.table().bordered();
		TableHeader header = table.header();
		header.headerRow(
				getResourceContext().getString(PropertyKeys.UI_NAME, "Name"), 
				getResourceContext().getString(PropertyKeys.UI_SUMMARY, "Summary"));
		TableBody body = table.body();
		getChildren().forEach(child -> body.row(viewGenerator.link(child), child.getTooltip()));
		tabs.item(getResourceContext().getString(PropertyKeys.UI_CONTENTS, "Contents"), table);				
		return contentContainer;
	}
		
	@Override
	public List<Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		
		for (EPackage subPackage: target.getESubpackages()) {			
			Action subPackageAction = adaptTo(subPackage, ViewAction.class);
			if (subPackageAction != null) {
				ret.add(filterChildAction(subPackageAction, Action.Role.NAVIGATION, null));
			}
		}
		
		for (EClassifier eClassifier: target.getEClassifiers()) {			
			Action eClassifierAction = adaptTo(eClassifier, ViewAction.class);
			if (eClassifierAction != null) {
				ret.add(filterChildAction(eClassifierAction, Action.Role.NAVIGATION, null));
			}
		}
		
		// Sub-packages before classifiers.
		Comparator<? super Action> comparator = (a,b) -> {
			int nameCmp = a.getText().compareTo(b.getText());
			if (a instanceof EPackageViewAction) {
				return b instanceof EPackageViewAction ? nameCmp : -1;
			} 
			
			if (b instanceof EPackageViewAction) {
				return a instanceof EPackageViewAction ? nameCmp : 1;
			}
			
			return nameCmp;			
		};
		return ret.stream().sorted(comparator).collect(Collectors.toList());
	}
	
	/**
	 * Generates PNG diagram.
	 * @return Image map for the diagram
	 * @throws IOException 
	 */
	protected String generateDiagram(
			boolean leftToRightDirection, 
			String width, 
			int depth, 
			PlantUmlTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			OutputStream out) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, eClassifierLinkResolver, eModelElementFirstDocSentenceProvider) {
			
			@Override
			protected Collection<EClass> getSubTypes(EClass eClass) {
				return EPackageViewAction.this.getSubTypes(eClass);
			}
			
			@Override
			protected Collection<EClass> getReferrers(EClass eClass) {
				return EPackageViewAction.this.getReferrers(eClass);
			}
			
			@Override
			protected boolean isAppendAttributes(EClass eClass) {
				return appendAttributes;
			}
			
			@Override
			protected boolean isAppendOperations(EClass eClass) {
				return appendOperations;				
			}
								
		};
		gen.appendStartUml();
		
		if (leftToRightDirection) {
			sb.append("left to right direction").append(System.lineSeparator());
		}
		
		if (width != null) {
			sb.append("scale ").append(width).append(" width").append(System.lineSeparator());
		}
										
		gen.appendWithRelationships(target.getEClassifiers(), relationshipDirection, depth);		
		
		gen.appendEndUml();

		SourceStringReader reader = new SourceStringReader(sb.toString());
		
		FileFormatOption fileFormatOption = new FileFormatOption(FileFormat.PNG);
		reader.outputImage(out, 0, fileFormatOption);
		
		return reader.getCMapData(0, fileFormatOption);
	}

	/**
	 * Override to return a list of sub-types of given EClass. 
	 * This implementation returns all sub-types found in the current package. 
	 * @param eClass
	 * @return
	 */
	protected Collection<EClass> getSubTypes(EClass eClass) {
		Collection<EClass> ret = new ArrayList<>();
		for (EClassifier ec: target.getEClassifiers()) {
			if (eClass != ec && ec instanceof EClass && eClass.isSuperTypeOf((EClass) ec)) {
				ret.add((EClass) ec);
			}
		}
		return ret;		
	}	
	
	@Override
	public Action getParent() {
		if (target.eContainer() instanceof EPackage) {
			return super.getParent();
		}
		return topLevelPackageParent;
	}
	
	@Override
	public boolean isInRole(String role) {
		return Action.Role.NAVIGATION.equals(role);
	}

}
