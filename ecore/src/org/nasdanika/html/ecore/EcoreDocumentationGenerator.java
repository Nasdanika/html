package org.nasdanika.html.ecore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.ResourceConsumerStreamAdapter;
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
		EcoreViewActionAdapterFactory adapterFactory = new EcoreViewActionAdapterFactory(principalAction);		
		resourceSet.getAdapterFactories().add(adapterFactory);
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
	
	protected ViewGenerator createViewGenerator(
			Consumer<?> headContentConsumer,
			Consumer<?> bodyContentConsumer, 
			BiFunction<String, Object, String> resourceConsumer) {
		
		return new EcoreDocumentationViewGenerator(headContentConsumer, bodyContentConsumer, resourceConsumer);
	}	
	
	public void generate(File outputDirectory) {
		BiFunction<String, InputStream, String> streamConsumer = (path, contents) -> {
			File target = new File(outputDirectory, path.replace("/", File.separator));
			File parent = target.getParentFile();
			if (!parent.exists()) {
				if (!parent.mkdirs()) {
					System.err.println("Could not create "+parent.getAbsolutePath());
					return null;
				}
			}
			
			try (BufferedInputStream cin = new BufferedInputStream(contents); BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {
				int b;
				while ((b = cin.read()) != -1) {
					out.write(b);
				}
			} catch (IOException e) {
				System.err.println("Could not write to "+parent.getAbsolutePath()+": "+e);
				return null;
			}		
			return path;
		};
		generate(new ResourceConsumerStreamAdapter(streamConsumer));		
	}	
		
	public void generate(BiFunction<String, Object, String> resourceConsumer) {
		Application app = new EcoreDocumentationApplication(Theme.Litera, true);

		JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		ApplicationBuilder  applicationBuilder = new ActionApplicationBuilder(principalAction.getChildren().get(0)) {
			
			@Override
			protected ViewGenerator createViewGenerator(
					Consumer<?> headContentConsumer,
					Consumer<?> bodyContentConsumer, 
					BiFunction<String, Object, String> resourceConsumer) {
				
				return EcoreDocumentationGenerator.this.createViewGenerator(headContentConsumer, bodyContentConsumer, resourceConsumer);
			}
			
//			@Override
//			protected BiFunction<String, Object, String> getResourceConsumer() {
//				return resourceConsumer;
//			}
			
		};
		
		applicationBuilder.build(app);
		
		resourceConsumer.apply("index.html", app);				
		resourceConsumer.apply("doc/doc-summary", summary());
		
		for (Action action: principalAction.getChildren()) {
			generateActionContent(action, resourceConsumer);
		}
		
	}
	
	protected Object summary() {
		StringBuilder contentBuilder = new StringBuilder();
		ViewGenerator viewGenerator = new EcoreDocumentationViewGenerator(contentBuilder::append, contentBuilder::append, null);
		
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
		header.headerRow("Package", "Summary");
		TableBody body = table.body();
		principalAction.getChildren().forEach(child -> body.row(viewGenerator.link(child), child.getTooltip()));
		contentContainer.row().col(table);				
		
		contentBuilder.append(contentContainer.produce(4));
		return contentBuilder;
	}
	
	protected void generateActionContent(Action action, BiFunction<String, Object, String> resourceConsumer) {
		StringBuilder contentBuilder = new StringBuilder();
		ViewPart contentPanelViewPart = new ContentPanelViewPart(action, false); // Use adapter?
		ViewGenerator viewGenerator = new EcoreDocumentationViewGenerator(contentBuilder::append, contentBuilder::append, (path, content) -> resourceConsumer.apply("doc/" + path, content));
		contentBuilder.append(contentPanelViewPart.generate(viewGenerator, null));
		resourceConsumer.apply("doc/"+action.getId()+".html", contentBuilder.toString());		
		for (Action child: action.getChildren()) {
			if (child.isInRole(Role.NAVIGATION)) {
				generateActionContent(child, resourceConsumer);
			}
		}
	}
		
}

