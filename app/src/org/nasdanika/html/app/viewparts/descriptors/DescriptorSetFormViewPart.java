package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

/**
 * Creates a {@link Form} from a {@link DescriptorSet}
 * TODO hierarchy - container, fieldset, ...
 * @author Pavel
 *
 */
public class DescriptorSetFormViewPart extends DescriptorSetContainerViewPart<Form> {
	
	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetFormViewPart(DescriptorSet descriptorSet, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		super(descriptorSet, horizontalLabelWidths, diagnose);
	}

	@Override
	protected Form generateContainer(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE).form();
	}

	@Override
	protected ViewPart createDescriptorSetViewPart(DescriptorSet descriptorSet, Form form) {
		return new DescriptorSetFieldSetViewPart(descriptorSet, form, horizontalLabelWidths, diagnose);
	}	

}
