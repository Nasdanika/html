package org.nasdanika.html.model.bootstrap.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Tag;
import org.nasdanika.html.model.bootstrap.Slide;

public class SlideSupplierFactoryAdapter extends DivSupplierFactoryAdapter<Slide> {
	
	public SlideSupplierFactoryAdapter(Slide slide, AdapterFactory adapterFactory) {
		super(slide, adapterFactory);
	}
	
	@Override
	protected Function<org.nasdanika.html.Tag, org.nasdanika.html.Tag> createConfigureFunction(Context context) throws Exception {
		
		@SuppressWarnings("resource")
		Consumer<org.nasdanika.html.Tag> slideConsumer = new Consumer<Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Slide";
			}
			
			@Override
			public void execute(Tag tag, ProgressMonitor progressMonitor) throws Exception {
				tag.addClass("carousel-item");
				Integer interval = getTarget().getInterval();
				if (interval != null) {
					tag.attribute("data-interval", interval);
				}
				
				// TODO - Captions.
			}
		};
		
		// TODO - Captions list factory, slide function.
				
		return super.createConfigureFunction(context).then(slideConsumer.asFunction());
	}

}
