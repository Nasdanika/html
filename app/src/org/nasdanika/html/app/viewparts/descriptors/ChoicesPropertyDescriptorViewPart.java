 package org.nasdanika.html.app.viewparts.descriptors;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoiceDescriptor;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Select;
import org.nasdanika.html.Select.OptionGroup;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.TextArea;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.Size;

/**
 * Generates Bootstrap {@link FormGroup}. 
 * @author Pavel
 *
 */
public class ChoicesPropertyDescriptorViewPart implements ViewPart {
	
	private ChoicesPropertyDescriptor descriptor;
	private boolean diagnose;
	private Map<Breakpoint, Size> horizontalLabelWidths;
	private int index;
	private List<Descriptor> choices; 

	/**
	 * 
	 * @param descriptor Descriptor
	 * @param horizonalFormSize If not null a horizontal {@link FormGroup} is generated with specified control size.
	 * @param diagnose If true the descriptor is diagnosed, diagnostics are output in the form group and the group is styled
	 * accordingly.
	 */
	public ChoicesPropertyDescriptorViewPart(ChoicesPropertyDescriptor descriptor, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose, int index) {
		this.descriptor = descriptor;
		this.choices = descriptor.getChoices();
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
		this.index = index;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Fragment description = bootstrapFactory.getHTMLFactory().fragment(StringEscapeUtils.escapeHtml4(descriptor.getDescription()));
		
		Status diagnosticStatus = null;

		List<String> invalidFeedback = new ArrayList<>();
		List<String> validFeedback = new ArrayList<>();
		if (diagnose) {
			Diagnostic diagnostic = descriptor.diagnose(progressMonitor);
			diagnosticStatus = diagnostic.getStatus();
			if (diagnosticStatus != Status.SUCCESS) {				
				for (Diagnostic child: diagnostic.getChildren()) {
					List<Object> childData = child.getData();
					if (!childData.isEmpty() 
							&& childData.get(0) == descriptor
							&& !Util.isBlank(child.getMessage())) {
						
						String htmlMessage = StringEscapeUtils.escapeHtml4(child.getMessage());
						if (child.getStatus() == Status.WARNING) {
							description.content(bootstrapFactory.alert(Color.WARNING, htmlMessage));							
						} else if (child.getStatus() == Status.ERROR) {
							description.content(bootstrapFactory.alert(Color.DANGER, htmlMessage));							
						} else if (child.getStatus() == Status.SUCCESS && !Util.isBlank(htmlMessage)) {
							description.content(bootstrapFactory.alert(Color.SUCCESS, htmlMessage));							
						}
					}
				}
			}
		}
		
		FormGroup ret = bootstrapFactory.formGroup(createLabel(), createControl(viewGenerator, diagnosticStatus, progressMonitor), description, horizontalLabelWidths);
		
		for (String ife: invalidFeedback) {
			ret.invalid(ife);
		}
		
		for (String vfe: validFeedback) {
			ret.valid(vfe);
		}
		
		return ret;
	}

	protected Object createLabel() {		
		String label = descriptor.getLabel();
		if (descriptor.getUpperBound() > 1) {
			label += " " + (index + 1);
		}
		// TODO - icon
		return index < descriptor.getLowerBound() ? TagName.b.create(label) : label;
	}
	
	/**
	 * For no choices creates a single checkbox with value "on", no label. For multiple choices creates a checkbox per choice.
	 * @param viewGenerator
	 * @param diagnosticStatus
	 * @param progressMonitor
	 * @return
	 */
	protected Object createCheckboxOrRadioControl(ViewGenerator viewGenerator, Status diagnosticStatus, InputType type, ProgressMonitor progressMonitor) {
		if (choices == null) {
			Input input = (Input) createInputControl(viewGenerator, diagnosticStatus, type, progressMonitor);
			input.addClass("form-check-input");
			return input;
		}
				
		List<Object> values = new ArrayList<>();
		Object pv = descriptor.get();
		if (pv != null) {
			if (pv.getClass().isArray()) {
				for (int i=0; i < Array.getLength(pv); ++i) {
					values.add(Array.get(pv, i));
				}
			} else if (pv instanceof Collection) {
				for (Object pvv: (Collection<?>) pv) {
					values.add(pvv);							
				}
			} else {
				values.add(pv);
			}
		}		
		
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		Fragment ret = htmlFactory.fragment();
		for (Descriptor choice: choices) {
			if (choice instanceof ChoiceDescriptor) {
				ret.content(createChoiceFormDiv(viewGenerator, (ChoiceDescriptor) choice, diagnosticStatus, type, values.contains(((ChoiceDescriptor) choice).get()), progressMonitor));
			} else {
				throw new UnsupportedOperationException("Unsupported choice type: " + choice);
			}
		}
		return ret;
	}	
	
	protected Tag createChoiceFormDiv(ViewGenerator viewGenerator, ChoiceDescriptor choice, Status diagnosticStatus, InputType type, boolean checked, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		Input input = htmlFactory.input(type);
		String name = descriptor.getName();
		if (type == InputType.radio && index > 0) {
			name += "-" + index;
		}
		input.name(name);
		input.required(type == InputType.radio && index < descriptor.getLowerBound());
		configureControl(input, diagnosticStatus);
		input.addClass("form-check-input");	
		Tag formCheckDiv = htmlFactory.div();
		formCheckDiv.addClass("form-check");
		formCheckDiv.content(input);
		input.value(choice.get());
		
		if (checked) {
			input.attribute("checked", true);
		}
		
		// TODO - Icon.
		formCheckDiv.content(htmlFactory.nonEmptyTag(TagName.label, choice.getLabel()).addClass("form-check-label"));			
		return formCheckDiv;
	}
	
	/**
	 * Creates a hidden input and a drop-down setting that input's value on selection.
	 * @param viewGenerator
	 * @param diagnosticStatus
	 * @param progressMonitor
	 * @return
	 */
	protected HTMLElement<?> createDropdownControl(ViewGenerator viewGenerator, Status diagnosticStatus, ProgressMonitor progressMonitor) {
		if (choices == null) {
			throw new IllegalArgumentException("drop-down shall be used for properties with choices");
		}
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Object value = descriptor.get();
		
		Select select = htmlFactory.select();
		for (Descriptor choice: choices) {
			if (choice instanceof ChoiceDescriptor) {
				ChoiceDescriptor choiceDescriptor = (ChoiceDescriptor) choice;
				Object choiceDescriptorValue = choiceDescriptor.get();
				select.option(
						DefaultConverter.INSTANCE.convert(choiceDescriptorValue, String.class), 
						choiceDescriptor.getLabel(),
						choiceDescriptorValue != null && choiceDescriptorValue.equals(value),
						false);
			} else if (choice instanceof DescriptorSet) {
				DescriptorSet choicesDescriptorSet = (DescriptorSet) choice;
				OptionGroup optionGroup = select.optionGroup(choicesDescriptorSet.getLabel());
				for (Descriptor groupChoice: choicesDescriptorSet.getDescriptors()) {
					if (groupChoice instanceof ChoiceDescriptor) {
						ChoiceDescriptor groupChoiceDescriptor = (ChoiceDescriptor) groupChoice;
						Object groupChoiceDescriptorValue = groupChoiceDescriptor.get();
						optionGroup.option(
								DefaultConverter.INSTANCE.convert(groupChoiceDescriptorValue, String.class), 
								groupChoiceDescriptor.getLabel(),
								groupChoiceDescriptorValue != null && groupChoiceDescriptorValue.equals(value),
								false);
					} else {
						throw new IllegalArgumentException("Unsupported option group choice descriptor: " + choice);
					}				
				}					
			} else {
				throw new IllegalArgumentException("Unsupported choice descriptor: " + choice);
			}				
		}
		select.name(descriptor.getName()).required(index < descriptor.getLowerBound());
		configureControl(select, diagnosticStatus);
		
		return select;		
	}	
	
	/**
	 * Creates a control based on input element for no choices and on select with choices
	 * @param viewGenerator
	 * @param diagnosticStatus
	 * @param progressMonitor
	 * @return
	 */
	protected HTMLElement<?> createInputControl(ViewGenerator viewGenerator, Status diagnosticStatus, InputType type, ProgressMonitor progressMonitor) {
		if (choices != null) {
			throw new IllegalArgumentException(type + " shall be used for properties without choices");
		}
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Object value = descriptor.get();
		Input control = htmlFactory.input(type);
		if (value != null) {
			control.value(value);
		}			
		
		control.name(descriptor.getName()).required(index < descriptor.getLowerBound());
		configureControl(control, diagnosticStatus);
		
		return control;		
	}	
	
	protected HTMLElement<?> createTextAreaControl(ViewGenerator viewGenerator, Status diagnosticStatus, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		TextArea control = htmlFactory.textArea();
		Object value = descriptor.get();
		if (value != null) {
			control.content(value);			
		}
		
		control.name(descriptor.getName()).required(index < descriptor.getLowerBound());		
		configureControl(control, diagnosticStatus);
		
		return control;		
	}

	protected void configureControl(InputBase<?> control, Status diagnosticStatus) {
		if (!descriptor.isEditable()) {
			control.disabled();
		}
		
		if (diagnosticStatus != null) {
			switch (diagnosticStatus) {
			case ERROR:
				control.addClass("is-invalid");
				break;
			case SUCCESS:
				control.addClass("is-valid");
				break;
			case WARNING:
				BootstrapFactory.INSTANCE.wrap(control).border(Color.WARNING);
				break;
			default:
				break;
			}
		}
	}	

	protected Object createControl(ViewGenerator viewGenerator, Status diagnosticStatus, ProgressMonitor progressMonitor) {
		if (descriptor.getControlHint() != null) {
			switch (descriptor.getControlHint()) {
			case CHECKBOX:
				return createCheckboxOrRadioControl(viewGenerator, diagnosticStatus, InputType.checkbox, progressMonitor);
			case DATE:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.date, progressMonitor);
			case DATE_TIME:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.datetime_local, progressMonitor);
			case DROP_DOWN:
				return createDropdownControl(viewGenerator, diagnosticStatus, progressMonitor);
			case FILE:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.file, progressMonitor);
			case NUMBER:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.number, progressMonitor);
			case PASSWORD:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.password, progressMonitor);
			case RADIO:
				return createCheckboxOrRadioControl(viewGenerator, diagnosticStatus, InputType.radio, progressMonitor);
			case TEXT:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.text, progressMonitor);
			case TEXT_AREA:
				return createTextAreaControl(viewGenerator, diagnosticStatus, progressMonitor);
			case TIME:
				return createInputControl(viewGenerator, diagnosticStatus, InputType.time, progressMonitor);
			default:
				throw new UnsupportedOperationException("Unsupported control type: " + descriptor.getControlHint());
			}
		}
		if (choices == null) {
			return createInputControl(viewGenerator, diagnosticStatus, InputType.text, progressMonitor);
		}
		return createDropdownControl(viewGenerator, diagnosticStatus, progressMonitor);
	}

}
