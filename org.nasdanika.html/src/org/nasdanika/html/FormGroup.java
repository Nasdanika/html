package org.nasdanika.html;

public interface FormGroup<T extends FormGroup<T>> extends UIElement<T> {
	
	enum Status { 
		SUCCESS(Bootstrap.Style.SUCCESS), 
		WARNING(Bootstrap.Style.WARNING), 
		ERROR(Bootstrap.Style.DANGER);
		
		private Bootstrap.Style style;
		
		private Status(Bootstrap.Style style) {
			this.style = style;
		}
		
		/**
		 * @return Style corresponding to status.
		 */
		public Bootstrap.Style toStyle() {
			return style;
		}
		
	}

	T status(Status status);
	
	T feedback(boolean feedback);
	
	T feedback();
	
}