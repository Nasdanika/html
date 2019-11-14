package org.nasdanika.html.bootstrap;

/**
 * Bootstrap spacing - padding and margins - https://getbootstrap.com/docs/4.1/utilities/spacing/.
 * Integer space can take values from 0 to 5.
 * @author Pavel Vlasov
 *
 */
public interface Spacing<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Spacing<B> top(Breakpoint breakpoint, Size size);
	
	Spacing<B> bottom(Breakpoint breakpoint, Size size);
	
	Spacing<B> left(Breakpoint breakpoint, Size size);
	
	Spacing<B> right(Breakpoint breakpoint, Size size);
	
	Spacing<B> x(Breakpoint breakpoint, Size size);
	
	Spacing<B> y(Breakpoint breakpoint, Size size);
	
	Spacing<B> all(Breakpoint breakpoint, Size size);
	
}