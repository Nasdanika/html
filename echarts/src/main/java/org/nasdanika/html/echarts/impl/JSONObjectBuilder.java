package org.nasdanika.html.echarts.impl;

import java.util.function.Supplier;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Base class for builders of {@link JSONObject}.
 * @author Pavel
 *
 */
public abstract class JSONObjectBuilder implements Supplier<JSONObject> 	{

	/**
	 * Returns a sub-object under a given key creating it if it doesn't exist.
	 * @param key
	 * @return
	 */
	protected JSONObject getJSONObject(String key) {
		if (!get().has(key)) {
			get().put(key, new JSONObject());
		}
		return get().getJSONObject(key);
	}

	/**
	 * Returns an array under a given key creating it if it doesn't exist.
	 * @param key
	 * @return
	 */
	protected JSONArray getJSONArray(String key) {
		if (!get().has(key)) {
			get().put(key, new JSONArray());
		}
		return get().getJSONArray(key);
	}

}
