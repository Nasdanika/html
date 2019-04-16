/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Content generator is a placeholder for content which shall be generated. Content generator description serves as a specification for implementing content generator. 
 * 
 * A sample scenario - when a model is generated into Java code content generator is generated as a method which throws UnsupportedOperationException and has content generator description as its javadoc.
 * Developers implement the method following the specification and remove or "dirty" ``@generated`` JavaDoc tag to prevent the generator overwriting it going forward.
 * 
 * Code generation may also create generation methods with pre-defined names, e.g. labels, view values and edit values for data source properties. In this case generators will reference those names.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ContentGenerator#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getContentGenerator()
 * @model
 * @generated
 */
public interface ContentGenerator extends Content {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content generator name may be used by a code generator as a basis for generator class/method name. For example, if generator name is ``mainframeFeed`` then it may result in generating a method called ``generateMainframeFeed``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getContentGenerator_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ContentGenerator#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // ContentGenerator
