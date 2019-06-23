package org.nasdanika.html.app;

import org.nasdanika.common.ProgressMonitor;

/**
 * An interface to delegate UI generation.
 * @author Pavel Vlasov
 *
 */
public interface ViewPart {
	
	Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor);

}
