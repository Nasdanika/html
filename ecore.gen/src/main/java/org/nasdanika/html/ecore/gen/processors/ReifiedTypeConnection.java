package org.nasdanika.html.ecore.gen.processors;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
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

	public interface Factory {
	
		void create(EClassNode source, EGenericType genericType, Function<EObject, EObjectNode.ResultRecord> nodeFactory);
		
	}
	
	private EGenericType genericType;
	private boolean visitTargetNode;
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	ReifiedTypeConnection(EClassNode source, EObjectNode target, int index, String path, EGenericType genericType, boolean visitTargetNode) {
		super(source, target, index, path);
		this.genericType = genericType;
		this.visitTargetNode = visitTargetNode;
	}
	
	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, visitTargetNode ? Collections.singletonMap(getTarget(), getTarget().accept(visitor)) : Collections.emptyMap());
	}

	public EGenericType getGenericType() {
		return genericType;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + genericType;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), genericType);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			ReifiedTypeConnection other = (ReifiedTypeConnection) obj;
			return Objects.equals(genericType,  other.getGenericType());			
		}
		return false;
	}

}
