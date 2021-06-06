package org.nasdanika.html.emf;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;

/**
 * Marker interface for "view" (as opposed to "edit") actions.
 * @author Pavel Vlasov
 *
 */
public interface ViewAction<T extends EObject> extends Action {

	static <T extends EObject> ViewAction<T> adaptToViewActionNonNull(T obj) {
		if (obj.eIsProxy()) {
			throw new ConfigurationException("Unresolved proxy " + obj);			
		}
		@SuppressWarnings("unchecked")
		ViewAction<T> ret = EObjectAdaptable.adaptTo(obj, ViewAction.class); 
		if (ret == null) {
			Marked marked = EObjectAdaptable.adaptTo(obj, Marked.class);
			throw new ConfigurationException("ViewAction adapter not found for " + obj, marked);
		}
		
		return ret;
	}
	
	default String featureIcon(EStructuralFeature feature) {
		return EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(feature.getEType(), EmfUtil.ICON_KEY));
	}
	
	default LabelImpl featureLabel(EStructuralFeature feature) {
		LabelImpl ret = new LabelImpl();
		ret.setText(featureLabelText(feature));
		ret.setIcon(featureIcon(feature));
		String featureDescription = featureDescription(feature);
		ret.setDescription(featureDescription);
		if (!Util.isBlank(featureDescription)) {
			ret.setTooltip(Util.firstPlainTextSentence(featureDescription, 50, 250));
		}
		
		return ret;		
	}
	
	default Label featureCategory(EStructuralFeature feature) {
		LabelImpl category =featureLabel(feature);
		category.setId(getId() + "-feature-category-" + feature.getName());
		return category;		
	}

	default String featureLabelText(EStructuralFeature feature) {
		return EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.LABEL_KEY, Util.nameToLabel(feature.getName()));
	}
	
	default String featureDescription(EStructuralFeature feature) {
		String classSegment = Util.camelToKebab(feature.getEContainingClass().getName());
		String featureSegment = Util.camelToKebab(feature.getName());
		URL descriptionResource = getClass().getResource(classSegment + "--" + featureSegment + ".md");
		if (descriptionResource == null) {
			return null;
		}
		try {
			String descriptionString = DefaultConverter.INSTANCE.toString(descriptionResource.openStream());
			return MarkdownHelper.INSTANCE.markdownToHtml(descriptionString);
		} catch (Exception e) {
			return "Exception rendering description: " + e;
		}
	}
	
	
	/**
	 * @return The underlying {@link EObject}.
	 */
	T getSemanticElement();

	static List<Action> adaptToViewActionsNonNull(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).collect(Collectors.toList());
	}

	static List<Action> adaptToViewActionsNonNullSorted(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).sorted((a,b) -> a.getText().compareTo(b.getText())).collect(Collectors.toList());
	}

	static Object listOfViewActions(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToViewActionsNonNull(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	static Object listOfViewActionsSorted(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToViewActionsNonNullSorted(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}
	
}
