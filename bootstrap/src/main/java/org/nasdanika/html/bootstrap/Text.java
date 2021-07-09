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

	Text<B> alignment(Breakpoint breakpoint, Alignment alignment);
		
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
	
	Text<B> nowrap();
	
	Text<B> nowrap(boolean nowrap);
	
	Text<B> truncate();
	
	Text<B> truncate(boolean truncate);
	
}
