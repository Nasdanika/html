package org.nasdanika.html.echarts;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;

/**
 * Interface to build ECharts option object - https://ecomfe.github.io/echarts-doc/public/en/option.html.
 * @author Pavel
 *
 */
public interface OptionBuilder extends Supplier<JSONObject> {
	
	EChartsFactory getFactory();
	
	JSONObject title();
	TitleBuilder titleBuilder();
	
	JSONObject legend();
	JSONObject grid();
	JSONObject xAxis();
	JSONObject yAxis();
	JSONObject polar();
	JSONObject radiusAxis();
	JSONObject angleAxis();
	JSONObject radar();
	JSONArray dataZoom();
	JSONArray visualMap();
	JSONObject tooltip();
	JSONObject axisPointer();
	JSONObject toolbox();
	JSONObject brush();
	JSONObject geo();
	JSONObject parallel();
	JSONObject parallelAxis();
	JSONObject singleAxis();
	JSONObject timeline();
	JSONObject graphic();
	JSONObject calendar();
	JSONObject dataset();
	JSONObject aria();
	JSONArray series();
	JSONObject color();
	JSONObject backgroundColor();
	JSONObject textStyle();
	JSONObject animation();
	JSONObject animationThreshold();
	JSONObject animationDuration();
	JSONObject animationEasing();
	JSONObject animationDelay();
	JSONObject animationDurationUpdate();
	JSONObject animationEasingUpdate();
	JSONObject animationDelayUpdate();
	JSONObject useUTC();	
	
	// Factory methods with this option.
	
	
	/**
	 * Creates an initialization script for an existing HTML element with a given ID and the option from this builder.
	 * @param id Id of an existing HTML element.
	 * @return JavaScript code to initialize the chart.
	 */
	String init(String id);
	
	/**
	 * Creates an initialization script for an existing HTML element and the option from this builder.
	 * If the element already has an id, that id is used, otherwise it is generated and set by this call.
	 * @param htmlElement Chart's DOM container. 
	 * @param option Option
	 * @return JavaScript code to initalize the chart.
	 */
	String init(HTMLElement<?> htmlElement);
	
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code using the option from this builder.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @return Fragment with the chart container and initialization script.
	 */
	Fragment create(Object width, Object height);
	
	/**
	 * Creates a DIV to contain a chart and a script tag with chart initialization code using the option from this builder.
	 * @param width Optional container width.
	 * @param height Optional container height.
	 * @param consumer Consumer to accept the generated container and script.
	 */
	void create(Object width, Object height, Consumer<Object> consumer);
	

}
