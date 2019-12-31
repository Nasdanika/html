package org.nasdanika.html.app;

import java.util.List;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;

/**
 * An interface to delegate UI generation.
 * @author Pavel Vlasov
 *
 */
public interface ViewPart {
	
	Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor);
	
	/**
	 * Wraps value into a view part.
	 * @param value
	 * @return
	 */
	static ViewPart fromValue(Object value) {
		return value instanceof ViewPart ? (ViewPart) value : new ViewPart() {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				return value;
			}
			
		};
	}
	
	default ViewPart then(ViewBuilder... builders) {
		if (builders.length == 0) {
			return this;
		}
		return new ViewPart() {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				Object ret = ViewPart.this.generate(viewGenerator, progressMonitor);
				for (ViewBuilder builder: builders) {
					if (builder != null) {
						builder.build(ret, viewGenerator, progressMonitor);
					}
				}
				return ret;
			}
		};
	}
	
	default ViewPart then(List<ViewBuilder> builders) {
		if (builders == null || builders.isEmpty()) {
			return this;
		}
		return then(builders.toArray(new ViewBuilder[builders.size()]));
	}
	
	/**
	 * @param mapper
	 * @return View part which applies the mapper function to the result.
	 */
	default ViewPart then(Function<Object,Object> mapper) {
		if (mapper == null) {
			return this;
		}
		
		return new ViewPart() {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				return mapper.apply(ViewPart.this.generate(viewGenerator, progressMonitor));
			}
			
		};
	}

}
