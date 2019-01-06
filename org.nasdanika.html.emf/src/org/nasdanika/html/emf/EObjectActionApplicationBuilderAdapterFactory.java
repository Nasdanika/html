package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;

/**
 * Adapts {@link EObject} to {@link ApplicationBuilder}. This implementation adapts to {@link ActionApplicationBuilder}.
 * To do so it adapts the target to {@link Action} first.
 * @author Pavel Vlasov
 *
 */
public class EObjectActionApplicationBuilderAdapterFactory extends ComposeableAdapterFactoryImpl {
	
	@Override
	public boolean isFactoryForType(Object type) {
		return ApplicationBuilder.class == type;
	}
		
	@Override
	protected Adapter createAdapter(Notifier target) {
		ViewAction action = (ViewAction) EcoreUtil.getRegisteredAdapter((EObject) target, ViewAction.class);
		if (action == null) {
			return null;
		}
		
		if (action.getPath() == null || action.getPath().isEmpty()) {
			// Just the root action, children as the navigation panel, no navigation bar.
			return new ApplicationBuilderAdapter(createApplicationBuilder(target, action, null, action.getChildren(), action));
		}
		
		if (action.getPath().size() == 1) {
			// Just the principal action is the active action.
			return new ApplicationBuilderAdapter(createApplicationBuilder(target, action.getParent(), action, action.getChildren(), action));
		}
		
		Action principalAction = action.getPath().get(1);
		return new ApplicationBuilderAdapter(createApplicationBuilder(target, action.getPath().get(0), principalAction, principalAction.getChildren(), action));
	}

	protected ActionApplicationBuilder createApplicationBuilder(
			Notifier target,
			Action rootAction, 
			Action principalAction, 
			List<? extends Action> navigationPanelActions, 
			Action activeAction) {
		
		return new ActionApplicationBuilder(rootAction, principalAction, navigationPanelActions, activeAction, getInput(target)) {
			
			@Override
			protected ViewPart getHeaderViewPart() {
				ViewPart headerViewPart = EObjectActionApplicationBuilderAdapterFactory.this.getHeaderViewPart(target);
				return headerViewPart != null ? headerViewPart : super.getHeaderViewPart();
			}
			
			@Override
			protected ViewPart getNavigationBarViewPart() {
				ViewPart navigationBarViewPart = EObjectActionApplicationBuilderAdapterFactory.this.getNavigationBarViewPart(target);
				return navigationBarViewPart != null ? navigationBarViewPart : super.getNavigationBarViewPart();
			}
			
			@Override
			protected ViewPart getNavigationPanelViewPart() {
				ViewPart navigationPanelViewPart = EObjectActionApplicationBuilderAdapterFactory.this.getNavigationPanelViewPart(target);
				return navigationPanelViewPart != null ? navigationPanelViewPart : super.getNavigationPanelViewPart();
			}
			
			@Override
			protected ViewPart getContentPanelViewPart() {
				ViewPart contentPanelViewPart = EObjectActionApplicationBuilderAdapterFactory.this.getContentPanelViewPart(target);
				return contentPanelViewPart != null ? contentPanelViewPart : super.getContentPanelViewPart();
			}
			
			@Override
			protected ViewPart getFooterViewPart() {
				ViewPart footerViewPart = EObjectActionApplicationBuilderAdapterFactory.this.getFooterViewPart(target);
				return footerViewPart != null ? footerViewPart : super.getFooterViewPart();
			}
			
		};
	}
	
	protected ViewPart getHeaderViewPart(Notifier target) {
		return (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, HeaderViewPart.class);
	}
	
	protected ViewPart getNavigationBarViewPart(Notifier target) {
		return (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, NavigationBarViewPart.class);		
	}
	
	protected ViewPart getNavigationPanelViewPart(Notifier target) {
		return (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, NavigationPanelViewPart.class);
	}
	
	protected ViewPart getContentPanelViewPart(Notifier target) {
		return (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, ContentPanelViewPart.class);		
	}
	
	protected ViewPart getFooterViewPart(Notifier target) {
		return (ViewPart) EcoreUtil.getRegisteredAdapter((EObject) target, FooterViewPart.class);		
	}
	
	
	/**
	 * Override to provide action input, e.g. by extracting it from HTTP request parameters.
	 * @param target
	 * @return
	 */
	protected Map<String, Object> getInput(Notifier target) {
		return Collections.emptyMap();
	}

}
