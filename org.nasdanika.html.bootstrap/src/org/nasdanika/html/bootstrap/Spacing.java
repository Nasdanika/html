package org.nasdanika.html.bootstrap;

/**
 * Bootstrap spacing - padding and margins - https://getbootstrap.com/docs/4.1/utilities/spacing/.
 * Integer space can take values from 0 to 5.
 * @author Pavel Vlasov
 *
 */
public interface Spacing<B extends BootstrapElement<?, ?>> {
	
	B toBootstrapElement();
	
	Spacing<B> top(int space);
	
	Spacing<B> top(DeviceSize size, int space);
	
	Spacing<B> topAuto();
	
	Spacing<B> topAuto(DeviceSize size);
	
	// ---
	
	Spacing<B> bottom(int space);
	
	Spacing<B> bottom(DeviceSize size, int space);
	
	Spacing<B> bottomAuto();
	
	Spacing<B> bottomAuto(DeviceSize size);
	
	// ---
	
	Spacing<B> left(int space);
	
	Spacing<B> left(DeviceSize size, int space);
	
	Spacing<B> leftAuto();
	
	Spacing<B> leftAuto(DeviceSize size);
	
	// ---
	
	Spacing<B> right(int space);
	
	Spacing<B> right(DeviceSize size, int space);
	
	Spacing<B> rightAuto();
	
	Spacing<B> rightAuto(DeviceSize size);
	
	// ---
	
	Spacing<B> x(int space);
	
	Spacing<B> x(DeviceSize size, int space);
	
	Spacing<B> xAuto();
	
	Spacing<B> xAuto(DeviceSize size);
	
	// ---
	
	Spacing<B> y(int space);
	
	Spacing<B> y(DeviceSize size, int space);
	
	Spacing<B> yAuto();
	
	Spacing<B> yAuto(DeviceSize size);	
	
	
}