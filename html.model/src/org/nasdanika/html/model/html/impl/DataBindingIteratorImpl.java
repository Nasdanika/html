/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.DataBindingIterator;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Binding Iterator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.DataBindingIteratorImpl#getDataBinding <em>Data Binding</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DataBindingIteratorImpl extends IteratorImpl implements DataBindingIterator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataBindingIteratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.DATA_BINDING_ITERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDataBinding() {
		return (String)eGet(HtmlPackage.Literals.DATA_BINDING_ITERATOR__DATA_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDataBinding(String newDataBinding) {
		eSet(HtmlPackage.Literals.DATA_BINDING_ITERATOR__DATA_BINDING, newDataBinding);
	}

} //DataBindingIteratorImpl
