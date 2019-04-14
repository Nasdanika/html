/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage
 * @generated
 */
public interface BootstrapFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BootstrapFactory eINSTANCE = org.nasdanika.html.model.bootstrap.impl.BootstrapFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>CDN Facet</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CDN Facet</em>'.
	 * @generated
	 */
	BootstrapCDNFacet createBootstrapCDNFacet();

	/**
	 * Returns a new object of class '<em>Wrap</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wrap</em>'.
	 * @generated
	 */
	Wrap createWrap();

	/**
	 * Returns a new object of class '<em>Alert</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Alert</em>'.
	 * @generated
	 */
	Alert createAlert();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	BootstrapPackage getBootstrapPackage();

} //BootstrapFactory
