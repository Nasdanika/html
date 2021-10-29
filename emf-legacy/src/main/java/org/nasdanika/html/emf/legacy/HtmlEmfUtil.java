package org.nasdanika.html.emf.legacy;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.ncore.util.NcoreUtil;

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

	public static Object listOfActions(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToActionNonNull(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	public static Object listOfActionsSorted(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToActionNonNullSorted(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	public static <T extends EObject> Table table(
			Collection<T> elements,
			Function<ETypedElement, ViewBuilder> headerBuilderProvider, 
			Function<T, ViewBuilder> rowBuilderProvider,
			BiFunction<T, ETypedElement, ViewBuilder> cellBuilderProvider, 
			ViewGenerator viewGenerator, 
			ProgressMonitor progressMonitor,
			ETypedElement... dataSources) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Table ret = bootstrapFactory.table().bordered();
		Row header = ret.header().row().color(Color.INFO);
		for (ETypedElement dataSource: dataSources) {
			ViewBuilder headerBuilder = headerBuilderProvider == null ? null : headerBuilderProvider.apply(dataSource);
			if (headerBuilder == null) {
				String text = NcoreUtil.getNasdanikaAnnotationDetail(dataSource, EmfUtil.LABEL_KEY, Util.nameToLabel(dataSource.getName()));
				String icon = NcoreUtil.getNasdanikaAnnotationDetail(dataSource, EmfUtil.ICON_KEY);
				LabelImpl label = new LabelImpl();
				label.setText(text);
				label.setIcon(icon);
				header.header(viewGenerator.label(label));
			} else {
				headerBuilder.build(header.header(), viewGenerator, progressMonitor);
			}
		}
		
		buildTable(ret, elements, rowBuilderProvider, cellBuilderProvider, viewGenerator, progressMonitor, dataSources);
		return ret;
	}

	public static <T extends EObject> void buildTable(
			Table table, 
			Collection<T> elements,
			Function<T, ViewBuilder> rowBuilderProvider, 
			BiFunction<T, ETypedElement, ViewBuilder> cellBuilderProvider,
			ViewGenerator viewGenerator, 
			ProgressMonitor progressMonitor, 
			ETypedElement... dataSources) {
		
		for (T element: elements) {
			Row row = table.body().row();
			for (ETypedElement dataSource: dataSources) {
				Cell cell = row.cell();
				ViewBuilder cellBuilder = cellBuilderProvider == null ? null : cellBuilderProvider.apply(element, dataSource);
				if (cellBuilder == null) {
					Object value;
					if (dataSource instanceof EStructuralFeature) {
						value = element.eGet((EStructuralFeature) dataSource);
					} else if (dataSource == EcorePackage.Literals.EOBJECT___ECONTAINER) {
						// A hack to get to the container.
						value = element.eContainer();
					} else if (dataSource instanceof EOperation) {
						try {
							value = element.eInvoke((EOperation) dataSource, ECollections.emptyEList());
						} catch (InvocationTargetException e) {
							throw new ConfigurationException("Exception invoking " + dataSource, e, EObjectAdaptable.adaptTo(element, Marked.class));
						}
					} else {
						throw new IllegalArgumentException("Unsupported cell data source: " + dataSource);						
					}
					if (value != null) {
						if (value instanceof EObject) {
							ViewAction<?> va = EObjectAdaptable.adaptTo((EObject) value, ViewAction.class);
							cell.toHTMLElement().content(va == null ? value : viewGenerator.link(va));
						} else if (value instanceof Collection) {
							Collection<?> vc = (Collection<?>) value;
							if (vc.size() == 1) {
								Object elementValue = vc.iterator().next();
								if (elementValue instanceof EObject) {
									ViewAction<?> eva = EObjectAdaptable.adaptTo((EObject) elementValue, ViewAction.class);
									cell.toHTMLElement().content(eva == null ? elementValue : viewGenerator.link(eva));
								} else {
									cell.toHTMLElement().content(elementValue);																
								}
							} else if (!vc.isEmpty()) {			
								HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
								Tag ol = htmlFactory.tag(TagName.ol);
								cell.toHTMLElement().content(ol);
								for (Object elementValue: (Iterable<?>) value) {
									if (elementValue instanceof EObject) {
										ViewAction<?> eva = EObjectAdaptable.adaptTo((EObject) elementValue, ViewAction.class);
										ol.content(htmlFactory.tag(TagName.li, eva == null ? elementValue : viewGenerator.link(eva)));
									} else {
										ol.content(htmlFactory.tag(TagName.li, elementValue));
									}
								}
							}
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
	}

	public static Color getSeverityColor(int severity) {
		switch (severity) {
		case Diagnostic.OK:
			return Color.SUCCESS;
		case Diagnostic.CANCEL:
			return Color.SECONDARY;
		case Diagnostic.ERROR:
			return Color.DANGER;
		case Diagnostic.INFO:
			return Color.INFO;
		case Diagnostic.WARNING:
			return Color.WARNING;
		default:
			return Color.PRIMARY;
		}
	}
	

}
