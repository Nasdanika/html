/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Border;
import org.nasdanika.html.model.bootstrap.Spacing;
import org.nasdanika.html.model.bootstrap.Text;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Appearance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getBackground <em>Background</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getBorder <em>Border</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getMargin <em>Margin</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getPadding <em>Padding</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.AppearanceImpl#getFloat <em>Float</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AppearanceImpl extends MinimalEObjectImpl.Container implements Appearance {
	/**
	 * The default value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected static final Color BACKGROUND_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AppearanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.APPEARANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Color getBackground() {
		return (Color)eDynamicGet(BootstrapPackage.APPEARANCE__BACKGROUND, BootstrapPackage.Literals.APPEARANCE__BACKGROUND, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBackground(Color newBackground) {
		eDynamicSet(BootstrapPackage.APPEARANCE__BACKGROUND, BootstrapPackage.Literals.APPEARANCE__BACKGROUND, newBackground);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, EObject> getAttributes() {
		return (EMap<String, EObject>)eDynamicGet(BootstrapPackage.APPEARANCE__ATTRIBUTES, BootstrapPackage.Literals.APPEARANCE__ATTRIBUTES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Border> getBorder() {
		return (EList<Border>)eDynamicGet(BootstrapPackage.APPEARANCE__BORDER, BootstrapPackage.Literals.APPEARANCE__BORDER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Spacing> getMargin() {
		return (EList<Spacing>)eDynamicGet(BootstrapPackage.APPEARANCE__MARGIN, BootstrapPackage.Literals.APPEARANCE__MARGIN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Spacing> getPadding() {
		return (EList<Spacing>)eDynamicGet(BootstrapPackage.APPEARANCE__PADDING, BootstrapPackage.Literals.APPEARANCE__PADDING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Text getText() {
		return (Text)eDynamicGet(BootstrapPackage.APPEARANCE__TEXT, BootstrapPackage.Literals.APPEARANCE__TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetText(Text newText, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newText, BootstrapPackage.APPEARANCE__TEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(Text newText) {
		eDynamicSet(BootstrapPackage.APPEARANCE__TEXT, BootstrapPackage.Literals.APPEARANCE__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.bootstrap.Float> getFloat() {
		return (EList<org.nasdanika.html.model.bootstrap.Float>)eDynamicGet(BootstrapPackage.APPEARANCE__FLOAT, BootstrapPackage.Literals.APPEARANCE__FLOAT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.APPEARANCE__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case BootstrapPackage.APPEARANCE__BORDER:
				return ((InternalEList<?>)getBorder()).basicRemove(otherEnd, msgs);
			case BootstrapPackage.APPEARANCE__MARGIN:
				return ((InternalEList<?>)getMargin()).basicRemove(otherEnd, msgs);
			case BootstrapPackage.APPEARANCE__PADDING:
				return ((InternalEList<?>)getPadding()).basicRemove(otherEnd, msgs);
			case BootstrapPackage.APPEARANCE__TEXT:
				return basicSetText(null, msgs);
			case BootstrapPackage.APPEARANCE__FLOAT:
				return ((InternalEList<?>)getFloat()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.APPEARANCE__BACKGROUND:
				return getBackground();
			case BootstrapPackage.APPEARANCE__ATTRIBUTES:
				if (coreType) return getAttributes();
				else return getAttributes().map();
			case BootstrapPackage.APPEARANCE__BORDER:
				return getBorder();
			case BootstrapPackage.APPEARANCE__MARGIN:
				return getMargin();
			case BootstrapPackage.APPEARANCE__PADDING:
				return getPadding();
			case BootstrapPackage.APPEARANCE__TEXT:
				return getText();
			case BootstrapPackage.APPEARANCE__FLOAT:
				return getFloat();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BootstrapPackage.APPEARANCE__BACKGROUND:
				setBackground((Color)newValue);
				return;
			case BootstrapPackage.APPEARANCE__ATTRIBUTES:
				((EStructuralFeature.Setting)getAttributes()).set(newValue);
				return;
			case BootstrapPackage.APPEARANCE__BORDER:
				getBorder().clear();
				getBorder().addAll((Collection<? extends Border>)newValue);
				return;
			case BootstrapPackage.APPEARANCE__MARGIN:
				getMargin().clear();
				getMargin().addAll((Collection<? extends Spacing>)newValue);
				return;
			case BootstrapPackage.APPEARANCE__PADDING:
				getPadding().clear();
				getPadding().addAll((Collection<? extends Spacing>)newValue);
				return;
			case BootstrapPackage.APPEARANCE__TEXT:
				setText((Text)newValue);
				return;
			case BootstrapPackage.APPEARANCE__FLOAT:
				getFloat().clear();
				getFloat().addAll((Collection<? extends org.nasdanika.html.model.bootstrap.Float>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BootstrapPackage.APPEARANCE__BACKGROUND:
				setBackground(BACKGROUND_EDEFAULT);
				return;
			case BootstrapPackage.APPEARANCE__ATTRIBUTES:
				getAttributes().clear();
				return;
			case BootstrapPackage.APPEARANCE__BORDER:
				getBorder().clear();
				return;
			case BootstrapPackage.APPEARANCE__MARGIN:
				getMargin().clear();
				return;
			case BootstrapPackage.APPEARANCE__PADDING:
				getPadding().clear();
				return;
			case BootstrapPackage.APPEARANCE__TEXT:
				setText((Text)null);
				return;
			case BootstrapPackage.APPEARANCE__FLOAT:
				getFloat().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BootstrapPackage.APPEARANCE__BACKGROUND:
				return BACKGROUND_EDEFAULT == null ? getBackground() != null : !BACKGROUND_EDEFAULT.equals(getBackground());
			case BootstrapPackage.APPEARANCE__ATTRIBUTES:
				return !getAttributes().isEmpty();
			case BootstrapPackage.APPEARANCE__BORDER:
				return !getBorder().isEmpty();
			case BootstrapPackage.APPEARANCE__MARGIN:
				return !getMargin().isEmpty();
			case BootstrapPackage.APPEARANCE__PADDING:
				return !getPadding().isEmpty();
			case BootstrapPackage.APPEARANCE__TEXT:
				return getText() != null;
			case BootstrapPackage.APPEARANCE__FLOAT:
				return !getFloat().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AppearanceImpl
