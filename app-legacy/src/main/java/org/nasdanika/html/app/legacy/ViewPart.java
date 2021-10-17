package org.nasdanika.html.app;

import java.util.List;
import java.util.function.Function;

import org.nasdanika.common.Context;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

/**
 * An interface to delegate UI generation.
 * @author Pavel Vlasov
 *
 */
public interface ViewPart {
		
	/**
	 * Binding of org.nasdanika.Supplier to {@link ViewPart}
	 * @author Pavel
	 *
	 */
	interface Supplier extends org.nasdanika.common.Supplier<ViewPart> {
		
		/**
		 * Wraps generic supplier in this strongly typed one.
		 * @param generic
		 */
		static Supplier from(org.nasdanika.common.Supplier<ViewPart> generic) {
			class SupplierImpl extends FilterExecutionParticipant<org.nasdanika.common.Supplier<ViewPart>> implements Supplier {

				public SupplierImpl(org.nasdanika.common.Supplier<ViewPart> target) {
					super(target);
				}

				@Override
				public ViewPart execute(ProgressMonitor progressMonitor) throws Exception {
					return target.execute(progressMonitor);
				}
				
			}
			return new SupplierImpl(generic);			
		}
		
		/**
		 * Binding of {@link SupplierFactory} to {@link Supplier}
		 * @author Pavel
		 *
		 */
		interface Factory extends SupplierFactory<ViewPart> {
			
			@Override
			Supplier create(Context context) throws Exception;
			
			/**
			 * Wraps generic {@link SupplierFactory} in this strongly typed {@link Factory}
			 * @param generic
			 * @return
			 */
			static Factory from(SupplierFactory<ViewPart> generic) {
				return new Factory() {

					@Override
					public Supplier create(Context context) throws Exception {
						return Supplier.from(generic.create(context));
					}
					
				};
			}
			
		}
		
	}	
	
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
	
	/**
	 * Override to return true if the ViewPart returns null or empty string.
	 * @return
	 */
	default boolean isEmpty() {
		return false;
	}

}
