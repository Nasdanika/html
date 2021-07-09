package org.nasdanika.html.bootstrap;

import java.util.List;

public interface RowContainer<T extends org.nasdanika.html.RowContainer<T>, B extends BootstrapElement<T,B>> extends BootstrapElement<T,B> {
	
	interface Row extends BootstrapElement<org.nasdanika.html.RowContainer.Row,Row> {
		
		Row color(Color color);
		
		Row backgroundColor(Color color);		
		
		
		interface Cell extends BootstrapElement<org.nasdanika.html.RowContainer.Row.Cell,Cell> {
			
			Cell color(Color color);			

			Cell backgroundColor(Color color);		
			
		}				
		
		Cell cell(Object... content);
		
		Cell header(Object... content);
		
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
