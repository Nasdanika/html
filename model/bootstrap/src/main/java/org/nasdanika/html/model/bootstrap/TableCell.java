/**
 */
package org.nasdanika.html.model.bootstrap;

import org.nasdanika.html.model.html.Container;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Cell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Table cell - regular or header.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableCell#isHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableCell#getColSpan <em>Col Span</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableCell#getRowSpan <em>Row Span</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableCell#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableCell#getBackground <em>Background</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell()
 * @model
 * @generated
 */
public interface TableCell extends Container, BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, table cell is generated as ``<th>`` instead of the default ``<td>``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Header</em>' attribute.
	 * @see #setHeader(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell_Header()
	 * @model
	 * @generated
	 */
	boolean isHeader();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableCell#isHeader <em>Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' attribute.
	 * @see #isHeader()
	 * @generated
	 */
	void setHeader(boolean value);

	/**
	 * Returns the value of the '<em><b>Col Span</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Column span for the cell.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Col Span</em>' attribute.
	 * @see #setColSpan(int)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell_ColSpan()
	 * @model
	 * @generated
	 */
	int getColSpan();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableCell#getColSpan <em>Col Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Col Span</em>' attribute.
	 * @see #getColSpan()
	 * @generated
	 */
	void setColSpan(int value);

	/**
	 * Returns the value of the '<em><b>Row Span</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Row span for the cell.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Row Span</em>' attribute.
	 * @see #setRowSpan(int)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell_RowSpan()
	 * @model
	 * @generated
	 */
	int getRowSpan();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableCell#getRowSpan <em>Row Span</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Span</em>' attribute.
	 * @see #getRowSpan()
	 * @generated
	 */
	void setRowSpan(int value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cell color.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell_Color()
	 * @model
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableCell#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cell background color. Displays differently from "Color". Can also be specified via appearance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Background</em>' attribute.
	 * @see #setBackground(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableCell_Background()
	 * @model
	 * @generated
	 */
	String getBackground();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.TableCell#getBackground <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' attribute.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(String value);

} // TableCell
