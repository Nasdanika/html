package org.nasdanika.html.app.impl;

import java.util.Collection;

import org.nasdanika.html.app.Action;

public final class Util {

	private Util() {
		// Utility
	}
	
	
	/**
	 * Compares actions by id and then using equals.
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static boolean equal(Action a1, Action a2) {
		if (a1 == a2) {
			return true;
		}
		if (a1 == null || a2 == null) {
			return false;
		}
		if (a1.getId() != null && a1.getId().equals(a2.getId())) {
			return true;
		}
		return a1.equals(a2);
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
	

}
