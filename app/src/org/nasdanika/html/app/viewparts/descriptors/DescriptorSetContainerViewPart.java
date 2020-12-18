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
import org.nasdanika.common.descriptors.ValueDescriptor.Control;
import org.nasdanika.html.Container;
import org.nasdanika.html.Form;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
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
public abstract class DescriptorSetContainerViewPart<C extends Container<?>> implements ViewPart {
	
	protected DescriptorSet descriptorSet;
	protected Map<Breakpoint, Size> horizontalLabelWidths;
	protected boolean diagnose;

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	protected DescriptorSetContainerViewPart(DescriptorSet descriptorSet, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose) {
		this.descriptorSet = descriptorSet;
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		
		C container = generateContainer(viewGenerator, progressMonitor);

		// TODO - icon
		Object label = generateLabel(viewGenerator, progressMonitor);
		if (label != null) {
			container.content(label);
		}
				
		Object description = generateDescription(viewGenerator, progressMonitor);
		if (description != null) {
			container.content(description);
		}
		
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
						container.content(alert);
					}
				}
			}
		}
		for (Descriptor descriptor: descriptorSet.getDescriptors()) {
			if (descriptor.isEnabled()) {
				ViewPart descriptorViewPart = createDescriptorViewPart(descriptor, container);
				if (descriptorViewPart != null) {
					container.content(descriptorViewPart.generate(viewGenerator, progressMonitor));
				}
			}
		}
		
		return container;
	}
	
	protected abstract C generateContainer(ViewGenerator viewGenerator, ProgressMonitor progressMonitor);

	protected Object generateDescription(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (Util.isBlank(descriptorSet.getDescription())) {
			return null;
		}
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		return htmlFactory.div(StringEscapeUtils.escapeHtml4(descriptorSet.getDescription())).style().margin().bottom("1em");
	}

	protected Object generateLabel(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return Util.isBlank(descriptorSet.getLabel()) ? null : TagName.h2.create(StringEscapeUtils.escapeHtml4(descriptorSet.getLabel()));
	}

	protected ViewPart createDescriptorViewPart(Descriptor descriptor, C container) {
		if (descriptor instanceof ChoicesPropertyDescriptor) {
			ChoicesPropertyDescriptor cpd = (ChoicesPropertyDescriptor) descriptor;
			if (cpd.getUpperBound() == 1 || (cpd.getControlHint() == Control.CHECKBOX && cpd.getChoices() != null)) {
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
		} else if (descriptor instanceof DescriptorSet) {
			return createDescriptorSetViewPart((DescriptorSet) descriptor, container);
		}
		if (descriptor == null) {
			return null;
		}
		throw new UnsupportedOperationException("Unsupported descriptor type: " + descriptor.getClass());
	}
	
	protected abstract ViewPart createDescriptorSetViewPart(DescriptorSet descriptorSet, C container);

}
