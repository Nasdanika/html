package org.nasdanika.html.app.viewparts.descriptors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.descriptors.PropertyDescriptor;
import org.nasdanika.common.descriptors.ValueDescriptor.Control;
import org.nasdanika.html.Container;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.Size;

/**
 * Creates a {@link Form} from a {@link DescriptorSet}
 * @author Pavel
 *
 */
public abstract class DescriptorSetConsumerViewBuilder implements ViewBuilder {
	
	/**
	 * Listens for view building events and can customize view elements.
	 * @author Pavel
	 *
	 */
	public interface Listener {
		
		/**
		 * Invoked on building {@link DescriptorSet} container. 
		 * @param descriptorSet
		 * @param container
		 * @param viewGenerator
		 * @param progressMonitor
		 */
		void onDescriptorSetContainer(DescriptorSet descriptorSet, Object container, ViewGenerator viewGenerator, ProgressMonitor progressMonitor);

		void onPropertyDescriptorFormGroup(
				PropertyDescriptor descriptor, 
				int index, FormGroup formGroup,
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor);

		void onPropertyDescriptorControl(
				PropertyDescriptor descriptor, 
				int index, Object control,
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor);
		
	}
	
	protected DescriptorSet descriptorSet;
	protected Map<Breakpoint, Size> horizontalLabelWidths;
	protected boolean diagnose;
	protected Listener listener;

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	protected DescriptorSetConsumerViewBuilder(
			DescriptorSet descriptorSet, 
			Map<Breakpoint, Size> horizontalLabelWidths, 
			boolean diagnose,
			Listener listener) {
		this.descriptorSet = descriptorSet;
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
		this.listener = listener;
	}

	@Override
	public void build(Object container, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (listener != null) {
			listener.onDescriptorSetContainer(descriptorSet, container, viewGenerator, progressMonitor);
		}
		Diagnostic diagnostic = diagnose ? descriptorSet.diagnose(progressMonitor) : null;		
		Status status = diagnostic == null ? null : diagnostic.getStatus();
		
		Consumer<Object> consumer = asConsumer(container);
		
		Object label = generateLabel(viewGenerator, status, progressMonitor);
		if (label != null) {
			consumer.accept(label);
		}
				
		Object description = generateDescription(viewGenerator, status, progressMonitor);
		if (description != null) {
			consumer.accept(description);
		}
		
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		if (diagnostic != null && diagnostic.getStatus() != Status.SUCCESS) {
			for (Diagnostic child: diagnostic.getChildren()) {
				List<Object> childData = child.getData();
				if (!childData.isEmpty() 
						&& childData.get(0) == descriptorSet
						&& !Util.isBlank(child.getMessage())
						&& (child.getStatus() == Status.WARNING || child.getStatus() == Status.ERROR)) {
					
					Tag alert = bootstrapFactory.alert(child.getStatus() == Status.WARNING ? Color.WARNING : Color.DANGER, StringEscapeUtils.escapeHtml4(child.getMessage()));
					consumer.accept(alert);
				}
			}
		}
		
		for (Descriptor descriptor: descriptorSet.getDescriptors()) {
			if (descriptor.isEnabled()) {
				ViewBuilder descriptorViewBuilder = createDescriptorViewBuilder(descriptor);
				if (descriptorViewBuilder != null) {
					descriptorViewBuilder.build(container, viewGenerator, progressMonitor);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	static Consumer<Object> asConsumer(Object container) {
		Consumer<Object> consumer;
		if (container instanceof Consumer) {
			consumer = (Consumer<Object>) container;
		} else if (container instanceof Container) {
			consumer = ((Container<?>) container)::content;
		} else {
			throw new IllegalArgumentException("First argument shall implement either " + Consumer.class.getName() + " or " + Container.class.getName() + ": " + container);
		}
		return consumer;
	}

	protected Object generateDescription(ViewGenerator viewGenerator, Status status, ProgressMonitor progressMonitor) {
		if (Util.isBlank(descriptorSet.getDescription())) {
			return null;
		}
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		return htmlFactory.div(StringEscapeUtils.escapeHtml4(descriptorSet.getDescription())).style().margin().bottom("1em");
	}

	protected Object generateLabel(ViewGenerator viewGenerator, Status status, ProgressMonitor progressMonitor) {
		if (Util.isBlank(descriptorSet.getLabel())) {
			return null;
		}
		DescriptorLabel label = new DescriptorLabel(descriptorSet, status);
		return TagName.h2.create(viewGenerator.label(label));
	}

	protected ViewBuilder createDescriptorViewBuilder(Descriptor descriptor) {
		if (descriptor instanceof ChoicesPropertyDescriptor) {
			ChoicesPropertyDescriptor cpd = (ChoicesPropertyDescriptor) descriptor;
			if (cpd.getUpperBound() == 1 || (cpd.getControlHint() == Control.CHECKBOX && cpd.getChoices() != null)) {
				return new ChoicesPropertyDescriptorViewBuilder(cpd, horizontalLabelWidths, diagnose, 0, listener);
			}
			List<ViewBuilder> viewBuilders = new ArrayList<>();
			for (int i=0; i < cpd.getUpperBound(); ++i) {
				viewBuilders.add(new ChoicesPropertyDescriptorViewBuilder(cpd, horizontalLabelWidths, diagnose, i, listener));
			}
			
			return (target, viewGenerator, progressMonitor) -> {
				for (ViewBuilder vp: viewBuilders) {
					vp.build(target, viewGenerator, progressMonitor);
				}
			};
		} else if (descriptor instanceof DescriptorSet) {
			return createDescriptorSetViewBuilder((DescriptorSet) descriptor);
		}
		if (descriptor == null) {
			return null;
		}
		throw new UnsupportedOperationException("Unsupported descriptor type: " + descriptor.getClass());
	}
	
	protected abstract ViewBuilder createDescriptorSetViewBuilder(DescriptorSet descriptorSet);

}
