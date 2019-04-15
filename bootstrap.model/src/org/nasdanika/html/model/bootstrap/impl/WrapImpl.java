/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Wrap;

import org.nasdanika.html.model.html.HTMLElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wrap</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.WrapImpl#getHtmlElement <em>Html Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WrapImpl extends ContentBootstrapElementImpl implements Wrap {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WrapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.WRAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HTMLElement getHtmlElement() {
		return (HTMLElement)eGet(BootstrapPackage.Literals.WRAP__HTML_ELEMENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHtmlElement(HTMLElement newHtmlElement) {
		eSet(BootstrapPackage.Literals.WRAP__HTML_ELEMENT, newHtmlElement);
	}

} //WrapImpl
