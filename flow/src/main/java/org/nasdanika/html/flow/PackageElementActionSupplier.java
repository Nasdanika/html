package org.nasdanika.html.flow;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.ncore.impl.ModelElementImpl;

public class PackageElementActionSupplier<T extends PackageElement<?>> extends EObjectActionSupplier<T> {
	
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500;

	protected Context context;
		
	public PackageElementActionSupplier(T value, Context context) {
		super(value);		
		this.context = context;
	}

	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action ret = AppFactory.eINSTANCE.createAction();		
		ret.getContent().addAll(EcoreUtil.copyAll(eObject.getDocumentation()));	
		String digest = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(eObject.getUri().getBytes(StandardCharsets.UTF_8)));
		ret.setId(digest);

		String cPath = ModelElementImpl.containmentPath(eObject);
		if (Util.isBlank(cPath)) {
			ret.setLocation("${base-uri}index.html");
		} else {
			ret.setLocation(cPath + "/index.html");
		}
		
		ret.setText(eObject.getName()); // Escape?
		return ret;
	}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return eObject.getName();
	}
	
	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		action.getContent().add(text);
	}
	
}
