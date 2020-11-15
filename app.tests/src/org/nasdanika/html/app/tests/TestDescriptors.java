package org.nasdanika.html.app.tests;

import static org.junit.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.exec.Group;
import org.nasdanika.exec.Loader;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.HTMLTableApplication;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestDescriptors extends HTMLTestBase {
	
	@Test
	public void testViewParts() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object group = loader.loadYaml(this.getClass().getResource("group-spec.yml"), monitor);
		assertEquals(Group.class, group.getClass());
		
//		Context context = Context.EMPTY_CONTEXT;
		Context context = Context.singleton("name", "Universe");
		
		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
		writeThemedPage("descriptors/view-parts/index.html", "Descriptor View Parts", "Hello");
	}
		
}
