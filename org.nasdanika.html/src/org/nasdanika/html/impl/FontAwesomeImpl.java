package org.nasdanika.html.impl;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.FontAwesome;
import org.nasdanika.html.UIElement;

class FontAwesomeImpl<T extends UIElement<?>> implements FontAwesome<T>, AutoCloseable {
	/**
	 * 
	 */
	private final T target;

	/**
	 * @param uiElementImpl
	 */
	FontAwesomeImpl(T uiElementImpl) {
		target = uiElementImpl;
	}

	@Override
	public T getTarget() {
		return target;
	}
	
	@Override
	public FontAwesome<T> custom(String custom) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+custom);
		return this;
	}	

	@Override
	public FontAwesome<T> brand(Brand brand) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+brand.literal);
		return this;
	}

	@Override
	public FontAwesome<T> chart(Chart chart) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+chart.literal);
		return this;
	}

	@Override
	public FontAwesome<T> currency(Currency currency) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+currency.literal);
		return this;
	}

	@Override
	public FontAwesome<T> directional(Directional directional) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+directional.literal);
		return this;
	}

	@Override
	public FontAwesome<T> fileType(FileType fileType) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+fileType.literal);
		return this;
	}

	@Override
	public FontAwesome<T> formControl(FormControl formControl) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+formControl.literal);
		return this;
	}

	@Override
	public FontAwesome<T> medical(Medical medical) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+medical.literal);
		return this;
	}

	@Override
	public FontAwesome<T> payment(Payment payment) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+payment.literal);
		return this;
	}

	@Override
	public FontAwesome<T> spinner(Spinner spinner) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+spinner.literal);
		return this;
	}

	@Override
	public FontAwesome<T> videoPlayer(VideoPlayer videoPlayer) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+videoPlayer.literal);
		return this;
	}

	@Override
	public FontAwesome<T> webApplication(WebApplication webApplication) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+webApplication.literal);
		return this;
	}
	

	@Override
	public FontAwesome<T> hand(FontAwesome.Hand hand) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+hand.literal);
		return this;
	}

	@Override
	public FontAwesome<T> transportation(Transportation transportation) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+transportation.literal);
		return this;
	}

	@Override
	public FontAwesome<T> gender(Gender gender) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+gender.literal);
		return this;
	}

	@Override
	public FontAwesome<T> textEditor(TextEditor textEditor) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+textEditor.literal);
		return this;
	}
	

	@Override
	public FontAwesome<T> fixedWidth() {
		getTarget().addClass("fa-fw");
		return this;
	}

	@Override
	public FontAwesome<T> li() {
		getTarget().addClass("fa-li");
		return this;
	}

	@Override
	public FontAwesome<T> ul() {
		getTarget().addClass("fa-ul");
		return this;
	}

	@Override
	public FontAwesome<T> spin() {
		getTarget().addClass("fa-spin");
		return this;
	}

	@Override
	public FontAwesome<T> pullLeft() {
		getTarget().addClass("pull-left", "fa-border");
		return this;
	}

	@Override
	public FontAwesome<T> pullRight() {
		getTarget().addClass("pull-right", "fa-border");
		return this;
	}

	@Override
	public FontAwesome<T> rotate(Rotate rotate) {
		switch (rotate) {
		case R180:
			getTarget().addClass("fa-rotate-180");
			break;
		case R270:
			getTarget().addClass("fa-rotate-270");
			break;
		case R90:
			getTarget().addClass("fa-rotate-90");
			break;
		default:
			break;
		
		}
		return this;
	}

	@Override
	public FontAwesome<T> flip(Flip flip) {
		getTarget().addClass("fa-flip-"+flip.name());
		return this;
	}

	@Override
	public FontAwesome<T> size(Size size) {
		size(size, target);
		return this;
	}

	static void size(Size size, UIElement<?> target) {
		switch (size) {
		case large:
			target.addClass("fa-lg");
			break;
		case x2:
			target.addClass("fa-2x");
			break;
		case x3:
			target.addClass("fa-3x");
			break;
		case x4:
			target.addClass("fa-4x");
			break;
		case x5:
			target.addClass("fa-5x");
			break;
		default:
			throw new IllegalArgumentException("Unexpected size: "+size);	
		}
	}

	@Override
	public String toString() {
		return getTarget().toString();
	}

	@Override
	public void close() throws Exception {
		getTarget().close();		
	}

	@Override
	public FontAwesome<T> style(Bootstrap.Style style) {
		target.addClass("text-"+style.name().toLowerCase());
		return this;
	}

	@Override
	public FontAwesome<T> accessibility(Accessibility accessibility) {
		getTarget().addClass("fa");
		getTarget().addClass("fa-"+accessibility.literal);
		return this;
	}
	
}