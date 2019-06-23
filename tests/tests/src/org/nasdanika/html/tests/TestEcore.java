package org.nasdanika.html.tests;

import java.io.File;
import java.io.InputStream;
import java.util.function.BiFunction;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ResourceLocator;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.EModelElementAnnotationResourceLocator;
import org.nasdanika.html.ecore.EcoreDocumentationGenerator;

public class TestEcore extends HTMLTestBase {
	
	/**
	 * Generates Ecore model documentation.
	 * @throws Exception
	 */
	@Test
	public void testEcoreDocumentation() {		
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null, null);
		generator.loadGenModel("urn:org.nasdanika.bank");
		Container<InputStream> fsc = new FileSystemContainer(new File("target/test-dumps/ecore"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		BiFunction<org.nasdanika.common.resources.File<InputStream>, Object, InputStream> encoder = (file, contents) -> {
			InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
			if (ret == null) {
				// toString() conversion
				ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
			}
			return ret;
		};
		generator.generate(fsc.adapt(null, encoder), progressMonitor);		
	}

	/**
	 * Generates Ecore model documentation for ``ru`` locale by utilizing {@link EModelElementAnnotationResourceLocator}.
	 * @throws Exception
	 */
	@Test
	public void testRussianEcoreDocumentation() {		
		EModelElementAnnotationResourceLocator rl = new EModelElementAnnotationResourceLocator("urn:org.nasdanika", key -> key+"_ru");
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null, rl);
		generator.loadGenModel("urn:org.nasdanika.bank");
		Container<InputStream> fsc = new FileSystemContainer(new File("target/test-dumps/ecore/ru"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		BiFunction<org.nasdanika.common.resources.File<InputStream>, Object, InputStream> encoder = (file, contents) -> {
			InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
			if (ret == null) {
				// toString() conversion
				ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
			}
			return ret;
		};
		generator.generate(fsc.adapt(null, encoder), progressMonitor);		
	}
	
}
