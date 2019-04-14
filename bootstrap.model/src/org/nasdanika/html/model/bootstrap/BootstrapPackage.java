/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.nasdanika.html.model.html.HtmlPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * A model of Bootstrap elements.
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.bootstrap.BootstrapFactory
 * @model kind="package"
 * @generated
 */
public interface BootstrapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bootstrap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.html.bootstrap";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.bootstrap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BootstrapPackage eINSTANCE = org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl <em>CDN Facet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapCDNFacet()
	 * @generated
	 */
	int BOOTSTRAP_CDN_FACET = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET__DESCRIPTION = HtmlPackage.FACET__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET__ANNOTATIONS = HtmlPackage.FACET__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>CDN Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET_FEATURE_COUNT = HtmlPackage.FACET_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Is Facet For</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET___IS_FACET_FOR__OBJECT = HtmlPackage.FACET___IS_FACET_FOR__OBJECT;

	/**
	 * The number of operations of the '<em>CDN Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET_OPERATION_COUNT = HtmlPackage.FACET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Theme</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getTheme()
	 * @generated
	 */
	int THEME = 1;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.BootstrapCDNFacet <em>CDN Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDN Facet</em>'.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapCDNFacet
	 * @generated
	 */
	EClass getBootstrapCDNFacet();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Theme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Theme</em>'.
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @model instanceClass="org.nasdanika.html.bootstrap.Theme"
	 * @generated
	 */
	EDataType getTheme();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BootstrapFactory getBootstrapFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl <em>CDN Facet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapCDNFacet()
		 * @generated
		 */
		EClass BOOTSTRAP_CDN_FACET = eINSTANCE.getBootstrapCDNFacet();

		/**
		 * The meta object literal for the '<em>Theme</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Theme
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getTheme()
		 * @generated
		 */
		EDataType THEME = eINSTANCE.getTheme();

	}

} //BootstrapPackage
