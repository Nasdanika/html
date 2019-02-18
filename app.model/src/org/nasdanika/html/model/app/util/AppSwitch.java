/**
 */
package org.nasdanika.html.model.app.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.model.app.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage
 * @generated
 */
public class AppSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AppPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AppSwitch() {
		if (modelPackage == null) {
			modelPackage = AppPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case AppPackage.ILABEL: {
				Label iLabel = (Label)theEObject;
				T result = caseILabel(iLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.LABEL: {
				org.nasdanika.html.model.app.Label label = (org.nasdanika.html.model.app.Label)theEObject;
				T result = caseLabel(label);
				if (result == null) result = caseILabel(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.IACTION: {
				Action iAction = (Action)theEObject;
				T result = caseIAction(iAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.ACTION: {
				org.nasdanika.html.model.app.Action action = (org.nasdanika.html.model.app.Action)theEObject;
				T result = caseAction(action);
				if (result == null) result = caseIAction(action);
				if (result == null) result = caseLabel(action);
				if (result == null) result = caseILabel(action);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.CONTENT_ACTION: {
				ContentAction contentAction = (ContentAction)theEObject;
				T result = caseContentAction(contentAction);
				if (result == null) result = caseAction(contentAction);
				if (result == null) result = caseIAction(contentAction);
				if (result == null) result = caseLabel(contentAction);
				if (result == null) result = caseILabel(contentAction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.IACTION_ACTIVATOR: {
				ActionActivator iActionActivator = (ActionActivator)theEObject;
				T result = caseIActionActivator(iActionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.INAVIGATION_ACTION_ACTIVATOR: {
				NavigationActionActivator iNavigationActionActivator = (NavigationActionActivator)theEObject;
				T result = caseINavigationActionActivator(iNavigationActionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.ISCRIPT_ACTION_ACTIVATOR: {
				ScriptActionActivator iScriptActionActivator = (ScriptActionActivator)theEObject;
				T result = caseIScriptActionActivator(iScriptActionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.ACTION_ACTIVATOR: {
				org.nasdanika.html.model.app.ActionActivator actionActivator = (org.nasdanika.html.model.app.ActionActivator)theEObject;
				T result = caseActionActivator(actionActivator);
				if (result == null) result = caseIActionActivator(actionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.NAVIGATION_ACTION_ACTIVATOR: {
				org.nasdanika.html.model.app.NavigationActionActivator navigationActionActivator = (org.nasdanika.html.model.app.NavigationActionActivator)theEObject;
				T result = caseNavigationActionActivator(navigationActionActivator);
				if (result == null) result = caseINavigationActionActivator(navigationActionActivator);
				if (result == null) result = caseActionActivator(navigationActionActivator);
				if (result == null) result = caseIActionActivator(navigationActionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AppPackage.SCRIPT_ACTION_ACTIVATOR: {
				org.nasdanika.html.model.app.ScriptActionActivator scriptActionActivator = (org.nasdanika.html.model.app.ScriptActionActivator)theEObject;
				T result = caseScriptActionActivator(scriptActionActivator);
				if (result == null) result = caseIScriptActionActivator(scriptActionActivator);
				if (result == null) result = caseActionActivator(scriptActionActivator);
				if (result == null) result = caseIActionActivator(scriptActionActivator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ILabel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ILabel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseILabel(Label object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLabel(org.nasdanika.html.model.app.Label object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIAction(Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAction(org.nasdanika.html.model.app.Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentAction(ContentAction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IAction Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IAction Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIActionActivator(ActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>INavigation Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>INavigation Action Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseINavigationActionActivator(NavigationActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IScript Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IScript Action Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIScriptActionActivator(ScriptActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionActivator(org.nasdanika.html.model.app.ActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navigation Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navigation Action Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavigationActionActivator(org.nasdanika.html.model.app.NavigationActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Action Activator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Action Activator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScriptActionActivator(org.nasdanika.html.model.app.ScriptActionActivator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //AppSwitch
