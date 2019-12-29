package org.nasdanika.html.app;

import org.nasdanika.common.Composeable;
import org.nasdanika.common.ProgressMonitor;

/**
 * Builds the target passed to the build method.
 * 
 * @author Pavel
 *
 */
public interface ViewBuilder extends Composeable<ViewBuilder> {
	
	ViewBuilder NOP = new ViewBuilder() {
		
		@Override
		public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			// NOP			
		}
		
		@Override
		public ViewBuilder compose(ViewBuilder other) {
			return other;
		}
		
	};
	
	/**
	 * Builds the target, e.g. adds content to a table cell.
	 * @param target
	 * @param viewGenerator
	 * @param monitor
	 */
	void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor);	

	@Override
	default ViewBuilder compose(ViewBuilder other) {
		return other == null || other == NOP ? this : (target, viewGenerator, progressMonitor) -> {
			// TODO - monitor splitting.
			ViewBuilder.this.build(target, viewGenerator, progressMonitor);
			other.build(target, viewGenerator, progressMonitor);
		};
	}
}
