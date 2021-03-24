/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Text;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#getAlignment <em>Alignment</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#getTransform <em>Transform</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#getWeight <em>Weight</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#isMonospace <em>Monospace</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#isItalic <em>Italic</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#isNowrap <em>Nowrap</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TextImpl#isTruncate <em>Truncate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextImpl extends MinimalEObjectImpl.Container implements Text {
	/**
	 * The default value of the '{@link #getAlignment() <em>Alignment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlignment()
	 * @generated
	 * @ordered
	 */
	protected static final String ALIGNMENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTransform() <em>Transform</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransform()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSFORM_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final String WEIGHT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isMonospace() <em>Monospace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMonospace()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MONOSPACE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isItalic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ITALIC_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isNowrap() <em>Nowrap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNowrap()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NOWRAP_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isTruncate() <em>Truncate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTruncate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRUNCATE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.TEXT;
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
	public String getAlignment() {
		return (String)eDynamicGet(BootstrapPackage.TEXT__ALIGNMENT, BootstrapPackage.Literals.TEXT__ALIGNMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAlignment(String newAlignment) {
		eDynamicSet(BootstrapPackage.TEXT__ALIGNMENT, BootstrapPackage.Literals.TEXT__ALIGNMENT, newAlignment);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return (String)eDynamicGet(BootstrapPackage.TEXT__COLOR, BootstrapPackage.Literals.TEXT__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(BootstrapPackage.TEXT__COLOR, BootstrapPackage.Literals.TEXT__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTransform() {
		return (String)eDynamicGet(BootstrapPackage.TEXT__TRANSFORM, BootstrapPackage.Literals.TEXT__TRANSFORM, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTransform(String newTransform) {
		eDynamicSet(BootstrapPackage.TEXT__TRANSFORM, BootstrapPackage.Literals.TEXT__TRANSFORM, newTransform);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWeight() {
		return (String)eDynamicGet(BootstrapPackage.TEXT__WEIGHT, BootstrapPackage.Literals.TEXT__WEIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWeight(String newWeight) {
		eDynamicSet(BootstrapPackage.TEXT__WEIGHT, BootstrapPackage.Literals.TEXT__WEIGHT, newWeight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMonospace() {
		return (Boolean)eDynamicGet(BootstrapPackage.TEXT__MONOSPACE, BootstrapPackage.Literals.TEXT__MONOSPACE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMonospace(boolean newMonospace) {
		eDynamicSet(BootstrapPackage.TEXT__MONOSPACE, BootstrapPackage.Literals.TEXT__MONOSPACE, newMonospace);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isItalic() {
		return (Boolean)eDynamicGet(BootstrapPackage.TEXT__ITALIC, BootstrapPackage.Literals.TEXT__ITALIC, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setItalic(boolean newItalic) {
		eDynamicSet(BootstrapPackage.TEXT__ITALIC, BootstrapPackage.Literals.TEXT__ITALIC, newItalic);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNowrap() {
		return (Boolean)eDynamicGet(BootstrapPackage.TEXT__NOWRAP, BootstrapPackage.Literals.TEXT__NOWRAP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNowrap(boolean newNowrap) {
		eDynamicSet(BootstrapPackage.TEXT__NOWRAP, BootstrapPackage.Literals.TEXT__NOWRAP, newNowrap);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTruncate() {
		return (Boolean)eDynamicGet(BootstrapPackage.TEXT__TRUNCATE, BootstrapPackage.Literals.TEXT__TRUNCATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTruncate(boolean newTruncate) {
		eDynamicSet(BootstrapPackage.TEXT__TRUNCATE, BootstrapPackage.Literals.TEXT__TRUNCATE, newTruncate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.TEXT__ALIGNMENT:
				return getAlignment();
			case BootstrapPackage.TEXT__COLOR:
				return getColor();
			case BootstrapPackage.TEXT__TRANSFORM:
				return getTransform();
			case BootstrapPackage.TEXT__WEIGHT:
				return getWeight();
			case BootstrapPackage.TEXT__MONOSPACE:
				return isMonospace();
			case BootstrapPackage.TEXT__ITALIC:
				return isItalic();
			case BootstrapPackage.TEXT__NOWRAP:
				return isNowrap();
			case BootstrapPackage.TEXT__TRUNCATE:
				return isTruncate();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BootstrapPackage.TEXT__ALIGNMENT:
				setAlignment((String)newValue);
				return;
			case BootstrapPackage.TEXT__COLOR:
				setColor((String)newValue);
				return;
			case BootstrapPackage.TEXT__TRANSFORM:
				setTransform((String)newValue);
				return;
			case BootstrapPackage.TEXT__WEIGHT:
				setWeight((String)newValue);
				return;
			case BootstrapPackage.TEXT__MONOSPACE:
				setMonospace((Boolean)newValue);
				return;
			case BootstrapPackage.TEXT__ITALIC:
				setItalic((Boolean)newValue);
				return;
			case BootstrapPackage.TEXT__NOWRAP:
				setNowrap((Boolean)newValue);
				return;
			case BootstrapPackage.TEXT__TRUNCATE:
				setTruncate((Boolean)newValue);
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
			case BootstrapPackage.TEXT__ALIGNMENT:
				setAlignment(ALIGNMENT_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__TRANSFORM:
				setTransform(TRANSFORM_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__WEIGHT:
				setWeight(WEIGHT_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__MONOSPACE:
				setMonospace(MONOSPACE_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__ITALIC:
				setItalic(ITALIC_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__NOWRAP:
				setNowrap(NOWRAP_EDEFAULT);
				return;
			case BootstrapPackage.TEXT__TRUNCATE:
				setTruncate(TRUNCATE_EDEFAULT);
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
			case BootstrapPackage.TEXT__ALIGNMENT:
				return ALIGNMENT_EDEFAULT == null ? getAlignment() != null : !ALIGNMENT_EDEFAULT.equals(getAlignment());
			case BootstrapPackage.TEXT__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case BootstrapPackage.TEXT__TRANSFORM:
				return TRANSFORM_EDEFAULT == null ? getTransform() != null : !TRANSFORM_EDEFAULT.equals(getTransform());
			case BootstrapPackage.TEXT__WEIGHT:
				return WEIGHT_EDEFAULT == null ? getWeight() != null : !WEIGHT_EDEFAULT.equals(getWeight());
			case BootstrapPackage.TEXT__MONOSPACE:
				return isMonospace() != MONOSPACE_EDEFAULT;
			case BootstrapPackage.TEXT__ITALIC:
				return isItalic() != ITALIC_EDEFAULT;
			case BootstrapPackage.TEXT__NOWRAP:
				return isNowrap() != NOWRAP_EDEFAULT;
			case BootstrapPackage.TEXT__TRUNCATE:
				return isTruncate() != TRUNCATE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //TextImpl
