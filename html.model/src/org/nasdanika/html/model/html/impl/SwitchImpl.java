/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.Case;
import org.nasdanika.html.model.html.Content;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Switch;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Switch</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.SwitchImpl#getCases <em>Cases</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.SwitchImpl#getOtherwise <em>Otherwise</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.SwitchImpl#getSelector <em>Selector</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SwitchImpl extends ModelElementImpl implements Switch {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SwitchImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.SWITCH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Case> getCases() {
		return (EList<Case>)eGet(HtmlPackage.Literals.SWITCH__CASES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Content> getOtherwise() {
		return (EList<Content>)eGet(HtmlPackage.Literals.SWITCH__OTHERWISE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSelector() {
		return (String)eGet(HtmlPackage.Literals.SWITCH__SELECTOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSelector(String newSelector) {
		eSet(HtmlPackage.Literals.SWITCH__SELECTOR, newSelector);
	}

} //SwitchImpl
