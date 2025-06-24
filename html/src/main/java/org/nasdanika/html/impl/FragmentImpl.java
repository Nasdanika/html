package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Producer;

import reactor.core.publisher.Mono;

class FragmentImpl implements Fragment {
	
	private List<Object> content = new ArrayList<>();
	private HTMLFactory factory; 
	
	FragmentImpl(HTMLFactory factory, Object... content) {
		this.factory = factory;
		content(content);
	}

	@Override
	public Fragment content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return this;
	}
	
	List<Object> getAllContent() {
		List<Object> ret = new ArrayList<>();
		for (Object c: content) {
			if (c instanceof FragmentImpl) {
				ret.addAll(((FragmentImpl) c).getAllContent());
			} else if (c!=null) {
				ret.add(c);
			}
		}
		return ret;
	}
	
	@Override
	public String produce(int indent) {
		StringBuilder sb = new StringBuilder();
		for (Object c: content) {
			try {
				sb.append(HTMLElementImpl.stringify(c, indent));
			} catch (Exception e) {
				sb.append(e);
			}
		}
		return sb.toString();
	}
		
	@Override
	public Mono<String> produceAsync(int indent) {
		List<Mono<Object>> contentProducers = content
			.stream()
			.map(Producer::of)
			.map(p -> p.produceAsync(indent))
			.toList();
				
		return Mono.zip(contentProducers, (Function<Object[], String>) elements -> combine(elements, indent));
	}
	
	private String combine(Object[] elements, int indent) {
		StringBuilder sb = new StringBuilder();
		for (Object o: elements) {
			sb.append(HTMLElementImpl.stringify(o, indent));
		}
		return sb.toString();			
	}		
	
	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}
	
	@Override
	public List<Object> getContent() {
		return content;
	}
	
	/**
	 * Fall-back to mitigate misses during refactoring.
	 */
	@Override
	public String toString() {
		return produce(0);
	}

	@Override
	public HTMLFactory getFactory() {
		return factory;
	}

}
