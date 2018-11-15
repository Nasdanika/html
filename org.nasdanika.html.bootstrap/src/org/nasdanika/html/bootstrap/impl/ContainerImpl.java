package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.DeviceSize;

public class ContainerImpl extends WrappingBootstrapElementImpl<Tag,Container> implements Container {

	ContainerImpl(BootstrapFactory factory, Tag htmlElement, boolean fluent) {
		super(factory, htmlElement);
		htmlElement
			.addClassConditional(!fluent, "container")
			.addClassConditional(fluent, "fluid-container");
		
	}
	
	class RowImpl extends DivWrappingBootstrapElementImpl<Row> implements Row {

		protected RowImpl(BootstrapFactory factory) {
			super(factory);
			htmlElement.addClass("row");
		}
		
		class ColImpl extends DivWrappingBootstrapElementImpl<Col> implements Col {

			protected ColImpl(BootstrapFactory factory, Object... content) {
				super(factory);
				htmlElement.addClass("col").content(content);
			}

			@Override
			public Col width(int width) {
				htmlElement.addClass("col-"+width);
				return this;
			}

			@Override
			public Col width(DeviceSize deviceSize, int width) {
				htmlElement.addClass("col-"+deviceSize.code+"-"+width);
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
