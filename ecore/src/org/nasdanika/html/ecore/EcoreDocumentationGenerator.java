package org.nasdanika.html.ecore;

import java.util.function.Consumer;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SimpleMutableContext;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.app.viewparts.ContentPanelViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;

public class EcoreDocumentationGenerator {

	private ActionImpl principalAction;
	private GenModelResourceSet resourceSet;
	private ActionImpl rootAction;

	/**
	 * Generates documentation
	 * @param title
	 * @param description
	 * @param resourceLocator Locator of localized resources.
	 */
	public EcoreDocumentationGenerator(String title, String description) {
		rootAction = new ActionImpl();
		rootAction.setText(title);
		rootAction.setDescription(description);
		rootAction.setActivator(new NavigationActionActivator() {
			
			@Override
			public String getUrl() {
				return "#content/doc-summary";
			}
		});
		
		principalAction = new ActionImpl();
		principalAction.setParent(rootAction);
		rootAction.getChildren().add(principalAction);
		
		resourceSet = new GenModelResourceSet();		
		resourceSet.getAdapterFactories().add(createAdapterFactory());
	}

	/**
	 * Override to customize the adapter factory.
	 * @return
	 */
	protected EcoreViewActionAdapterFactory createAdapterFactory() {
		return new EcoreViewActionAdapterFactory(principalAction);
	}
	
	public void loadGenModel(String nsURI) {
		Resource resource = resourceSet.loadGenModel(nsURI);
		for (EObject contents: resource.getContents()) {
			if (contents instanceof GenModel) {
				for (GenPackage genPackage: ((GenModel) contents).getGenPackages()) {
					principalAction.getChildren().add(EObjectAdaptable.adaptTo(genPackage.getEcorePackage(), ViewAction.class));
				}
			}
		}
	}
	
	protected ViewGenerator createViewGenerator(Context context, Consumer<?> headContentConsumer, Consumer<?> bodyContentConsumer) {		
		return new EcoreDocumentationViewGenerator(context, headContentConsumer, bodyContentConsumer);
	}	
		
	public void generate(org.nasdanika.common.resources.Container<Object> resourceConsumer, ProgressMonitor progressMonitor) {		
		Application app = new EcoreDocumentationApplication(Theme.Litera, true);

		JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		MutableContext context = new SimpleMutableContext();
		context.register(org.nasdanika.common.resources.Container.class, resourceConsumer);
		
		int totalWork = (principalAction.getChildren().size() + 2) * 100;
		progressMonitor.setWorkRemained(totalWork);
	
		MutableContext docContext = new SimpleMutableContext();
		docContext.register(org.nasdanika.common.resources.Container.class, resourceConsumer.getContainer("doc"));
		docContext.put("image-path", "doc/");
		
		ApplicationBuilder  applicationBuilder = new ActionApplicationBuilder(principalAction.getChildren().get(0)) {
			
			@Override
			protected ViewGenerator createViewGenerator(Consumer<?> headContentConsumer, Consumer<?> bodyContentConsumer) {				
				return EcoreDocumentationGenerator.this.createViewGenerator(docContext, headContentConsumer, bodyContentConsumer);
			}
			
		};
		
		applicationBuilder.build(app, progressMonitor);
		
		try (ProgressMonitor im = progressMonitor.split("Generating index.html", 100)) {
			resourceConsumer.getFile("index.html").setContents(app, im);			
		}
		try (ProgressMonitor sm = progressMonitor.split("Generating summary", 100)) {
			resourceConsumer.getFile("doc/doc-summary").setContents(summary(docContext), sm);			
		}
		for (Action action: principalAction.getChildren()) {
			generateActionContent(action, docContext, progressMonitor);
		}
		
	}
	
	protected Object summary(Context context) {
		StringBuilder contentBuilder = new StringBuilder();
		ViewGenerator viewGenerator = new EcoreDocumentationViewGenerator(context, contentBuilder::append, contentBuilder::append);
		
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);		
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		contentContainer.row().col("<H2>"+rootAction.getText()+"</H2>");
		String description = rootAction.getDescription();
		if (!Util.isBlank(description)) {
			contentContainer.row().col(description);
		}
				
		Table table = bootstrapFactory.table().bordered();
		TableHeader header = table.header();
		// TODO - localization.
		header.headerRow("Package", "Summary");
		TableBody body = table.body();
		principalAction.getChildren().forEach(child -> body.row(viewGenerator.link(child), child.getTooltip()));
		contentContainer.row().col(table);				
		
		contentBuilder.append(contentContainer.produce(4));
		return contentBuilder;
	}
	
	protected void generateActionContent(Action action, Context context, ProgressMonitor progressMonitor) {
		try (ProgressMonitor am = progressMonitor.split("Generating action content for "+action.getText(), 100)) {
			am.setWorkRemained(100 + action.getChildren().size() * 100);
			StringBuilder contentBuilder = new StringBuilder();
			ViewPart contentPanelViewPart = new ContentPanelViewPart(action, false); // Use adapter?
			ViewGenerator viewGenerator = new EcoreDocumentationViewGenerator(context, contentBuilder::append, contentBuilder::append);
			contentBuilder.append(contentPanelViewPart.generate(viewGenerator, progressMonitor));
			@SuppressWarnings("unchecked")
			org.nasdanika.common.resources.Container<Object> container = context.get(org.nasdanika.common.resources.Container.class);
			container.getFile(action.getId()+".html").setContents(contentBuilder, am);
			for (Action child: action.getChildren()) {
				if (child.isInRole(Role.NAVIGATION)) {
					generateActionContent(child, context, am);
				}
			}
		}
	}
		
}

