package org.nasdanika.html.app.impl;

import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
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
		
	protected Context context;
	protected abstract ViewPart getHeaderViewPart();
	protected abstract ViewPart getNavigationBarViewPart();
	protected abstract ViewPart getNavigationPanelViewPart();
	protected abstract ViewPart getContentPanelViewPart();
	protected abstract ViewPart getFooterViewPart();
	
	protected ViewPartApplicationBuilder(Context context) {
		this.context = context;
	}
	
	protected ViewPartApplicationBuilder() {
		this(Context.EMPTY_CONTEXT);
	}
			
	/**
	 * Creates a view generator to be used by view parts.
	 * @param bodyContentConsumer
	 * @return
	 */
	protected ViewGenerator createViewGenerator(Application application, Consumer<?> headContentConsumer, Consumer<?> bodyContentConsumer) {
		return new ViewGeneratorImpl(context, headContentConsumer, bodyContentConsumer);
	}	
	
	/**
	 * Creates a view generator, wires it to the page's head and body and then passes to view parts. 
	 */
	@Override
	public void build(Application application, ProgressMonitor progressMonitor) {
		// A little trick to solve the chicken and egg problem of needing a view generator to create fragments to pass to the view generator.
		Fragment[] cf = { null, null };
		ViewGenerator viewGenerator = createViewGenerator(application, content-> cf[0].accept(content), content-> cf[1].accept(content));
				
		Fragment headContentFragment = viewGenerator.get(HTMLFactory.class).fragment();
		cf[0] = headContentFragment;
		application.getHTMLPage().head(headContentFragment);
		
		Fragment bodyContentFragment = viewGenerator.get(HTMLFactory.class).fragment();
		cf[1] = bodyContentFragment;
		application.getHTMLPage().body(bodyContentFragment);
		
		application.header(generateHeader(viewGenerator, progressMonitor));
		application.navigationBar(generateNavigationBar(viewGenerator, progressMonitor));
		application.navigationPanel(generateNavigationPanel(viewGenerator, progressMonitor));
		application.contentPanel(generateContentPanel(viewGenerator, progressMonitor));
		application.footer(generateFooter(viewGenerator, progressMonitor));
	}
	protected Object generateFooter(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getFooterViewPart().generate(viewGenerator, progressMonitor);
	}
	protected Object generateContentPanel(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getContentPanelViewPart().generate(viewGenerator, progressMonitor);
	}
	protected Object generateNavigationPanel(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getNavigationPanelViewPart().generate(viewGenerator, progressMonitor);
	}
	protected Object generateNavigationBar(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getNavigationBarViewPart().generate(viewGenerator, progressMonitor);
	}
	protected Object generateHeader(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getHeaderViewPart().generate(viewGenerator, progressMonitor);
	}
}
