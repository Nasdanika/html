package org.nasdanika.html.app;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.nasdanika.common.Context;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Storable;
import org.nasdanika.html.bootstrap.Color;

/**
 * Something that can be displayed in the UI in a variety of ways - buttons, links, badges, alerts, ...
 * @author Pavel Vlasov
 */
public interface Label extends Identity {
	
	/**
	 * Binding of org.nasdanika.Supplier to {@link Action}
	 * @author Pavel
	 *
	 */
	interface Supplier<L extends Label> extends org.nasdanika.common.Supplier<L> {
		
		/**
		 * Wraps generic supplier in this strongly typed one.
		 * @param generic
		 */
		static <L extends Label> Supplier<L> from(org.nasdanika.common.Supplier<L> generic) {
			class SupplierImpl extends FilterExecutionParticipant<org.nasdanika.common.Supplier<L>> implements Supplier<L> {
	
				public SupplierImpl(org.nasdanika.common.Supplier<L> target) {
					super(target);
				}
	
				@Override
				public L execute(ProgressMonitor progressMonitor) throws Exception {
					return target.execute(progressMonitor);
				}
				
			}
			return new SupplierImpl(generic);			
		}
		
		/**
		 * Binding of {@link SupplierFactory} to {@link Supplier}
		 * @author Pavel
		 *
		 */
		interface Factory<L extends Label> extends SupplierFactory<L> {
			
			@Override
			Supplier<L> create(Context context) throws Exception;
			
			/**
			 * Wraps generic {@link SupplierFactory} in this strongly typed {@link Factory}
			 * @param generic
			 * @return
			 */
			static <L extends Label> Factory<L> from(SupplierFactory<L> generic) {
				return new Factory<L>() {
	
					@Override
					public Supplier<L> create(Context context) throws Exception {
						return Supplier.from(generic.create(context));
					}
					
				};
			}
			
		}
		
	}	
	
	
	/**
	 * Label icon. URL if contains slash, css class otherwise, e.g. 'far fa-user'.
	 * @return
	 */
	String getIcon();
	
	/**
	 * Icon text.
	 * @return
	 */
	String getText();
		
	/**
	 * @return Short help text, typically rendered as 'title' attribute.
	 */
	String getTooltip();
	
	/**
	 * @return Bootstrap color background or outline. Applicable only to bootstrap-based UI elements.
	 */
	Color getColor();
	
	/**
	 * Indicates that label shall be rendered as outline, not "solid" - see https://getbootstrap.com/docs/4.5/components/buttons/#outline-buttons 
	 * @return
	 */
	boolean isOutline();
		
	/**
	 * A detailed description which can be shown to the user in, say, a dialog box.
	 * @return
	 */	
	String getDescription();
	
	/**
	 * A notification, e.g. a number of e-mails in the inbox. Notifications are typically shown as badges.
	 * @return
	 */
	String getNotification();
	
	/**
	 * @param keyResolver Resolves label type to map key. E.g. to <code>app-label</code> for labels and <code>app-action</code> for actions.
	 * @return
	 */
	default Storable asStorable(Function<Label,String> keyResolver) {
		
		return new Storable() {
			
			/**
			 * Stores itself to a Map.
			 */
			@Override
			public Object store(URL base, ProgressMonitor progressMonitor) throws Exception {
				Map<String, Object> data = new LinkedHashMap<>();
				Color color = getColor();
				if (color != null) {
					data.put("color", color.code);
				}
				String description = getDescription();
				if (!Util.isBlank(description)) {
					data.put("description", description);
				}
				String icon = getIcon();
				if (!Util.isBlank(icon)) {
					data.put("icon", icon);
				}
				String notification = getNotification();
				if (!Util.isBlank(notification)) {
					data.put("notification", notification);
				}
				String text = getText();
				if (!Util.isBlank(text)) {
					data.put("text", text);
				}
				String tooltip = getTooltip();
				if (!Util.isBlank(tooltip)) {
					data.put("tooltip", tooltip);
				}
				if (isOutline()) {
					data.put("outline", true);
				}
				return Collections.singletonMap(keyResolver.apply(Label.this), data);
			}
			
		};
	}

}
