package org.nasdanika.html;

public interface Button extends UIElement<Button>, Dropdown<Button>, Container<Button> {
	
	enum Type { BUTTON, SUBMIT, RESET }
	
	Button type(Type type);
	
	Button style(Bootstrap.Style style);
	
	Button size(Bootstrap.Size size);
	
	Button block(boolean block);
	
	Button block();
	
	Button active(boolean active);
	
	Button active();
	
	Button disabled(boolean disabled);
	
	Button disabled();
	
	Button split(boolean split);
	
	Button split();
	
	Button dropup(boolean dropup);
	
	Button dropup();
	
	/**
	 * If forEachExpression is not blank, <code>data-bind="foreach: &lt;forEachExpression&gt;"</code> attribute is added to <code>&lt;ul&gt;</code>
	 * tag in the button group.
	 * @param forEach
	 * @return
	 */
	Button koItemForEach(Object forEachExpression);

}
