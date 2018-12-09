package org.nasdanika.html.tests;

import org.junit.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.impl.HTMLElementImpl;


public class TestHtml extends HTMLTestBase {
	
	@Test
	public void testStringify() {
		System.out.println(HTMLElementImpl.stringify(getClass().getResource("test-resource.txt"), 0, HTMLFactory.INSTANCE));
	}
	
	
}
