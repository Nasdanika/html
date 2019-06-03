package org.nasdanika.html.ecore;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.ecore.EPackage;

public class EPackageViewAction extends ENamedElementViewAction<EPackage> {

	public EPackageViewAction(EPackage value) {
		super(value);
	}
	
	/**
	 * Encodes package NS URI as HEX.
	 */
	@Override
	public Object getId() {
		return Hex.encodeHexString(((EPackage) getValue()).getNsURI().getBytes(StandardCharsets.UTF_8));
	}

}
