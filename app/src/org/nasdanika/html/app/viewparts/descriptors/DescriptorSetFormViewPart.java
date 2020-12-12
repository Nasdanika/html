package org.nasdanika.html.app.viewparts.descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Size;

/**
 * Creates a {@link Form} from a {@link DescriptorSet}
 * TODO hierarchy - container, fieldset, ...
 * @author Pavel
 *
 */
public class DescriptorSetFormViewPart implements ViewPart {
	
	private DescriptorSet descriptorSet;
	private Map<Breakpoint, Size> horizontalLabelWidths;
	private boolean diagnose;

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetFormViewPart(DescriptorSet descriptorSet, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		this.descriptorSet = descriptorSet;
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		Form form = htmlFactory.form();
		if (diagnose) {
			Diagnostic diagnostic = descriptorSet.diagnose(progressMonitor);
			if (diagnostic.getStatus() != Status.SUCCESS) {
				for (Diagnostic child: diagnostic.getChildren()) {
					List<Object> childData = child.getData();
					if (!childData.isEmpty() 
							&& childData.get(0) == descriptorSet
							&& !Util.isBlank(child.getMessage())
							&& (child.getStatus() == Status.WARNING || child.getStatus() == Status.ERROR)) {
						
						Tag alert = bootstrapFactory.alert(child.getStatus() == Status.WARNING ? Color.WARNING : Color.DANGER, StringEscapeUtils.escapeHtml4(child.getMessage()));
						form.content(alert);
					}
				}
			}
		}
		for (Descriptor descriptor: descriptorSet.getDescriptors()) {
			ViewPart descriptorViewPart = createDescriptorViewPart(descriptor);
			if (descriptorViewPart != null) {
				form.content(descriptorViewPart.generate(viewGenerator, progressMonitor));
			}
		}
		
		return form;
	}

	protected ViewPart createDescriptorViewPart(Descriptor descriptor) {
		if (descriptor instanceof ChoicesPropertyDescriptor) {
			ChoicesPropertyDescriptor cpd = (ChoicesPropertyDescriptor) descriptor;
			if (cpd.getUpperBound() == 1) {
				return new ChoicesPropertyDescriptorViewPart(cpd, horizontalLabelWidths, diagnose, 0);
			}
			List<ViewPart> viewParts = new ArrayList<>();
			for (int i=0; i < cpd.getUpperBound(); ++i) {
				viewParts.add(new ChoicesPropertyDescriptorViewPart(cpd, horizontalLabelWidths, diagnose, i));
			}
			
			return (viewGenerator, progressMonitor) -> {
				Fragment ret = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE).fragment();
				for (ViewPart vp: viewParts) {
					ret.content(vp.generate(viewGenerator, progressMonitor));
				}
				return ret;
			};
		}
		// TODO - nested descriptor sets as fieldsets.
		if (descriptor == null) {
			return null;
		}
		throw new UnsupportedOperationException("Unsupported descriptor type: " + descriptor.getClass());
	}

}
