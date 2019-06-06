package org.nasdanika.html.echarts.impl;

import org.nasdanika.html.echarts.TitleBuilder;

abstract class TitleBuilderImpl extends JSONObjectBuilder implements TitleBuilder {


	@Override
	public void text(String text) {
		get().put("text", text);
	}

}
