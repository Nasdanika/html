package org.nasdanika.cdo.http;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Implementations of this interface manage locks.
 * @author Pavel
 *
 */
public interface LockManager {
			
	/**
	 * @param scope
	 * @return A lock for a given scope. Scopes are application-specific.
	 */
	ReadWriteLock getLock(String scope);

}
