package org.nasdanika.html.ecore.gen.processors;

import java.util.Map;
import java.util.function.BiFunction;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.Connection;
import org.nasdanika.graph.emf.EObjectNode;

/**
 * Connection from a EClass node to a generic type qualified by a typed element for which its type is reified in the context of the source EClass.
 * 
 * @author Pavel
 *
 */
public class ReifiedTypeConnection extends Connection {

	protected ReifiedTypeConnection(EObjectNode source, EObjectNode target, int index, String path) {
		super(source, target, index, path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
