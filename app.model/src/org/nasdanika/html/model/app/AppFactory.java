/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage
 * @generated
 */
public interface AppFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AppFactory eINSTANCE = org.nasdanika.html.model.app.impl.AppFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Label</em>'.
	 * @generated
	 */
	Label createLabel();

	/**
	 * Returns a new object of class '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	Action createAction();

	/**
	 * Returns a new object of class '<em>Themed Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Themed Action</em>'.
	 * @generated
	 */
	ThemedAction createThemedAction();

	/**
	 * Returns a new object of class '<em>Content Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Content Action</em>'.
	 * @generated
	 */
	ContentAction createContentAction();

	/**
	 * Returns a new object of class '<em>Themed Content Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Themed Content Action</em>'.
	 * @generated
	 */
	ThemedContentAction createThemedContentAction();

	/**
	 * Returns a new object of class '<em>Navigation Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Navigation Action Activator</em>'.
	 * @generated
	 */
	NavigationActionActivator createNavigationActionActivator();

	/**
	 * Returns a new object of class '<em>Script Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Script Action Activator</em>'.
	 * @generated
	 */
	ScriptActionActivator createScriptActionActivator();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AppPackage getAppPackage();

} //AppFactory
