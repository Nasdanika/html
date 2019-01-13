/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
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
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#isOutline <em>Outline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LabelImpl#getNotification <em>Notification</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelImpl extends CDOObjectImpl implements Label {
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

} //LabelImpl
