package org.nasdanika.html.alpinejs.tests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.alpinejs.AlpineJs;
import org.nasdanika.html.alpinejs.AlpineJsFactory;

public class TestAlpineJs {

	@Test
	public void testData() {
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		HTMLPage page = htmlFactory.page();
		AlpineJsFactory alpineJsFactory = AlpineJsFactory.INSTANCE;
		alpineJsFactory.cdn(page);
		
		Tag container = htmlFactory.div();
		AlpineJs<Tag> containerAlpineJs = alpineJsFactory.from(container);
		containerAlpineJs.data("{ open: false }");
		page.body(container);
		
		System.out.println(page);
	}

	@Test
	public void testJsonData() throws IOException {
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		HTMLPage page = htmlFactory.page();
		AlpineJsFactory alpineJsFactory = AlpineJsFactory.INSTANCE;
		alpineJsFactory.cdn(page);
		
		Tag container = htmlFactory.div();
		AlpineJs<Tag> containerAlpineJs = alpineJsFactory.from(container);
		JSONObject containerData = containerAlpineJs.data();
		page.body(container);
						
		containerData.put("name", "Nasdanika");
		
		Tag strong = TagName.strong.create();
		container.content(strong);
		alpineJsFactory.from(strong).text("name");				
		
		System.out.println(page);
	    File outputFile = new File("target/test-output/json-data.html");
	    outputFile.getParentFile().mkdirs();
		Files.writeString(outputFile.toPath(), page.toString());

	}
	
	@Test 
	public void testCdnPage() {
		HTMLPage page = HTMLFactory.INSTANCE.page();
		AlpineJsFactory alpineJsFactory = AlpineJsFactory.INSTANCE;
		alpineJsFactory.cdn(page);
		System.out.println(page);
	}

}
