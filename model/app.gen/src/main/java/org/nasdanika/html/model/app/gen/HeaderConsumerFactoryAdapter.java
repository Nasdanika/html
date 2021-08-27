package org.nasdanika.html.model.app.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.gen.content.TextSupplierFactoryAdapter;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.gen.AppearanceConsumerFactoryAdapter;

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
			cTitle.eAdapters().add(new LabelSupplierFactoryAdapter<Label>(cTitle));
			Appearance titleAppearance = BootstrapFactory.eINSTANCE.createAppearance();
			titleAppearance.eAdapters().add(new AppearanceConsumerFactoryAdapter(titleAppearance));
			cTitle.setAppearance(titleAppearance);
			Text cText = ContentFactory.eINSTANCE.createText();
			cText.eAdapters().add(new TextSupplierFactoryAdapter(cText));
			cText.setContent("nsd-app-header-title");
			titleAppearance.getAttributes().put("class", cText);
			ret.add(cTitle);
		}
		ret.addAll(super.getContent());
		
		return ret;
	}

}
