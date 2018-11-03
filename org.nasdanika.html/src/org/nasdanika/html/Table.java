package org.nasdanika.html;

public interface Table extends HTMLElement<Table>, RowContainer<Table> {
		
	/**
	 * Creates ``col`` tag in the ``colgroup``.
	 * @return
	 */
	Tag col();
	

	RowContainer<?> header();

	RowContainer<?> body();
	
	RowContainer<?> footer();
	
	
}
