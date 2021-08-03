package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Select;
import org.nasdanika.html.Select.OptionGroup;

/**
 * Property which creates controls which are subtypes of {@link InputBase} 
 * @author Pavel
 *
 */
public interface InputProperty extends Property {

	InputType getInputType(Object obj);
	
	/**
	 * Property value to use in input "value" attribute.  
	 * @param obj Value object for single value property sources and collection element for multi-value property sources.
	 * @return
	 */
	Object getEditValue(Object obj);
	
	@Override
	default InputBase<?> createEditControl(ViewGenerator viewGenerator, Object obj) {
		HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
		Object editValue = getEditValue(obj);		
		InputBase<?> control = getInputType(obj).create(htmlFactory, editValue);
		control.name(getPropertyName());
		if (control instanceof Select) {
			Select select = (Select) control;
			List<Choice> choices = getChoices(obj);
			for (Choice choice: choices) {
				Object choiceValue = choice.getValue();
				if (choiceValue == null) {
					if (choice.getChildren().isEmpty()) {
						select.option(null, editValue == null, true, choice.getText());						
					} else {
						OptionGroup optionGroup = select.optionGroup(choice.getText());
						for (Choice subChoice: choice.getChildren()) {
							Object subChoiceValue = subChoice.getValue();
							optionGroup.option(subChoiceValue == null ? null : subChoiceValue.toString(), editValue == subChoiceValue, subChoiceValue == null, subChoice.getText());
						}
					}
				} else {
					select.option(choiceValue.toString(), editValue == choiceValue, false, choice.getText());
					
				}				
			}						
		}
		return control;
	}
	
	
}
