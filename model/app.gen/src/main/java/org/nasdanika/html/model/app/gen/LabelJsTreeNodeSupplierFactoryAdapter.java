package org.nasdanika.html.model.app.gen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.Tag;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;

public class LabelJsTreeNodeSupplierFactoryAdapter<M extends Label> extends AdapterImpl implements SupplierFactory<JsTreeNode> {
	
	public LabelJsTreeNodeSupplierFactoryAdapter(M label) {
		setTarget(label);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public M getTarget() {
		return (M) super.getTarget();
	}

	@Override
	public Supplier<JsTreeNode> create(Context context) {
		Function<Map<EStructuralFeature, Object>, JsTreeNode> jsTreeNodeFunction = new Function<Map<EStructuralFeature, Object>, JsTreeNode>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "jsTree Node";
			}

			@Override
			public JsTreeNode execute(Map<EStructuralFeature, Object> features, ProgressMonitor progressMonitor) {
				JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
				JsTreeNode ret = jsTreeFactory.jsTreeNode();
				M semanticElement = getTarget();
				
				String icon = context.interpolateToString(semanticElement.getIcon());
				if (!Util.isBlank(icon)) {
					ret.icon(icon);
				}
				String text = context.interpolateToString(semanticElement.getText());
				if (!Util.isBlank(text)) {
					for (EObject semanticAncestor = semanticElement.eContainer(); semanticAncestor != null; semanticAncestor = semanticAncestor.eContainer()) {
						if (semanticAncestor instanceof NavigationPanel) {
							int labelTrimLength = ((NavigationPanel) semanticAncestor).getLabelTrimLength();
							if (labelTrimLength > 0 && text.length() > labelTrimLength) {
								ret.anchorAttribute("title", text);
								text = text.substring(0, labelTrimLength - 3) + "...";
							}
							break;
						}						
					}
					
					ret.text(text);
				}
				String tooltip = context.interpolateToString(semanticElement.getTooltip());
				if (!Util.isBlank(tooltip)) {
					ret.anchorAttribute("title", tooltip);
				}
				for (Entry<String, EObject> attr: semanticElement.getAttributes().entrySet()) {
					String attrName = attr.getKey();
					if (attrName.startsWith("data-nsd-")) { // Pass through Nasdanika attributes 
						EObject attrValue = attr.getValue();
						if (attrValue instanceof Text) { // Text only
							ret.attribute(attrName, ((Text) attrValue).getContent());							
						}
					}
				}
				ret.id(jsTreeFactory.getHTMLFactory().nextId()); // TODO - label ID 
				ret.setData(semanticElement);
				
				/**
				 * Storing feature data to use downstream.
				 */
				for (Entry<EStructuralFeature, Object> fe: features.entrySet()) {
					ret.setData(fe.getKey(), fe.getValue());
				}
				
				ret.selected(semanticElement.isActive());
				
				Tag help = (Tag) features.get(AppPackage.Literals.LABEL__HELP);
				if (help != null) {
					// TODO - Context menu item.
				}
				
				@SuppressWarnings("unchecked")
				List<JsTreeNode> children = (List<JsTreeNode>) features.get(AppPackage.Literals.LABEL__CHILDREN);
				if (children != null) {
					ret.children().addAll(children);
				}
				
				if (ret.children().isEmpty()) {
					ret.attribute("type", "leaf"); // Set node type to leaf if there are no children.
				}
								
				return ret;
			}
			
		};
		
		MapCompoundSupplierFactory<EStructuralFeature, Object> featuresFactory = new MapCompoundSupplierFactory<>("Features");
		
		M semanticElement = getTarget();
		Label help = semanticElement.getHelp();
		if (help != null) {
			featuresFactory.put(AppPackage.Literals.LABEL__HELP, EObjectAdaptable.adaptToSupplierFactoryNonNull(help, Tag.class));
		}
		
		List<EObject> children = semanticElement.getChildren();
		if (!children.isEmpty()) {
			featuresFactory.put(AppPackage.Literals.LABEL__CHILDREN, new ListCompoundSupplierFactory<>("Children", EObjectAdaptable.adaptToSupplierFactoryNonNull(children, JsTreeNode.class)));			
		}
		
		SupplierFactory<Tag> modalFactory = getModalFactory();
		if (modalFactory != null) {
			featuresFactory.put(AppPackage.Literals.LINK__MODAL, modalFactory);
		}
				
		return featuresFactory.create(context).then(jsTreeNodeFunction);
	}
	
	/**
	 * For links. Placing here to simplify code. This implementation returns empty supplier.
	 * @return
	 */
	protected SupplierFactory<Tag> getModalFactory() {
		return null;
	}
	
}
