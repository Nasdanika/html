package org.nasdanika.html.model.app.gen;

import java.util.List;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Navs;

public final class Util {
	
	// Util
	private Util() {
		throw new UnsupportedOperationException("Utility class, not to be instantiated");
	}

	public static Navs navs(List<Object> items, BootstrapFactory bootstrapFactory) {
		Navs navs = bootstrapFactory.navs();
		
		return navs;
	}
	
}


//@SuppressWarnings("unchecked")
//@Override
//public Navs categorizedLinkNavs(List<Action> actions, Action activeAction, Color textColor) {	
//	BootstrapFactory bootstrapFactory = get(BootstrapFactory.class);
//	Navs navs = bootstrapFactory.navs();
//	for (Entry<Label, ?> categoryGroup: Util.groupByCategory(actions)) {
//		Label category = categoryGroup.getKey();
//		if (category == null || (Util.isBlank(category.getText()) && Util.isBlank(category.getIcon()))) {
//			for (Action ca: (List<Action>) categoryGroup.getValue()) {
//				// Children are ignored if activator is not null.
//				Fragment fragment = get(HTMLFactory.class).fragment();
//				label(ca, (Consumer<Object>) fragment::content);
//				ActionActivator activator = ca.getActivator();
//				if (activator != null && activator.inline()) {						
//					ProgressMonitor progressMonitor = get(ProgressMonitor.class, get(ProgressMonitor.class, new NullProgressMonitor()));
//					navs.content(ca.generate(this, progressMonitor));
//				} else if (activator instanceof NavigationActionActivator) {
//					Tag item = navs.item(fragment, ((NavigationActionActivator) activator).getUrl(getString(BASE_URI_PROPERTY)), Util.equalOrInPath(activeAction, ca), ca.isDisabled());
//					if (!Util.isBlank(ca.getConfirmation())) {
//						item.on(Event.click, "return confirm('"+ca.getConfirmation()+"');");
//					}
//					if (textColor != null) {
//						bootstrapFactory.wrap(item).text().color(textColor);
//					}
//					decorate(item, ca);
//				} else if (activator instanceof ScriptActionActivator) {
//					String code = ((ScriptActionActivator) activator).getCode();
//					if (!Util.isBlank(ca.getConfirmation())) {
//						code = "if (confirm('"+ca.getConfirmation()+"')) { "+code+" }";
//					}
//					Tag item = navs.item(fragment, "#", Util.equalOrInPath(activeAction, ca), ca.isDisabled()).on(Event.click, code);
//					if (textColor != null) {
//						bootstrapFactory.wrap(item).text().color(textColor);
//					}
//					decorate(item, ca);
//				} else if (ca.getChildren().isEmpty()) {
//					// As text
//					Tag item = navs.item(fragment, "#", Util.equalOrInPath(activeAction, ca), ca.isDisabled());				
//					if (textColor != null) {
//						bootstrapFactory.wrap(item).text().color(textColor);
//					}
//					decorate(item, ca);
//				} else {
//					Dropdown dropdown = navs.dropdown(Util.equalOrInPath(activeAction, ca), fragment);				
//					if (textColor != null) {
//						dropdown.text().color(textColor);
//					}
//					decorate(dropdown.getToggle(), ca);						
//					for (Entry<Label, List<Action>> cats: ca.getChildrenGroupedByCategory()) {
//						if (cats.getKey() != null) {
//							if (Util.isBlank(cats.getKey().getIcon()) && Util.isBlank(cats.getKey().getText())) {
//								decorate(dropdown.divider(), cats.getKey());
//							} else {
//								decorate(dropdown.header(labelFragment(cats.getKey())), cats.getKey());
//							}
//						}
//						for (Action cac: cats.getValue()) {	
//							dropdown.item(link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
//						}
//					}
//				}
//			}
//		} else {
//			Dropdown dropdown = navs.dropdown(false, labelFragment(category));
//			if (textColor != null) {
//				dropdown.text().color(textColor);
//			}
//			decorate(dropdown.getToggle(), category);
//			for (Action cac: (List<Action>) categoryGroup.getValue()) {	
//				dropdown.item(link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
//			}
//		}
//	}
//	
//	return navs;
//}
