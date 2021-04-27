package org.nasdanika.html.emf;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

/**
 * @author Pavel
 * @since 2015.4.3
 */
public final class HtmlEmfUtil {
	
	private HtmlEmfUtil() {
		// Singleton
	}
	
	public static Action adaptToActionNonNull(EObject obj) {
		if (obj.eIsProxy()) {
			throw new ConfigurationException("Unresolved proxy " + obj);			
		}
		Action ret = EObjectAdaptable.adaptTo(obj, Action.class); 
		if (ret == null) {
			Marked marked = EObjectAdaptable.adaptTo(obj, Marked.class);
			throw new ConfigurationException("ViewAction adapter not found for " + obj, marked);
		}
		
		return ret;
	}

	public static List<Action> adaptToActionNonNull(Collection<? extends EObject> c) {
		return c.stream().map(HtmlEmfUtil::adaptToActionNonNull).collect(Collectors.toList());
	}

	public static List<Action> adaptToActionNonNullSorted(Collection<? extends EObject> c) {
		return c.stream().map(HtmlEmfUtil::adaptToActionNonNull).sorted((a,b) -> a.getText().compareTo(b.getText())).collect(Collectors.toList());
	}

	public static Object listOfActions(Collection<? extends EObject> elements, String header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToActionNonNull(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	public static Object listOfActionsSorted(Collection<? extends EObject> elements, String header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToActionNonNullSorted(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	public static <T extends EObject> Table table(
			Collection<T> elements,
			Function<T, ViewBuilder> rowBuilderProvider,
			BiFunction<T, EStructuralFeature, ViewBuilder> cellBuilderProvider, 
			ViewGenerator viewGenerator, 
			ProgressMonitor progressMonitor,
			EStructuralFeature... features) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Table ret = bootstrapFactory.table().bordered();
		Row header = ret.header().row().color(Color.INFO);
		for (EStructuralFeature feature: features) {
			header.header(Util.nameToLabel(feature.getName()));
		}
		
		for (T element: elements) {
			Row row = ret.body().row();
			for (EStructuralFeature feature: features) {
				Cell cell = row.cell();
				ViewBuilder cellBuilder = cellBuilderProvider == null ? null : cellBuilderProvider.apply(element, feature);
				if (cellBuilder == null) {
					Object value = element.eGet(feature);
					if (value != null) {
						if (value instanceof EObject) {
							ViewAction va = EObjectAdaptable.adaptTo((EObject) value, ViewAction.class);
							cell.toHTMLElement().content(va == null ? value : viewGenerator.link(va));
						} else {
							cell.toHTMLElement().content(value);							
						}
					}
				} else {
					cellBuilder.build(cell, viewGenerator, progressMonitor);
				}
			}
			
			if (rowBuilderProvider != null) {
				ViewBuilder rowBuilder = rowBuilderProvider.apply(element);
				if (rowBuilder != null) {
					rowBuilder.build(row, viewGenerator, progressMonitor);
				}
			}
		}
		return ret;
	}
	

}
