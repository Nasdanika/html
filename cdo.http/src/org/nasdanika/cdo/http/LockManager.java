package org.nasdanika.cdo.http;

import java.util.concurrent.locks.ReadWriteLock;

import org.eclipse.emf.ecore.EObject;

/**
 * Implementations of this interface manage locks.
 * An instance of a lock manager is typically obtained by adapting {@link EObject}.
 * @author Pavel
 *
 */
public interface LockManager extends ReadWriteLock {
			
}
