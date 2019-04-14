/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ResourceReference#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.ResourceReference#getRequestProperties <em>Request Properties</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceReference()
 * @model interface="true" abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface ResourceReference extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource location URL. Relative URL's are resolved with the model URL as the base. 
	 * Location may contain interpolation tokens - it allows to implement master-details relationships.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceReference_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ResourceReference#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Request Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Properties</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceReference_RequestProperties()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getRequestProperties();

} // ResourceReference
