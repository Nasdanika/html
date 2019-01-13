/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ThemedAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Themed Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ThemedActionImpl#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ThemedActionImpl extends ActionImpl implements ThemedAction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThemedActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.THEMED_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Theme getTheme() {
		return (Theme)eGet(AppPackage.Literals.THEMED_ACTION__THEME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTheme(Theme newTheme) {
		eSet(AppPackage.Literals.THEMED_ACTION__THEME, newTheme);
	}

} //ThemedActionImpl
