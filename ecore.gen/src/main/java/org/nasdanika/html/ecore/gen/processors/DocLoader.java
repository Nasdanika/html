package org.nasdanika.html.ecore.gen.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Composeable;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.ModelElement;

/**
 * Loads documentation actions from classpath resources
 * @author Pavel
 * @deprecated Use node processor factories
 */
@Deprecated
public class DocLoader implements Composeable<DocLoader> {
	
	protected Map<URI, Action> prototypes = new HashMap<>();
		
	public Action getPrototype(URI uri) {
		Action prototype = prototypes.get(uri);
		if (prototype == null) {
			return null;
		}
		Action copy = EcoreUtil.copy(prototype);
		copy.setUuid(UUID.randomUUID().toString());
		TreeIterator<EObject> cit = copy.eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof ModelElement) {
				((ModelElement) next).setUuid(UUID.randomUUID().toString());
			}
		}
		return copy;
	}

	@Override
	public DocLoader compose(DocLoader other) {
		if (other == null) {
			return this;
		}
		return new DocLoader() {
			
			@Override
			public Action getPrototype(URI uri) {
				Action ret = DocLoader.this.getPrototype(uri);
				return ret == null ? other.getPrototype(uri) : ret;
			}
			
		};
	}

}
