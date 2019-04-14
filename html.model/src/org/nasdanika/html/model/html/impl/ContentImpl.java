/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.Content;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Iterator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.ContentImpl#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.ContentImpl#getFacets <em>Facets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.ContentImpl#getDataBinding <em>Data Binding</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ContentImpl extends ModelElementImpl implements Content {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.CONTENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iterator getIterator() {
		return (Iterator)eGet(HtmlPackage.Literals.CONTENT__ITERATOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIterator(Iterator newIterator) {
		eSet(HtmlPackage.Literals.CONTENT__ITERATOR, newIterator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Facet<Content>> getFacets() {
		return (EList<Facet<Content>>)eGet(HtmlPackage.Literals.CONTENT__FACETS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDataBinding() {
		return (String)eGet(HtmlPackage.Literals.CONTENT__DATA_BINDING, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDataBinding(String newDataBinding) {
		eSet(HtmlPackage.Literals.CONTENT__DATA_BINDING, newDataBinding);
	}

} //ContentImpl
