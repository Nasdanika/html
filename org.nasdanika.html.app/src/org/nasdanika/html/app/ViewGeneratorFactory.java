package org.nasdanika.html.app;

import java.util.function.Consumer;

/**
 * Creates view generators with specified content consumers.
 * @author Pavel Vlasov
 *
 */
public interface ViewGeneratorFactory {
	
	/**
	 * Creates a new generator.
	 * @param contentConsumer Optional consumer for defining UI elements. E.g. a button opening a modal dialog
	 * needs to define that dialog elsewhere on the page. ViewGenerator does it by adding a modal dialog to the content consumer 
	 * if it is not null. If consumer is null then the generator may adjust its generation logic or fail.  
	 * @return
	 */
	ViewGenerator createViewGenerator(Consumer<?> contentConsumer);

}
