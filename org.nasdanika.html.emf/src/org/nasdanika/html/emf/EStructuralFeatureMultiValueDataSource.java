package org.nasdanika.html.emf;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.MultiValueDataSource;

public class EStructuralFeatureMultiValueDataSource implements MultiValueDataSource {
	
	private EStructuralFeature feature;
	private EObject eObject;

	public EStructuralFeatureMultiValueDataSource(EObject eObject, EStructuralFeature feature) {
		if (!feature.isMany()) {
			throw new IllegalArgumentException("Single feature");
		}
		this.eObject = eObject;
		this.feature = feature;
	}

	@Override
	public Object getVersion(Object obj) {
		if (obj instanceof CDOObject) {
			return ((CDOObject) obj).cdoRevision();			
		}
		
		if (obj instanceof EObject) {
			return ((EObject) obj).eResource().getTimeStamp();
		}
		
		return null;
	}

	@Override
	public Diagnostic update(Object obj, Object version, List<Delta> deltas) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public List<Object> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getValues(Map<Object, String> filter, Map<Object, Boolean> sort) {
		return (List<Object>) eObject.eGet(feature);
	}

}
