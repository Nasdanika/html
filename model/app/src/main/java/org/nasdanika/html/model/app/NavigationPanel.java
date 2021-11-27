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
 *   <li>{@link org.nasdanika.html.model.app.NavigationPanel#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.NavigationPanel#getLabelTrimLength <em>Label Trim Length</em>}</li>
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

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * ID for jsTree to store state between pages, e.g. expaned nodes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationPanel_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationPanel#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Label Trim Length</b></em>' attribute.
	 * The default value is <code>"50"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Trim Length</em>' attribute.
	 * @see #setLabelTrimLength(int)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationPanel_LabelTrimLength()
	 * @model default="50"
	 * @generated
	 */
	int getLabelTrimLength();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationPanel#getLabelTrimLength <em>Label Trim Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label Trim Length</em>' attribute.
	 * @see #getLabelTrimLength()
	 * @generated
	 */
	void setLabelTrimLength(int value);

} // NavigationPanel
