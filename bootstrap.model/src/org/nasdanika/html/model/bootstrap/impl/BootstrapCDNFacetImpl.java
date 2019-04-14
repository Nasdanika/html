/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.bootstrap.BootstrapCDNFacet;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.Page;
import org.nasdanika.html.model.html.impl.FacetImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CDN Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BootstrapCDNFacetImpl extends FacetImpl<Page> implements BootstrapCDNFacet {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BootstrapCDNFacetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.BOOTSTRAP_CDN_FACET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Theme getTheme() {
		return (Theme)eGet(BootstrapPackage.Literals.BOOTSTRAP_CDN_FACET__THEME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTheme(Theme newTheme) {
		eSet(BootstrapPackage.Literals.BOOTSTRAP_CDN_FACET__THEME, newTheme);
	}
	
	@Override
	public boolean isFacetFor(Page target) {
		for (Facet<Page> facet: target.getFacets()) {
			if (facet instanceof BootstrapCDNFacet) {
				return false; // Only one bootstrap cdn facet.
			}
		}
		return true;
	}

} //BootstrapCDNFacetImpl
