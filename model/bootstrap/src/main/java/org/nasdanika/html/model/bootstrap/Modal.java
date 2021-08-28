/**
 */
package org.nasdanika.html.model.bootstrap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#getFooter <em>Footer</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#isDismisser <em>Dismisser</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#isScrollable <em>Scrollable</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#isCentered <em>Centered</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Modal#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal()
 * @model
 * @generated
 */
public interface Modal extends BootstrapElement {

	/**
	 * Returns the value of the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modal header content and appearance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Header</em>' containment reference.
	 * @see #setHeader(BootstrapElement)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Header()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	BootstrapElement getHeader();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#getHeader <em>Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' containment reference.
	 * @see #getHeader()
	 * @generated
	 */
	void setHeader(BootstrapElement value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modal body content and appearance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(BootstrapElement)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Body()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	BootstrapElement getBody();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(BootstrapElement value);

	/**
	 * Returns the value of the '<em><b>Footer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modal footer content and appearance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Footer</em>' containment reference.
	 * @see #setFooter(BootstrapElement)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Footer()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	BootstrapElement getFooter();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#getFooter <em>Footer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Footer</em>' containment reference.
	 * @see #getFooter()
	 * @generated
	 */
	void setFooter(BootstrapElement value);

	/**
	 * Returns the value of the '<em><b>Dismisser</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dismisser</em>' attribute.
	 * @see #setDismisser(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Dismisser()
	 * @model default="true"
	 * @generated
	 */
	boolean isDismisser();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#isDismisser <em>Dismisser</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dismisser</em>' attribute.
	 * @see #isDismisser()
	 * @generated
	 */
	void setDismisser(boolean value);

	/**
	 * Returns the value of the '<em><b>Scrollable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scrollable</em>' attribute.
	 * @see #setScrollable(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Scrollable()
	 * @model
	 * @generated
	 */
	boolean isScrollable();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#isScrollable <em>Scrollable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scrollable</em>' attribute.
	 * @see #isScrollable()
	 * @generated
	 */
	void setScrollable(boolean value);

	/**
	 * Returns the value of the '<em><b>Centered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Centered</em>' attribute.
	 * @see #setCentered(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Centered()
	 * @model
	 * @generated
	 */
	boolean isCentered();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#isCentered <em>Centered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Centered</em>' attribute.
	 * @see #isCentered()
	 * @generated
	 */
	void setCentered(boolean value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modal size - ``small``, ``large``, or ``extra-large``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getModal_Size()
	 * @model default=""
	 * @generated
	 */
	String getSize();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Modal#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(String value);
} // Modal
