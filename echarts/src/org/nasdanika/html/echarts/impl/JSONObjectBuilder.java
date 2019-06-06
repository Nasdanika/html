package org.nasdanika.html.echarts.impl;

import java.util.function.Supplier;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Base class for builders of {@link JSONObject}.
 * @author Pavel
 *
 */
public class JSONObjectBuilder implements Supplier<JSONObject> 	{

	private JSONObject option = new JSONObject();
	
	/**
	 * Returns a sub-object under a given key creating it if it doesn't exist.
	 * @param key
	 * @return
	 */
	protected JSONObject getJSONObject(String key) {
		if (!option.has(key)) {
			option.put(key, new JSONObject());
		}
		return option.getJSONObject(key);
	}

	/**
	 * Returns an array under a given key creating it if it doesn't exist.
	 * @param key
	 * @return
	 */
	protected JSONArray getJSONArray(String key) {
		if (!option.has(key)) {
			option.put(key, new JSONArray());
		}
		return option.getJSONArray(key);
	}
	
	@Override
	public JSONObject get() {
		return option;
	}

}
