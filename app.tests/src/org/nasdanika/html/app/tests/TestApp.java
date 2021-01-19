package org.nasdanika.html.app.tests;

import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.factories.BootstrapContainerApplicationSupplierFactory;
import org.nasdanika.html.app.factories.ComposedLoader;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.bootstrap.factories.BootstrapLoader;
import org.nasdanika.html.bootstrap.factories.BootstrapPageSupplierFactory;
import org.nasdanika.html.factories.HTMLLoader;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestApp extends HTMLTestBase {
	
	@Test
	public void testLabel() throws Exception {
		Context context = Context.singleton("color", "success");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ComposedLoader loader = new ComposedLoader();
		Object labelFactory = loader.loadYaml(this.getClass().getResource("label-spec.yml"), monitor);
		
		Label label = Util.callSupplier(Util.<Label>asSupplierFactory(labelFactory).create(context), monitor);
		ViewGeneratorImpl viewGenerator = new ViewGeneratorImpl(context, null, null);
		writePage("app/label.html", "Label", viewGenerator.label(label));
	}
	
	@Test
	public void testHTMLPage() throws Exception {
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ObjectLoader loader = new HTMLLoader();
		Object pageFactory = loader.loadYaml(this.getClass().getResource("page-spec.yml"), monitor);
		
		HTMLPage htmlPage = Util.callSupplier(Util.<HTMLPage>asSupplierFactory(pageFactory).create(context), monitor);
		System.out.println(htmlPage.toString());
	}
	
	@Test
	public void testHTMLPageBody() throws Exception {
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ObjectLoader loader = new HTMLLoader();
		Object pageFactory = loader.loadYaml(this.getClass().getResource("page-body-spec.yml"), monitor);
		
		HTMLPage htmlPage = Util.callSupplier(Util.<HTMLPage>asSupplierFactory(pageFactory).create(context), monitor);
		System.out.println(htmlPage.toString());
	}
	
	@Test
	public void testAppearanceComposed() throws Exception {
		Context context = Context.singleton("color", "success");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ComposedLoader loader = new ComposedLoader();
		Object appearanceFactory = loader.loadYaml(this.getClass().getResource("bootstrap-appearance-spec.yml"), monitor);
		
		Consumer<Object> appearance = Util.callSupplier(Util.<Consumer<Object>>asSupplierFactory(appearanceFactory).create(context), monitor);
		Tag div = TagName.div.create("I'm customized");
		appearance.accept(div);
		writePage("app/appearance.html", "Appearance", div);
	}
		
	@Test
	public void testAppearance() throws Exception {
		Context context = Context.singleton("color", "success");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ObjectLoader loader = new BootstrapLoader();
		Object appearanceFactory = loader.loadYaml(this.getClass().getResource("appearance-spec.yml"), monitor);
		
		Consumer<Object> appearance = Util.callSupplier(Util.<Consumer<Object>>asSupplierFactory(appearanceFactory).create(context), monitor);
		Tag div = TagName.div.create("I'm styled");		
		appearance.accept(div);
		
		BootstrapPageSupplierFactory pageFactory = (BootstrapPageSupplierFactory) loader.loadYaml(HTMLTestBase.class.getResource("bootstrap-page-spec.yml"), monitor);
		HTMLPage bootstrapPage = Util.callSupplier(pageFactory.create(Context.EMPTY_CONTEXT), monitor); 

		bootstrapPage.title("Appearance demo");
		bootstrapPage.body(div);
		
		System.out.println(bootstrapPage);
	}	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testLabelSupplierFactory() throws Exception {
		ComposedLoader loader = new ComposedLoader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object sf = loader.loadYaml(this.getClass().getResource("label-supplier-factory-spec.yml"), monitor);
		SupplierFactory<Label> sfa = Adaptable.adaptTo(sf, SupplierFactory.class);
		Context context = Context.singleton("color", "success");
		Label label = Util.callSupplier(sfa.create(context), monitor);
		
		ViewGenerator viewGenerator = new ViewGeneratorImpl(null, null);
		
		writePage("app/label-supplier-factory.html", "Label Supplier Factory", viewGenerator.label(label));
	}
	
	@Test
	public void testAction() throws Exception {
		Context context = Context.singleton("color", "success");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		ComposedLoader loader = new ComposedLoader();
		Object actionFactory = loader.loadYaml(this.getClass().getResource("app/root.yml"), monitor);
		
		Action action = Util.callSupplier(Util.<Action>asSupplierFactory(actionFactory).create(context), monitor);
		
	}
	
	@Test
	public void testBootstrapActionApplication() throws Exception {
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		MutableContext context = Context.singleton("color", "success").fork();
		ViewPart viewPart = (v,p) -> "Dynamic content obtained from the context";
		context.put("view-part", viewPart);
		String base = "tmp://base/";
		context.put(Context.BASE_URI_PROPERTY, base);
		
		ComposedLoader loader = new ComposedLoader();
		Object actionFactory = loader.loadYaml(this.getClass().getResource("app/root.yml"), monitor);
		Action action = Util.callSupplier(Util.<Action>asSupplierFactory(actionFactory).create(context), monitor);
		for (Theme theme: Theme.values()) {
			writeAction(context, base, theme, action, action.getChildren().get(0), action, monitor);
		}
	}
	
	private void writeAction(Context context, String base, Theme theme, Action root, Action principal, Action active, ProgressMonitor monitor) throws Exception {
		MutableContext actionContext = context.fork();		
		if (!active.isEmpty() && active.getActivator() instanceof NavigationActionActivator) {
			NavigationActionActivator activator = (NavigationActionActivator) active.getActivator();
			String actionURI = activator.getUrl(null);
			actionContext.put(Context.BASE_URI_PROPERTY, actionURI);
			ApplicationBuilder builder = new ActionApplicationBuilder(actionContext, root, principal, active);
			String themePath = theme == Theme.Default ? "bootstrap" : theme.name().toLowerCase();
			String resourceName = "org/nasdanika/html/app/templates/" + themePath + "/" + (theme == Theme.Slate ? "primary" : "dark") + ".yml";
			System.out.println(resourceName);
			Application app = Util.callSupplier(((BootstrapContainerApplicationSupplierFactory) composedLoader.loadYaml(getClass().getClassLoader().getResource(resourceName), monitor)).create(actionContext), monitor);
			builder.build(app, monitor);
			// app.getHTMLPage().head("\n<!-- my comment -->\n");

			String url = ((NavigationActionActivator) active.getActivator()).getUrl(null);
			if (url != null && url.startsWith(base)) {			
				String path = "app/" + themePath + "/" + url.substring(base.length());
				writeFile(path, app.toString());
			}			
		}		
		for (Action child: active.getChildren()) {
			writeAction(actionContext, base, theme, root, principal, child, monitor);
		}
	}
	
	@Test
	public void testURI() throws Exception {
		URI base = URI.createURI("tmp://base/maze/case/param/pompom/test.html");
		URI uri = URI.createURI("tmp://base/maze/case/purum/index.html");
		System.out.println(uri.deresolve(base, true, true, true));
		
	}
	
		
}
