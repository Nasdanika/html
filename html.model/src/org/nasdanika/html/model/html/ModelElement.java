/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.cdo.CDOObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for other model elements featuring description and properties.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.ModelElement#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getModelElement()
 * @model abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface ModelElement extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Model element description. Descriptions become part of generated model documentation and serve as a specification for their elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Annotations</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element annotation can be used to provide extra information about the containng element not supported by its features. For example, if an element was generated from Ecore model its annotations may contain namespace URI of the package, EClass name, and structural feature name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Annotations</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getModelElement_Annotations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Property> getAnnotations();

} // ModelElement
