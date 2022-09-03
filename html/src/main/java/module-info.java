import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.impl.DefaultHTMLFactory;

module org.nasdanika.html {
	
	requires transitive org.nasdanika.common;
	requires org.apache.commons.text;
	
	exports org.nasdanika.html;
	exports org.nasdanika.html.impl;
	
	provides HTMLFactory with DefaultHTMLFactory;
	
}