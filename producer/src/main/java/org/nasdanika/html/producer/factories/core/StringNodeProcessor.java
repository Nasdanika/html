package org.nasdanika.html.producer.factories.core;

import org.nasdanika.html.Producer;

import reactor.core.publisher.Mono;

public class StringNodeProcessor implements Producer<Object> {
	
	private org.nasdanika.ncore.String str;

	public StringNodeProcessor(org.nasdanika.ncore.String str) {
		this.str = str;
	}
	
	@Override
	public Object produce(int indent) {
		return str.getValue();
	}

	@Override
	public Mono<Object> produceAsync(int indent) {
		return Mono.just(produce(indent));
	}		
	
}
