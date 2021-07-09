package org.nasdanika.html.bootstrap;

/**
 * @author Pavel Vlasov
 *
 */
public interface Float<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Float<B> right();
	Float<B> right(Breakpoint breakpoint);
	
	Float<B> left();
	Float<B> left(Breakpoint breakpoint);
	
	Float<B> none();
	Float<B> none(Breakpoint breakpoint);
	
}