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
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.ecore.PlantUmlTextGenerator.RelationshipDirection;

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

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		contentContainer.row().col("<B>Namespace URI:</B> "+target.getNsURI()).padding().bottom(3);
		String description = getDescription();
		if (!Util.isBlank(description) && description.length() < descriptionTabLengthThreshold) {
			contentContainer.row().col(description);
		}
		
		Navs tabs = bootstrapFactory.tabs();
		contentContainer.row().col(tabs);
		
		if (!Util.isBlank(description) && description.length() >= descriptionTabLengthThreshold) {
			tabs.item("Description", description);
		}		

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			String diagramCMap = generateDiagram(false, null, 0, RelationshipDirection.both, true, true, baos);
			baos.close();
			String imagePath = viewGenerator.getResourceConsumer().apply(getId()+".png", baos.toByteArray());
			if (imagePath != null) {
				HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
				Tag diagramImage = htmlFactory.tag(TagName.img).attribute("src", imagePath).attribute("usemap", "#plantuml_map");
				tabs.item("Diagram", htmlFactory.fragment(diagramImage, diagramCMap));				
			}
		} catch (IOException e) {
			tabs.item("Diagram", bootstrapFactory.alert(Color.DANGER, "Error generating package diagram: "+e));
			e.printStackTrace();
		}
		
		Table table = bootstrapFactory.table().bordered();
		TableHeader header = table.header();
		header.headerRow("Name", "Summary");
		TableBody body = table.body();
		getChildren().forEach(child -> body.row(viewGenerator.link(child), child.getTooltip()));
		tabs.item("Contents", table);				
		return contentContainer;
	}
	
	@Override
	public List<Action> getChildren() {
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
		return super.getChildren().stream().sorted(comparator).collect(Collectors.toList());
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
