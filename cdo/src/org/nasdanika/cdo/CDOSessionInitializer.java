package org.nasdanika.cdo;

import org.eclipse.emf.cdo.session.CDOSession;

/**
 * Initializes CDO Session, e.g. registers packages and/or creates initial objects in the repository if they are not present.
 * @author Pavel Vlasov
 *
 */
public interface CDOSessionInitializer {
	
	void init(CDOSession session);

}
