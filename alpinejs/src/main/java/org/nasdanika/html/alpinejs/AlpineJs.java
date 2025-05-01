package org.nasdanika.html.alpinejs;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.HTMLElement;

/**
 * Knockout.js interface.
 * @author Pavel Vlasov
 *
 */
public interface AlpineJs<H extends HTMLElement<?>> {
	
	AlpineJs<H> data(Object data);
	JSONObject data();
	
	AlpineJs<H> text(Object expression);
	AlpineJs<H> html(Object expression);
	AlpineJs<H> init(Object expression);
	AlpineJs<H> show(Object expression);
	AlpineJs<H> bind(String attribute, Object expression);
	AlpineJs<H> on(String event, Object expression);
	AlpineJs<H> model(Object dataField);
	AlpineJs<H> modelable(Object dataField);
	AlpineJs<H> effect(Object expression);
	AlpineJs<H> ignore();
	AlpineJs<H> ref(Object name);
	AlpineJs<H> cloak();

	AlpineJs<H> id(Object ids);
	JSONArray id();	
	
	AlpineJs<H> transition();
	AlpineJs<H> transition(String modifier);
	AlpineJs<H> transition(String modifier, Object expression);
	
	H toHTMLElement();

}
