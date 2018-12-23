package org.nasdanika.html.app.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Categorized;
import org.nasdanika.html.app.Label;

public final class Util {

	private Util() {
		// Utility
	}
		
	/**
	 * Compares objects by id and then using equals.
	 * @param l1
	 * @param l2
	 * @return
	 */
	 public static <T extends Label> boolean equal(T l1, T l2) {
		if (l1 == l2) {
			return true;
		}
		if (l1 == null || l2 == null) {
			return false;
		}
		if (l1.getId() != null && l1.getId().equals(l2.getId())) {
			return true;
		}
		return l1.equals(l2);
	}
	 
	/**
	 * Groups items by category. Categories are listed in the order in appearance. Uncategorized entries (null category) 
	 * are listed before any categories.
	 * @param items
	 * @return
	 */
	public static <T extends Categorized> List<Map.Entry<Label, List<T>>> groupByCategory(Iterable<T> items) {
		List<T> uncategorized = new ArrayList<>();
		List<Map.Entry<Label, List<T>>> ret = new ArrayList<>();
		I: for (T item: items) {
			Label category = item.getCategory();
			if (category == null) {
				uncategorized.add(item);
			} else {
				for (Entry<Label, List<T>> e: ret) {
					if (equal(e.getKey(), category)) {
						e.getValue().add(item);
						continue I;
					}
				}
				List<T> ci = new ArrayList<>();
				ci.add(item);
				ret.add(new AbstractMap.SimpleEntry<Label, List<T>>(category, ci));
			}
		}		
		if (!uncategorized.isEmpty()) {
			ret.add(new AbstractMap.SimpleEntry<Label, List<T>>(null, uncategorized));
		}
		return ret;
	}
	
	public static  boolean contains(Collection<? extends Action> collection, Action action) {
		for (Action e: collection) {
			if (equal(e, action)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the parameter action is equal to the active action or is in its path.
	 * @param a
	 * @return
	 */
	public static boolean equalOrInPath(Action activeAction, Action a) {
		return equal(activeAction, a) || contains(activeAction.getPath(), a);
	}
	
	// TODO - sectionLevel(Action)
}
