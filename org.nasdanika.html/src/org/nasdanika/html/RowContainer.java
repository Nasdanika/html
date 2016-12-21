package org.nasdanika.html;

import java.util.List;

public interface RowContainer<T extends RowContainer<T>> extends Container<T>, UIElement<T> {
	
	interface Row extends UIElement<Row>, Container<Row> {
		
		interface Cell extends UIElement<Cell>, Container<Cell> {
			
			Cell colspan(int colspan);
			
			Cell rowspan(int rowspan);
			
		}				
		
		Cell cell(Object... content);
		
		Cell header(Object... content);
		
		Row style(Bootstrap.Style style);
		
		List<Cell> cells();
		
	}
		
	List<Row> rows();
	
	/**
	 * Creates a row
	 * @param cells Optional cells content.
	 * @return
	 */
	Row row(Object...cells);
	
	/**
	 * Creates a row
	 * @param headers Optional headers content.
	 * @return
	 */
	Row headerRow(Object...headers);			

}
