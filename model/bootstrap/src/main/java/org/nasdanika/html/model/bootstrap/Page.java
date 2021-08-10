/**
 */
package org.nasdanika.html.model.bootstrap;

import org.nasdanika.html.bootstrap.Theme;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HTML page with bootstrap elements in the head - meta, stylesheets, and scripts.
 * 
 * [Overview video](https://www.youtube.com/watch?v=Q6u6hL10xXA) in Russian.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Page#isCdn <em>Cdn</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Page#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getPage()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='theme'"
 * @generated
 */
public interface Page extends org.nasdanika.html.model.html.Page {
	/**
	 * Returns the value of the '<em><b>Cdn</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is true (default) then a generated page contains stylesheet and script elements pointing to Bootstrap CDN (Content Delivery Network).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cdn</em>' attribute.
	 * @see #setCdn(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getPage_Cdn()
	 * @model default="true"
	 * @generated
	 */
	boolean isCdn();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Page#isCdn <em>Cdn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cdn</em>' attribute.
	 * @see #isCdn()
	 * @generated
	 */
	void setCdn(boolean value);

	/**
	 * Returns the value of the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bootstrap [theme](https://www.nasdanika.org/master/products/html/apidocs/org.nasdanika.html.bootstrap/apidocs/org/nasdanika/html/bootstrap/Theme.html). This attribute is applicable only if CDN is set to true. In this case Bootstrap stylesheets added to the page point to a specific theme.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Theme</em>' attribute.
	 * @see #setTheme(Theme)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getPage_Theme()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Theme"
	 * @generated
	 */
	Theme getTheme();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Page#getTheme <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Theme</em>' attribute.
	 * @see #getTheme()
	 * @generated
	 */
	void setTheme(Theme value);

} // Page
