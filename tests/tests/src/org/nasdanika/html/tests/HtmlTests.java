package org.nasdanika.html.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestHtml.class,
	TestApp.class,
	TestBootstrap.class,
	TestFontAwesome.class,	
	TestEmf.class,
	TestEcore.class
})
public class HtmlTests {
	
}
