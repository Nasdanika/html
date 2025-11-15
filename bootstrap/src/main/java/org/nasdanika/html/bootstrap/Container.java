package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

/**
 * Bootstrap container.
 * @author Pavel Vlasov
 *
 */
public interface Container extends BootstrapElement<Tag,Container> {
	
	interface Row extends BootstrapElement<Tag,Row> {
		
		interface Col extends BootstrapElement<Tag,Col>, org.nasdanika.html.Container<Col> {
			
			Col width(Breakpoint breakpoint, Size width);
			
		}
		
		default Col col(Object... content) {
			return col(TagName.div, content);
		}
		
		Col col(TagName tagName, Object... content);		
		
	}
	
	Row row();
	
}
