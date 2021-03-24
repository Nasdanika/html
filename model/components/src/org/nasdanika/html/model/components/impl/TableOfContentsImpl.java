/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.TableConfiguration;

import org.nasdanika.html.model.components.ComponentsPackage;
import org.nasdanika.html.model.components.TableOfContents;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Of Contents</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isStriped <em>Striped</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isBordered <em>Bordered</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isBorderless <em>Borderless</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isHover <em>Hover</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isSmall <em>Small</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl#isTooltips <em>Tooltips</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableOfContentsImpl extends TableOfContentsBaseImpl implements TableOfContents {
	/**
	 * The default value of the '{@link #isDark() <em>Dark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDark()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DARK_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isStriped() <em>Striped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStriped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STRIPED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isBordered() <em>Bordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBordered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BORDERED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isBorderless() <em>Borderless</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBorderless()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BORDERLESS_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isHover() <em>Hover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHover()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HOVER_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isSmall() <em>Small</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSmall()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SMALL_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isDescriptions() <em>Descriptions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDescriptions()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DESCRIPTIONS_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isTooltips() <em>Tooltips</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTooltips()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TOOLTIPS_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableOfContentsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.TABLE_OF_CONTENTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDark() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__DARK, BootstrapPackage.Literals.TABLE_CONFIGURATION__DARK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDark(boolean newDark) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__DARK, BootstrapPackage.Literals.TABLE_CONFIGURATION__DARK, newDark);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isStriped() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__STRIPED, BootstrapPackage.Literals.TABLE_CONFIGURATION__STRIPED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStriped(boolean newStriped) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__STRIPED, BootstrapPackage.Literals.TABLE_CONFIGURATION__STRIPED, newStriped);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBordered() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__BORDERED, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBordered(boolean newBordered) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__BORDERED, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERED, newBordered);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBorderless() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERLESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBorderless(boolean newBorderless) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERLESS, newBorderless);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHover() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__HOVER, BootstrapPackage.Literals.TABLE_CONFIGURATION__HOVER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHover(boolean newHover) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__HOVER, BootstrapPackage.Literals.TABLE_CONFIGURATION__HOVER, newHover);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSmall() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__SMALL, BootstrapPackage.Literals.TABLE_CONFIGURATION__SMALL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSmall(boolean newSmall) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__SMALL, BootstrapPackage.Literals.TABLE_CONFIGURATION__SMALL, newSmall);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDescriptions() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS, ComponentsPackage.Literals.TABLE_OF_CONTENTS__DESCRIPTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescriptions(boolean newDescriptions) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS, ComponentsPackage.Literals.TABLE_OF_CONTENTS__DESCRIPTIONS, newDescriptions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTooltips() {
		return (Boolean)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS, ComponentsPackage.Literals.TABLE_OF_CONTENTS__TOOLTIPS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltips(boolean newTooltips) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS, ComponentsPackage.Literals.TABLE_OF_CONTENTS__TOOLTIPS, newTooltips);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.TABLE_OF_CONTENTS__DARK:
				return isDark();
			case ComponentsPackage.TABLE_OF_CONTENTS__STRIPED:
				return isStriped();
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERED:
				return isBordered();
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS:
				return isBorderless();
			case ComponentsPackage.TABLE_OF_CONTENTS__HOVER:
				return isHover();
			case ComponentsPackage.TABLE_OF_CONTENTS__SMALL:
				return isSmall();
			case ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS:
				return isDescriptions();
			case ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS:
				return isTooltips();
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
			case ComponentsPackage.TABLE_OF_CONTENTS__DARK:
				setDark((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__STRIPED:
				setStriped((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERED:
				setBordered((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS:
				setBorderless((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__HOVER:
				setHover((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__SMALL:
				setSmall((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS:
				setDescriptions((Boolean)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS:
				setTooltips((Boolean)newValue);
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
			case ComponentsPackage.TABLE_OF_CONTENTS__DARK:
				setDark(DARK_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__STRIPED:
				setStriped(STRIPED_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERED:
				setBordered(BORDERED_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS:
				setBorderless(BORDERLESS_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__HOVER:
				setHover(HOVER_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__SMALL:
				setSmall(SMALL_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS:
				setDescriptions(DESCRIPTIONS_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS:
				setTooltips(TOOLTIPS_EDEFAULT);
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
			case ComponentsPackage.TABLE_OF_CONTENTS__DARK:
				return isDark() != DARK_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__STRIPED:
				return isStriped() != STRIPED_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERED:
				return isBordered() != BORDERED_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS:
				return isBorderless() != BORDERLESS_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__HOVER:
				return isHover() != HOVER_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__SMALL:
				return isSmall() != SMALL_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__DESCRIPTIONS:
				return isDescriptions() != DESCRIPTIONS_EDEFAULT;
			case ComponentsPackage.TABLE_OF_CONTENTS__TOOLTIPS:
				return isTooltips() != TOOLTIPS_EDEFAULT;
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
		if (baseClass == TableConfiguration.class) {
			switch (derivedFeatureID) {
				case ComponentsPackage.TABLE_OF_CONTENTS__DARK: return BootstrapPackage.TABLE_CONFIGURATION__DARK;
				case ComponentsPackage.TABLE_OF_CONTENTS__STRIPED: return BootstrapPackage.TABLE_CONFIGURATION__STRIPED;
				case ComponentsPackage.TABLE_OF_CONTENTS__BORDERED: return BootstrapPackage.TABLE_CONFIGURATION__BORDERED;
				case ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS: return BootstrapPackage.TABLE_CONFIGURATION__BORDERLESS;
				case ComponentsPackage.TABLE_OF_CONTENTS__HOVER: return BootstrapPackage.TABLE_CONFIGURATION__HOVER;
				case ComponentsPackage.TABLE_OF_CONTENTS__SMALL: return BootstrapPackage.TABLE_CONFIGURATION__SMALL;
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
		if (baseClass == TableConfiguration.class) {
			switch (baseFeatureID) {
				case BootstrapPackage.TABLE_CONFIGURATION__DARK: return ComponentsPackage.TABLE_OF_CONTENTS__DARK;
				case BootstrapPackage.TABLE_CONFIGURATION__STRIPED: return ComponentsPackage.TABLE_OF_CONTENTS__STRIPED;
				case BootstrapPackage.TABLE_CONFIGURATION__BORDERED: return ComponentsPackage.TABLE_OF_CONTENTS__BORDERED;
				case BootstrapPackage.TABLE_CONFIGURATION__BORDERLESS: return ComponentsPackage.TABLE_OF_CONTENTS__BORDERLESS;
				case BootstrapPackage.TABLE_CONFIGURATION__HOVER: return ComponentsPackage.TABLE_OF_CONTENTS__HOVER;
				case BootstrapPackage.TABLE_CONFIGURATION__SMALL: return ComponentsPackage.TABLE_OF_CONTENTS__SMALL;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TableOfContentsImpl
