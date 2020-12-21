package org.nasdanika.html.app.tests;

import org.junit.Test;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestApp extends HTMLTestBase {
	
	@Test
	public void testLabel() throws Exception {
		ViewGenerator viewGenerator = new ViewGeneratorImpl(null, null);
		viewGenerator.put("color", "SUCCESS");
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Label label = (Label) viewGenerator.loadYaml(this.getClass().getResource("label-spec.yml"), monitor);
		
		writeThemedPage("app/label.html", "Label", viewGenerator.label(label));
	}
	
		
}
