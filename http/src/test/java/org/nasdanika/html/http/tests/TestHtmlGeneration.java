package org.nasdanika.html.producer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.html.Producer;
import org.nasdanika.html.producer.HtmlGenerator;
import org.nasdanika.ncore.NcoreFactory;

public class TestHtmlGeneration {

	@Test
	public void testString() {
		org.nasdanika.ncore.String str = NcoreFactory.eINSTANCE.createString();
		str.setValue("Hello World");
		
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Collections.singleton(str), 
				Context.EMPTY_CONTEXT, 
				null, 
				progressMonitor);
		
		Map<EObject, Producer<Object>> processors = htmlGenerator.createProcessors(progressMonitor);
		Object result = processors.get(str).produce(0);
		
		assertEquals(str.getValue(), result);
	}

}
