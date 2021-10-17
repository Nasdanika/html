package org.nasdanika.html.app.tests;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.descriptors.NamedDescriptor;
import org.nasdanika.common.descriptors.PropertyDescriptor;
import org.nasdanika.common.descriptors.ValueDescriptor;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.exec.Group;
import org.nasdanika.exec.Loader;
import org.nasdanika.exec.input.PropertySet;
import org.nasdanika.html.Form;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.viewparts.descriptors.DescriptorSetConsumerViewBuilder.Listener;
import org.nasdanika.html.app.viewparts.descriptors.DescriptorSetFormViewPart;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.Size;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestDescriptors extends HTMLTestBase {
	
	Listener listener = new Listener() {
		
		@Override
		public void onPropertyDescriptorFormGroup(PropertyDescriptor descriptor, int index, FormGroup formGroup, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			
		}
		
		@Override
		public void onPropertyDescriptorControl(PropertyDescriptor descriptor, int index, Object control, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			System.out.println("*** \t Property: " + descriptor.getName() + " -> " + control.getClass());
		}
		
		@Override
		public void onDescriptorSetContainer(DescriptorSet descriptorSet, Object container, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			System.out.println("*** Descriptor set: " + ((NamedDescriptor) descriptorSet).getName() + " -> " + container.getClass());
		}

		@Override
		public void onDiagnostic(Descriptor descriptor, Diagnostic diagnostic, ProgressMonitor progressMonitor) {
//			if (diagnostic instanceof BasicDiagnostic) {
//				BasicDiagnostic subDiagnostic = new BasicDiagnostic(Status.ERROR, "Found some issues with " + descriptor, descriptor);
//				((BasicDiagnostic) diagnostic).add(subDiagnostic);
//			}			
		}
	};
	
	
	@Test
	public void testViewParts() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object group = loader.loadYaml(this.getClass().getResource("group-spec.yml"), monitor);
		assertEquals(Group.class, group.getClass());
		
//		Context context = Context.EMPTY_CONTEXT;
		MutableContext context = Context.singleton("name", "Quadrant").fork();
		
		PropertySet propertySet = ((Group) group).adaptTo(PropertySet.class);
		DescriptorSet descriptorSet = propertySet.createDescriptorSet(context);
		Map<Breakpoint, Size> horizontalLabelWidths = Collections.singletonMap(Breakpoint.DEFAULT, Size.S2);
		DescriptorSetFormViewPart viewPart = new DescriptorSetFormViewPart(descriptorSet, horizontalLabelWidths, false, listener) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				Form form = (Form) super.generate(viewGenerator, progressMonitor);
				form.action("purum");
				form.button("Submit");
				return form;
			}
		};
		
//		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
		writePage("descriptors/view-parts/index.html", "Descriptor View Parts", viewPart);
	}
		
	public static class AnnotatedListener {
		
		@Listener.DescriptorSetContainerListener
		public void onLegalFinePrintContainer(
				DescriptorSet descriptorSet, 
				Object container, 
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor) {
			System.out.println("*** Gotcha: " + descriptorSet.getLabel());
		}
		
		@Listener.PropertyDescriptorControlListener
		public void onLegalAgreeToTermsAndConditionsControl(PropertyDescriptor descriptor, int index, Object control, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			System.out.println("*** Gotcha >>> \t Property: " + descriptor.getName() + " -> " + control.getClass());
		}

		@Listener.PropertyDescriptorFormGroupListener("password")
		public void onNameFormGroup(PropertyDescriptor descriptor, int index, FormGroup formGroup, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			System.out.println("*** Gotcha >>> Property form group: " + formGroup);
		}

		@Listener.DiagnosticListener("name")
		public void onLocationDiagnostic(Descriptor descriptor, Diagnostic diagnostic, ProgressMonitor progressMonitor) {
			System.out.println("*** Gotcha >>> Diagnostic: " + diagnostic);
			if (diagnostic instanceof BasicDiagnostic) {
				BasicDiagnostic subDiagnostic = new BasicDiagnostic(Status.ERROR, "Found some issues with " + descriptor, descriptor);
				((BasicDiagnostic) diagnostic).add(subDiagnostic);
			}			
		}
		
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
		
		DescriptorSetFormViewPart viewPart = new DescriptorSetFormViewPart(descriptorSet, horizontalLabelWidths, true, listener.compose(Listener.asListener(new AnnotatedListener()))) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				Form form = (Form) super.generate(viewGenerator, progressMonitor);
				form.action("purum");
				form.button("Submit");
				return form;
			}
		};
		
//		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
		writePage("descriptors/diagnostic/index.html", "Descriptor Diagnostic", viewPart);
	}
	
		
}
