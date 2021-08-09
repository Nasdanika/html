/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableConfiguration;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableSection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isStriped <em>Striped</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isBordered <em>Bordered</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isBorderless <em>Borderless</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isHover <em>Hover</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#isSmall <em>Small</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableImpl#getFooter <em>Footer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableImpl extends TableRowContainerImpl implements Table {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.TABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDark() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__DARK, BootstrapPackage.Literals.TABLE_CONFIGURATION__DARK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDark(boolean newDark) {
		eDynamicSet(BootstrapPackage.TABLE__DARK, BootstrapPackage.Literals.TABLE_CONFIGURATION__DARK, newDark);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isStriped() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__STRIPED, BootstrapPackage.Literals.TABLE_CONFIGURATION__STRIPED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStriped(boolean newStriped) {
		eDynamicSet(BootstrapPackage.TABLE__STRIPED, BootstrapPackage.Literals.TABLE_CONFIGURATION__STRIPED, newStriped);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBordered() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__BORDERED, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBordered(boolean newBordered) {
		eDynamicSet(BootstrapPackage.TABLE__BORDERED, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERED, newBordered);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBorderless() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__BORDERLESS, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERLESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBorderless(boolean newBorderless) {
		eDynamicSet(BootstrapPackage.TABLE__BORDERLESS, BootstrapPackage.Literals.TABLE_CONFIGURATION__BORDERLESS, newBorderless);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHover() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__HOVER, BootstrapPackage.Literals.TABLE_CONFIGURATION__HOVER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHover(boolean newHover) {
		eDynamicSet(BootstrapPackage.TABLE__HOVER, BootstrapPackage.Literals.TABLE_CONFIGURATION__HOVER, newHover);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSmall() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE__SMALL, BootstrapPackage.Literals.TABLE_CONFIGURATION__SMALL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSmall(boolean newSmall) {
		eDynamicSet(BootstrapPackage.TABLE__SMALL, BootstrapPackage.Literals.TABLE_CONFIGURATION__SMALL, newSmall);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableHeader getHeader() {
		return (TableHeader)eDynamicGet(BootstrapPackage.TABLE__HEADER, BootstrapPackage.Literals.TABLE__HEADER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(TableHeader newHeader, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newHeader, BootstrapPackage.TABLE__HEADER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeader(TableHeader newHeader) {
		eDynamicSet(BootstrapPackage.TABLE__HEADER, BootstrapPackage.Literals.TABLE__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableSection getBody() {
		return (TableSection)eDynamicGet(BootstrapPackage.TABLE__BODY, BootstrapPackage.Literals.TABLE__BODY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(TableSection newBody, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newBody, BootstrapPackage.TABLE__BODY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBody(TableSection newBody) {
		eDynamicSet(BootstrapPackage.TABLE__BODY, BootstrapPackage.Literals.TABLE__BODY, newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableSection getFooter() {
		return (TableSection)eDynamicGet(BootstrapPackage.TABLE__FOOTER, BootstrapPackage.Literals.TABLE__FOOTER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFooter(TableSection newFooter, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFooter, BootstrapPackage.TABLE__FOOTER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFooter(TableSection newFooter) {
		eDynamicSet(BootstrapPackage.TABLE__FOOTER, BootstrapPackage.Literals.TABLE__FOOTER, newFooter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.TABLE__HEADER:
				return basicSetHeader(null, msgs);
			case BootstrapPackage.TABLE__BODY:
				return basicSetBody(null, msgs);
			case BootstrapPackage.TABLE__FOOTER:
				return basicSetFooter(null, msgs);
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
			case BootstrapPackage.TABLE__DARK:
				return isDark();
			case BootstrapPackage.TABLE__STRIPED:
				return isStriped();
			case BootstrapPackage.TABLE__BORDERED:
				return isBordered();
			case BootstrapPackage.TABLE__BORDERLESS:
				return isBorderless();
			case BootstrapPackage.TABLE__HOVER:
				return isHover();
			case BootstrapPackage.TABLE__SMALL:
				return isSmall();
			case BootstrapPackage.TABLE__HEADER:
				return getHeader();
			case BootstrapPackage.TABLE__BODY:
				return getBody();
			case BootstrapPackage.TABLE__FOOTER:
				return getFooter();
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
			case BootstrapPackage.TABLE__DARK:
				setDark((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__STRIPED:
				setStriped((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__BORDERED:
				setBordered((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__BORDERLESS:
				setBorderless((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__HOVER:
				setHover((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__SMALL:
				setSmall((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE__HEADER:
				setHeader((TableHeader)newValue);
				return;
			case BootstrapPackage.TABLE__BODY:
				setBody((TableSection)newValue);
				return;
			case BootstrapPackage.TABLE__FOOTER:
				setFooter((TableSection)newValue);
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
			case BootstrapPackage.TABLE__DARK:
				setDark(DARK_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__STRIPED:
				setStriped(STRIPED_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__BORDERED:
				setBordered(BORDERED_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__BORDERLESS:
				setBorderless(BORDERLESS_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__HOVER:
				setHover(HOVER_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__SMALL:
				setSmall(SMALL_EDEFAULT);
				return;
			case BootstrapPackage.TABLE__HEADER:
				setHeader((TableHeader)null);
				return;
			case BootstrapPackage.TABLE__BODY:
				setBody((TableSection)null);
				return;
			case BootstrapPackage.TABLE__FOOTER:
				setFooter((TableSection)null);
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
			case BootstrapPackage.TABLE__DARK:
				return isDark() != DARK_EDEFAULT;
			case BootstrapPackage.TABLE__STRIPED:
				return isStriped() != STRIPED_EDEFAULT;
			case BootstrapPackage.TABLE__BORDERED:
				return isBordered() != BORDERED_EDEFAULT;
			case BootstrapPackage.TABLE__BORDERLESS:
				return isBorderless() != BORDERLESS_EDEFAULT;
			case BootstrapPackage.TABLE__HOVER:
				return isHover() != HOVER_EDEFAULT;
			case BootstrapPackage.TABLE__SMALL:
				return isSmall() != SMALL_EDEFAULT;
			case BootstrapPackage.TABLE__HEADER:
				return getHeader() != null;
			case BootstrapPackage.TABLE__BODY:
				return getBody() != null;
			case BootstrapPackage.TABLE__FOOTER:
				return getFooter() != null;
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
				case BootstrapPackage.TABLE__DARK: return BootstrapPackage.TABLE_CONFIGURATION__DARK;
				case BootstrapPackage.TABLE__STRIPED: return BootstrapPackage.TABLE_CONFIGURATION__STRIPED;
				case BootstrapPackage.TABLE__BORDERED: return BootstrapPackage.TABLE_CONFIGURATION__BORDERED;
				case BootstrapPackage.TABLE__BORDERLESS: return BootstrapPackage.TABLE_CONFIGURATION__BORDERLESS;
				case BootstrapPackage.TABLE__HOVER: return BootstrapPackage.TABLE_CONFIGURATION__HOVER;
				case BootstrapPackage.TABLE__SMALL: return BootstrapPackage.TABLE_CONFIGURATION__SMALL;
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
				case BootstrapPackage.TABLE_CONFIGURATION__DARK: return BootstrapPackage.TABLE__DARK;
				case BootstrapPackage.TABLE_CONFIGURATION__STRIPED: return BootstrapPackage.TABLE__STRIPED;
				case BootstrapPackage.TABLE_CONFIGURATION__BORDERED: return BootstrapPackage.TABLE__BORDERED;
				case BootstrapPackage.TABLE_CONFIGURATION__BORDERLESS: return BootstrapPackage.TABLE__BORDERLESS;
				case BootstrapPackage.TABLE_CONFIGURATION__HOVER: return BootstrapPackage.TABLE__HOVER;
				case BootstrapPackage.TABLE_CONFIGURATION__SMALL: return BootstrapPackage.TABLE__SMALL;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TableImpl
