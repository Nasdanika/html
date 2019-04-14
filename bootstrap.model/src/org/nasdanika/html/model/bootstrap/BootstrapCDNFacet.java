/**
 */
package org.nasdanika.html.model.bootstrap;

import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.Page;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CDN Facet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Page facet for adding CDN Bootstrap and Bootswatch stylesheets and scripts to the page for a specific Bootstrap or Bootswatch theme.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.BootstrapCDNFacet#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBootstrapCDNFacet()
 * @model
 * @generated
 */
public interface BootstrapCDNFacet extends Facet<Page> {

	/**
	 * Returns the value of the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bootstrap theme for the page.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Theme</em>' attribute.
	 * @see #setTheme(Theme)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBootstrapCDNFacet_Theme()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Theme"
	 * @generated
	 */
	Theme getTheme();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.BootstrapCDNFacet#getTheme <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Theme</em>' attribute.
	 * @see #getTheme()
	 * @generated
	 */
	void setTheme(Theme value);
} // BootstrapCDNFacet
