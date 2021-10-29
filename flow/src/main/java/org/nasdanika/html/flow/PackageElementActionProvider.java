package org.nasdanika.html.flow;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.html.emf.EObjectActionProvider;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreUtil;

public class PackageElementActionProvider<T extends PackageElement<?>> extends EObjectActionProvider<T> {
	
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500;

	protected Context context;
		
	public PackageElementActionProvider(T value, Context context) {
		super(value);		
		this.context = context;
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Action ret = super.createAction(registry, resolveConsumer, progressMonitor);		
		T eObj = getTarget();
		URI uri = NcoreUtil.getUri(eObj);
		String id = uri == null ? eObj.getUuid() : uri.toString();
		String digest = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(id.getBytes(StandardCharsets.UTF_8)));
		ret.setId(digest);
		
		String description = eObj.getDescription();
		addContent(ret, description);
		ret.setDescription(description);

		BiSupplier<EObject, String> cPath = NcoreUtil.containmentPath(eObj);
		if (cPath == null || Util.isBlank(cPath.getSecond())) {
			ret.setLocation("${base-uri}index.html");
		} else {
			ret.setLocation(cPath.getSecond() + "/index.html");
		}
		
		ret.setText(eObj.getName()); // Escape?
		ret.setSectionStyle(SectionStyle.HEADER);
		return ret;
	}

	@Override
	public String name() {
		return getTarget().getName();
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> ret = new ArrayList<>();
		ret.add(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENDS);
		ret.add(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENSIONS);
		ret.add(NcorePackage.Literals.MARKED__MARKER);
		ret.add(FlowPackage.Literals.PACKAGE_ELEMENT__MODIFIERS);
		ret.add(NcorePackage.Literals.MODEL_ELEMENT__URI);
//		ret.add(NcorePackage.Literals.MODEL_ELEMENT__UUID);
		return ret;
	}
	
	@Override
	protected Table createPropertiesTable(
			Action action,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		Table propertiesTable = super.createPropertiesTable(action, context, progressMonitor);
		propertiesTable.getAttributes().put("style", createText("width:auto"));
		return propertiesTable;
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		super.resolve(action, context, progressMonitor);
		
		// Adding documentation here so it appears under the properties table
		T eObj = getTarget();
		action.getContent().addAll(EcoreUtil.copyAll(eObj.getDocumentation()));			
	}
	
	@Override
	protected EObject renderValue(
			Action base, 
			ETypedElement typedElement, 
			Object value,
			org.nasdanika.html.emf.EObjectActionResolver.Context context, ProgressMonitor progressMonitor)
			throws Exception {

		if (value instanceof Marker) {
			Marker marker = (Marker) value;
			StringBuilder textBuilder = new StringBuilder(marker.getLocation());
			if (marker.getLine() > 0) {
				textBuilder.append(" ").append(marker.getLine());
				if (marker.getColumn() > 0) {
					textBuilder.append(":").append(marker.getColumn());
				}
			}
			return createText(textBuilder.toString());
		}
		return super.renderValue(base, typedElement, value, context, progressMonitor);
	}
	
}
