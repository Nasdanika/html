/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.nasdanika.html.app.ApplicationException;
import org.nasdanika.html.app.Executable;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionActivator;
import org.nasdanika.html.model.app.AppPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getNotification <em>Notification</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isFloatRight <em>Float Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getActivator <em>Activator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionImpl extends CDOObjectImpl implements Action {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.ACTION;
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
		return (Color)eGet(AppPackage.Literals.LABEL__COLOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(Color newColor) {
		eSet(AppPackage.Literals.LABEL__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eGet(AppPackage.Literals.LABEL__DESCRIPTION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eSet(AppPackage.Literals.LABEL__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIcon() {
		return (String)eGet(AppPackage.Literals.LABEL__ICON, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIcon(String newIcon) {
		eSet(AppPackage.Literals.LABEL__ICON, newIcon);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eGet(AppPackage.Literals.LABEL__ID, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eSet(AppPackage.Literals.LABEL__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText() {
		return (String)eGet(AppPackage.Literals.LABEL__TEXT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(String newText) {
		eSet(AppPackage.Literals.LABEL__TEXT, newText);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTooltip() {
		return (String)eGet(AppPackage.Literals.LABEL__TOOLTIP, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltip(String newTooltip) {
		eSet(AppPackage.Literals.LABEL__TOOLTIP, newTooltip);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOutline() {
		return (Boolean)eGet(AppPackage.Literals.LABEL__OUTLINE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutline(boolean newOutline) {
		eSet(AppPackage.Literals.LABEL__OUTLINE, newOutline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getNotification() {
		return (String)eGet(AppPackage.Literals.LABEL__NOTIFICATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNotification(String newNotification) {
		eSet(AppPackage.Literals.LABEL__NOTIFICATION, newNotification);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Action> getChildren() {
		return (EList<Action>)eGet(AppPackage.Literals.ACTION__CHILDREN, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConfirmation() {
		return (String)eGet(AppPackage.Literals.ACTION__CONFIRMATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfirmation(String newConfirmation) {
		eSet(AppPackage.Literals.ACTION__CONFIRMATION, newConfirmation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFloatRight() {
		return (Boolean)eGet(AppPackage.Literals.ACTION__FLOAT_RIGHT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFloatRight(boolean newFloatRight) {
		eSet(AppPackage.Literals.ACTION__FLOAT_RIGHT, newFloatRight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisabled() {
		return (Boolean)eGet(AppPackage.Literals.ACTION__DISABLED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(boolean newDisabled) {
		eSet(AppPackage.Literals.ACTION__DISABLED, newDisabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActionActivator getActivator() {
		return (ActionActivator)eGet(AppPackage.Literals.ACTION__ACTIVATOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActivator(ActionActivator newActivator) {
		eSet(AppPackage.Literals.ACTION__ACTIVATOR, newActivator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIterator() {
		return (String)eGet(AppPackage.Literals.ACTION__ITERATOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIterator(String newIterator) {
		eSet(AppPackage.Literals.ACTION__ITERATOR, newIterator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.html.model.app.Label getCategory() {
		return (org.nasdanika.html.model.app.Label)eGet(AppPackage.Literals.ACTION__CATEGORY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCategory(org.nasdanika.html.model.app.Label newCategory) {
		eSet(AppPackage.Literals.ACTION__CATEGORY, newCategory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getRoles() {
		return (EList<String>)eGet(AppPackage.Literals.ACTION__ROLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Label.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == org.nasdanika.html.model.app.Label.class) {
			switch (derivedFeatureID) {
				case AppPackage.ACTION__COLOR: return AppPackage.LABEL__COLOR;
				case AppPackage.ACTION__DESCRIPTION: return AppPackage.LABEL__DESCRIPTION;
				case AppPackage.ACTION__ICON: return AppPackage.LABEL__ICON;
				case AppPackage.ACTION__ID: return AppPackage.LABEL__ID;
				case AppPackage.ACTION__TEXT: return AppPackage.LABEL__TEXT;
				case AppPackage.ACTION__TOOLTIP: return AppPackage.LABEL__TOOLTIP;
				case AppPackage.ACTION__OUTLINE: return AppPackage.LABEL__OUTLINE;
				case AppPackage.ACTION__NOTIFICATION: return AppPackage.LABEL__NOTIFICATION;
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
		if (baseClass == Label.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == org.nasdanika.html.model.app.Label.class) {
			switch (baseFeatureID) {
				case AppPackage.LABEL__COLOR: return AppPackage.ACTION__COLOR;
				case AppPackage.LABEL__DESCRIPTION: return AppPackage.ACTION__DESCRIPTION;
				case AppPackage.LABEL__ICON: return AppPackage.ACTION__ICON;
				case AppPackage.LABEL__ID: return AppPackage.ACTION__ID;
				case AppPackage.LABEL__TEXT: return AppPackage.ACTION__TEXT;
				case AppPackage.LABEL__TOOLTIP: return AppPackage.ACTION__TOOLTIP;
				case AppPackage.LABEL__OUTLINE: return AppPackage.ACTION__OUTLINE;
				case AppPackage.LABEL__NOTIFICATION: return AppPackage.ACTION__NOTIFICATION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public org.nasdanika.html.app.Action getParent() {
		EObject container = eContainer();
		return container instanceof org.nasdanika.html.app.Action ? (org.nasdanika.html.app.Action) container : null;
	}

	@Override
	public Object execute(ViewGenerator viewGenerator) {
		Executable delegate = (Executable) EcoreUtil.getRegisteredAdapter(this, Executable.class);
		if (delegate == null) {
			throw new ApplicationException("No execution delegate", this);
		}
		return delegate.execute(viewGenerator);
	}
	
	@Override
	public boolean isInRole(String role) {
		return getRoles().contains(role);
	}

} //ActionImpl
