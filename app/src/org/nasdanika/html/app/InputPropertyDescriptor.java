package org.nasdanika.html.app;

import java.util.Map;

import org.nasdanika.html.InputBase;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.Size;

/**
 * {@link PropertyDescriptor} of an {@link InputProperty}.
 * @author Pavel Vlasov
 *
 */
public interface InputPropertyDescriptor extends PropertyDescriptor, InputProperty {
	
	/**
	 * Creates a form group.
	 * @param viewGenerator
	 * @param obj
	 * @return
	 */
	default FormGroup formGroup(ViewGenerator viewGenerator, Object obj, Map<Breakpoint, Size> horizontalLabelWidths) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		InputBase<?> input = createEditControl(viewGenerator, obj);
		return bootstrapFactory.formGroup(viewGenerator.label(this), input, getTooltip(), horizontalLabelWidths);
	}

}
