/**
 */
package org.nasdanika.html.model.app;

import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Application page consisting of several parts - header, navigation bar, navigation panel, content panel, and footer. Page extends Tag, but ``name`` attribute shall not be used - it defaults to ``div``.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Page#isFluid <em>Fluid</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getNavigationBar <em>Navigation Bar</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getNavigationPanel <em>Navigation Panel</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getContentPanel <em>Content Panel</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getFooter <em>Footer</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Page#getContentRowAppearance <em>Content Row Appearance</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Fluid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fluid</em>' attribute.
	 * @see #setFluid(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_Fluid()
	 * @model
	 * @generated
	 */
	boolean isFluid();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#isFluid <em>Fluid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fluid</em>' attribute.
	 * @see #isFluid()
	 * @generated
	 */
	void setFluid(boolean value);

	/**
	 * Returns the value of the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Page header is displayed on the top of the page.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Header</em>' containment reference.
	 * @see #setHeader(Header)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_Header()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	Header getHeader();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getHeader <em>Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' containment reference.
	 * @see #getHeader()
	 * @generated
	 */
	void setHeader(Header value);

	/**
	 * Returns the value of the '<em><b>Navigation Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation bar is displayed below the header.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation Bar</em>' containment reference.
	 * @see #setNavigationBar(NavigationBar)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_NavigationBar()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationBar getNavigationBar();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getNavigationBar <em>Navigation Bar</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Bar</em>' containment reference.
	 * @see #getNavigationBar()
	 * @generated
	 */
	void setNavigationBar(NavigationBar value);

	/**
	 * Returns the value of the '<em><b>Navigation Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation panel is positioned on the left of the content panel below the navigation bar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation Panel</em>' containment reference.
	 * @see #setNavigationPanel(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_NavigationPanel()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getNavigationPanel();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getNavigationPanel <em>Navigation Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Navigation Panel</em>' containment reference.
	 * @see #getNavigationPanel()
	 * @generated
	 */
	void setNavigationPanel(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Content Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content panel is positioned on the right of the navigation panel below the navigation bar.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content Panel</em>' containment reference.
	 * @see #setContentPanel(ContentPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_ContentPanel()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	ContentPanel getContentPanel();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getContentPanel <em>Content Panel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Panel</em>' containment reference.
	 * @see #getContentPanel()
	 * @generated
	 */
	void setContentPanel(ContentPanel value);

	/**
	 * Returns the value of the '<em><b>Footer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Footer</em>' containment reference.
	 * @see #setFooter(Footer)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_Footer()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	Footer getFooter();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getFooter <em>Footer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Footer</em>' containment reference.
	 * @see #getFooter()
	 * @generated
	 */
	void setFooter(Footer value);

	/**
	 * Returns the value of the '<em><b>Content Row Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Row Appearance</em>' containment reference.
	 * @see #setContentRowAppearance(Appearance)
	 * @see org.nasdanika.html.model.app.AppPackage#getPage_ContentRowAppearance()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	Appearance getContentRowAppearance();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Page#getContentRowAppearance <em>Content Row Appearance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Row Appearance</em>' containment reference.
	 * @see #getContentRowAppearance()
	 * @generated
	 */
	void setContentRowAppearance(Appearance value);

} // Page
