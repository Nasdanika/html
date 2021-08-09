/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Configuration of [bootstrap table](https://getbootstrap.com/docs/4.0/content/tables/)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isStriped <em>Striped</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isBordered <em>Bordered</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isBorderless <em>Borderless</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isHover <em>Hover</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isSmall <em>Small</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface TableConfiguration extends EObject {
	/**
	 * Returns the value of the '<em><b>Dark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dark table flag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dark</em>' attribute.
	 * @see #setDark(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Dark()
	 * @model
	 * @generated
	 */
	boolean isDark();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isDark <em>Dark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dark</em>' attribute.
	 * @see #isDark()
	 * @generated
	 */
	void setDark(boolean value);

	/**
	 * Returns the value of the '<em><b>Striped</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Striped table flag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Striped</em>' attribute.
	 * @see #setStriped(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Striped()
	 * @model
	 * @generated
	 */
	boolean isStriped();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isStriped <em>Striped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Striped</em>' attribute.
	 * @see #isStriped()
	 * @generated
	 */
	void setStriped(boolean value);

	/**
	 * Returns the value of the '<em><b>Bordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bordered table flag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bordered</em>' attribute.
	 * @see #setBordered(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Bordered()
	 * @model
	 * @generated
	 */
	boolean isBordered();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isBordered <em>Bordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bordered</em>' attribute.
	 * @see #isBordered()
	 * @generated
	 */
	void setBordered(boolean value);

	/**
	 * Returns the value of the '<em><b>Borderless</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Borderless table flag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Borderless</em>' attribute.
	 * @see #setBorderless(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Borderless()
	 * @model
	 * @generated
	 */
	boolean isBorderless();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isBorderless <em>Borderless</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Borderless</em>' attribute.
	 * @see #isBorderless()
	 * @generated
	 */
	void setBorderless(boolean value);

	/**
	 * Returns the value of the '<em><b>Hover</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If checked, rows change background on mouse pointer hover.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Hover</em>' attribute.
	 * @see #setHover(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Hover()
	 * @model
	 * @generated
	 */
	boolean isHover();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isHover <em>Hover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hover</em>' attribute.
	 * @see #isHover()
	 * @generated
	 */
	void setHover(boolean value);

	/**
	 * Returns the value of the '<em><b>Small</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Small table flag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Small</em>' attribute.
	 * @see #setSmall(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableConfiguration_Small()
	 * @model
	 * @generated
	 */
	boolean isSmall();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableConfiguration#isSmall <em>Small</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Small</em>' attribute.
	 * @see #isSmall()
	 * @generated
	 */
	void setSmall(boolean value);

} // TableConfiguration
