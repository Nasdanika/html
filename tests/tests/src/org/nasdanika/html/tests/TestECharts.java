package org.nasdanika.html.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.echarts.EChartsFactory;
import org.nasdanika.html.echarts.OptionBuilder;


public class TestECharts extends HTMLTestBase {
		
	@Test
	public void testCards() throws Exception {
		Card card = BootstrapFactory.INSTANCE.card().border(Color.SUCCESS);		
		card.getTitle().toHTMLElement().content("ECharts demo");
		OptionBuilder optionBuilder = EChartsFactory.INSTANCE.createOptionBuilder();
		
		optionBuilder.titleBuilder().text("Nasdanika ECharts example");
		
		JSONArray legendData = new JSONArray();
		legendData.put("Sales");
		optionBuilder.legend().put("data", legendData);
		
		JSONArray xAxisData = new JSONArray();
		xAxisData.put("shirt");
		xAxisData.put("cardign");
		xAxisData.put("chiffon shirt");
		xAxisData.put("pants");
		xAxisData.put("heels");
		xAxisData.put("socks");
		optionBuilder.xAxis().put("data", xAxisData);
		
		optionBuilder.yAxis(); // just to create an empty object?
		
		JSONObject salesSeries = new JSONObject();
		salesSeries.put("name", "Sales");
		salesSeries.put("type", "bar");
		
		JSONArray salesData = new JSONArray();
		salesData.put(5);
		salesData.put(20);
		salesData.put(36);
		salesData.put(10);
		salesData.put(10);
		salesData.put(20);
		salesSeries.put("data", salesData);
				
		optionBuilder.series().put(salesSeries);
		
		card.getBody().toHTMLElement().content(optionBuilder.create("700px", "300px"));
		writeThemedPage("echarts/card.html", "Bootstrap card", card); 
	}	
	
}
