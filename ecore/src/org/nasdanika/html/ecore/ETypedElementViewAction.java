package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.localization.PropertyKeys;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;

public class ETypedElementViewAction<T extends ETypedElement> extends ENamedElementViewAction<T> {
	
	public ETypedElementViewAction(T value) {
		super(value);
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		Table propertiesTable = propertiesTable(viewGenerator);
		if (!propertiesTable.toHTMLElement().isEmpty()) {
			contentContainer.row().col(propertiesTable).padding().bottom(3);
		}
		String description = getDescription();
		if (!Util.isBlank(description)) {
			contentContainer.row().col(description);
		}		
		
		return contentContainer;
	}
	
	protected Table propertiesTable(ViewGenerator viewGenerator) {
		Table table = viewGenerator.get(BootstrapFactory.class).table();
		table.toHTMLElement().style().width("auto");
		
		EClassifier type = target.getEType();
		if (type != null) {
			ViewAction typeViewAction = EObjectAdaptable.adaptTo(type, ViewAction.class);
			Row typeRow = table.row();
			typeRow.header(getResourceContext().getString(PropertyKeys.UI_TYPE, "Type"));
			typeRow.cell(typeViewAction == null ?  type.getName() : viewGenerator.link(typeViewAction));
		}
		
		Row cardinalityRow = table.row();
		cardinalityRow.header(getResourceContext().getString(PropertyKeys.UI_CARDINALITY, "Cardinality"));
		cardinalityRow.cell(cardinality(target));
		
		return table;
	}
	
	@Override
	public String getText() {
		StringBuilder label = new StringBuilder();
		EGenericType genericType = target.getEGenericType();
		if (genericType != null) {
			label.append(computeLabel(genericType));
			if (target.isMany()) {
				label.append("*");
			}
			label.append(" ");
		}

		String name = super.getText();
		EObject container = target.eContainer();
		if (container instanceof EClassifier) {
			EClassifier classifier = (EClassifier) container;
			label.append(classifier.getName()); /// ???? Why ????
		} else {
			label.append(name);
		}
		return label.toString();
		
	}

}
