package org.nasdanika.html.producer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.html.Producer;
import org.nasdanika.html.producer.HtmlGenerator;
import org.nasdanika.ncore.NcoreFactory;

public class TestHtmlGeneration {

	@Test
	public void testString() {
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Context.EMPTY_CONTEXT, 
				null, 
				progressMonitor);

		org.nasdanika.ncore.String str = NcoreFactory.eINSTANCE.createString();
		str.setValue("Hello World");
		
		assertTrue(htmlGenerator.canHandle(str));
				
		Producer<Object> processor = htmlGenerator.createProducer(str, progressMonitor);
		Object result = processor.produce(0);
		
		assertEquals(str.getValue(), result);
	}

}
