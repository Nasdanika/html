package org.nasdanika.html;

public interface Table extends RowContainer<Table> {
	
	Table caption(Object... content);
	
	interface TableHeader extends RowContainer<TableHeader> {
		
	}
	
	interface TableFooter extends RowContainer<TableFooter> {
		
	}
	
	interface TableBody extends RowContainer<TableBody> {
		
	}
		
	/**
	 * Creates ``col`` tag in the ``colgroup``.
	 * @return
	 */
	Tag col();
	

	TableHeader header();

	TableBody body();
	
	TableFooter footer();
	
	
}
