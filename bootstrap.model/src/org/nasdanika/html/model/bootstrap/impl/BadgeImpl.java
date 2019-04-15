/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.bootstrap.Color;

import org.nasdanika.html.model.bootstrap.Badge;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Badge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BadgeImpl#isPill <em>Pill</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BadgeImpl#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BadgeImpl extends ContainerBootstrapElementImpl implements Badge {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BadgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.BADGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPill() {
		return (Boolean)eGet(BootstrapPackage.Literals.BADGE__PILL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPill(boolean newPill) {
		eSet(BootstrapPackage.Literals.BADGE__PILL, newPill);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Color getColor() {
		return (Color)eGet(BootstrapPackage.Literals.BADGE__COLOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(Color newColor) {
		eSet(BootstrapPackage.Literals.BADGE__COLOR, newColor);
	}

} //BadgeImpl
