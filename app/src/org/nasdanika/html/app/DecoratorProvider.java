package org.nasdanika.html.app;

/**
 * DecoratorProvider service can registered with {@link ViewGenerator} to be used by, say, {@link ViewPart}s to
 * decorate view elements.  
 * @author Pavel
 *
 */
public interface DecoratorProvider {
	
	Decorator getDecorator(String name);

}
