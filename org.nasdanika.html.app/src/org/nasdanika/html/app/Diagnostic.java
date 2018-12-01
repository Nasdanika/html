package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.bootstrap.Color;

/**
 * Result of validation.
 * @author Pavel Vlasov
 *
 */
public interface Diagnostic {
	
	Status getStatus();
	
	Object getMessage();
	
	List<Diagnostic> getChildren();
	
	/**
	 * Diagnostic source, e.g. a property descriptor or property id for property-level diagnostic or property source or null for
	 * object level diagnostic.
	 * @return
	 */
	Object getSource();

	default Label asLabel() {
		return new Label() {

			@Override
			public String getIcon() {
				return getStatus().icon;
			}

			@Override
			public String getText() {
				return getMessage().toString();
			}

			@Override
			public String getTooltip() {
				return null;
			}

			@Override
			public Color getColor() {
				return getStatus().color;
			}

			@Override
			public boolean isOutline() {
				return false;
			}

			@Override
			public String getDescription() {
				return null;
			}

			@Override
			public Object getId() {
				return null;
			}

			@Override
			public String getNotification() {
				return null;
			}
			
		};		
	}
}
