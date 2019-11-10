package org.nasdanika.html.bootstrap;

/**
 * Bootstrap spacing - padding and margins - https://getbootstrap.com/docs/4.1/utilities/spacing/.
 * Integer space can take values from 0 to 5.
 * @author Pavel Vlasov
 *
 */
public interface Spacing<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Spacing<B> top(DeviceSize size, Size space);
	
	Spacing<B> bottom(DeviceSize size, Size space);
	
	Spacing<B> left(DeviceSize size, Size space);
	
	Spacing<B> right(DeviceSize size, Size space);
	
	Spacing<B> x(DeviceSize size, Size space);
	
	Spacing<B> y(DeviceSize size, Size space);
	
}