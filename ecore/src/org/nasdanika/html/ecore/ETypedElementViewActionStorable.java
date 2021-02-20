package org.nasdanika.html.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class ETypedElementViewActionStorable<T extends ETypedElement> extends ENamedElementViewActionStorable<T> {
	
	public ETypedElementViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	public void configure(ProgressMonitor monitor) throws Exception {
//		super.configure(monitor);
//
//		StringBuilder label = new StringBuilder(eObject.getName());
//		EGenericType genericType = eObject.getEGenericType();
//		if (genericType != null) {
//			label.append(" : ");
//			label.append(computeLabel(genericType, monitor));
//			if (eObject.isMany()) {
//				label.append("*");
//			}
//		}
//		
//		action.getContent().add(propertiesTable(monitor)); 
//		action.setText(label.toString());
//	}
//
//	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {		
//		Table table = BootstrapFactory.eINSTANCE.createTable();
//		
//		Appearance tableAppearance = BootstrapFactory.eINSTANCE.createAppearance();
//		table.setAppearance(tableAppearance);
//		Property styleAttribute = NcoreFactory.eINSTANCE.createProperty();
//		styleAttribute.setName("style");
//		styleAttribute.setValue("width:auto");
//		tableAppearance.getAttributes().add(styleAttribute);
//		
//		EGenericType genericType = eObject.getEGenericType(); 
//		if (genericType != null) {
//			genericType(genericType, addRow(table, "Type"), monitor);
//		}
//		
//		addRow(table, "Cardinality").add(wrap(cardinality(eObject)));
//		
//		return table;
//	}
//	
//	/**
//	 * Adds a row to the table, returns cell content collection for adding content.
//	 * @param table
//	 * @param header
//	 * @return
//	 */
//	protected static List<EObject> addRow(Table table, String header) {
//		TableRow row = BootstrapFactory.eINSTANCE.createTableRow();
//		table.getRows().add(row);
//		
//		TableCell rowHeader = BootstrapFactory.eINSTANCE.createTableCell();
//		row.getCells().add(rowHeader);
//		rowHeader.setHeader(true);
//		rowHeader.getContent().add(wrap(header));
//		
//		TableCell rowCell = BootstrapFactory.eINSTANCE.createTableCell();
//		row.getCells().add(rowCell);
//		return rowCell.getContent();		
//	}

}
