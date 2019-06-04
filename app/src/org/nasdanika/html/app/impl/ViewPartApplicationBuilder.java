package org.nasdanika.html.app.impl;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Builds an application from {@link ViewPart}'s for the header, navigation bar, navigation panel, content panel, and footer.
 * This class can be customized at two levels - by overriding getXXXViewPart() and generateXXX() methods. The first method is 
 * suitable for radical changes in generating behavior, e.g. replacing action groups with JsTree. The second method
 * is more suitable for touching up the generated part, e.g. applying styles such as background.
 * @author Pavel Vlasov
 *
 */
public abstract class ViewPartApplicationBuilder implements ApplicationBuilder {
		
	protected abstract ViewPart getHeaderViewPart();
	protected abstract ViewPart getNavigationBarViewPart();
	protected abstract ViewPart getNavigationPanelViewPart();
	protected abstract ViewPart getContentPanelViewPart();
	protected abstract ViewPart getFooterViewPart();
	
	/**
	 * Creates a view generator to be used by view parts.
	 * @param bodyContentConsumer
	 * @return
	 */
	protected ViewGenerator createViewGenerator(
			Consumer<?> headContentConsumer, 
			Consumer<?> bodyContentConsumer,
			BiFunction<String, Object, String> resourceConsumer) {
		return new ViewGeneratorImpl(headContentConsumer, bodyContentConsumer, resourceConsumer);
	}	
	
	/**
	 * Creates a view generator, wires it to the page's head and body and then passes to view parts. 
	 */
	@Override
	public void build(Application application) {
		// A little trick to solve the chicken and egg problem of needing a view generator to create fragments to pass to the view generator.
		Fragment[] cf = { null, null };
		ViewGenerator viewGenerator = createViewGenerator(content-> cf[0].accept(content), content-> cf[1].accept(content), getResourceConsumer());
				
		Fragment headContentFragment = viewGenerator.getHTMLFactory().fragment();
		cf[0] = headContentFragment;
		application.getHTMLPage().head(headContentFragment);
		
		Fragment bodyContentFragment = viewGenerator.getHTMLFactory().fragment();
		cf[1] = bodyContentFragment;
		application.getHTMLPage().body(bodyContentFragment);
		
		application.header(generateHeader(viewGenerator));
		application.navigationBar(generateNavigationBar(viewGenerator));
		application.navigationPanel(generateNavigationPanel(viewGenerator));
		application.contentPanel(generateContentPanel(viewGenerator));
		application.footer(generateFooter(viewGenerator));
	}
	protected Object generateFooter(ViewGenerator viewGenerator) {
		return getFooterViewPart().generate(viewGenerator);
	}
	protected Object generateContentPanel(ViewGenerator viewGenerator) {
		return getContentPanelViewPart().generate(viewGenerator);
	}
	protected Object generateNavigationPanel(ViewGenerator viewGenerator) {
		return getNavigationPanelViewPart().generate(viewGenerator);
	}
	protected Object generateNavigationBar(ViewGenerator viewGenerator) {
		return getNavigationBarViewPart().generate(viewGenerator);
	}
	protected Object generateHeader(ViewGenerator viewGenerator) {
		return getHeaderViewPart().generate(viewGenerator);
	}

	/**
	 * Override to add support for resource consumption.
	 * This implementation returns a consumer which does nothing and returns null.
	 * @return
	 */
	protected BiFunction<String, Object, String> getResourceConsumer() {
		return (path, content) -> null;
	}
}
