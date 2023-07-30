package org.nasdanika.html.model.bootstrap.util;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;

public class BootstrapUtil {
	

	/**
	 * Builds a static table.
	 * @param <T>
	 * @param elements
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @param columnBuilders
	 * @return
	 */
	@SafeVarargs
	public static <T> Table buildTable(
			Collection<? extends T> elements, 
			boolean buildHeader,
			ProgressMonitor progressMonitor,			
			ColumnBuilder<? super T>... columnBuilders) {
		return buildTable(elements, buildHeader, Arrays.asList(columnBuilders), progressMonitor);
	}
	
	/**
	 * Builds a static table
	 * @param <T>
	 * @param elements
	 * @param columnBuilders
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	public static <T> Table buildTable(
			Collection<? extends T> elements, 
			boolean buildHeader,
			Collection<ColumnBuilder<? super T>> columnBuilders,
			ProgressMonitor progressMonitor) {
		Table ret = BootstrapFactory.eINSTANCE.createTable();
		if (buildHeader) {
			TableHeader header = BootstrapFactory.eINSTANCE.createTableHeader();
			ret.setHeader(header);
			TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
			header.getRows().add(headerRow);
			EList<TableCell> headerRowCells = headerRow.getCells();
			for (ColumnBuilder<? super T> cb: columnBuilders) {
				TableCell headerCell = BootstrapFactory.eINSTANCE.createTableCell();
				headerCell.setHeader(true);
				headerRowCells.add(headerCell);
				cb.buildHeader(headerCell, progressMonitor);
			}
		}
		
		TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
		ret.setBody(body);
		EList<TableRow> bodyRows = body.getRows();
		for (T element: elements) {
			TableRow elementRow = BootstrapFactory.eINSTANCE.createTableRow();
			bodyRows.add(elementRow);
			EList<TableCell> elementRowCells = elementRow.getCells();
			for (ColumnBuilder<? super T> cb: columnBuilders) {
				TableCell elementCell = BootstrapFactory.eINSTANCE.createTableCell();
				elementRowCells.add(elementCell);
				cb.buildCell(element, elementCell, progressMonitor);
			}
		}
		return ret;
	}
	
}
