package org.nasdanika.html.bootstrap;

import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;

public interface Card extends BootstrapElement<Tag, Card> {
	
	/**
	 * Card navs with content using the underlying card header for navs a and body for content. 
	 * @author Pavel Vlasov
	 *
	 */
	public interface Navs {
		
		/**
		 * Adds an item with content. 
		 * @param name
		 * @param active
		 * @param disabled
		 * @param contentId
		 * @param content
		 * @return
		 */
		Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content);
				
//		Dropdown dropdown(boolean active, Object... name); 	
				
		/**
		 * Styles navs as tabs.
		 * @param tabs
		 * @return
		 */
		Navs tabs(boolean tabs);
		
		/**
		 * @return tabs(true)
		 */
		default Navs tabs() {
			return tabs(true);
		}
			
		/**
		 * Styles navs as tabs.
		 * @param tabs
		 * @return
		 */
		Navs pills(boolean pills);
		
		/**
		 * @return tabs(true)
		 */
		default Navs pills() {
			return pills(true);
		}
	}
	
	/**
	 * @return Card navs backed by this card.
	 */
	Navs asNavs();
	
	/**
	 * @return Title tag for styling and adding content.
	 */
	TagBootstrapElement getHeader();
	
	/**
	 * @return Body tag for styling and adding content.
	 */
	TagBootstrapElement getBody();
	
	/**
	 * @return Footer tag for styling and adding content.
	 */
	TagBootstrapElement getFooter();

}
