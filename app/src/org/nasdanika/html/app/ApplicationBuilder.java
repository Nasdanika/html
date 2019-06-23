package org.nasdanika.html.app;

import org.nasdanika.common.ProgressMonitor;

public interface ApplicationBuilder {
	
	void build(Application application, ProgressMonitor progressMonitor);

}
