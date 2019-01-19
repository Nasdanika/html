package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.AbstractActionApplicationBuilder;

/**
 * Generates application by adapting the target to {@link ViewAction}
 * and then delegating to view part adapters and falling back to super behavior if there are no registered adapters.
 * @author Pavel
 *
 */
public class ViewActionApplicationBuilder<T extends EObject> extends AbstractActionApplicationBuilder {
				
	protected T target;
	
	public ViewActionApplicationBuilder(T target) {
		this.target = target;
	}
	
	@Override
	protected Action getActiveAction() {
		return (Action) EcoreUtil.getRegisteredAdapter(target, ViewAction.class);
	}	
		
	@Override
	protected ViewPart getHeaderViewPart() {
		ViewPart headerViewPart = (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, HeaderViewPart.class);
		return headerViewPart != null ? headerViewPart : super.getHeaderViewPart();
	}
	
	@Override
	protected ViewPart getNavigationBarViewPart() {
		ViewPart navigationBarViewPart = (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, NavigationBarViewPart.class);
		return navigationBarViewPart != null ? navigationBarViewPart : super.getNavigationBarViewPart();
	}
	
	@Override
	protected ViewPart getNavigationPanelViewPart() {
		ViewPart navigationPanelViewPart = (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, NavigationPanelViewPart.class);
		return navigationPanelViewPart != null ? navigationPanelViewPart : super.getNavigationPanelViewPart();
	}
	
	@Override
	protected ViewPart getContentPanelViewPart() {
		ViewPart contentPanelViewPart = (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, ContentPanelViewPart.class);
		return contentPanelViewPart != null ? contentPanelViewPart : super.getContentPanelViewPart();
	}
	
	@Override
	protected ViewPart getFooterViewPart() {
		ViewPart footerViewPart = (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, FooterViewPart.class);
		return footerViewPart != null ? footerViewPart : super.getFooterViewPart();
	}
		
};
