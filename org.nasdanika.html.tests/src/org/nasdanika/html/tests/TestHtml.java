package org.nasdanika.html.tests;

import org.junit.Test;
import org.nasdanika.html.impl.HTMLElementImpl;


public class TestHtml extends HTMLTestBase {
	
	@Test
	public void testStringify() {
		System.out.println(HTMLElementImpl.stringify(getClass().getResource("test-resource.txt")));
	}
	
	
}
