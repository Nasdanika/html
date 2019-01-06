package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.InputType;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.bootstrap.Color;

public class EStructuralFeaturePropertyDescriptor extends EStructuralFeatureProperty implements PropertyDescriptor {

	protected ENamedElementLabel<EStructuralFeature> label;

	public EStructuralFeaturePropertyDescriptor(EStructuralFeature feature) {
		super(feature);
		label = new ENamedElementLabel<EStructuralFeature>(feature);
	}

	@Override
	public String getIcon() {
		return label.getIcon();
	}

	@Override
	public String getText() {
		return label.getText();
	}

	@Override
	public String getTooltip() {
		return label.getDescription();
	}

	@Override
	public Color getColor() {
		return label.getColor();
	}

	@Override
	public boolean isOutline() {
		return label.isOutline();
	}

	@Override
	public String getDescription() {
		return label.getDescription();
	}

	@Override
	public Object getId() {
		return label.getId();
	}

	@Override
	public String getNotification() {
		return label.getNotification();
	}

	@Override
	public Label getCategory() {
		return null;
	}

	@Override
	public InputType getInputType() {
		return InputType.text;
	}

	@Override
	public boolean isSortable() {
		return false;
	}

	@Override
	public boolean isFilterable() {
		return false;
	}

	@Override
	public List<Action> getViewActions(Object obj) {
		return Collections.emptyList();
	}

	@Override
	public List<Action> getEditActions(Object obj) {
		return Collections.emptyList();
	}
	
}
