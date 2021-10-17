package org.nasdanika.html.app.factories;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.Category;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

public class CategorySupplierFactory extends LabelSupplierFactory<Category> {
		
	private StringSupplierFactoryAttribute path = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("path", false, false, null, null), true));
	private ListSupplierFactoryAttribute<Action,?> actions;
	
	public CategorySupplierFactory() {
		actions = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("actions", false, true, null, null), false));
	}

	@Override
	protected void setData(Context context, Map<Object, Object> data, LabelImpl label) {
		super.setData(context, data, label);
		Category category = (Category) label;
		if (path.isLoaded()) {
			category.setPath((String) path.get(data));
		}
		for (Action action: category.getActions()) {
			((ActionImpl) action).setCategory(category);
			if (!Util.isBlank(category.getPath()) && action.getActivator() instanceof PathNavigationActionActivator) {
				((PathNavigationActionActivator) action.getActivator()).getPath().addFirst(category.getPath());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Category createLabel(Context context, Map<Object, Object> data) {
		return new Category((Decorator) appearance.get(data), actions.isLoaded() ? (List<Action>) actions.get(data) : Collections.emptyList());
	}

}
