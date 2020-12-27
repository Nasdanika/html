package org.nasdanika.html.app.tests;

import static org.junit.Assert.fail;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.junit.Assert;
import org.nasdanika.common.Command;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Select;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.factories.ComposedLoader;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.bootstrap.impl.BootstrapPageFactory;
import org.nasdanika.html.echarts.EChartsFactory;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

public class HTMLTestBase {
	
	protected ObjectLoader composedLoader = new ComposedLoader();
	
	/**
	 * Writes content to a bootstrap/fontawesome/jstree/knockout page and to a file under repository site.
	 * @param path
	 * @param title
	 * @param pageConsumer for callback to wire {@link ViewGenerator} to page header and body consumers.
	 * @param content
	 * @throws Exception
	 */
	protected void writePage(String path, String title, Object... content) throws Exception {				
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		BootstrapPageFactory pageFactory = (BootstrapPageFactory) composedLoader.loadYaml(HTMLTestBase.class.getResource("bootstrap-page-spec.yml"), progressMonitor);
		HTMLPage bootstrapPage = pageFactory.create(Context.EMPTY_CONTEXT); 
		FontAwesomeFactory.INSTANCE.cdn(bootstrapPage);
		JsTreeFactory.INSTANCE.cdn(bootstrapPage);
		KnockoutFactory.INSTANCE.cdn(bootstrapPage);
		EChartsFactory.INSTANCE.cdn(bootstrapPage);
		// More declarations as needed.		
		bootstrapPage.title(title);
		ViewGenerator viewGenerator = new ViewGeneratorImpl(bootstrapPage::head, bootstrapPage::body); 
		bootstrapPage.body(viewGenerator.processViewPart(content, progressMonitor));			
		writeFile(path, bootstrapPage.toString());
	}
	
	/**
	 * Writes text file.
	 * @param path
	 * @param content
	 */
	protected void writeFile(String path, String content) throws IOException {
		File target = new File(("target/test-dumps/"+path).replace("/", File.separator));
		File parent = target.getParentFile();
		if (!parent.exists()) {
			if (!parent.mkdirs()) {
				Assert.fail("Cannot create "+parent.getAbsolutePath());
			}
		}
		
		System.out.println("Writing to "+target.getAbsolutePath());
		try (Writer writer = new FileWriter(target)) {
			writer.write(content);
		}		
		
	}

	/**
	 * Writes binary file.
	 * @param path
	 * @param content
	 */
	protected void writeFile(String path, InputStream content) throws IOException {
		File target = new File(("target/test-dumps/"+path).replace("/", File.separator));
		File parent = target.getParentFile();
		if (!parent.exists()) {
			if (!parent.mkdirs()) {
				Assert.fail("Cannot create "+parent.getAbsolutePath());
			}
		}
		
		System.out.println("Writing to "+target.getAbsolutePath());
		try (BufferedInputStream cin = new BufferedInputStream(content); BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target))) {
			int b;
			while ((b = cin.read()) != -1) {
				out.write(b);
			}
		}		
		
	}

	/**
	 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	protected static InputStream callSupplier(Context context, ProgressMonitor monitor, Object component) throws Exception {
		try (Supplier<InputStream> supplier = Util.asInputStreamSupplierFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				InputStream result = supplier.splitAndExecute(progressMonitor);
				supplier.splitAndCommit(progressMonitor);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (supplier.splitAndRollback(progressMonitor)) {
					fail("Exception " + e + ", rollback successful");
				} else {
					fail("Exception " + e + ", rollback failed");						
				}
				throw new NasdanikaException("Never get here");
			}
		}
	}

	/**
	 * Executes full consumer lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	protected static void callConsumer(Context context, ProgressMonitor monitor, Object component, BinaryEntityContainer container) throws Exception {
		try (Consumer<BinaryEntityContainer> consumer = Util.asConsumerFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = consumer.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				consumer.splitAndExecute(container, progressMonitor);
				consumer.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (consumer.splitAndRollback(progressMonitor)) {
					fail("Exception " + e + ", rollback successful");
				} else {
					fail("Exception " + e + ", rollback failed");						
				}
			}
		}
	}

	/**
	 * Executes full command lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	protected static void callCommand(Context context, ProgressMonitor monitor, Object component) throws Exception {
		try (Command command = Util.asCommandFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = command.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				command.splitAndExecute(progressMonitor);
				command.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (command.splitAndRollback(progressMonitor)) {
					fail("Exception " + e + ", rollback successful");
				} else {
					fail("Exception " + e + ", rollback failed");						
				}
			}
		}
	}	
	
}
