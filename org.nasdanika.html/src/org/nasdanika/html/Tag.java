package org.nasdanika.html;

/**
 * A generic UI element. It doesn't add any functionality,
		only binds UIElement
 * and Container generic parameter to self for convenience.
 * 
 * @author Pavel
 *
 */
public interface Tag extends HTMLElement<Tag>, Container<Tag> {

	String getTagName();
	
	boolean is(TagName tagName);
	
}
