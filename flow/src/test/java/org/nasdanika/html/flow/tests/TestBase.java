package org.nasdanika.html.flow.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.nasdanika.common.Command;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
//import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.flow.util.FlowYamlLoadingExecutionParticipant;
import org.nasdanika.html.model.app.gen.AppGenYamlLoadingExecutionParticipant;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestBase {
	
	public static void dumpToYaml(EObject eObject) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4); 
		new Yaml(dumperOptions).dump(dump(eObject), new OutputStreamWriter(System.out));
	}
	
	/**
	 * Dumps {@link EObject} to {@link Map} for to further dump to YAML. 
	 * Outputs class, path, URI, and name.
	 * @param eObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> dump(EObject eObject) {
		Map<String,Object> ret = new LinkedHashMap<>(); 
		ret.put("class", eObject.eClass().getName());
		for (EReference ref: eObject.eClass().getEAllReferences()) {
			if (ref. isContainment()) { 
				if (ref.isMany()) {
					Collection<Map<String,Object>> elements = new ArrayList<>(); 
					for (EObject el: ((Collection<EObject>) eObject.eGet(ref))) { 
						if (el != null) {
							elements.add(dump(el));
						}
					}
					if (!elements.isEmpty()) {
						ret.put(ref.getName(), elements);
					}
				} else {
					EObject val = (EObject) eObject.eGet(ref); 
					if (val != null) {
						ret.put(ref.getName(), dump(val));
					}
				}
			}
		}
		return ret;
	}
	
	// TODO - supplier, consumer, command tests - chaining with then

	protected EObject loadObject(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		Class<? extends TestBase> clazz = TestBase.this.getClass();
		URL resourceURL = clazz.getResource(resource);
		if (resourceURL == null) {
			throw new IllegalArgumentException("Classloader resource not found: " + resource + " by " + clazz); 
		}
		URI resourceURI = URI.createURI(resourceURL.toString());

		class ObjectSupplier extends AppGenYamlLoadingExecutionParticipant implements Supplier<EObject> {

			public ObjectSupplier(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {
				return Collections.singleton(resourceURI);
			}

			@Override
			public EObject execute(ProgressMonitor progressMonitor) throws Exception {				
				return resourceSet.getResource(resourceURI, false).getContents().iterator().next();
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			return Util.call(new ObjectSupplier(context), progressMonitor, diagnosticConsumer);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	// Generating container
	protected Diagnostic generate(
			EObject eObject, 
			BinaryEntityContainer container,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			ConsumerFactory<BinaryEntityContainer> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(eObject, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory");
			return Util.call(consumerFactory.create(context), container, progressMonitor);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Diagnostic generate(
			String resource, 
			BinaryEntityContainer container,
			Context modelContext,
			Context generationContext,			
			ProgressMonitor progressMonitor,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return generate(eObject, container, generationContext, progressMonitor);
	}
	
	// Loading Object	
	protected Object load(
			EObject eObject, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			SupplierFactory<Object> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eObject, Object.class), "Cannot adapt to SupplierFactory");
			return Util.call(supplierFactory.create(context), progressMonitor, diagnosticConsumer);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Object load(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context modelContext,
			Context generationContext,
			ProgressMonitor progressMonitor) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return load(eObject, diagnosticConsumer, generationContext, progressMonitor);
	}
	
	// Consuming
	protected Diagnostic consume(
			EObject eObject, 
			Object arg,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			ConsumerFactory<Object> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(eObject, Object.class), "Cannot adapt to ConsumerFactory");
			return Util.call(consumerFactory.create(context), arg, progressMonitor);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Diagnostic consume(
			String resource, 
			Object arg,
			Context modelContext,
			Context generationContext,			
			ProgressMonitor progressMonitor,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return consume(eObject, arg, generationContext, progressMonitor);
	}
		
	public static void copy(File source, File target, boolean cleanTarget, BiConsumer<File,File> listener) throws IOException {
		if (cleanTarget && target.isDirectory()) {
			delete(target.listFiles());
		}
		if (source.isDirectory()) {
			target.mkdirs();
			for (File sc: source.listFiles()) {
				copy(sc, new File(target, sc.getName()), false, listener);
			}
		} else if (source.isFile()) {
			Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);			
			if (listener != null) {
				listener.accept(source, target);
			}
		}
	}

	public static void delete(File... files) {
		for (File file: files) {
			if (file.exists()) {
				if (file.isDirectory()) {
					delete(file.listFiles());
				}
				file.delete();
			}
		}
	}	
	
	// --- From flow tests ---
	
	/**
	 * Loads resource and passes its root to the consumer.
	 * @param resource Resource location relative to the test class.
	 * @param consumer Consumer of the resource root - typically with assertions.
	 * @param diagnosticConsumer Consumer of diagnostic to validate status.
	 * @throws Exception
	 */
	protected void load(
			String resource, 
			Consumer<EObject> consumer, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		load(resource, Context.EMPTY_CONTEXT, consumer, diagnosticConsumer, progressMonitor);
	}

	/**
	 * Loads resource and passes its root to the consumer.
	 * @param resource Resource location relative to the test class.
	 * @param consumer Consumer of the resource root - typically with assertions.
	 * @param diagnosticConsumer Consumer of diagnostic to validate status.
	 * @throws Exception
	 */
	protected void load(
			String resource,
			Context context, 
			Consumer<EObject> consumer, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) throws Exception {	

		URI resourceURI = URI.createURI(TestBase.this.getClass().getResource(resource).toString());

		class TestCommand extends FlowYamlLoadingExecutionParticipant implements Command {

			public TestCommand(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {				
				return Collections.singleton(resourceURI);
			}

			@Override
			protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
				ResourceSet rs = super.createResourceSet(progressMonitor);
				rs.getURIConverter().getURIHandlers().add(new URIHandlerImpl() {

					@Override
					public boolean canHandle(URI uri) {
						return uri != null && "classpath".equals(uri.scheme());
					}

					@Override
					public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
						return getClass().getClassLoader().getResourceAsStream(uri.path());
					}
					
				});
				return rs;
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) throws Exception {
				if (consumer != null) {
					consumer.accept(resourceSet.getResource(resourceURI, false).getContents().get(0));
				}
			}
			
			@Override
			protected boolean isDiagnoseModel() {
				return false;
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(context), progressMonitor);
			if (diagnosticConsumer != null) {
				diagnosticConsumer.accept(diagnostic);
			}
			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
		
	}

}
