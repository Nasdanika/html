package org.nasdanika.html.forcegraph3d.tests;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.forcegraph3d.ForceGraph3D;
import org.nasdanika.html.forcegraph3d.ForceGraph3D.ControlType;
import org.nasdanika.html.forcegraph3d.ForceGraph3DFactory;

public class TestForceGraph3D {
	@Test
	public void testGenerate() {
		ForceGraph3DFactory forceGraph3DFactory = ForceGraph3DFactory.INSTANCE;
		ForceGraph3D graph = forceGraph3DFactory.create();
		graph
			.elementId("graphElement")
			.controlType(ControlType.fly)
			.renderConfig("{ a:b }")
			.addExtraRederer("er1")
			.addExtraRederer("er2")
//			.graphData("{ x: y }")
			.addNode(Map.of("id", 33, "name", "My node"))
			.addNode(Map.of("id", 44, "name", "Another node"))
			.addNode(Map.of("id", 55, "name", "Yet another node"))
			.addLink(Map.of("source", 33, "target", 55));
		
		System.out.println(graph.produce(4));
	}

	@Test
	public void testPage() {
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		HTMLPage page = htmlFactory.page();
		ForceGraph3DFactory forceGraph3DFactory = ForceGraph3DFactory.INSTANCE;
		forceGraph3DFactory.cdn(page);
		
		Tag container = htmlFactory.div();
		ForceGraph3D graph = forceGraph3DFactory.create();
		
		// TODO
		
		page.body(container);
		
		System.out.println(page);
	}

//	@Test
//	public void testJsonData() throws IOException {
//		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
//		HTMLPage page = htmlFactory.page();
//		AlpineJsFactory alpineJsFactory = AlpineJsFactory.INSTANCE;
//		alpineJsFactory.cdn(page);
//		
//		Tag container = htmlFactory.div();
//		AlpineJs<Tag> containerAlpineJs = alpineJsFactory.from(container);
//		JSONObject containerData = containerAlpineJs.data();
//		page.body(container);
//						
//		containerData.put("name", "Nasdanika");
//		
//		Tag strong = TagName.strong.create();
//		container.content(strong);
//		alpineJsFactory.from(strong).text("name");				
//		
//		System.out.println(page);
//	    File outputFile = new File("target/test-output/json-data.html");
//	    outputFile.getParentFile().mkdirs();
//		Files.writeString(outputFile.toPath(), page.toString());
//	}
	
	@Test 
	public void testCdnPage() {
		HTMLPage page = HTMLFactory.INSTANCE.page();
		ForceGraph3DFactory forceGraph3DFactory = ForceGraph3DFactory.INSTANCE;
		forceGraph3DFactory.cdn(page);
		System.out.println(page);
	}

}
