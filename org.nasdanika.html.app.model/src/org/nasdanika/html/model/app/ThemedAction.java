/**
 */
package org.nasdanika.html.model.app;

import org.nasdanika.html.app.Themed;
import org.nasdanika.html.bootstrap.Theme;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Themed Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.ThemedAction#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getThemedAction()
 * @model superTypes="org.nasdanika.html.model.app.Action org.nasdanika.html.model.app.IThemed"
 * @generated
 */
public interface ThemedAction extends Action, Themed {
	/**
	 * Returns the value of the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Theme</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Theme</em>' attribute.
	 * @see #setTheme(Theme)
	 * @see org.nasdanika.html.model.app.AppPackage#getThemedAction_Theme()
	 * @model dataType="org.nasdanika.html.model.app.Theme"
	 * @generated
	 */
	Theme getTheme();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ThemedAction#getTheme <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Theme</em>' attribute.
	 * @see #getTheme()
	 * @generated
	 */
	void setTheme(Theme value);

} // ThemedAction
