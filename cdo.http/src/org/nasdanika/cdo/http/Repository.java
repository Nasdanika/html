package org.nasdanika.cdo.http;

import org.eclipse.emf.cdo.CDOObject;

/**
 * An interface for finding objects by Id.
 * @author Pavel
 *
 */
public interface Repository {
	
	CDOObject find(String id);

}