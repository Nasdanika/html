package org.nasdanika.html.app;

import java.util.function.BiFunction;

import org.nasdanika.common.Context;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;

/**
 * {@link Context} / {@link ViewGenerator} service to be used by {@link ViewPart}s for finding actions by their ID's, e.g. for rendering links.
 * @author Pavel
 *
 */
public interface ActionRegistry {
	
	Action get(String id);
	
	static ActionRegistry fromAction(Action action) {
		return action.getRoot()::findById;
	}
	
	/**
	 * Creates a {@link BiFunction} which can be used in a computing context for property expansion, e.g. during interpolation.
	 * @return
	 */
	default PropertyComputer asPropertyComputer() {
		return new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context context, String key, Class<T> type) {
				return (T) ("TODO " + key);
//				Action action = ActionRegistry.this.get(key);
//				if (action == null) {
//					return null;
//				}
//				
//				if (subKey == null) {
//					Tag link = context.get(ViewGenerator.class, new ViewGeneratorImpl(context, null, null)).link(action);
//					return (T) (type.isAssignableFrom(Tag.class) ? link : link.toString());
//				}
//				
//				switch (subKey) {
//				case "text":
//					return (T) action.getText();
//				case "icon":
//					return (T) action.getIcon();
//				case "url":
//					ActionActivator activator = action.getActivator();
//					if (activator instanceof NavigationActionActivator) {
//						return (T) ((NavigationActionActivator) activator).getUrl(context.getString(Context.BASE_URI_PROPERTY));
//					}
//					return null;
//				default:
//					return null;
//				}
			}
		};
	}
	
}
