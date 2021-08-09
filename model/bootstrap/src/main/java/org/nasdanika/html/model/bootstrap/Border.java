/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Border</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Border configuration specifies border location and color.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Border#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Border#isTop <em>Top</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Border#isBottom <em>Bottom</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Border#isLeft <em>Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Border#isRight <em>Right</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='placement color'"
 * @generated
 */
public interface Border extends EObject {
	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Border bootstrap color.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder_Color()
	 * @model required="true"
	 * @generated
	 */
	String getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Border#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(String value);

	/**
	 * Returns the value of the '<em><b>Top</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Top border.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Top</em>' attribute.
	 * @see #setTop(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder_Top()
	 * @model default="true"
	 * @generated
	 */
	boolean isTop();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Border#isTop <em>Top</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Top</em>' attribute.
	 * @see #isTop()
	 * @generated
	 */
	void setTop(boolean value);

	/**
	 * Returns the value of the '<em><b>Bottom</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bottom border.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bottom</em>' attribute.
	 * @see #setBottom(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder_Bottom()
	 * @model default="true"
	 * @generated
	 */
	boolean isBottom();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Border#isBottom <em>Bottom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bottom</em>' attribute.
	 * @see #isBottom()
	 * @generated
	 */
	void setBottom(boolean value);

	/**
	 * Returns the value of the '<em><b>Left</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Left border.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left</em>' attribute.
	 * @see #setLeft(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder_Left()
	 * @model default="true"
	 * @generated
	 */
	boolean isLeft();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Border#isLeft <em>Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' attribute.
	 * @see #isLeft()
	 * @generated
	 */
	void setLeft(boolean value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Righ border.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right</em>' attribute.
	 * @see #setRight(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBorder_Right()
	 * @model default="true"
	 * @generated
	 */
	boolean isRight();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Border#isRight <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' attribute.
	 * @see #isRight()
	 * @generated
	 */
	void setRight(boolean value);

} // Border
