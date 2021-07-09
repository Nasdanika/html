package org.nasdanika.html.echarts.impl;

import java.util.function.Consumer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.echarts.EChartsFactory;
import org.nasdanika.html.echarts.OptionBuilder;
import org.nasdanika.html.echarts.TitleBuilder;

public class OptionBuilderImpl extends JSONObjectBuilder implements OptionBuilder {

	private JSONObject option = new JSONObject();

	@Override
	public JSONObject get() {
		return option;
	}
	
	private EChartsFactory factory;

	public OptionBuilderImpl(EChartsFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public JSONObject title() {
		return getJSONObject("title");
	}

	@Override
	public JSONObject legend() {
		return getJSONObject("legend");
	}

	@Override
	public JSONObject grid() {
		return getJSONObject("grid");
	}

	@Override
	public JSONObject xAxis() {
		return getJSONObject("xAxis");
	}

	@Override
	public JSONObject yAxis() {
		return getJSONObject("yAxis");
	}

	@Override
	public JSONObject polar() {
		return getJSONObject("polar");
	}

	@Override
	public JSONObject radiusAxis() {
		return getJSONObject("radiusAxis");
	}

	@Override
	public JSONObject angleAxis() {
		return getJSONObject("angleAxis");
	}

	@Override
	public JSONObject radar() {
		return getJSONObject("radar");
	}

	@Override
	public JSONArray dataZoom() {
		return getJSONArray("dataZoom");
	}

	@Override
	public JSONArray visualMap() {
		return getJSONArray("visualMap");
	}

	@Override
	public JSONObject tooltip() {
		return getJSONObject("tooltip");
	}

	@Override
	public JSONObject axisPointer() {
		return getJSONObject("axisPointer");
	}

	@Override
	public JSONObject toolbox() {
		return getJSONObject("toolbox");
	}

	@Override
	public JSONObject brush() {
		return getJSONObject("brush");
	}

	@Override
	public JSONObject geo() {
		return getJSONObject("geo");
	}

	@Override
	public JSONObject parallel() {
		return getJSONObject("parallel");
	}

	@Override
	public JSONObject parallelAxis() {
		return getJSONObject("parallelAxis");
	}

	@Override
	public JSONObject singleAxis() {
		return getJSONObject("singleAxis");
	}

	@Override
	public JSONObject timeline() {
		return getJSONObject("timeline");
	}

	@Override
	public JSONObject graphic() {
		return getJSONObject("graphic");
	}

	@Override
	public JSONObject calendar() {
		return getJSONObject("calendar");
	}

	@Override
	public JSONObject dataset() {
		return getJSONObject("dataset");
	}

	@Override
	public JSONObject aria() {
		return getJSONObject("aria");
	}

	@Override
	public JSONArray series() {
		return getJSONArray("series");
	}

	@Override
	public JSONObject color() {
		return getJSONObject("color");
	}

	@Override
	public JSONObject backgroundColor() {
		return getJSONObject("backgroundColor");
	}

	@Override
	public JSONObject textStyle() {
		return getJSONObject("textStyle");
	}

	@Override
	public JSONObject animation() {
		return getJSONObject("animation");
	}

	@Override
	public JSONObject animationThreshold() {
		return getJSONObject("animationThreshold");
	}

	@Override
	public JSONObject animationDuration() {
		return getJSONObject("animationDuration");
	}

	@Override
	public JSONObject animationEasing() {
		return getJSONObject("animationEasing");
	}

	@Override
	public JSONObject animationDelay() {
		return getJSONObject("animationDelay");
	}

	@Override
	public JSONObject animationDurationUpdate() {
		return getJSONObject("animationDurationUpdate");
	}

	@Override
	public JSONObject animationEasingUpdate() {
		return getJSONObject("animationEasingUpdate");
	}

	@Override
	public JSONObject animationDelayUpdate() {
		return getJSONObject("animationDelayUpdate");
	}

	@Override
	public JSONObject useUTC() {
		return getJSONObject("useUTC");
	}

	@Override
	public EChartsFactory getFactory() {
		return factory;
	}

	@Override
	public String init(String id) {
		return getFactory().init(id, this);
	}

	@Override
	public String init(HTMLElement<?> htmlElement) {
		return getFactory().init(htmlElement, this);
	}

	@Override
	public Fragment create(Object width, Object height) {
		return getFactory().create(this, width, height);
	}

	@Override
	public void create(Object width, Object height, Consumer<Object> consumer) {
		getFactory().create(this, width, height);		
	}

	@Override
	public TitleBuilder titleBuilder() {
		return new TitleBuilderImpl() {
			
			@Override
			public JSONObject get() {
				return OptionBuilderImpl.this.getJSONObject("title");
			}
			
		};
	}

}
