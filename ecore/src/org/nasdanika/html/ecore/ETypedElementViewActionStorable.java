package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

public class ETypedElementViewActionStorable<T extends ETypedElement> extends ENamedElementViewActionStorable<T> {
	
	public ETypedElementViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);

		StringBuilder label = new StringBuilder(eObject.getName());
		EGenericType genericType = eObject.getEGenericType();
		if (genericType != null) {
			label.append(" : ");
			label.append(computeLabel(genericType, progressMonitor));
			if (eObject.isMany()) {
				label.append("*");
			}
		}
		put(data, "text", label.toString());
		
		addContent(data, propertiesTable(progressMonitor).toString()); 
		return data;
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
