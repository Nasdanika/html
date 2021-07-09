package org.nasdanika.html.echarts.impl;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.json.JSONObject;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.echarts.EChartsFactory;
import org.nasdanika.html.echarts.OptionBuilder;

/**
 * 
 * @author Pavel
 *
 */
public class DefaultEChartsFactory implements EChartsFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultEChartsFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}

	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}

	@Override
	public <P extends HTMLPage> P cdn(P page) {
		page.script("https://cdnjs.cloudflare.com/ajax/libs/echarts/4.2.1/echarts.min.js");
		return page;
	}

	@Override
	public String init(String id, JSONObject option) {
		return "echarts.init(document.getElementById('"+id+"')).setOption(\n"+option.toString(4)+");";
	}

	@Override
	public String init(String id, Supplier<JSONObject> optionSupplier) {
		return init(id, optionSupplier.get());
	}

	@Override
	public String init(HTMLElement<?> htmlElement, JSONObject option) {
		Object id = htmlElement.getId();
		if (id == null) {
			id = "echart-container-"+getHTMLFactory().nextId();
			htmlElement.id(id);
		}
		return init(id.toString(), option);
	}

	@Override
	public String init(HTMLElement<?> htmlElement, Supplier<JSONObject> optionSupplier) {
		return init(htmlElement, optionSupplier.get());
	}

	@Override
	public Fragment create(JSONObject option, Object width, Object height) {
		Fragment ret = getHTMLFactory().fragment();
		create(option, width, height, ret);
		return ret;
	}

	@Override
	public Fragment create(Supplier<JSONObject> optionSupplier, Object width, Object height) {
		return create(optionSupplier.get(), width, height);
	}

	@Override
	public void create(JSONObject option, Object width, Object height, Consumer<Object> consumer) {
		HTMLFactory htmlFactory = getHTMLFactory();
		Tag div = htmlFactory.div();
		div.style().width(width);
		div.style().height(height);
		Tag initScript = htmlFactory.tag(TagName.script, init(div, option));		
		consumer.accept(div);
		consumer.accept(initScript);
	}

	@Override
	public void create(Supplier<JSONObject> optionSupplier, Object width, Object height, Consumer<Object> consumer) {
		create(optionSupplier.get(), width, height, consumer);		
	}

	@Override
	public OptionBuilder createOptionBuilder() {
		return new OptionBuilderImpl(this);
	}
	
}
