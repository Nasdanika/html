package org.nasdanika.html.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.model.app.Action;

public class ETypedElementActionSupplier<T extends ETypedElement> extends ENamedElementActionSupplier<T> {
	
	public ETypedElementActionSupplier(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);

		StringBuilder label = new StringBuilder(eObject.getName());
		EGenericType genericType = eObject.getEGenericType();
		if (genericType != null) {
			label.append(" : ");
			label.append(computeLabel(genericType, progressMonitor));
			if (eObject.isMany()) {
				label.append("*");
			}
		}
		action.setText(label.toString());
		
		addContent(action, propertiesTable(contextEClass, progressMonitor).toString()); 
		return action;
	}

	protected Table propertiesTable(EClass contextEClass, ProgressMonitor monitor) {		
		Table table = context.get(BootstrapFactory.class).table();
		table.toHTMLElement().style().width("auto");
		
		EGenericType genericType = eObject.getEGenericType(); 
		if (genericType != null) {
			genericType(genericType, contextEClass, addRow(table, "Type")::add, monitor);
		}
		
		addRow(table, "Cardinality").add(cardinality(eObject));
		
		return table;
	}
	
	/**
	 * Adds a row to the table, returns cell content collection for adding content.
	 * @param table
	 * @param header
	 * @return
	 */
	protected static List<Object> addRow(Table table, String header) {
		Row row = table.row();
		row.header(header);
		Cell contentCell = row.cell();
		return contentCell.toHTMLElement().getContent();
	}

}
