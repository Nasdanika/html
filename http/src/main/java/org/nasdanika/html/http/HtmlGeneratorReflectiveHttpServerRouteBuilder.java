package org.nasdanika.html.http;

import java.util.List;

import org.nasdanika.html.Producer;
import org.nasdanika.html.producer.HtmlGenerator;
import org.nasdanika.http.ReflectiveHttpServerRouteBuilder;
import org.nasdanika.http.TelemetryFilter;

import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

public class HtmlGeneratorReflectiveHttpServerRouteBuilder extends ReflectiveHttpServerRouteBuilder {
	
	private HtmlGeneratorResultTransformer htmlGeneratorResultTransformer;
	
	@SuppressWarnings("rawtypes")
	TypedResultTransformer<Producer> producerResultTransformer = TypedResultTransformer.from(Producer.class, producer -> producer.produceAsync(0));

	public HtmlGeneratorReflectiveHttpServerRouteBuilder(TelemetryFilter telemetryFilter, HtmlGenerator htmlGenerator) {
		super(telemetryFilter);
		this.htmlGeneratorResultTransformer = new HtmlGeneratorResultTransformer(htmlGenerator);
	}
	
	@Override
	protected List<ResultTransformer> createResultTransformers(
			Route route, 
			HttpServerRequest request,
			HttpServerResponse response) {
		
		List<ResultTransformer> resultTransformers = super.createResultTransformers(route, request, response);
		resultTransformers.add(htmlGeneratorResultTransformer);
		resultTransformers.add(producerResultTransformer);
		return resultTransformers;
	}

}
