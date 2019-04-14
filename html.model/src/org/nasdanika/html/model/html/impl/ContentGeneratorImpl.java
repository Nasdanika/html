/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.ContentGenerator;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Generator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.ContentGeneratorImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContentGeneratorImpl extends ContentImpl implements ContentGenerator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentGeneratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.CONTENT_GENERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eGet(HtmlPackage.Literals.CONTENT_GENERATOR__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eSet(HtmlPackage.Literals.CONTENT_GENERATOR__NAME, newName);
	}

} //ContentGeneratorImpl
