package org.nasdanika.html.app.tests;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionFactory;
import org.nasdanika.html.app.impl.BootstrapContainerApplicationFactory;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestApp extends HTMLTestBase {
	
	@Test
	public void testLabel() throws Exception {
		ViewGenerator viewGenerator = new ViewGeneratorImpl(null, null);
		viewGenerator.put("color", "SUCCESS");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Label label = (Label) viewGenerator.loadYaml(this.getClass().getResource("label-spec.yml"), monitor);
		
		writePage("app/label.html", "Label", viewGenerator.label(label));
	}
	
	@Test
	public void testBootstrapActionApplication() throws Exception {
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Context context = Context.singleton("color", "SUCCESS");
		Application app = ((BootstrapContainerApplicationFactory) composedLoader.loadYaml(getClass().getResource("application-spec.yml"), monitor)).create(context);
		
		Action action = ((ActionFactory) composedLoader.loadYaml(this.getClass().getResource("action-spec.yml"), monitor)).create(context);

		ApplicationBuilder builder = new ActionApplicationBuilder(context, action.getChildren().get(0).getChildren().get(0));
		builder.build(app, monitor);
		
		writeFile("app/app.html", app.toString());
	}
		
}
