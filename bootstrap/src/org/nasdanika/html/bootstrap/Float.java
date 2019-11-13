package org.nasdanika.html.bootstrap;

/**
 * Bootstrap spacing - padding and margins - https://getbootstrap.com/docs/4.1/utilities/spacing/.
 * Integer space can take values from 0 to 5.
 * @author Pavel Vlasov
 *
 */
public interface Float<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Float<B> right();
	Float<B> right(Breakpoint size);
	
	Float<B> left();
	Float<B> left(Breakpoint size);
	
	Float<B> none();
	Float<B> none(Breakpoint size);
	
}