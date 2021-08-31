package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;

/**
 * Bootstrap modal dialog - https://getbootstrap.com/docs/4.5/components/modal/
 * @author Pavel
 *
 */
public interface Modal extends BootstrapElement<Tag, Modal> {
	
	/**
	 * @return Content tag, containing header, body, and footer, for styling.
	 */
	TagBootstrapElement getContent();
	
	/**
	 * @return Header tag for styling and adding content.
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
	
	/**
	 * Adds data-toggle="modal" data-target="#&lt;modal id&gt;" attributes to the trigger. Generates model id if it is not set.
	 * @param trigger
	 */
	void bindTrigger(HTMLElement<?> trigger);
	
	/**
	 * @return Javascript code to show this modal.
	 */
	String activatorScript();

	/**
	 * Adds data-dismiss="modal" attributes to the dismisser. 
	 * @param trigger
	 */
	void bindDismisser(HTMLElement<?> dismisser);
	
	Modal scrollable(boolean scrollable);
	
	default Modal scrollable() {
		return scrollable(true);
	}
	
	Modal centered(boolean centered);
	
	default Modal centered() {
		return centered(true);
	}
	
	/**
	 * Modal size - small, large, or extra large.
	 * @param size
	 * @return
	 */
	Modal size(Breakpoint size);

}
