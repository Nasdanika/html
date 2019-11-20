package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Breakpoint;
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
		
		class ColImpl extends DivWrappingBootstrapElementImpl<Col> implements Col {

			protected ColImpl(BootstrapFactory factory, Object... content) {
				super(factory, true);
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
				htmlElement.addClass(breakpoint.size("col", width));
				return this;
			}			
			
		}

		@Override
		public Col col(Object... content) {
			Col ret = new ColImpl(getFactory(), content);
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
