/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.Color;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Label is a base class for actions and categories.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Label#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getNotification <em>Notification</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getLabel()
 * @model abstract="true" superTypes="org.nasdanika.html.model.app.ILabel"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='color'"
 * @generated
 */
public interface Label extends EObject, org.nasdanika.html.app.Label {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Label bootstrap color.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(Color)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Color()
	 * @model dataType="org.nasdanika.html.model.app.Color"
	 * @generated
	 */
	Color getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(Color value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Label text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Label icon. Treated as URL if contains ``/`` or as a CSS class otherwise. E.g. ``fas fa-wrench`` would be treated as a CSS class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Icon</em>' attribute.
	 * @see #setIcon(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Icon()
	 * @model
	 * @generated
	 */
	String getIcon();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getIcon <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Icon</em>' attribute.
	 * @see #getIcon()
	 * @generated
	 */
	void setIcon(String value);

	/**
	 * Returns the value of the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Label tooltip.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tooltip</em>' attribute.
	 * @see #setTooltip(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Tooltip()
	 * @model
	 * @generated
	 */
	String getTooltip();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getTooltip <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tooltip</em>' attribute.
	 * @see #getTooltip()
	 * @generated
	 */
	void setTooltip(String value);

	/**
	 * Returns the value of the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For some label representations specifies that the label shall be displayed as an outline instead of a solid fill.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outline</em>' attribute.
	 * @see #setOutline(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Outline()
	 * @model
	 * @generated
	 */
	boolean isOutline();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#isOutline <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outline</em>' attribute.
	 * @see #isOutline()
	 * @generated
	 */
	void setOutline(boolean value);

	/**
	 * Returns the value of the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Notification to display next to the label. E.g. a number of new messages in an inbox.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Notification</em>' attribute.
	 * @see #setNotification(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Notification()
	 * @model
	 * @generated
	 */
	String getNotification();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getNotification <em>Notification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notification</em>' attribute.
	 * @see #getNotification()
	 * @generated
	 */
	void setNotification(String value);

} // Label
