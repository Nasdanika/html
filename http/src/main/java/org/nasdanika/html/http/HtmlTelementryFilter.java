package org.nasdanika.html.http;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.http.TelemetryFilter;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapPropagator;
import reactor.core.publisher.Flux;
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
		
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapByteArrayHandler(BiFunction<HttpServerRequest, HttpServerResponse, byte[]> byteArrayHandler) {
		return (request, response) -> {
			LOGGER.info("Processing byte array request [{}] {}", request.method().name(), request.uri());
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				byte[] result = byteArrayHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				return response.sendByteArray(Mono.just(result));
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				LOGGER.error("Error processing byte array request [" + request.method().name() + "] " + request.uri() + ": " + e, e);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
	
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapStringHandler(BiFunction<HttpServerRequest, HttpServerResponse, String> stringHandler) {
		return (request, response) -> {
			LOGGER.info("Processing string request [{}] {}", request.method().name(), request.uri());
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				String result = stringHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				return response.sendString(Mono.just(result));
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				LOGGER.error("Error processing string request [" + request.method().name() + "] " + request.uri() + ": " + e, e);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
	
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapJSONObjectHandler(BiFunction<HttpServerRequest, HttpServerResponse, JSONObject> jsonObjectHandler) {
		return (request, response) -> {
			LOGGER.info("Processing JSON object request [{}] {}", request.method().name(), request.uri());
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				JSONObject result = jsonObjectHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				return response.sendString(Mono.just(result.toString(2)));
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				LOGGER.error("Error processing JSON object request [" + request.method().name() + "] " + request.uri() + ": " + e, e);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
	
	public BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> wrapJSONArrayHandler(BiFunction<HttpServerRequest, HttpServerResponse, JSONArray> jsonArrayHandler) {
		return (request, response) -> {
			LOGGER.info("Processing JSON array request [{}] {}", request.method().name(), request.uri());
			Span span = buildSpan(request).startSpan();
			long start = System.currentTimeMillis();
			try (Scope scope = span.makeCurrent()) {
				JSONArray result = jsonArrayHandler.apply(request, response);
				if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
				}
				span.setStatus(StatusCode.OK);
				return response.sendString(Mono.just(result.toString(2)));
			} catch (Exception e) {
				span.recordException(e);
				span.setStatus(StatusCode.ERROR);
				LOGGER.error("Error processing JSON array request [" + request.method().name() + "] " + request.uri() + ": " + e, e);
				return response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR).send();				
			} finally {
				span.end();
			}			
		};				
	}	
	
	public <T> Mono<T> filter(HttpServerRequest request, Mono<T> publisher) {
		Span requestSpan = buildSpan(request).startSpan();
		long start = System.currentTimeMillis();
		return publisher
			.map(result -> {
	        	if (durationConsumer != null) {
					durationConsumer.accept(request.method().name() + " " + request.uri(), System.currentTimeMillis() - start);
	        	}
	        	requestSpan.setStatus(StatusCode.OK);
				LOGGER.info("[{}] {}", request.method().name(), request.uri());
				return result;
			})
			.onErrorMap(error -> {
				LOGGER.error("Error processing [" + request.method().name() + "] " + request.uri() + ": " + error, error);
	        	requestSpan.recordException(error);
	        	requestSpan.setStatus(StatusCode.ERROR);
				return error;
			})
    		.contextWrite(reactor.util.context.Context.of(Context.class, Context.current().with(requestSpan)))
			.doFinally(signal -> requestSpan.end());				
	}
		
	public <T> Flux<T> filter(HttpServerRequest request, Flux<T> publisher) {
		Span requestSpan = buildSpan(request).startSpan();
		return publisher
			.map(result -> {
	        	requestSpan.setStatus(StatusCode.OK);
				LOGGER.info("[{}] {}", request.method().name(), request.uri());
				return result;
			})
			.onErrorMap(error -> {
				LOGGER.error("Error processing [" + request.method().name() + "] " + request.uri() + ": " + error, error);
	        	requestSpan.recordException(error);
	        	requestSpan.setStatus(StatusCode.ERROR);
				return error;
			})
    		.contextWrite(reactor.util.context.Context.of(Context.class, Context.current().with(requestSpan)))
			.doFinally(signal -> requestSpan.end());				
	}	
	
		
}
