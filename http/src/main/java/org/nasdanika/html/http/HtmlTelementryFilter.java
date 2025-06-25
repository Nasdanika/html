package org.nasdanika.html.http;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.nasdanika.html.Producer;
import org.nasdanika.http.TelemetryFilter;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapPropagator;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

/**
 * Provides wrap producer methods
 */
public class HtmlTelementryFilter extends TelemetryFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlTelementryFilter.class);		

	public HtmlTelementryFilter(
			Tracer tracer, 
			TextMapPropagator propagator, 
			BiConsumer<String, Long> durationConsumer,
			boolean resolveRemoteHostName) {
		
		super(tracer, propagator, durationConsumer, resolveRemoteHostName);
	}
	
	/**
	 * Wraps a handler which returns {@link Producer}
	 * @param producerHandler
	 * @return
	 */
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapProducerHandler(BiFunction<HttpServerRequest, HttpServerResponse, Producer<?>> producerHandler) {
		return (request, response) -> {
			LOGGER.info("Processing producer request [{}] {}", request.method().name(), request.uri());
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				Producer<?> result = producerHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				if (result == null) {
					return response.send();
				}
				
				Mono<?> producerResult = result.produceAsync(0);
				if (producerResult == null) {
					return response.send();
				}
				
				Mono<String> prsm = producerResult.map(pr -> pr == null ? null : pr.toString());
				return response.sendString(prsm);
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				LOGGER.error("Error processing producer request [" + request.method().name() + "] " + request.uri() + ": " + e, e);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
		
}
