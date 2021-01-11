package org.nasdanika.html.app.viewparts.descriptors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Composeable;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.descriptors.NamedDescriptor;
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
	
	private static String inferDescriptorName(String methodName, String prefix, String suffix) {
		if (methodName.startsWith(prefix) && methodName.endsWith(suffix)) {
			String[] segments = StringUtils.splitByCharacterTypeCamelCase(methodName.substring(prefix.length(), methodName.length() - suffix.length()));
			for (int i = 0; i < segments.length; ++i) {
				segments[i] = segments[i].toLowerCase();
			}
			return String.join("-", segments);
		}
		
		return null; 
	}
	
	/**
	 * Listens for view building events and can customize view elements.
	 * @author Pavel
	 *
	 */
	public interface Listener extends Composeable<Listener> {
		
		/**
		 * Annotation for methods which should be called from <code>onPropertyDescriptorControl</code> listener method.
		 * Annotated methods shall have the same number of parameters as <code>onPropertyDescriptorControl</code> with compatible types.
		 * @author Pavel
		 */
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)
		@interface PropertyDescriptorControlListener {
			
			/**
			 * Property name. If empty the name is derived from the method name. 
			 * In the latter case the method name shall be in format <code>on&lt;property name in camel case&gt;Control</code>.
			 * The name will be converted to kebab case and used for matching. 
			 * E.g. <code>onFirstNameControl()</code> method name will be converted to <code>first-name</code> property name. 
			 * @return
			 */
			String value() default "";
			
		}
		
		/**
		 * Annotation for methods which should be called from <code>onPropertyDescriptorFormGroup</code> listener method.
		 * Annotated methods shall have the same number of parameters as <code>onPropertyDescriptorFormGroup</code> with compatible types.
		 * @author Pavel
		 */
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)		
		@interface PropertyDescriptorFormGroupListener {
			
			/**
			 * Property name. If empty the name is derived from the method name. 
			 * In the latter case the method name shall be in format <code>on&lt;property name in camel case&gt;FormGroup</code>.
			 * The name will be converted to kebab case and used for matching.
			 * E.g. <code>onFirstNameFormGroup()</code> method name will be converted to <code>first-name</code> property name. 
			 * @return
			 */
			String value() default "";
			
		}

		/**
		 * Annotation for methods which should be called from <code>onDescriptorSetContainer</code> listener method.
		 * Annotated methods shall have the same number of parameters as <code>onDescriptorSetContainer</code> with compatible types.
		 * @author Pavel
		 */
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)		
		@interface DescriptorSetContainerListener {
			
			/**
			 * Descriptor set name. If empty the name is derived from the method name. 
			 * In the latter case the method name shall be in format <code>on&lt;descriptor set name in camel case&gt;Container</code>.
			 * The name will be converted to kebab case and used for matching.
			 * E.g. <code>onDatabaseConfigurationContainer()</code> method name will be converted to <code>database-configuration</code> descriptor name. 
			 * @return
			 */
			String value() default "";
			
		}

		
		/**
		 * Annotation for methods which should be called from <code>onDiagnostic</code> listener method.
		 * Annotated methods shall have the same number of parameters as <code>onDiagnostic</code> with compatible types.
		 * @author Pavel
		 */
		@Retention(RetentionPolicy.RUNTIME)
		@Target(ElementType.METHOD)		
		@interface DiagnosticListener {
			
			/**
			 * Descriptor name. If empty the name is derived from the method name. 
			 * In the latter case the method name shall be in format <code>on&lt;descriptor name in camel case&gt;Diagnostic</code>.
			 * The name will be converted to kebab case and used for matching.
			 * E.g. <code>onFirstNameDiagnostic()</code> method name will be converted to <code>first-name</code> descriptor name. 
			 * @return
			 */
			String value() default "";
			
		}
		
		/**
		 * Invoked on building {@link DescriptorSet} container. 
		 * @param descriptorSet
		 * @param container
		 * @param viewGenerator
		 * @param progressMonitor
		 */
		void onDescriptorSetContainer(
				DescriptorSet descriptorSet, 
				Object container, 
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor);

		void onPropertyDescriptorFormGroup(
				PropertyDescriptor descriptor, 
				int index, 
				FormGroup formGroup,
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor);

		void onPropertyDescriptorControl(
				PropertyDescriptor descriptor, 
				int index, 
				Object control,
				ViewGenerator viewGenerator, 
				ProgressMonitor progressMonitor);
				
		/**
		 * Invoked on diagnosing a {@link DescriptorSet} or a {@link PropertyDescriptor}. 
		 * Can contribute to the diagnostic result.
		 * @param descriptor
		 * @param diagnostic
		 */
		void onDiagnostic(Descriptor descriptor, Diagnostic diagnostic, ProgressMonitor progressMonitor);
		
		@Override
		default Listener compose(Listener other) {
			// TODO Auto-generated method stub
			return other == null ? this : from(new Listener[] { this, other });
		}
		
		static Listener from(Listener... listeners) {
			return from(Arrays.asList(listeners));
		}
		
		static Listener from(Iterable<Listener> listeners) {
			return new Listener() {

				@Override
				public void onDescriptorSetContainer(
						DescriptorSet descriptorSet, 
						Object container,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					for (Listener listener: listeners) {
						if (listener != null) {
							listener.onDescriptorSetContainer(descriptorSet, container, viewGenerator, progressMonitor);
						}
					}
				}

				@Override
				public void onPropertyDescriptorFormGroup(
						PropertyDescriptor descriptor, 
						int index, 
						FormGroup formGroup,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					for (Listener listener: listeners) {
						if (listener != null) {
							listener.onPropertyDescriptorFormGroup(descriptor, index, formGroup, viewGenerator, progressMonitor);
						}
					}
				}

				@Override
				public void onPropertyDescriptorControl(
						PropertyDescriptor descriptor, 
						int index, 
						Object control,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					for (Listener listener: listeners) {
						if (listener != null) {
							listener.onPropertyDescriptorControl(descriptor, index, control, viewGenerator, progressMonitor);
						}
					}
				}

				@Override
				public void onDiagnostic(
						Descriptor descriptor, 
						Diagnostic diagnostic,
						ProgressMonitor progressMonitor) {
					
					for (Listener listener: listeners) {
						if (listener != null) {
							listener.onDiagnostic(descriptor, diagnostic, progressMonitor);
						}
					}
				}
				
			};
		}
		
		/**
		 * Wraps the target into a {@link Listener} which delegates listener calls to
		 * annotated methods.
		 * @param target Target object with annotated methods.
		 * @return Listener instance delegating to annotated methods.
		 */
		static Listener asListener(Object target) {		
			
			class MethodEntry<D extends Descriptor> {
				
				private Predicate<D> predicate;
				private Method method;

				MethodEntry(Predicate<D> predicate, Method method) {
					this.predicate = predicate;
					this.method = method;
				}
				
				@SuppressWarnings("unchecked")
				void invoke(Object[] args) throws Exception {
					if (predicate.test((D) args[0])) {
						method.invoke(target, args);
					}
				}				
				
			}
			
			Collection<MethodEntry<DescriptorSet>> onDescriptorSetContainerMethods = new ArrayList<>();
			Collection<MethodEntry<PropertyDescriptor>> onPropertyDescriptorFormGroupMethods = new ArrayList<>();
			Collection<MethodEntry<PropertyDescriptor>> onPropertyDescriptorControlMethods = new ArrayList<>();
			Collection<MethodEntry<Descriptor>> onDiagnosticMethods = new ArrayList<>();
			
			for (Method method: target.getClass().getMethods()) {
				DescriptorSetContainerListener descriptorSetContainerListener = method.getAnnotation(DescriptorSetContainerListener.class);
				PropertyDescriptorControlListener propertyDescriptorControlListener = method.getAnnotation(PropertyDescriptorControlListener.class);
				PropertyDescriptorFormGroupListener propertyDescriptorFormGroupListener = method.getAnnotation(PropertyDescriptorFormGroupListener.class);
				DiagnosticListener diagnosticListener = method.getAnnotation(DiagnosticListener.class);
				if (descriptorSetContainerListener != null) {
					String name = descriptorSetContainerListener.value();
					if (Util.isBlank(name)) {
						name = inferDescriptorName(method.getName(), "on", "Container");
						if (Util.isBlank(name)) {
							throw new IllegalArgumentException("Cannot infer descriptor set name from method name - method name shall start with 'on' and end with 'Container': " + method.getName());
						}						
					}
					String finalName = name;
					Predicate<DescriptorSet> predicate = ds -> ds instanceof NamedDescriptor && finalName.equals(((NamedDescriptor) ds).getName());
					onDescriptorSetContainerMethods.add(new MethodEntry<DescriptorSet>(predicate, method));
				} else if (propertyDescriptorControlListener != null) {
					String name = propertyDescriptorControlListener.value();
					if (Util.isBlank(name)) {
						name = inferDescriptorName(method.getName(), "on", "Control");
						if (Util.isBlank(name)) {
							throw new IllegalArgumentException("Cannot infer property from method name - method name shall start with 'on' and end with 'Control': " + method.getName());
						}						
					}
					String finalName = name;
					Predicate<PropertyDescriptor> predicate = pd -> finalName.equals(pd.getName());
					onPropertyDescriptorControlMethods.add(new MethodEntry<PropertyDescriptor>(predicate, method));					
				} else if (propertyDescriptorFormGroupListener != null) {
					String name = propertyDescriptorFormGroupListener.value();
					if (Util.isBlank(name)) {
						name = inferDescriptorName(method.getName(), "on", "FormGroup");
						if (Util.isBlank(name)) {
							throw new IllegalArgumentException("Cannot infer property from method name - method name shall start with 'on' and end with 'FormGroup': " + method.getName());
						}						
					}
					String finalName = name;
					Predicate<PropertyDescriptor> predicate = pd -> finalName.equals(pd.getName());
					onPropertyDescriptorFormGroupMethods.add(new MethodEntry<PropertyDescriptor>(predicate, method));										
				} else if (diagnosticListener != null) {
					String name = diagnosticListener.value();
					if (Util.isBlank(name)) {
						name = inferDescriptorName(method.getName(), "on", "Diagnostic");
						if (Util.isBlank(name)) {
							throw new IllegalArgumentException("Cannot infer descriptor from method name - method name shall start with 'on' and end with 'Diagnostic': " + method.getName());
						}						
					}
					String finalName = name;
					Predicate<Descriptor> predicate = d -> d instanceof NamedDescriptor && finalName.equals(((NamedDescriptor) d).getName());
					onDiagnosticMethods.add(new MethodEntry<Descriptor>(predicate, method));															
				}				
			}
			
			return new Listener() {

				@Override
				public void onDescriptorSetContainer(
						DescriptorSet descriptorSet, 
						Object container,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					Object[] args = {descriptorSet, container, viewGenerator, progressMonitor};
					for (MethodEntry<DescriptorSet> methodEntry: onDescriptorSetContainerMethods) {
						try {
							methodEntry.invoke(args);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}
					}					
				}

				@Override
				public void onPropertyDescriptorFormGroup(
						PropertyDescriptor descriptor, 
						int index, 
						FormGroup formGroup,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					Object[] args = {descriptor, index, formGroup, viewGenerator, progressMonitor};
					for (MethodEntry<PropertyDescriptor> methodEntry: onPropertyDescriptorFormGroupMethods) {
						try {
							methodEntry.invoke(args);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}
					}					
				}

				@Override
				public void onPropertyDescriptorControl(
						PropertyDescriptor descriptor, 
						int index, 
						Object control,
						ViewGenerator viewGenerator, 
						ProgressMonitor progressMonitor) {
					
					Object[] args = {descriptor, index, control, viewGenerator, progressMonitor};
					for (MethodEntry<PropertyDescriptor> methodEntry: onPropertyDescriptorControlMethods) {
						try {
							methodEntry.invoke(args);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}
					}					
				}

				@Override
				public void onDiagnostic(
						Descriptor descriptor, 
						Diagnostic diagnostic,
						ProgressMonitor progressMonitor) {
					
					Object[] args = {descriptor, diagnostic, progressMonitor};
					for (MethodEntry<Descriptor> methodEntry: onDiagnosticMethods) {
						try {
							methodEntry.invoke(args);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}
					}					
				}
				
			};
			
		}
		
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
		
		if (diagnose && listener != null) {
			listener.onDiagnostic(descriptorSet, diagnostic, progressMonitor);
		}
		
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
