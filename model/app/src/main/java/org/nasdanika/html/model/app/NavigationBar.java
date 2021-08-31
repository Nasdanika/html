/**
 */
package org.nasdanika.html.model.app;

import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Bar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Navigation bar has a brand and navigation items.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#getBrand <em>Brand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#getExpand <em>Expand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#getBackground <em>Background</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar()
 * @model
 * @generated
 */
public interface NavigationBar extends PagePart {
	/**
	 * Returns the value of the '<em><b>Brand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Brand label displayed on the left.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Brand</em>' containment reference.
	 * @see #setBrand(Label)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Brand()
	 * @model containment="true"
	 * @generated
	 */
	Label getBrand();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationBar#getBrand <em>Brand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Brand</em>' containment reference.
	 * @see #getBrand()
	 * @generated
	 */
	void setBrand(Label value);

	/**
	 * Returns the value of the '<em><b>Dark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dark</em>' attribute.
	 * @see #setDark(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Dark()
	 * @model
	 * @generated
	 */
	boolean isDark();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationBar#isDark <em>Dark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dark</em>' attribute.
	 * @see #isDark()
	 * @generated
	 */
	void setDark(boolean value);

	/**
	 * Returns the value of the '<em><b>Expand</b></em>' attribute.
	 * The default value is <code>"LARGE"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expand</em>' attribute.
	 * @see #setExpand(Breakpoint)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Expand()
	 * @model default="LARGE" dataType="org.nasdanika.html.model.bootstrap.Breakpoint"
	 * @generated
	 */
	Breakpoint getExpand();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationBar#getExpand <em>Expand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expand</em>' attribute.
	 * @see #getExpand()
	 * @generated
	 */
	void setExpand(Breakpoint value);

	/**
	 * Returns the value of the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Background</em>' attribute.
	 * @see #setBackground(Color)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Background()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Color"
	 * @generated
	 */
	Color getBackground();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationBar#getBackground <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' attribute.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(Color value);

} // NavigationBar
