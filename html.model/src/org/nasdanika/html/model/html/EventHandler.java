/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.cdo.CDOObject;

import org.nasdanika.html.Event;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Handler</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for event handlers.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.EventHandler#getEvent <em>Event</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getEventHandler()
 * @model abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface EventHandler extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Event to be handled.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event</em>' attribute.
	 * @see #setEvent(Event)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getEventHandler_Event()
	 * @model dataType="org.nasdanika.html.model.html.Event" required="true"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.EventHandler#getEvent <em>Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' attribute.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

} // EventHandler
