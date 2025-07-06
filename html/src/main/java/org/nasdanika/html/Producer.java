package org.nasdanika.html;

import java.util.function.Supplier;

import reactor.core.publisher.Mono;

/**
 * Functional interface for producing/generating HTML.
 * @author Pavel Vlasov
 *
 */
public interface Producer<T> {
		
	/**
	 * Produces content.
	 * @param indent Indent. 
	 * @return Content. 
	 */
	default T produce(int indent) {
		return produceAsync(indent).block();
	}
	
	Mono<T> produceAsync(int indent);
	
	@SuppressWarnings("unchecked")
	static <T> Producer<T> of(T content) {
		if (content == null) {
			return null;
		}
		if (content instanceof Producer) {
			return (Producer<T>) content; 
		}
		return new Producer<T>() {

			@Override
			public Mono<T> produceAsync(int indent) {
				if (content instanceof Supplier) {
					return Mono.fromSupplier((Supplier<T>) content);
				}
				
				if (content instanceof Mono) {
					return (Mono<T>) content;
				}
				
				return Mono.just(content);
			}
			
			@Override
			public T produce(int indent) {
				if (content instanceof Supplier) {
					return ((Supplier<T>) content).get();
				}
				
				if (content instanceof Mono) {
					return ((Mono<T>) content).block();
				}
				
				return content;
			}
			
		};
	}
	
	/**
	 * One producer may delegate building of its contents to another producer.
	 * Optional operation.
	 * @param obj
	 */
	default void build(Object obj) {
		throw new UnsupportedOperationException();
	}

}
