package org.nasdanika.html;

public interface TextArea extends InputBase<TextArea>, Container<TextArea> {
	
	TextArea cols(int cols);
	
	TextArea rows(int rows);
	
	TextArea maxLength(int maxLength);
	
	TextArea placeholder(Object placeholder);
	
	TextArea readonly();
	TextArea readonly(boolean readonly);

	TextArea wrap();
	TextArea wrap(boolean wrap);

}
