/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.NavigationPanelStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getLabelTrimLength <em>Label Trim Length</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#isCollapsible <em>Collapsible</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getJsTreeSearchThreshold <em>Js Tree Search Threshold</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationPanelImpl extends PagePartImpl implements NavigationPanel {
	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final NavigationPanelStyle STYLE_EDEFAULT = NavigationPanelStyle.AUTO;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLabelTrimLength() <em>Label Trim Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelTrimLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LABEL_TRIM_LENGTH_EDEFAULT = 50;

	/**
	 * The default value of the '{@link #isCollapsible() <em>Collapsible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLAPSIBLE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getJsTreeSearchThreshold() <em>Js Tree Search Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJsTreeSearchThreshold()
	 * @generated
	 * @ordered
	 */
	protected static final int JS_TREE_SEARCH_THRESHOLD_EDEFAULT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.NAVIGATION_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanelStyle getStyle() {
		return (NavigationPanelStyle)eDynamicGet(AppPackage.NAVIGATION_PANEL__STYLE, AppPackage.Literals.NAVIGATION_PANEL__STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStyle(NavigationPanelStyle newStyle) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__STYLE, AppPackage.Literals.NAVIGATION_PANEL__STYLE, newStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eDynamicGet(AppPackage.NAVIGATION_PANEL__ID, AppPackage.Literals.NAVIGATION_PANEL__ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__ID, AppPackage.Literals.NAVIGATION_PANEL__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLabelTrimLength() {
		return (Integer)eDynamicGet(AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH, AppPackage.Literals.NAVIGATION_PANEL__LABEL_TRIM_LENGTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabelTrimLength(int newLabelTrimLength) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH, AppPackage.Literals.NAVIGATION_PANEL__LABEL_TRIM_LENGTH, newLabelTrimLength);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCollapsible() {
		return (Boolean)eDynamicGet(AppPackage.NAVIGATION_PANEL__COLLAPSIBLE, AppPackage.Literals.NAVIGATION_PANEL__COLLAPSIBLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCollapsible(boolean newCollapsible) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__COLLAPSIBLE, AppPackage.Literals.NAVIGATION_PANEL__COLLAPSIBLE, newCollapsible);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getJsTreeSearchThreshold() {
		return (Integer)eDynamicGet(AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD, AppPackage.Literals.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJsTreeSearchThreshold(int newJsTreeSearchThreshold) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD, AppPackage.Literals.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD, newJsTreeSearchThreshold);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.NAVIGATION_PANEL__STYLE:
				return getStyle();
			case AppPackage.NAVIGATION_PANEL__ID:
				return getId();
			case AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH:
				return getLabelTrimLength();
			case AppPackage.NAVIGATION_PANEL__COLLAPSIBLE:
				return isCollapsible();
			case AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD:
				return getJsTreeSearchThreshold();
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				setStyle((NavigationPanelStyle)newValue);
				return;
			case AppPackage.NAVIGATION_PANEL__ID:
				setId((String)newValue);
				return;
			case AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH:
				setLabelTrimLength((Integer)newValue);
				return;
			case AppPackage.NAVIGATION_PANEL__COLLAPSIBLE:
				setCollapsible((Boolean)newValue);
				return;
			case AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD:
				setJsTreeSearchThreshold((Integer)newValue);
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_PANEL__ID:
				setId(ID_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH:
				setLabelTrimLength(LABEL_TRIM_LENGTH_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_PANEL__COLLAPSIBLE:
				setCollapsible(COLLAPSIBLE_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD:
				setJsTreeSearchThreshold(JS_TREE_SEARCH_THRESHOLD_EDEFAULT);
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				return getStyle() != STYLE_EDEFAULT;
			case AppPackage.NAVIGATION_PANEL__ID:
				return ID_EDEFAULT == null ? getId() != null : !ID_EDEFAULT.equals(getId());
			case AppPackage.NAVIGATION_PANEL__LABEL_TRIM_LENGTH:
				return getLabelTrimLength() != LABEL_TRIM_LENGTH_EDEFAULT;
			case AppPackage.NAVIGATION_PANEL__COLLAPSIBLE:
				return isCollapsible() != COLLAPSIBLE_EDEFAULT;
			case AppPackage.NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD:
				return getJsTreeSearchThreshold() != JS_TREE_SEARCH_THRESHOLD_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //NavigationPanelImpl
