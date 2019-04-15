/**
 */
package org.nasdanika.html.model.bootstrap.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.html.model.bootstrap.*;

import org.nasdanika.html.model.html.Container;
import org.nasdanika.html.model.html.Content;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.HTMLElement;
import org.nasdanika.html.model.html.ModelElement;

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
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage
 * @generated
 */
public class BootstrapSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BootstrapPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapSwitch() {
		if (modelPackage == null) {
			modelPackage = BootstrapPackage.eINSTANCE;
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
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BootstrapPackage.BOOTSTRAP_CDN_FACET: {
				BootstrapCDNFacet bootstrapCDNFacet = (BootstrapCDNFacet)theEObject;
				T1 result = caseBootstrapCDNFacet(bootstrapCDNFacet);
				if (result == null) result = caseFacet(bootstrapCDNFacet);
				if (result == null) result = caseModelElement(bootstrapCDNFacet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BOOTSTRAP_ELEMENT: {
				BootstrapElement bootstrapElement = (BootstrapElement)theEObject;
				T1 result = caseBootstrapElement(bootstrapElement);
				if (result == null) result = caseHTMLElement(bootstrapElement);
				if (result == null) result = caseModelElement(bootstrapElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT: {
				ContentBootstrapElement contentBootstrapElement = (ContentBootstrapElement)theEObject;
				T1 result = caseContentBootstrapElement(contentBootstrapElement);
				if (result == null) result = caseBootstrapElement(contentBootstrapElement);
				if (result == null) result = caseContent(contentBootstrapElement);
				if (result == null) result = caseHTMLElement(contentBootstrapElement);
				if (result == null) result = caseModelElement(contentBootstrapElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CONTAINER_BOOTSTRAP_ELEMENT: {
				ContainerBootstrapElement containerBootstrapElement = (ContainerBootstrapElement)theEObject;
				T1 result = caseContainerBootstrapElement(containerBootstrapElement);
				if (result == null) result = caseContentBootstrapElement(containerBootstrapElement);
				if (result == null) result = caseContainer(containerBootstrapElement);
				if (result == null) result = caseBootstrapElement(containerBootstrapElement);
				if (result == null) result = caseContent(containerBootstrapElement);
				if (result == null) result = caseHTMLElement(containerBootstrapElement);
				if (result == null) result = caseModelElement(containerBootstrapElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.WRAP: {
				Wrap wrap = (Wrap)theEObject;
				T1 result = caseWrap(wrap);
				if (result == null) result = caseContentBootstrapElement(wrap);
				if (result == null) result = caseBootstrapElement(wrap);
				if (result == null) result = caseContent(wrap);
				if (result == null) result = caseHTMLElement(wrap);
				if (result == null) result = caseModelElement(wrap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ALERT: {
				Alert alert = (Alert)theEObject;
				T1 result = caseAlert(alert);
				if (result == null) result = caseContainerBootstrapElement(alert);
				if (result == null) result = caseContentBootstrapElement(alert);
				if (result == null) result = caseContainer(alert);
				if (result == null) result = caseBootstrapElement(alert);
				if (result == null) result = caseContent(alert);
				if (result == null) result = caseHTMLElement(alert);
				if (result == null) result = caseModelElement(alert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.LIST_GROUP: {
				ListGroup listGroup = (ListGroup)theEObject;
				T1 result = caseListGroup(listGroup);
				if (result == null) result = caseContentBootstrapElement(listGroup);
				if (result == null) result = caseBootstrapElement(listGroup);
				if (result == null) result = caseContent(listGroup);
				if (result == null) result = caseHTMLElement(listGroup);
				if (result == null) result = caseModelElement(listGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.LIST_GROUP_ITEM: {
				ListGroupItem listGroupItem = (ListGroupItem)theEObject;
				T1 result = caseListGroupItem(listGroupItem);
				if (result == null) result = caseModelElement(listGroupItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BADGE: {
				Badge badge = (Badge)theEObject;
				T1 result = caseBadge(badge);
				if (result == null) result = caseContainerBootstrapElement(badge);
				if (result == null) result = caseContentBootstrapElement(badge);
				if (result == null) result = caseContainer(badge);
				if (result == null) result = caseBootstrapElement(badge);
				if (result == null) result = caseContent(badge);
				if (result == null) result = caseHTMLElement(badge);
				if (result == null) result = caseModelElement(badge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CDN Facet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CDN Facet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBootstrapCDNFacet(BootstrapCDNFacet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBootstrapElement(BootstrapElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Bootstrap Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Bootstrap Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContentBootstrapElement(ContentBootstrapElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Bootstrap Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Bootstrap Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContainerBootstrapElement(ContainerBootstrapElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wrap</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wrap</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseWrap(Wrap object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alert</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alert</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAlert(Alert object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseListGroup(ListGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Group Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Group Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseListGroupItem(ListGroupItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Badge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Badge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBadge(Badge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Facet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Facet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseFacet(Facet<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContent(Content object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>HTML Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>HTML Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseHTMLElement(HTMLElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContainer(Container object) {
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
	public T1 defaultCase(EObject object) {
		return null;
	}

} //BootstrapSwitch
