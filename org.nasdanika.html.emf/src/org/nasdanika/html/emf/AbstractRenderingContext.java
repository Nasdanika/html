package org.nasdanika.html.emf;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Base class for rendering contexts. 
 * Caches created renderers, authorization results, and resources.
 * Load resources from annotations.
 * @author Pavel Vlasov
 *
 */
public abstract class AbstractRenderingContext implements RenderingContext {
	
	protected Map<EClass, Renderer> eClassRenderers = new HashMap<>();
	protected Map<EObject, Renderer> eObjectRenderers = new HashMap<>();

	protected AbstractRenderingContext() {
		
	}

	@Override
	public Renderer getRenderer(EObject obj) {
		return eObjectRenderers.computeIfAbsent(obj, this::doGetRenderer);
	}

	@Override
	public Renderer getRenderer(EClass eClass) {
		return eClassRenderers.computeIfAbsent(eClass, this::doGetRenderer);
	}
	
	protected abstract Renderer doGetRenderer(EObject obj);

	protected abstract Renderer goGetRenderer(EClass eClass);

}
