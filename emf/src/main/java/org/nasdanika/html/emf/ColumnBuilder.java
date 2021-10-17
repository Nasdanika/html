package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.emf.EObjectActionResolver.Context;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.bootstrap.TableCell;

/**
 * Interface for building table columns.
 * @author Pavel
 *
 */
public interface ColumnBuilder<T> {

	/**
	 * Builds a header cell.
	 * @param header
	 * @throws Exception
	 */
	public void buildHeader(
			TableCell header,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception;

	/**
	 * Builds a value cell
	 * @throws Exception
	 */
	public void buildCell(
			T rowElement, 
			TableCell cell,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception;
	
}
