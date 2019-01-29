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
	Float<B> right(DeviceSize size);
	
	Float<B> left();
	Float<B> left(DeviceSize size);
	
	Float<B> none();
	Float<B> none(DeviceSize size);
	
}