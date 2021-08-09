/**
 */
package org.nasdanika.html.model.components;

import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Of Contents Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for tables of content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.TableOfContentsBase#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TableOfContentsBase#getRole <em>Role</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TableOfContentsBase#getDepth <em>Depth</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContentsBase()
 * @model abstract="true"
 * @generated
 */
public interface TableOfContentsBase extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Header</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Table of contents header
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Header</em>' attribute.
	 * @see #setHeader(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContentsBase_Header()
	 * @model default=""
	 * @generated
	 */
	String getHeader();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TableOfContentsBase#getHeader <em>Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' attribute.
	 * @see #getHeader()
	 * @generated
	 */
	void setHeader(String value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Table of contents includes action children in the specified role - navigation or section.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see #setRole(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContentsBase_Role()
	 * @model default=""
	 * @generated
	 */
	String getRole();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TableOfContentsBase#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(String value);

	/**
	 * Returns the value of the '<em><b>Depth</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Table of contents depth.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Depth</em>' attribute.
	 * @see #setDepth(int)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContentsBase_Depth()
	 * @model default="3"
	 * @generated
	 */
	int getDepth();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TableOfContentsBase#getDepth <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Depth</em>' attribute.
	 * @see #getDepth()
	 * @generated
	 */
	void setDepth(int value);

} // TableOfContentsBase
