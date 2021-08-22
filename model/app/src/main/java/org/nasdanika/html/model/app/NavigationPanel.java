/**
 */
package org.nasdanika.html.model.app;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Navigation panel contains navigation items. Supports several styles.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.NavigationPanel#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getNavigationPanel()
 * @model
 * @generated
 */
public interface NavigationPanel extends PagePart {
	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * The default value is <code>"Auto"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.html.model.app.NavigationPanelStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Panel style
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.NavigationPanelStyle
	 * @see #setStyle(NavigationPanelStyle)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationPanel_Style()
	 * @model default="Auto"
	 * @generated
	 */
	NavigationPanelStyle getStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationPanel#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.NavigationPanelStyle
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(NavigationPanelStyle value);

} // NavigationPanel
