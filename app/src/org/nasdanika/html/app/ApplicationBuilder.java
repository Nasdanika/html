package org.nasdanika.html.app;

import org.nasdanika.common.Context;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

public interface ApplicationBuilder {
		
	/**
	 * Binding of org.nasdanika.Supplier to {@link ApplicationBuilder}
	 * @author Pavel
	 *
	 */
	interface Supplier extends org.nasdanika.common.Supplier<ApplicationBuilder> {
		
		/**
		 * Wraps generic supplier in this strongly typed one.
		 * @param generic
		 */
		static Supplier from(org.nasdanika.common.Supplier<ApplicationBuilder> generic) {
			class SupplierImpl extends FilterExecutionParticipant<org.nasdanika.common.Supplier<ApplicationBuilder>> implements Supplier {
	
				public SupplierImpl(org.nasdanika.common.Supplier<ApplicationBuilder> target) {
					super(target);
				}
	
				@Override
				public ApplicationBuilder execute(ProgressMonitor progressMonitor) throws Exception {
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
		interface Factory extends SupplierFactory<ApplicationBuilder> {
			
			@Override
			Supplier create(Context context) throws Exception;
			
			/**
			 * Wraps generic {@link SupplierFactory} in this strongly typed {@link Factory}
			 * @param generic
			 * @return
			 */
			static Factory from(SupplierFactory<ApplicationBuilder> generic) {
				return new Factory() {
	
					@Override
					public Supplier create(Context context) throws Exception {
						return Supplier.from(generic.create(context));
					}
					
				};
			}
			
		}
		
	}	
	
	/**
	 * Builds the application
	 * @param application
	 * @param progressMonitor
	 */
	void build(Application application, ProgressMonitor progressMonitor);

}
