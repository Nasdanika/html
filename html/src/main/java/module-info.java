import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.impl.DefaultHTMLFactory;

module org.nasdanika.html {
	
	requires transitive org.nasdanika.common;
	requires org.apache.commons.text;
	requires transitive reactor.core;
	requires transitive org.reactivestreams;
	
	exports org.nasdanika.html;
	exports org.nasdanika.html.impl;
	
	provides HTMLFactory with DefaultHTMLFactory;
	
}