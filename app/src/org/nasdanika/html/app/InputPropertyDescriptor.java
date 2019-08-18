package org.nasdanika.html.app;

import java.util.Map;

import org.nasdanika.html.InputBase;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.FormGroup;

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
	default FormGroup formGroup(ViewGenerator viewGenerator, Object obj) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		InputBase<?> input = createEditControl(viewGenerator, obj);
		return bootstrapFactory.formGroup(viewGenerator.label(this), input, getTooltip(), getFormGroupHorizontalLabelWidths(obj));
	}
	
	default Map<DeviceSize, Integer> getFormGroupHorizontalLabelWidths(Object obj) {
		return null;		
	}
}
