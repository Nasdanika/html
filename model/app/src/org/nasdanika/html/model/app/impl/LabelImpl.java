/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getNotification <em>Notification</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LabelImpl extends MinimalEObjectImpl.Container implements Label {
	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final Color COLOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTooltip() <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTooltip()
	 * @generated
	 * @ordered
	 */
	protected static final String TOOLTIP_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isOutline() <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOutline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OUTLINE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getNotification() <em>Notification</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotification()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTIFICATION_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.LABEL;
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
	public Color getColor() {
		return (Color)eDynamicGet(AppPackage.LABEL__COLOR, AppPackage.Literals.LABEL__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(Color newColor) {
		eDynamicSet(AppPackage.LABEL__COLOR, AppPackage.Literals.LABEL__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText() {
		return (String)eDynamicGet(AppPackage.LABEL__TEXT, AppPackage.Literals.LABEL__TEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(String newText) {
		eDynamicSet(AppPackage.LABEL__TEXT, AppPackage.Literals.LABEL__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIcon() {
		return (String)eDynamicGet(AppPackage.LABEL__ICON, AppPackage.Literals.LABEL__ICON, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIcon(String newIcon) {
		eDynamicSet(AppPackage.LABEL__ICON, AppPackage.Literals.LABEL__ICON, newIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTooltip() {
		return (String)eDynamicGet(AppPackage.LABEL__TOOLTIP, AppPackage.Literals.LABEL__TOOLTIP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltip(String newTooltip) {
		eDynamicSet(AppPackage.LABEL__TOOLTIP, AppPackage.Literals.LABEL__TOOLTIP, newTooltip);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOutline() {
		return (Boolean)eDynamicGet(AppPackage.LABEL__OUTLINE, AppPackage.Literals.LABEL__OUTLINE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutline(boolean newOutline) {
		eDynamicSet(AppPackage.LABEL__OUTLINE, AppPackage.Literals.LABEL__OUTLINE, newOutline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNotification() {
		return (String)eDynamicGet(AppPackage.LABEL__NOTIFICATION, AppPackage.Literals.LABEL__NOTIFICATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNotification(String newNotification) {
		eDynamicSet(AppPackage.LABEL__NOTIFICATION, AppPackage.Literals.LABEL__NOTIFICATION, newNotification);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.LABEL__COLOR:
				return getColor();
			case AppPackage.LABEL__TEXT:
				return getText();
			case AppPackage.LABEL__ICON:
				return getIcon();
			case AppPackage.LABEL__TOOLTIP:
				return getTooltip();
			case AppPackage.LABEL__OUTLINE:
				return isOutline();
			case AppPackage.LABEL__NOTIFICATION:
				return getNotification();
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
			case AppPackage.LABEL__COLOR:
				setColor((Color)newValue);
				return;
			case AppPackage.LABEL__TEXT:
				setText((String)newValue);
				return;
			case AppPackage.LABEL__ICON:
				setIcon((String)newValue);
				return;
			case AppPackage.LABEL__TOOLTIP:
				setTooltip((String)newValue);
				return;
			case AppPackage.LABEL__OUTLINE:
				setOutline((Boolean)newValue);
				return;
			case AppPackage.LABEL__NOTIFICATION:
				setNotification((String)newValue);
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
			case AppPackage.LABEL__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case AppPackage.LABEL__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case AppPackage.LABEL__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case AppPackage.LABEL__TOOLTIP:
				setTooltip(TOOLTIP_EDEFAULT);
				return;
			case AppPackage.LABEL__OUTLINE:
				setOutline(OUTLINE_EDEFAULT);
				return;
			case AppPackage.LABEL__NOTIFICATION:
				setNotification(NOTIFICATION_EDEFAULT);
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
			case AppPackage.LABEL__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case AppPackage.LABEL__TEXT:
				return TEXT_EDEFAULT == null ? getText() != null : !TEXT_EDEFAULT.equals(getText());
			case AppPackage.LABEL__ICON:
				return ICON_EDEFAULT == null ? getIcon() != null : !ICON_EDEFAULT.equals(getIcon());
			case AppPackage.LABEL__TOOLTIP:
				return TOOLTIP_EDEFAULT == null ? getTooltip() != null : !TOOLTIP_EDEFAULT.equals(getTooltip());
			case AppPackage.LABEL__OUTLINE:
				return isOutline() != OUTLINE_EDEFAULT;
			case AppPackage.LABEL__NOTIFICATION:
				return NOTIFICATION_EDEFAULT == null ? getNotification() != null : !NOTIFICATION_EDEFAULT.equals(getNotification());
		}
		return super.eIsSet(featureID);
	}

} //LabelImpl
