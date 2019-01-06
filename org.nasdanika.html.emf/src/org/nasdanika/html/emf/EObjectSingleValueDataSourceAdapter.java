package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.SingleValueDataSource;

public class EObjectSingleValueDataSourceAdapter extends AdapterImpl implements SingleValueDataSource {

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
	public Object getValue() {
		return getTarget();
	}

}
