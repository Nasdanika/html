/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getHelp <em>Help</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getNotification <em>Notification</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelImpl extends BootstrapElementImpl implements Label {
	/**
	 * The default value of the '{@link #isActive() <em>Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

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
	 * The default value of the '{@link #getHelp() <em>Help</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHelp()
	 * @generated
	 * @ordered
	 */
	protected static final String HELP_EDEFAULT = null;

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
	public boolean isActive() {
		return (Boolean)eDynamicGet(AppPackage.LABEL__ACTIVE, BootstrapPackage.Literals.ITEM__ACTIVE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActive(boolean newActive) {
		eDynamicSet(AppPackage.LABEL__ACTIVE, BootstrapPackage.Literals.ITEM__ACTIVE, newActive);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisabled() {
		return (Boolean)eDynamicGet(AppPackage.LABEL__DISABLED, BootstrapPackage.Literals.ITEM__DISABLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(boolean newDisabled) {
		eDynamicSet(AppPackage.LABEL__DISABLED, BootstrapPackage.Literals.ITEM__DISABLED, newDisabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Color getColor() {
		return (Color)eDynamicGet(AppPackage.LABEL__COLOR, BootstrapPackage.Literals.ITEM__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(Color newColor) {
		eDynamicSet(AppPackage.LABEL__COLOR, BootstrapPackage.Literals.ITEM__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHelp() {
		return (String)eDynamicGet(AppPackage.LABEL__HELP, AppPackage.Literals.LABEL__HELP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHelp(String newHelp) {
		eDynamicSet(AppPackage.LABEL__HELP, AppPackage.Literals.LABEL__HELP, newHelp);
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
	public BootstrapElement getChildren() {
		return (BootstrapElement)eDynamicGet(AppPackage.LABEL__CHILDREN, AppPackage.Literals.LABEL__CHILDREN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapElement basicGetChildren() {
		return (BootstrapElement)eDynamicGet(AppPackage.LABEL__CHILDREN, AppPackage.Literals.LABEL__CHILDREN, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setChildren(BootstrapElement newChildren) {
		eDynamicSet(AppPackage.LABEL__CHILDREN, AppPackage.Literals.LABEL__CHILDREN, newChildren);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.LABEL__ACTIVE:
				return isActive();
			case AppPackage.LABEL__DISABLED:
				return isDisabled();
			case AppPackage.LABEL__COLOR:
				return getColor();
			case AppPackage.LABEL__HELP:
				return getHelp();
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
			case AppPackage.LABEL__CHILDREN:
				if (resolve) return getChildren();
				return basicGetChildren();
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
			case AppPackage.LABEL__ACTIVE:
				setActive((Boolean)newValue);
				return;
			case AppPackage.LABEL__DISABLED:
				setDisabled((Boolean)newValue);
				return;
			case AppPackage.LABEL__COLOR:
				setColor((Color)newValue);
				return;
			case AppPackage.LABEL__HELP:
				setHelp((String)newValue);
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
			case AppPackage.LABEL__CHILDREN:
				setChildren((BootstrapElement)newValue);
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
			case AppPackage.LABEL__ACTIVE:
				setActive(ACTIVE_EDEFAULT);
				return;
			case AppPackage.LABEL__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case AppPackage.LABEL__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case AppPackage.LABEL__HELP:
				setHelp(HELP_EDEFAULT);
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
			case AppPackage.LABEL__CHILDREN:
				setChildren((BootstrapElement)null);
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
			case AppPackage.LABEL__ACTIVE:
				return isActive() != ACTIVE_EDEFAULT;
			case AppPackage.LABEL__DISABLED:
				return isDisabled() != DISABLED_EDEFAULT;
			case AppPackage.LABEL__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case AppPackage.LABEL__HELP:
				return HELP_EDEFAULT == null ? getHelp() != null : !HELP_EDEFAULT.equals(getHelp());
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
			case AppPackage.LABEL__CHILDREN:
				return basicGetChildren() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Item.class) {
			switch (derivedFeatureID) {
				case AppPackage.LABEL__ACTIVE: return BootstrapPackage.ITEM__ACTIVE;
				case AppPackage.LABEL__DISABLED: return BootstrapPackage.ITEM__DISABLED;
				case AppPackage.LABEL__COLOR: return BootstrapPackage.ITEM__COLOR;
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
		if (baseClass == Item.class) {
			switch (baseFeatureID) {
				case BootstrapPackage.ITEM__ACTIVE: return AppPackage.LABEL__ACTIVE;
				case BootstrapPackage.ITEM__DISABLED: return AppPackage.LABEL__DISABLED;
				case BootstrapPackage.ITEM__COLOR: return AppPackage.LABEL__COLOR;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //LabelImpl
