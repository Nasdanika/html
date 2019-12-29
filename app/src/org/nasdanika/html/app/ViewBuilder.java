package org.nasdanika.html.app;

import java.util.function.Function;

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
	
	/**
	 * @param mapper
	 * @return ViewBuilder which applies the mapper function to target and passes the return value to this builder.
	 */
	default ViewBuilder before(Function<Object,Object> mapper) {
		if (mapper == null) {
			return this;
		}
		
		return new ViewBuilder() {
			
			@Override
			public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				ViewBuilder.this.build(mapper.apply(target), viewGenerator, progressMonitor);				
			}
			
		};
	}
}
