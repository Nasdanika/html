package org.nasdanika.html.bootstrap;

/**
 * Interface for styling text.
 * @author Pavel Vlasov
 *
 */
public interface Text<B> {
	
	B toBootstrapElement();
	
	Text<B> color(Color color);
	
	enum Alignment {
		LEFT, CENTER, RIGHT, JUSTIFY
	}
	
	Text<B> alignment(Alignment alignment);

	Text<B> alignment(DeviceSize deviceSize, Alignment alignment);
		
	enum Transform {
		LOWERCASE, UPPERCASE, CAPITALIZE
	}

	Text<B> transform(Transform transform);
	
	enum Weight {
		LIGHT, NORMAL, BOLD
	}
	
	Text<B> weight(Weight weight);
	
	Text<B> monospace();
	
	Text<B> monospace(boolean monospace);
	
	Text<B> italic();
	
	Text<B> italic(boolean italic);
	
	// TODO - nowrap, truncate
	
}
