 package org.nasdanika.html.app.viewparts.descriptors;

import java.util.List;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
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

	/**
	 * 
	 * @param descriptor Descriptor
	 * @param horizonalFormSize If not null a horizontal {@link FormGroup} is generated with specified control size.
	 * @param diagnose If true the descriptor is diagnosed, diagnostics are output in the form group and the group is styled
	 * accordingly.
	 */
	public ChoicesPropertyDescriptorViewPart(ChoicesPropertyDescriptor descriptor, Map<Breakpoint, Size> horizontalLabelWidths, boolean diagnose, int index) {
		this.descriptor = descriptor;
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
		this.index = index;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		// TODO - handle enabled
		// TODO - handle cardinality
		
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Fragment description = bootstrapFactory.getHTMLFactory().fragment(StringEscapeUtils.escapeHtml4(descriptor.getDescription()));
		
		Status diagnosticStatus = null;

		if (diagnose) {
			Diagnostic diagnostic = descriptor.diagnose(progressMonitor);
			diagnosticStatus = diagnostic.getStatus();
			if (diagnosticStatus != Status.SUCCESS) {				
				for (Diagnostic child: diagnostic.getChildren()) {
					List<Object> childData = child.getData();
					if (!childData.isEmpty() 
							&& childData.get(0) == descriptor
							&& !Util.isBlank(child.getMessage())
							&& (child.getStatus() == Status.WARNING || child.getStatus() == Status.ERROR)) {
						
						Tag alert = bootstrapFactory.alert(child.getStatus() == Status.WARNING ? Color.WARNING : Color.DANGER, StringEscapeUtils.escapeHtml4(child.getMessage()));
						description.content(alert);
					}
				}
			}
		}
		
		FormGroup ret = bootstrapFactory.formGroup(createLabel(), createControl(viewGenerator, diagnosticStatus, progressMonitor), description, horizontalLabelWidths);
		
		
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

	protected HTMLElement<?> createControl(ViewGenerator viewGenerator, Status diagnosticStatus, ProgressMonitor progressMonitor) {
		if (descriptor.getControlHint() != null) {
			switch (descriptor.getControlHint()) {
			case CHECKBOX:
				break;
			case DATE:
				break;
			case DROP_DOWN:
				break;
			case FILE:
				break;
			case NUMBER:
				break;
			case PASSWORD:
				break;
			case RADIO:
				break;
			case TEXT:
				break;
			case TEXT_AREA:
				break;
			case TIME:
				break;			
			}
		}

		// Default - text or plain text for non-editable
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		// TODO
		
		Input control = htmlFactory.input(InputType.text).name(descriptor.getName()).required(index <= descriptor.getLowerBound());
		Object value = descriptor.get();
		if (value != null) {
			control.value(value);
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
		
		return control;
	}

}
