/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Spacing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Spacing - padding or margin. Specifies size, location, and breakpoint.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#getBreakpoint <em>Breakpoint</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isTop <em>Top</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isBottom <em>Bottom</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isLeft <em>Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isRight <em>Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isX <em>X</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Spacing#isY <em>Y</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='size breakpoint'"
 * @generated
 */
public interface Spacing extends EObject {
	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Spacing size - from 0 to 5 or auto.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(Size)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Size()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Size" required="true"
	 * @generated
	 */
	Size getSize();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(Size value);

	/**
	 * Returns the value of the '<em><b>Breakpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Spacing breakpoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Breakpoint</em>' attribute.
	 * @see #setBreakpoint(Breakpoint)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Breakpoint()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Breakpoint"
	 * @generated
	 */
	Breakpoint getBreakpoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#getBreakpoint <em>Breakpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Breakpoint</em>' attribute.
	 * @see #getBreakpoint()
	 * @generated
	 */
	void setBreakpoint(Breakpoint value);

	/**
	 * Returns the value of the '<em><b>Top</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Top spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Top</em>' attribute.
	 * @see #setTop(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Top()
	 * @model default="true"
	 * @generated
	 */
	boolean isTop();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isTop <em>Top</em>}' attribute.
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
	 * Bottom spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bottom</em>' attribute.
	 * @see #setBottom(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Bottom()
	 * @model default="true"
	 * @generated
	 */
	boolean isBottom();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isBottom <em>Bottom</em>}' attribute.
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
	 * Left spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left</em>' attribute.
	 * @see #setLeft(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Left()
	 * @model default="true"
	 * @generated
	 */
	boolean isLeft();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isLeft <em>Left</em>}' attribute.
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
	 * Righ spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right</em>' attribute.
	 * @see #setRight(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Right()
	 * @model default="true"
	 * @generated
	 */
	boolean isRight();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isRight <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' attribute.
	 * @see #isRight()
	 * @generated
	 */
	void setRight(boolean value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Horizontal spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_X()
	 * @model default="false"
	 * @generated
	 */
	boolean isX();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #isX()
	 * @generated
	 */
	void setX(boolean value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Vertical spacing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSpacing_Y()
	 * @model default="false"
	 * @generated
	 */
	boolean isY();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Spacing#isY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #isY()
	 * @generated
	 */
	void setY(boolean value);

} // Spacing
