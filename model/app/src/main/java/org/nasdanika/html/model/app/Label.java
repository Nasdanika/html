/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EObject;

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
 *   <li>{@link org.nasdanika.html.model.app.Label#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getNotification <em>Notification</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Label#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getLabel()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='color'"
 * @generated
 */
public interface Label extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Notification to display next to the label. E.g. a number of new messages in an inbox.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.html.model.app.Color}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Label bootstrap color.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see org.nasdanika.html.model.app.Color
	 * @see #setColor(org.nasdanika.html.model.app.Color)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Color()
	 * @model
	 * @generated
	 */
	org.nasdanika.html.model.app.Color getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see org.nasdanika.html.model.app.Color
	 * @see #getColor()
	 * @generated
	 */
	void setColor(org.nasdanika.html.model.app.Color value);

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

	/**
	 * Returns the value of the '<em><b>Children</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference.
	 * @see #setChildren(Label)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Children()
	 * @model
	 * @generated
	 */
	Label getChildren();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getChildren <em>Children</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Children</em>' reference.
	 * @see #getChildren()
	 * @generated
	 */
	void setChildren(Label value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Notification to display next to the label. E.g. a number of new messages in an inbox.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLabel_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Label#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

} // Label
