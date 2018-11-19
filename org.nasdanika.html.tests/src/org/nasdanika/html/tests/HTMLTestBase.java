package org.nasdanika.html.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.junit.Assert;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

public class HTMLTestBase {
	
	/**
	 * Writes content to bootstrap page and to a file under repository site.
	 * @param path
	 * @param title
	 * @param content
	 * @throws Exception
	 */
	protected void dump(String path, String title, Object... content) throws Exception {		
		HTMLPage bootstrapPage = BootstrapFactory.INSTANCE.bootstrapCdnHTMLPage();
		FontAwesomeFactory.INSTANCE.cdn(bootstrapPage);
		JsTreeFactory.INSTANCE.cdn(bootstrapPage);
		KnockoutFactory.INSTANCE.cdn(bootstrapPage);
		// More declarations as needed.		
		bootstrapPage.title(title);
		bootstrapPage.body(content);
		
		File target = new File(("target/test-dumps/"+path).replace("/", File.separator));
		File parent = target.getParentFile();
		if (!parent.exists()) {
			if (!parent.mkdirs()) {
				Assert.fail("Cannot create "+parent.getAbsolutePath());
			}
		}
		
		System.out.println("Dumping '"+title+"' to "+target.getAbsolutePath());
		try (Writer writer = new FileWriter(target)) {
			writer.write(bootstrapPage.toString());
		}		
	}

}
