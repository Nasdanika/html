package org.nasdanika.html.echarts;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.json.JSONObject;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.echarts.impl.DefaultEChartsFactory;

/**
 * Factory interface for building charts.
 * @author Pavel
 *
 */
public interface EChartsFactory {
	
	EChartsFactory INSTANCE = new DefaultEChartsFactory(HTMLFactory.INSTANCE);	
		
	HTMLFactory getHTMLFactory();
	
	/**
	 * Adds ECharts script declaration pointing to CDN.
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);
	
	/**
	 * Creates an initialization script for an existing HTML element with a given ID.
	 * @param id Id of an existing HTML element.
	 * @param option Chart option.
	 * @return JavaScript code to initialize the chart.
	 */
	String init(String id, JSONObject option);
	
	/**
	 * Creates an initialization script for an existing HTML element with a given ID.
	 * @param id Id of an existing HTML element.
	 * @param optionSupplier Chart option supplier, e.g. {@link OptionBuilder}.
	 * @return JavaScript code to initialize the chart.
	 */
	String init(String id, Supplier<JSONObject> optionSupplier);
	
	/**
	 * Creates an initialization script for an existing HTML element. If the element already has an id, that id is used,
	 * otherwise it is generated and set by this call.
	 * @param htmlElement Chart's DOM container. 
	 * @param option Option
	 * @return JavaScript code to initalize the chart.
	 */
	String init(HTMLElement<?> htmlElement, JSONObject option);
	
	/**
	 * Creates an initialization script for an existing HTML element. If the element already has an id, that id is used,
	 * otherwise it is generated and set by this call.
	 * @param htmlElement Chart's DOM container. 
	 * @param optionSupplier Chart option supplier, e.g. {@link OptionBuilder}.
	 * @return JavaScript code to initalize the chart.
	 */
	String init(HTMLElement<?> htmlElement, Supplier<JSONObject> optionSupplier);
	
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code.
	 * @param option Chart option.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @return Fragment with the chart container and initialization script.
	 */
	Fragment create(JSONObject option, Object width, Object height);
	
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code.
	 * @param optionSupplier Chart option supplier, e.g. {@link OptionBuilder}.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @return Fragment with the chart container and initialization script.
	 */
	Fragment create(Supplier<JSONObject> optionSupplier, Object width, Object height);
	
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code.
	 * @param option Chart option.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @param consumer Consumer to accept the generated container and script.
	 */
	void create(JSONObject option, Object width, Object height, Consumer<Object> consumer);
		
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code.
	 * @param optionSupplier Chart option supplier, e.g. {@link OptionBuilder}.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @param consumer Consumer to accept the generated container and script.
	 */
	void create(Supplier<JSONObject> optionSupplier, Object width, Object height, Consumer<Object> consumer);
	
	/**
	 * @return A new {@link OptionBuilder}.
	 */
	OptionBuilder createOptionBuilder();
}
