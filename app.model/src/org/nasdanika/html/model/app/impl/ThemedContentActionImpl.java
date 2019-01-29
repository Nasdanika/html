/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.app.Themed;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ThemedAction;
import org.nasdanika.html.model.app.ThemedContentAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Themed Content Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ThemedContentActionImpl#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ThemedContentActionImpl extends ContentActionImpl implements ThemedContentAction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThemedContentActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.THEMED_CONTENT_ACTION;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Themed.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ThemedAction.class) {
			switch (derivedFeatureID) {
				case AppPackage.THEMED_CONTENT_ACTION__THEME: return AppPackage.THEMED_ACTION__THEME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Themed.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ThemedAction.class) {
			switch (baseFeatureID) {
				case AppPackage.THEMED_ACTION__THEME: return AppPackage.THEMED_CONTENT_ACTION__THEME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ThemedContentActionImpl
