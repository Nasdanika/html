package org.nasdanika.html.ecore.tests;

import java.util.function.Consumer;

import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.html.ecore.gen.EcoreLabelProviderAdapterFactory;
import org.nasdanika.html.model.app.util.LabelProvider;
import org.nasdanika.html.model.app.util.LabelProvider.Result;
import org.nasdanika.ncore.NcorePackage;

public class TestEcoreLabelBuilders {
	
	@Test
	public void testNcoreDocGen() {
		EPackage ncorePackage = NcorePackage.eINSTANCE;
		EcoreLabelProviderAdapterFactory adapterFactory = new EcoreLabelProviderAdapterFactory(null, null, null);
		LabelProvider packageLabelProvider = (LabelProvider) adapterFactory.adapt(ncorePackage, LabelProvider.class);
		Supplier<Result> packageLabelSupplier = packageLabelProvider.asSupplier(null);
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
		Consumer<Diagnostic> diagnosticConsumer = System.out::println;
		Result supplierResult = packageLabelSupplier.call(progressMonitor, diagnosticConsumer);
		System.out.println(supplierResult.label());
		System.out.println(supplierResult.registry());		
	}

}
