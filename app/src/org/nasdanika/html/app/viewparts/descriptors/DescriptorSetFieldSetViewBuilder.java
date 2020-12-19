package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

/**
 * Creates a {@link FieldSet} in a {@link Form} from a {@link DescriptorSet}
 * @author Pavel
 *
 */
public class DescriptorSetFieldSetViewBuilder extends DescriptorSetConsumerViewBuilder {
	
	@Override
	public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		FieldSet fieldSet = ((Form) target).fieldset();
		if (!Util.isBlank(descriptorSet.getLabel())) {
			fieldSet.legend(viewGenerator.label(new DescriptorLabel(descriptorSet, null)));			
		}
		super.build(fieldSet, viewGenerator, progressMonitor);
	}

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetFieldSetViewBuilder(DescriptorSet descriptorSet, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		super(descriptorSet, horizontalLabelWidths, diagnose);
	}
	
	@Override
	protected Object generateLabel(ViewGenerator viewGenerator, Status status, ProgressMonitor progressMonitor) {
		return null; // Using legend
	}
	
	@Override
	protected ViewBuilder createDescriptorSetViewBuilder(DescriptorSet descriptorSet) {
		return new DescriptorSetCardViewBuilder(descriptorSet, horizontalLabelWidths, diagnose);
	}	

}
