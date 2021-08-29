package org.nasdanika.html.model.app.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.Appearance;

public class HeaderConsumerFactoryAdapter extends PagePartConsumerFactoryAdapter<Header> {

	protected HeaderConsumerFactoryAdapter(Header header) {
		super(header);
	}
	
	@Override
	protected List<EObject> getContent() {		
		List<EObject> ret = new ArrayList<>();
		Label title = getTarget().getTitle();
		if (title != null) {
			Label cTitle = EcoreUtil.copy(title);
			cTitle.eAdapters().add(AppAdapterFactory.INSTANCE.adapt(cTitle, SupplierFactory.class));
			
			Appearance titleAppearance = org.nasdanika.html.model.bootstrap.BootstrapFactory.eINSTANCE.createAppearance();
			titleAppearance.eAdapters().add(AppAdapterFactory.INSTANCE.adapt(titleAppearance, ConsumerFactory.class));
			cTitle.setAppearance(titleAppearance);
			
			Text cText = ContentFactory.eINSTANCE.createText();
			cText.eAdapters().add(AppAdapterFactory.INSTANCE.adapt(cText, SupplierFactory.class));
			cText.setContent("nsd-app-header-title");
			titleAppearance.getAttributes().put("class", cText);
			ret.add(cTitle);
		}
		ret.addAll(super.getContent());		
		return ret;
	}
	
	private Function<BiSupplier<HTMLElement<?>, List<Object>>, HTMLElement<?>> createNavsFunction(Context context) {
		return new Function<BiSupplier<HTMLElement<?>,List<Object>>, HTMLElement<?>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Navs";
			}
			
			@Override
			public HTMLElement<?> execute(BiSupplier<HTMLElement<?>, List<Object>> input, ProgressMonitor progressMonitor) throws Exception {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Navs navs = Util.navs(input.getSecond(), bootstrapFactory);
				navs.toHTMLElement().addClass("nsd-app-header-navs");
				
				navs.item("Test", "Grum");
				
				((Tag) input.getFirst()).content(navs);				
				return input.getFirst();
			}
		};
	}
	
	/**
	 * Adapts items to suppliers, builds {@link Navs} from items and adds them to the header.
	 */
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		EList<EObject> items = getTarget().getItems();
		if (items.isEmpty()) {
			return super.createConfigureFunction(context);
		}
		Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, List<Object>>> itemsFunction = new ListCompoundSupplierFactory<>("Items", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, Object.class)).<HTMLElement<?>>asFunctionFactory().create(context);
		return super.createConfigureFunction(context).then(itemsFunction).then(createNavsFunction(context));
	}

}
