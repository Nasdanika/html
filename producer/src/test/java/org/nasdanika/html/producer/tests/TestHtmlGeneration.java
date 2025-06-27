package org.nasdanika.html.producer.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
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
	
	@Test
	public void testText() {
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Context.EMPTY_CONTEXT, 
				null, 
				progressMonitor);

		org.nasdanika.exec.content.Text text = ContentFactory.eINSTANCE.createText();
		text.setContent("Hello World");
		
		assertTrue(htmlGenerator.canHandle(text));
				
		Producer<Object> processor = htmlGenerator.createProducer(text, progressMonitor);
		Object result = processor.produce(0);
		
		assertEquals(text.getContent(), result);
	}
	
	@Test
	public void testTextInterpolation() {
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Context.singleton("greetee", "World"), 
				null, 
				progressMonitor);

		org.nasdanika.exec.content.Text text = ContentFactory.eINSTANCE.createText();
		text.setContent("Hello ${greetee}");
		text.setInterpolate(true);
		
		assertTrue(htmlGenerator.canHandle(text));
				
		Producer<Object> processor = htmlGenerator.createProducer(text, progressMonitor);
		Object result = processor.produce(0);
		
		assertEquals("Hello World", result);
	}
	
	@Test
	public void testMarkdown() {
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Context.EMPTY_CONTEXT, 
				null, 
				progressMonitor);

		org.nasdanika.exec.content.Text text = ContentFactory.eINSTANCE.createText();
		text.setContent("Hello *World*");
		
		Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
		markdown.setSource(text);
		
		assertTrue(htmlGenerator.canHandle(markdown));
				
		Producer<Object> processor = htmlGenerator.createProducer(markdown, progressMonitor);
		Object result = processor.produce(0);
		
		assertTrue(result.toString().contains("<p>Hello <em>World</em></p>"));
	}
		
	@Test
	public void testInterpolator() {
		PrintStreamProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		HtmlGenerator htmlGenerator = HtmlGenerator.load(
				Context.singleton("greetee", "Universe"), 
				null, 
				progressMonitor);

		org.nasdanika.exec.content.Text text = ContentFactory.eINSTANCE.createText();
		text.setContent("Hello ${greetee}");
		
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		interpolator.setSource(text);
		
		assertTrue(htmlGenerator.canHandle(interpolator));
				
		Producer<Object> processor = htmlGenerator.createProducer(interpolator, progressMonitor);
		Object result = processor.produce(0);
		
		assertEquals("Hello Universe", result);
	}

}
