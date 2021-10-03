package org.nasdanika.html.flow;

import java.util.List;

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
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);

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
		
		addContent(action, propertiesTable(progressMonitor).toString()); 
		return action;
	}

	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {		
		Table table = context.get(BootstrapFactory.class).table();
		table.toHTMLElement().style().width("auto");
		
		EGenericType genericType = eObject.getEGenericType(); 
		if (genericType != null) {
			genericType(genericType, addRow(table, "Type"), monitor);
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
