package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.FormGroup;

/**
 * Generates Bootstrap {@link FormGroup}. 
 * @author Pavel
 *
 */
public class ChoicesPropertyDescriptorViewPart implements ViewPart {
	
	private ChoicesPropertyDescriptor descriptor;
	private boolean diagnose;
	private Map<Breakpoint, Integer> horizontalLabelWidths;

	/**
	 * 
	 * @param descriptor Descriptor
	 * @param horizonalFormSize If not null a horizontal {@link FormGroup} is generated with specified control size.
	 * @param diagnose If true the descriptor is diagnosed, diagnostics are output in the form group and the group is styled
	 * accordingly.
	 */
	public ChoicesPropertyDescriptorViewPart(ChoicesPropertyDescriptor descriptor, Map<Breakpoint, Integer> horizontalLabelWidths, boolean diagnose) {
		this.descriptor = descriptor;
		this.horizontalLabelWidths = horizontalLabelWidths;
		this.diagnose = diagnose;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		// TODO - handle cardinality
		
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		FormGroup ret = bootstrapFactory.formGroup(descriptor.getLabel(), createInput(viewGenerator, progressMonitor), descriptor.getDescription(), horizontalLabelWidths);

		return ret;
	}

	protected HTMLElement<?> createInput(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
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
		Input ret = htmlFactory.input(InputType.text);
		// TODO
		
		return null;
	}

}
