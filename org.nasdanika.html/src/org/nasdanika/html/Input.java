package org.nasdanika.html;

import org.nasdanika.html.HTMLFactory.InputType;

/**
 * A generic UI element. It doesn't add any functionality, only binds UIElement and Container generic parameter to self for convenience.
 * @author Pavel
 *
 */
public interface Input extends InputBase<Input>, Container<Input> {
	
	Input autocomplete(boolean autocomplete);
	Input autocomplete();
	
	Input formaction(Object formaction);
	
	Input formenctype(Form.EncType formEncType);
	
	Input formmethod(Form.Method formMethod);
	
	Input formnovalidate(boolean formnovalidate);
	Input formnovalidate();
	
	Input formtarget(Object formTarget);
	
	Input dimensions(int width, int height);
	
	Input list(Object dataListId);
	
	Input min(Object min);
	Input max(Object max);
	
	Input multiple(boolean multiple);
	Input multiple();
	
	Input pattern(Object pattern);
	
	Input value(Object value);
	
	Input placeholder(Object placeholder);
	
	Input step(Object step);	
	
	InputType getType();	

}
