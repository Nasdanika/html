package org.nasdanika.html.fontawesome;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Producer;

/**
 * Interface for creating Font Awesome (https://fontawesome.com/) styled UI elements.
 * @author Pavel Vlasov
 *
 */
public interface Icon<T extends HTMLElement<?>> extends Producer {
	
	enum Style {
		SOLID,
		REGULAR,
		BRANDS
	}

	// TODO - rework for 5.5
				
	T toHTMLElement();
	
	Icon<T> fixedWidth();
	
	/**
	 * Adds fa-li class for list items.
	 * @return
	 */
	Icon<T> li();
	
	/**
	 * Adds fa-ul for unordered lists.
	 * @return
	 */
	Icon<T> ul();

	Icon<T> pullLeft();
	
	Icon<T> pullRight();
	
	enum Size { large, x2, x3, x4, x5 }
	
	Icon<T> size(Size size);

	Icon<T> spin();
	
	enum Rotate { R90, R180, R270 }
	
	Icon<T> rotate(Rotate rotate);
	
	enum Flip { horizontal, vertical }
	
	Icon<T> flip(Flip flip);
	
	
	interface Stack {
		
		enum IconSize { x1, x2, x3, x4, x5 }
	
		Stack icon(HTMLElement<?> icon, IconSize size, boolean inverse);
		
		Stack icon(Icon<?> icon, IconSize size, boolean inverse);
		
		Stack size(Size size);
		
	}
	
	//FontAwesome<T> style(Bootstrap.Style style);

}