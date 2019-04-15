/**
 */
package org.nasdanika.html.model.html.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.html.model.html.*;

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
 * @see org.nasdanika.html.model.html.HtmlPackage
 * @generated
 */
public class HtmlSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static HtmlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HtmlSwitch() {
		if (modelPackage == null) {
			modelPackage = HtmlPackage.eINSTANCE;
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
			case HtmlPackage.MODEL_ELEMENT: {
				ModelElement modelElement = (ModelElement)theEObject;
				T1 result = caseModelElement(modelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.PAGE: {
				Page page = (Page)theEObject;
				T1 result = casePage(page);
				if (result == null) result = caseModelElement(page);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.CONTENT: {
				Content content = (Content)theEObject;
				T1 result = caseContent(content);
				if (result == null) result = caseModelElement(content);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.CONTENT_REFERENCE: {
				ContentReference contentReference = (ContentReference)theEObject;
				T1 result = caseContentReference(contentReference);
				if (result == null) result = caseContent(contentReference);
				if (result == null) result = caseModelElement(contentReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.RESOURCE_CONTENT: {
				ResourceContent resourceContent = (ResourceContent)theEObject;
				T1 result = caseResourceContent(resourceContent);
				if (result == null) result = caseContent(resourceContent);
				if (result == null) result = caseResourceReference(resourceContent);
				if (result == null) result = caseModelElement(resourceContent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.TEXT: {
				Text text = (Text)theEObject;
				T1 result = caseText(text);
				if (result == null) result = caseContent(text);
				if (result == null) result = caseModelElement(text);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.CONTENT_GENERATOR: {
				ContentGenerator contentGenerator = (ContentGenerator)theEObject;
				T1 result = caseContentGenerator(contentGenerator);
				if (result == null) result = caseContent(contentGenerator);
				if (result == null) result = caseModelElement(contentGenerator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T1 result = caseContainer(container);
				if (result == null) result = caseContent(container);
				if (result == null) result = caseModelElement(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.FACET: {
				Facet<?> facet = (Facet<?>)theEObject;
				T1 result = caseFacet(facet);
				if (result == null) result = caseModelElement(facet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.ITERATOR: {
				Iterator iterator = (Iterator)theEObject;
				T1 result = caseIterator(iterator);
				if (result == null) result = caseModelElement(iterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.PROPERTY: {
				Property property = (Property)theEObject;
				T1 result = caseProperty(property);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.DATA_BINDING_ITERATOR: {
				DataBindingIterator dataBindingIterator = (DataBindingIterator)theEObject;
				T1 result = caseDataBindingIterator(dataBindingIterator);
				if (result == null) result = caseIterator(dataBindingIterator);
				if (result == null) result = caseModelElement(dataBindingIterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.RESOURCE_ITERATOR: {
				ResourceIterator resourceIterator = (ResourceIterator)theEObject;
				T1 result = caseResourceIterator(resourceIterator);
				if (result == null) result = caseIterator(resourceIterator);
				if (result == null) result = caseResourceReference(resourceIterator);
				if (result == null) result = caseModelElement(resourceIterator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.SWITCH: {
				org.nasdanika.html.model.html.Switch switch_ = (org.nasdanika.html.model.html.Switch)theEObject;
				T1 result = caseSwitch(switch_);
				if (result == null) result = caseModelElement(switch_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.CASE: {
				Case case_ = (Case)theEObject;
				T1 result = caseCase(case_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.RESOURCE_REFERENCE: {
				ResourceReference resourceReference = (ResourceReference)theEObject;
				T1 result = caseResourceReference(resourceReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.HTML_ELEMENT: {
				HTMLElement htmlElement = (HTMLElement)theEObject;
				T1 result = caseHTMLElement(htmlElement);
				if (result == null) result = caseModelElement(htmlElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.EVENT_HANDLER: {
				EventHandler eventHandler = (EventHandler)theEObject;
				T1 result = caseEventHandler(eventHandler);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.SCRIPT: {
				Script script = (Script)theEObject;
				T1 result = caseScript(script);
				if (result == null) result = caseEventHandler(script);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HtmlPackage.SCRIPT_REFERENCE: {
				ScriptReference scriptReference = (ScriptReference)theEObject;
				T1 result = caseScriptReference(scriptReference);
				if (result == null) result = caseEventHandler(scriptReference);
				if (result == null) result = caseResourceReference(scriptReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePage(Page object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Content Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContentReference(ContentReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Content</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceContent(ResourceContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseText(Text object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Generator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Generator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseContentGenerator(ContentGenerator object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIterator(Iterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperty(Property object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Binding Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Binding Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDataBindingIterator(DataBindingIterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Iterator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Iterator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceIterator(ResourceIterator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Switch</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Switch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSwitch(org.nasdanika.html.model.html.Switch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseCase(Case object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceReference(ResourceReference object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Event Handler</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEventHandler(EventHandler object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScript(Script object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Script Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Script Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScriptReference(ScriptReference object) {
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

} //HtmlSwitch
