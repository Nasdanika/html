package org.nasdanika.html.emf.legacy;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
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
	
	default String memberIcon(ETypedElement member) {
		return EmfUtil.getNasdanikaAnnotationDetail(member, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(member.getEType(), EmfUtil.ICON_KEY));
	}
	
	default LabelImpl memberLabel(ETypedElement member) {
		LabelImpl ret = new LabelImpl();
		ret.setText(memberLabelText(member));
		ret.setIcon(memberIcon(member));
		String memberDescription = memberDescription(member);
		ret.setDescription(memberDescription);
		if (!Util.isBlank(memberDescription)) {
			ret.setTooltip(Util.firstPlainTextSentence(memberDescription, 50, 250));
		}
		
		return ret;		
	}
	
	default Label memberCategory(ETypedElement member) {
		LabelImpl category = memberLabel(member);
		category.setId(getId() + "-" + member.eClass().getName() + "-category-" + member.getName());
		return category;		
	}

	default String memberLabelText(ETypedElement member) {
		return EmfUtil.getNasdanikaAnnotationDetail(member, EmfUtil.LABEL_KEY, Util.nameToLabel(member.getName()));
	}
	
	default String memberDescription(ETypedElement member) {		
		String classSegment = Util.camelToKebab(member instanceof EStructuralFeature ? ((EStructuralFeature) member).getEContainingClass().getName() : ((EOperation) member).getEContainingClass().getName()); // Just operation name, override to add support of signatures.
		String memberSegment = Util.camelToKebab(member.getName());
		URL descriptionResource = getClass().getResource(classSegment + (member instanceof EStructuralFeature ? "--" : "---") + memberSegment + ".md");
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
