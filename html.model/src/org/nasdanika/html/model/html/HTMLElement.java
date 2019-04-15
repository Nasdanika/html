/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>HTML Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for HTML elements
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.HTMLElement#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.HTMLElement#getStyles <em>Styles</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.HTMLElement#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.HTMLElement#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.HTMLElement#getEventHandlers <em>Event Handlers</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement()
 * @model abstract="true"
 * @generated
 */
public interface HTMLElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element attributes. Style and class attributes can be specified using attribute properties or using styles and classes attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getAttributes();

	/**
	 * Returns the value of the '<em><b>Styles</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element styles. When HTML element is generated styles are generated into ``style`` attribute. If such attribute is already present the styles are combined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Styles</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement_Styles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getStyles();

	/**
	 * Returns the value of the '<em><b>Classes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element classes. When HTML element is generated classes are generated into ``class`` attribute. If such attribute is already present the classes are combined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classes</em>' attribute list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement_Classes()
	 * @model
	 * @generated
	 */
	EList<String> getClasses();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional element id. It is generated to ``id`` attribute. If such attribute is already specified this value takes precedence.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.HTMLElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Event Handlers</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.EventHandler}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Event handlers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Handlers</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHTMLElement_EventHandlers()
	 * @model containment="true"
	 * @generated
	 */
	EList<EventHandler> getEventHandlers();

} // HTMLElement
