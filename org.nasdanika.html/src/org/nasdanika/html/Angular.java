package org.nasdanika.html;

/**
 * Angular.js interface.
 * @author Pavel Vlasov
 *
 */
public interface Angular<T> {

	/**
	 * Custom directive
	 * @param directive
	 * @param value
	 * @return
	 */
	T directive(String directive, Object expr);
	
	/**
	 * Adds AngularJS application attribute with blank name.
	 * @return
	 */
	T app();
	
	T app(Object expr);
	T bind(Object expr);
	T bindHtml(Object expr);
	T bindTemplate(Object expr);
	T blur(Object expr);
	T change(Object expr);
	T checked(Object expr);
	T clazz(Object expr);
	T classEven(Object expr);
	T classOdd(Object expr);
	T click(Object expr);
	T cloak();
	T controller(Object expr);
	T copy(Object expr);
	T csp();
	T cut(Object expr);
	T dblClick(Object expr);
	T disabled(Object expr);
	T focus(Object expr);
	T form(Object expr);
	T hide(Object expr);
	T href(Object expr);
	T if_(Object expr);
	T include(Object expr);
	T init(Object expr);
	T jq(Object expr);
	T keyDown(Object expr);
	T keyPress(Object expr);
	T keyUp(Object expr);
	T list();
	T model(Object expr);
	T modelOptions(Object expr);
	T mouseDown(Object expr);
	T mouseEenter(Object expr);
	T mouseLeave(Object expr);
	T mouseMove(Object expr);
	T mouseOver(Object expr);
	T mouseUp(Object expr);
	T nonBindable();
	T open(Object expr);
	T options(Object expr);
	T paste(Object expr);
	T readonly(Object expr);
	T repeat(Object expr);
	T selected(Object expr);
	T show(Object expr);
	T src(Object expr);
	T srcset(Object expr);
	T style(Object expr);
	T submit(Object expr);
	T switch_(Object expr);
	T value(Object expr);

	T switchWhen(Object expr);

	T switchDefault();
	
	T required(Object expr);
	T minLength(Object expr);
	T maxLength(Object expr);
	T pattern(Object expr);
	T trim(Object expr);

	
}
