package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Carousel;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class CarouselImpl extends UIElementImpl<Carousel> implements Carousel {
	
	private int activeSlide;
	private List<SlideImpl> slides = new ArrayList<>();
	private org.nasdanika.html.Bootstrap.Color indicatorsBootstrapBackground;
	private org.nasdanika.html.UIElement.HTMLColor indicatorsHTMLBackground;
	
	private class SlideImpl implements Slide, AutoCloseable {
		
		private List<Object> content = new ArrayList<>();
		private List<Object> caption = new ArrayList<>();

		@Override
		public void close() throws Exception {
			for (Object c: content) {
				if (c instanceof AutoCloseable) {
					((AutoCloseable) c).close();
				}
			}
			for (Object c: caption) {
				if (c instanceof AutoCloseable) {
					((AutoCloseable) c).close();
				}
			}			
		}

		@Override
		public Slide active(boolean active) {
			activeSlide = active ? slides.indexOf(this) : 0;
			return this;
		}

		@Override
		public Slide active() {
			return active(true);
		}

		@Override
		public Slide content(Object... content) {
			for (Object c: content) {
				if (c != null) {
					this.content.add(c);
				}
			}
			return this;
		}

		@Override
		public Slide caption(Object... caption) {
			for (Object c: caption) {
				if (c != null) {
					this.caption.add(c);
				}
			}
			return this;
		}
		
	}

	public CarouselImpl(HTMLFactory factory) {
		super(factory, "div");
		id(factory.nextId()+"_carousel");
		addClass("carousel", "slide");
	}

	@Override
	public Carousel ride(boolean ride) {
		attribute("data-ride", ride ? "carousel" : null);
		return this;
	}
		
	@Override
	public Slide slide() {
		SlideImpl ret = new SlideImpl();
		slides.add(ret);
		return ret;
	}
	
	@Override
	public void close() throws Exception {
		for (SlideImpl s: slides) {
			s.close();
		}
		super.close();
	}
	
	@Override
	protected List<Object> getContent() {
		List<Object> ret = new ArrayList<>();
		
		Tag ol = factory.tag(TagName.ol).addClass("carousel-indicators");
		if (indicatorsBootstrapBackground!=null) {
			ol.style("background", indicatorsBootstrapBackground.code);
		} else if (indicatorsHTMLBackground!=null) {
			ol.style("background", indicatorsHTMLBackground.name());
		}
		for (int i=0; i<slides.size(); ++i) {
			Tag li = factory.tag(TagName.li)
					.attribute("data-target", "#"+getId())
					.attribute("data-slide-to", i);
			
			if (activeSlide == i) {
				li.addClass("active");
			}
			
			ol.content(li);
		}
		ret.add(ol);
		
		Tag innerDiv = factory.div().addClass("carousel-inner");
		for (int i=0; i<slides.size(); ++i) {
			Tag item = factory.div().addClass("item");
			if (activeSlide == i) {
				item.addClass("active");
			}
			for (Object c: slides.get(i).content) {
				item.content(c);
			}
			if (!slides.get(i).caption.isEmpty()) {
				Tag captionDiv = factory.div().addClass("carousel-caption");
				for (Object c: slides.get(i).caption) {
					captionDiv.content(c);
				}
				item.content(captionDiv);
			}
			innerDiv.content(item);
		}
		ret.add(innerDiv);
		
		ret.add(factory.tag(TagName.a, factory.span("").addClass("glyphicon", "glyphicon-chevron-left"))
				.addClass("left", "carousel-control")
				.attribute("href", "#"+getId())
				.attribute("role", "button")
				.attribute("data-slide", "prev"));
		
		ret.add(factory.tag(TagName.a, factory.span("").addClass("glyphicon", "glyphicon-chevron-right"))
				.addClass("right", "carousel-control")
				.attribute("href", "#"+getId())
				.attribute("role", "button")
				.attribute("data-slide", "next"));
		
		return ret;
	}

	@Override
	public Carousel indicatorsBackground(Bootstrap.Color background) {
		indicatorsBootstrapBackground = background;
		indicatorsHTMLBackground = null;
		return this;
	}

	@Override
	public Carousel indicatorsBackground(HTMLColor background) {
		indicatorsHTMLBackground = background;
		indicatorsBootstrapBackground = null;
		return this;
	}

}
