package org.nasdanika.html;

import org.nasdanika.html.Bootstrap.Glyphicon;

/**
 * Interface to configure [jsTree](https://www.jstree.com/) ``data-jstree`` attribute.
 * @author Pavel
 *
 */
public interface JsTree {
	
	default JsTree opened() {
		return opened(true);
	}
	
	JsTree opened(boolean opened);
	
	default JsTree selected() {
		return selected(true);
	}
	
	JsTree selected(boolean selected);
	
	default JsTree disabled() {
		return disabled(true);
	}
	
	JsTree disabled(boolean disabled);
	
	/**
	 * Sets icon - image URL or (glyph) icon class.
	 * @param icon
	 * @return
	 */
	JsTree icon(String icon);
	
	/**
	 * @return {@link FontAwesome} to set FontAwesome icon.
	 */
	FontAwesome<Tag> icon();
	
	/**
	 * Sets Bootstrap glyphicon.
	 * @param glyphicon
	 * @return
	 */
	default JsTree icon(Glyphicon glyphicon) {
		return icon("glyphicon glyphicon-"+glyphicon.code());		
	}

}
