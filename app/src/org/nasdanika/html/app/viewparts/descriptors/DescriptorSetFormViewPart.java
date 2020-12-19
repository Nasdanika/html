package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

/**
 * Creates a {@link Form} from a {@link DescriptorSet}
 * @author Pavel
 *
 */
public class DescriptorSetFormViewPart implements ViewPart {

	private ViewBuilder viewBuilder;  
	
	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetFormViewPart(DescriptorSet descriptorSet, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		viewBuilder = new DescriptorSetConsumerViewBuilder(descriptorSet, horizontalLabelWidths, diagnose) {

			@Override
			protected ViewBuilder createDescriptorSetViewBuilder(DescriptorSet descriptorSet) {
				return new DescriptorSetFieldSetViewBuilder(descriptorSet, horizontalLabelWidths, diagnose);
			}	
			
		};
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Form form = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE).form();
		viewBuilder.build(form, viewGenerator, progressMonitor);
		return form;
	}

}
