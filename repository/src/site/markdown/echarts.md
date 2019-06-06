# ECharts

[Java API](apidocs/org.nasdanika.html.echarts/apidocs/index.html) to build charts with [ECharts](https://ecomfe.github.io/echarts-doc/public/en/index.html) 4.2.1.

The entry point to the API is [EChartsFactory](apidocs/org.nasdanika.html.echarts/apidocs/index.html?org/nasdanika/html/echarts/EChartsFactory.html).
It can be obtained as ``EChartsFactory factory = EChartsFactory.INSTANCE;``.  

Charts can be created either with the factory or by creating an instance of [OptionBuilder](apidocs/org.nasdanika.html.echarts/apidocs/index.html?org/nasdanika/html/echarts/OptionBuilder.html)
and then using the instance to configure and create a chart. 

``org.nasdanika.html.echarts`` bundle has ``resources`` folder containing ECharts JavaScript. 
These resources can be used to generate self-sufficient Web UI without dependency on CDN's. 

## Example

The below example creates a bar chart in a Bootstrap card. Chart title is built using TitleBuilder, the rest of the option is creates with low-level JSON API's.

```
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

optionBuilder.yAxis(); // yAxis object must be present, so we call this method to create an empty object.

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
```
<iframe src="tests/dumps/echarts/card.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>


## Option builders pattern

The API provides two ways to populate chart option:

* Low-level API's using JSONObject and JSONArray
* Use of option builders.

 Option builders offer the advantage of more concise and controlled option population, reducing the amount of coding effort and risk of typos and other errors.
 ECharts option has an extensive configuration and currently Nasdanika ECharts option builders do not support all of it. However, they define the pattern of how to create additional option builders.
 More option builders will be provided over time.   

## Use in Maven projects

Add repository and dependency as shown below:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	...	
	<repositories>
		...
		<repository>
			<id>nasdanika-html-snapshots</id>
			<name>nasdanika-html-snapshots</name>
			<url>https://www.nasdanika.org/home/products/html/maven-repository</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<layout>default</layout>
		</repository>
		...
	</repositories>	
	...		
	<dependencies>
		...		
		<dependency>
			<groupId>org.nasdanika.html</groupId>
			<artifactId>org.nasdanika.html.echarts</artifactId>
			<version>2.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```
