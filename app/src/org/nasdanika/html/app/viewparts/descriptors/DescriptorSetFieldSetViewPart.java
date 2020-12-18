package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.Form;
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
public class DescriptorSetFieldSetViewPart extends DescriptorSetContainerViewPart<FieldSet> {
	
	private Form form;
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		super.generate(viewGenerator, progressMonitor);
		return null; // Fieldset is already part of the form.
	}

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetFieldSetViewPart(DescriptorSet descriptorSet, Form form, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		super(descriptorSet, horizontalLabelWidths, diagnose);
		this.form = form;
	}

	@Override
	protected FieldSet generateContainer(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		FieldSet fieldSet = form.fieldset();
		// TODO - icon
		if (!Util.isBlank(descriptorSet.getLabel())) {
			fieldSet.legend(StringEscapeUtils.escapeHtml4(descriptorSet.getLabel()));			
		}
		return fieldSet;
	}
	
	@Override
	protected Object generateLabel(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return null; // Using legend
	}
	
	@Override
	protected ViewPart createDescriptorSetViewPart(DescriptorSet descriptorSet, FieldSet container) {
		throw new UnsupportedOperationException();
	}	

}
