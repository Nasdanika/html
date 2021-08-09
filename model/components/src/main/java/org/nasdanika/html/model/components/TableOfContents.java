/**
 */
package org.nasdanika.html.model.components;

import org.nasdanika.html.model.bootstrap.TableConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Of Contents</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Containing action content rendered in a table.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.TableOfContents#isDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TableOfContents#isTooltips <em>Tooltips</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContents()
 * @model
 * @generated
 */
public interface TableOfContents extends TableOfContentsBase, TableConfiguration {
	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If selected, action descriptions are shown in the table of contents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' attribute.
	 * @see #setDescriptions(boolean)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContents_Descriptions()
	 * @model default="false"
	 * @generated
	 */
	boolean isDescriptions();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TableOfContents#isDescriptions <em>Descriptions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Descriptions</em>' attribute.
	 * @see #isDescriptions()
	 * @generated
	 */
	void setDescriptions(boolean value);

	/**
	 * Returns the value of the '<em><b>Tooltips</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If selected and "descriptions" is not selected, action tooltips are shown in the table of contents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tooltips</em>' attribute.
	 * @see #setTooltips(boolean)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTableOfContents_Tooltips()
	 * @model default="false"
	 * @generated
	 */
	boolean isTooltips();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TableOfContents#isTooltips <em>Tooltips</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tooltips</em>' attribute.
	 * @see #isTooltips()
	 * @generated
	 */
	void setTooltips(boolean value);

} // TableOfContents
