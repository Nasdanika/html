import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.impl.DefaultHTMLFactory;

module org.nasdanika.html {
	
	requires transitive org.nasdanika.common;
	
	exports org.nasdanika.html;
	exports org.nasdanika.html.impl;
	
	provides HTMLFactory with DefaultHTMLFactory;
	
}