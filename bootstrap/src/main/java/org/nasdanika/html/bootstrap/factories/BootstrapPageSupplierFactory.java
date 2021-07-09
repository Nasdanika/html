package org.nasdanika.html.bootstrap.factories;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.factories.HTMLPageSupplierFactory;

public class BootstrapPageSupplierFactory extends HTMLPageSupplierFactory {
	
	private Attribute<Boolean> cdn = addFeature(new Attribute<Boolean>("cdn", false, false, true, null));
	private EnumSupplierFactoryAttribute<Theme> theme;

	public BootstrapPageSupplierFactory() {
		theme = addFeature(new EnumSupplierFactoryAttribute<Theme>(new StringSupplierFactoryAttribute(new Attribute<String>("theme", false, false, null, null), true), Theme.class, Theme.Default));
	}	
	
	@Override
	protected HTMLPage configure(Context context, Map<Object, Object> data, HTMLPage page, ProgressMonitor progressMonitor) {
		BootstrapFactory factory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);		
		if ((boolean) cdn.get(data)) {
			return factory.bootstrapCdnHTMLPage(page, (Theme) theme.get(data));
		} 
			
		if (theme.isLoaded()) {
			throw new ConfigurationException("Theme cannot be specified when 'cdn' is set to 'false'", theme.getMarker());			
		}
		
		return factory.bootstrapHTMLPage(page);
	}

}
