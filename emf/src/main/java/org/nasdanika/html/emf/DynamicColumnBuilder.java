package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.emf.EObjectActionResolver.Context;
import org.nasdanika.html.model.app.Action;

/**
 * Interface for building table columns definitions to pass to a dynamic table.
 * @author Pavel
 *
 */
public interface DynamicColumnBuilder<T> {

	/**
	 * Builds a header cell.
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @return Map to be converted to JSON object to pass to the table columns attribute.
	 * @throws Exception
	 */
	public org.nasdanika.ncore.Map buildHeader(
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception;

	/**
	 * Builds a value cell and adds it to the row object (item)
	 * @param element 
	 * @param item
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @throws Exception
	 */
	public void buildCell(
			T element,
			org.nasdanika.ncore.Map item,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception;
	
}
