package org.nasdanika.html.model.bootstrap.gen;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.bootstrap.Carousel;

public class CarouselSupplierFactoryAdapter extends DivSupplierFactoryAdapter<Carousel> {
	
	public CarouselSupplierFactoryAdapter(Carousel carousel, AdapterFactory adapterFactory) {
		super(carousel, adapterFactory);
	}
	
	@Override
	protected Function<org.nasdanika.html.Tag, org.nasdanika.html.Tag> createConfigureFunction(Context context) {
		
		@SuppressWarnings("resource")
		Function<BiSupplier<Tag, List<Object>>, Tag> carouselFunction = new Function<BiSupplier<Tag, List<Object>>, Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Carousel";
			}
			
			@Override
			public Tag execute(BiSupplier<Tag, List<Object>> input, ProgressMonitor progressMonitor) {
				Tag tag = input.getFirst();
				tag.addClass("carousel", "slide");
				Carousel semanticElement = getTarget();
				if (semanticElement.isRide()) {
					tag.attribute("data-ride", "carousel");
				}
				if (!Util.isBlank(semanticElement.getInterval())) {
					tag.attribute("data-interval", semanticElement.getInterval());
				}

				List<Object> slides = input.getSecond();
				HTMLFactory htmlFactory = tag.getFactory();
				
				Object id = tag.getId();
				if (id == null) {
					id = htmlFactory.nextId();
					tag.id(id);
				}
				
				Tag indicatorsList = null;
				
				// indicator
				if (semanticElement.isIndicator()) {
					indicatorsList = htmlFactory.tag(TagName.ol).addClass("carousel-indicators");
					tag.accept(indicatorsList);
				}
				
				Tag carouselInner = htmlFactory.div().addClass("carousel-inner");
				tag.accept(carouselInner);
				int index = 0;
				for (Object slide: slides) {
					carouselInner.accept(slide);
					Tag indicator = null;
					if (indicatorsList != null) {
						indicator = htmlFactory.tag(TagName.li)
							.attribute("data-target", "#" + id)
							.attribute("data-slide-to", index);
						indicatorsList.accept(indicator);
					}
					if (index == 0) {
						((HTMLElement<?>) slide).addClass("active");
						indicator.addClass("active");
					}
					++index;
				}
				
				// controls
				if (semanticElement.isControls()) {
					Button prevButton = htmlFactory.button()
							.addClass("carousel-control-prev", "border-0")
							.attribute("type", "button")
							.attribute("data-target", "#" + id)
							.attribute("data-slide", "prev");
					
					prevButton.accept(htmlFactory.span().addClass("carousel-control-prev-icon").attribute("aria-hidden", "true"));
					prevButton.accept(htmlFactory.span("Previous").addClass("sr-only"));
					tag.accept(prevButton);
					
					Button nextButton = htmlFactory.button()
							.addClass("carousel-control-next", "border-0")
							.attribute("type", "button")
							.attribute("data-target", "#" + id)
							.attribute("data-slide", "next");
					
					nextButton.accept(htmlFactory.span().addClass("carousel-control-next-icon").attribute("aria-hidden", "true"));
					nextButton.accept(htmlFactory.span("Next").addClass("sr-only"));
					tag.accept(nextButton);
				}
				
				// crossFade
				if (semanticElement.isCrossFade()) {
					tag.addClass("carousel-fade");
				}
				
				return tag;
			}
		};
		
		ListCompoundSupplierFactory<Object> slidesFactory = new ListCompoundSupplierFactory<>("Slides", EObjectAdaptable.adaptToSupplierFactoryNonNull(getTarget().getSlides(), Object.class));
		
		return super.createConfigureFunction(context).then(slidesFactory.create(context).asFunction()).then(carouselFunction);
	}

}

