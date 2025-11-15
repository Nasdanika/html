package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Size;

public class ContainerImpl extends WrappingBootstrapElementImpl<Tag,Container> implements Container {

	public ContainerImpl(BootstrapFactory factory, Tag htmlElement, boolean fluid) {
		super(factory, htmlElement);
		htmlElement
			.addClassConditional(!fluid, "container")
			.addClassConditional(fluid, "container-fluid");
		
	}
	
	class RowImpl extends DivWrappingBootstrapElementImpl<Row> implements Row {

		protected RowImpl(BootstrapFactory factory) {
			super(factory, true);
			htmlElement.addClass("row");
		}
		
		class ColImpl extends WrappingBootstrapElementImpl<Tag,Col> implements Col {

			protected ColImpl(TagName tagName, BootstrapFactory factory, Object... content) {
				super(factory, factory.getHTMLFactory().nonEmptyTag(tagName));
				htmlElement.addClass("col");
				htmlElement.content(content);
			}

			@Override
			public Col content(Object... content) {
				htmlElement.content(content);
				return this;
			}

			@Override
			public boolean isEmpty() {
				return htmlElement.isEmpty();
			}

			@Override
			public Col width(Breakpoint breakpoint, Size width) {
				htmlElement.removeClass("col");
				htmlElement.addClass(breakpoint.size("col", width));
				return this;
			}			
			
		}

		@Override
		public Col col(TagName tagName, Object... content) {
			Col ret = new ColImpl(tagName, getFactory(), content);
			htmlElement.content(ret.toHTMLElement());
			return ret;
		}
		
	}

	@Override
	public Row row() {
		Row ret = new RowImpl(getFactory());
		htmlElement.content(ret.toHTMLElement());
		return ret;
	}

}
