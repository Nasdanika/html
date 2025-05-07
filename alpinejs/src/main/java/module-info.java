import org.nasdanika.html.alpinejs.AlpineJsFactory;
import org.nasdanika.html.alpinejs.impl.DefaultAlpineJsFactory;

module org.nasdanika.html.alpinejs {
		
	requires transitive org.nasdanika.html;
	requires org.apache.commons.text;
	
	exports org.nasdanika.html.alpinejs;
	exports org.nasdanika.html.alpinejs.impl;
	
	provides AlpineJsFactory with DefaultAlpineJsFactory;
	
}
