package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.bootstrap.Color;

public abstract class EStructuralFeatureMultiValuePropertySource extends EStructuralFeatureMultiValueDataSource implements PropertySource {

	protected ENamedElementLabel<EStructuralFeature> labelDelegate;

	public EStructuralFeatureMultiValuePropertySource(EObject eObject, EStructuralFeature feature) {
		super(eObject, feature);
		labelDelegate = new ENamedElementLabel<EStructuralFeature>(feature);
	}

	@Override
	public String getIcon() {
		return labelDelegate.getIcon();
	}

	@Override
	public String getText() {
		return labelDelegate.getText();
	}

	@Override
	public String getTooltip() {
		return labelDelegate.getTooltip();
	}

	@Override
	public Color getColor() {
		return labelDelegate.getColor();
	}

	@Override
	public boolean isOutline() {
		return labelDelegate.isOutline();
	}

	@Override
	public String getDescription() {
		return labelDelegate.getDescription();
	}

	@Override
	public Object getId() {
		return labelDelegate.getId();
	}

	@Override
	public String getNotification() {
		return labelDelegate.getNotification();
	}

	@Override
	public List<Action> getActions() {
		return Collections.emptyList();
	}

	@Override
	public List<Action> getActions(Object obj) {
		return Collections.emptyList();
	}

}
