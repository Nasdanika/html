package org.nasdanika.html.model.app.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Util;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.util.DocLoadingDrawioFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * Factory for mapping drawio model to app model
 * @param <G>
 * @param <E>
 */
public abstract class AppDrawioFactory extends DocLoadingDrawioFactory<EObject> {
	
	public AppDrawioFactory(ResourceSet resourceSet) {
		super(resourceSet);
	}

	@Override
	protected Map<String, EPackage> getEPackages() {
		Map<String, EPackage> ret = new LinkedHashMap<>();
		ret.put("app",  AppPackage.eINSTANCE);
		ret.put("bootstraph",  BootstrapPackage.eINSTANCE);
		ret.put("html",  HtmlPackage.eINSTANCE);
		ret.put("exec",  ExecPackage.eINSTANCE);		
		ret.put("ncore", NcorePackage.eINSTANCE);
		return ret;
	}
	
	@Override
	protected void setSemanticId(EObject semanticElement, String semanticId, String elementId) {
		if (semanticElement instanceof Link) {
			Link link = (Link) semanticElement;
			if (Util.isBlank(semanticId)) {
				if (Util.isBlank(link.getLocation()) && !Util.isBlank(elementId)) {
					link.setLocation(elementId+".html");
				}
			} else if (Util.isBlank(link.getLocation())) {
				link.setLocation(semanticId + ".html");
			}
		} else {
			super.setSemanticId(semanticElement, semanticId, elementId);
		}
	}
	
	protected void addDocumentation(EObject semanticElement, EObject documentation) {
		if (semanticElement instanceof Action) {
			((Action) semanticElement).getContent().add(documentation);
		} else {
			super.addDocumentation(semanticElement, documentation);
		}
	}
	
	/**
	 * Sets label text extracted from the label HTML.
	 * @param semanticElement
	 * @param labelText
	 */
	protected void setLabelText(EObject semanticElement, String labelText) {
		if (semanticElement instanceof Label) {
			Label label = (Label) semanticElement;
			if (Util.isBlank(label.getText())) {
				label.setText(labelText);
			}
		} else {
			super.setLabelText(semanticElement, labelText);
		}
	}
	
	@Override
	protected void setPageName(EObject semanticElement, String pageName) {
		if (semanticElement instanceof Label) {
			Label label = (Label) semanticElement;
			if (Util.isBlank(label.getText())) {
				label.setText(pageName);
			}
		} else {
			super.setPageName(semanticElement, pageName);
		}
	}
	
	protected void setTooltip(EObject semanticElement, String tooltip) {
		if (semanticElement instanceof Label) {
			Label label = (Label) semanticElement;
			if (Util.isBlank(label.getTooltip())) {
				label.setTooltip(tooltip);
			}
		} else {
			super.setTooltip(semanticElement, tooltip);
		}
	}		
	
	@Override
	protected String getSemanticUUIDPropertyName() {
		return "action-uuid";
	}
		
}
