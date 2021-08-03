package org.nasdanika.html.app;

import java.util.function.Function;

import org.nasdanika.common.Composeable;
import org.nasdanika.common.Context;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

/**
 * Builds the target passed to the build method.
 * 
 * @author Pavel
 *
 */
public interface ViewBuilder extends Composeable<ViewBuilder> {
		
	/**
	 * Binding of org.nasdanika.Supplier to {@link ViewBuilder}
	 * @author Pavel
	 *
	 */
	interface Supplier extends org.nasdanika.common.Supplier<ViewBuilder> {
		
		/**
		 * Wraps generic supplier in this strongly typed one.
		 * @param generic
		 */
		static Supplier from(org.nasdanika.common.Supplier<ViewBuilder> generic) {
			class SupplierImpl extends FilterExecutionParticipant<org.nasdanika.common.Supplier<ViewBuilder>> implements Supplier {

				public SupplierImpl(org.nasdanika.common.Supplier<ViewBuilder> target) {
					super(target);
				}

				@Override
				public ViewBuilder execute(ProgressMonitor progressMonitor) throws Exception {
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
		interface Factory extends SupplierFactory<ViewBuilder> {
			
			@Override
			Supplier create(Context context) throws Exception;
			
			/**
			 * Wraps generic {@link SupplierFactory} in this strongly typed {@link Factory}
			 * @param generic
			 * @return
			 */
			static Factory from(SupplierFactory<ViewBuilder> generic) {
				return new Factory() {

					@Override
					public Supplier create(Context context) throws Exception {
						return Supplier.from(generic.create(context));
					}
					
				};
			}
			
		}
		
	}	
	
	ViewBuilder NOP = new ViewBuilder() {
		
		@Override
		public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			// NOP			
		}
		
		@Override
		public ViewBuilder compose(ViewBuilder other) {
			return other == null ? this : other;
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
