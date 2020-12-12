package org.nasdanika.html.app.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.descriptors.ValueDescriptor;
import org.nasdanika.exec.Group;
import org.nasdanika.exec.Loader;
import org.nasdanika.exec.input.PropertySet;
import org.nasdanika.html.Form;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.viewparts.descriptors.DescriptorSetFormViewPart;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

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
		MutableContext context = Context.singleton("name", "Universe").fork();
		
		PropertySet propertySet = ((Group) group).adaptTo(PropertySet.class);
		DescriptorSet descriptorSet = propertySet.createDescriptorSet(context);
		Map<Breakpoint, Size> horizontalLabelWidths = Collections.singletonMap(Breakpoint.DEFAULT, Size.S2);
		DescriptorSetFormViewPart viewPart = new DescriptorSetFormViewPart(descriptorSet, horizontalLabelWidths, false) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				Form form = (Form) super.generate(viewGenerator, progressMonitor);
				form.action("purum");
				form.button("Submit");
				return form;
			}
		};
		
//		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
		writeThemedPage("descriptors/view-parts/index.html", "Descriptor View Parts", viewPart);
	}
		
	@Test
	public void testDiagnostic() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object group = loader.loadYaml(this.getClass().getResource("group-spec.yml"), monitor);
		assertEquals(Group.class, group.getClass());
		
//		Context context = Context.EMPTY_CONTEXT;
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		
		PropertySet propertySet = ((Group) group).adaptTo(PropertySet.class);
		DescriptorSet descriptorSet = propertySet.createDescriptorSet(context);
		((ValueDescriptor) descriptorSet.getDescriptors().get(0)).set("Hello");
		Map<Breakpoint, Size> horizontalLabelWidths = Collections.singletonMap(Breakpoint.DEFAULT, Size.S2);
		DescriptorSetFormViewPart viewPart = new DescriptorSetFormViewPart(descriptorSet, horizontalLabelWidths, true) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				Form form = (Form) super.generate(viewGenerator, progressMonitor);
				form.action("purum");
				form.button("Submit");
				return form;
			}
		};
		
//		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
		writeThemedPage("descriptors/diagnostic/index.html", "Descriptor Diagnostic", viewPart);
	}
	
		
}
