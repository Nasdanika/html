package org.nasdanika.html.bootstrap;

/**
 * Bootstrap spacing - padding and margins - https://getbootstrap.com/docs/4.1/utilities/spacing/.
 * Integer space can take values from 0 to 5.
 * @author Pavel Vlasov
 *
 */
public interface Spacing<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Spacing<B> top(Breakpoint size, Size space);
	
	Spacing<B> bottom(Breakpoint size, Size space);
	
	Spacing<B> left(Breakpoint size, Size space);
	
	Spacing<B> right(Breakpoint size, Size space);
	
	Spacing<B> x(Breakpoint size, Size space);
	
	Spacing<B> y(Breakpoint size, Size space);
	
}