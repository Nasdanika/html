package org.nasdanika.html.tests;

import java.io.File;
import java.io.InputStream;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ResourceLocator;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.EModelElementAnnotationResourceLocator;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.emf.localization.RussianResourceLocator;
import org.nasdanika.emf.localization.UI;
import org.nasdanika.html.app.impl.ProgressReportGenerator;
import org.nasdanika.html.ecore.EcoreDocumentationGenerator;
import org.nasdanika.html.ecore.EcoreViewActionAdapterFactory;

public class TestEcore extends HTMLTestBase {
	
	/**
	 * Generates Ecore model documentation.
	 * @throws Exception
	 */
	@Test
	public void testEcoreDocumentation() throws Exception {		
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null, null, false);
		generator.loadGenModel("urn:org.nasdanika.bank");
		FileSystemContainer fsc = new FileSystemContainer(new File("target/test-dumps/ecore"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		ProgressEntry pe = new ProgressEntry("Generating Bank Model Documentation", 0);
		
		BiFunction<String, Object, InputStream> converter = (path, contents) -> {
			InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
			if (ret == null) {
				// toString() conversion
				ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
			}
			return ret;
		};
		
		Container<Object> objectContainer = fsc.stateAdapter().adapt(null, converter);
		generator.generate(objectContainer, progressMonitor.compose(pe));
		
		// HTML report
		ProgressReportGenerator prg = new ProgressReportGenerator("Documentation generation", pe);
		prg.generate(objectContainer.getContainer("progress-report", progressMonitor.split("Getting progress-report container", 1)), progressMonitor);		
		
	}

	/**
	 * Generates Ecore model documentation for ``ru`` locale by utilizing {@link EModelElementAnnotationResourceLocator}.
	 * @throws Exception
	 */
	@Test
	public void testRussianEcoreDocumentation() throws Exception {		
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator(
				"Модель Банка Насданики", 
				"Общее описание модели - обычно в случае если документации пакетов недостаточно",
				UI.RU, 
				false) {
						
			@Override
			protected EcoreViewActionAdapterFactory createAdapterFactory() {
				EcoreViewActionAdapterFactory adapterFactory = super.createAdapterFactory();
				
				adapterFactory.registerAdapterFactory(
						new FunctionAdapterFactory<ResourceLocator, EModelElement>(
								EcorePackage.Literals.EMODEL_ELEMENT, 
								ResourceLocator.class, 
								this.getClass().getClassLoader(), 
								RussianResourceLocator::new));
				
				return adapterFactory;
			}
			
		};
		generator.loadGenModel("urn:org.nasdanika.bank");
		FileSystemContainer fsc = new FileSystemContainer(new File("target/test-dumps/ecore/ru"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		BiFunction<String, Object, InputStream> converter = (file, contents) -> {
			InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
			if (ret == null) {
				// toString() conversion
				ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
			}
			return ret;
		};
		generator.generate(fsc.stateAdapter().adapt(null, converter), progressMonitor);		
	}
	
}
