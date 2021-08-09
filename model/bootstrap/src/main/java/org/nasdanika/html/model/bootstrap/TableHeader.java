/**
 */
package org.nasdanika.html.model.bootstrap;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Table header.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableHeader#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableHeader#isLight <em>Light</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableHeader()
 * @model
 * @generated
 */
public interface TableHeader extends TableSection {
	/**
	 * Returns the value of the '<em><b>Dark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dark header (mutually exclusive with light).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dark</em>' attribute.
	 * @see #setDark(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableHeader_Dark()
	 * @model
	 * @generated
	 */
	boolean isDark();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableHeader#isDark <em>Dark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dark</em>' attribute.
	 * @see #isDark()
	 * @generated
	 */
	void setDark(boolean value);

	/**
	 * Returns the value of the '<em><b>Light</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Light header (mutually exclusive with dark).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Light</em>' attribute.
	 * @see #setLight(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableHeader_Light()
	 * @model
	 * @generated
	 */
	boolean isLight();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableHeader#isLight <em>Light</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Light</em>' attribute.
	 * @see #isLight()
	 * @generated
	 */
	void setLight(boolean value);

} // TableHeader
