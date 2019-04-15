/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ContentBootstrapElement;

import org.nasdanika.html.model.html.Content;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Iterator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Bootstrap Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl#getFacets <em>Facets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl#getDataBinding <em>Data Binding</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ContentBootstrapElementImpl extends BootstrapElementImpl implements ContentBootstrapElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentBootstrapElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.CONTENT_BOOTSTRAP_ELEMENT;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Content.class) {
			switch (derivedFeatureID) {
				case BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__ITERATOR: return HtmlPackage.CONTENT__ITERATOR;
				case BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__FACETS: return HtmlPackage.CONTENT__FACETS;
				case BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING: return HtmlPackage.CONTENT__DATA_BINDING;
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
		if (baseClass == Content.class) {
			switch (baseFeatureID) {
				case HtmlPackage.CONTENT__ITERATOR: return BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__ITERATOR;
				case HtmlPackage.CONTENT__FACETS: return BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__FACETS;
				case HtmlPackage.CONTENT__DATA_BINDING: return BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ContentBootstrapElementImpl
