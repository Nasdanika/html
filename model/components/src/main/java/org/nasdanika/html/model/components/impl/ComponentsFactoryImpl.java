/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.html.model.components.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentsFactoryImpl extends EFactoryImpl implements ComponentsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentsFactory init() {
		try {
			ComponentsFactory theComponentsFactory = (ComponentsFactory)EPackage.Registry.INSTANCE.getEFactory(ComponentsPackage.eNS_URI);
			if (theComponentsFactory != null) {
				return theComponentsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ComponentsPackage.TABLE_OF_CONTENTS: return createTableOfContents();
			case ComponentsPackage.LIST_OF_CONTENTS: return createListOfContents();
			case ComponentsPackage.TEXT_TO_SPEECH_TEXT: return createTextToSpeechText();
			case ComponentsPackage.TEXT_TO_SPEECH_RESOURCE: return createTextToSpeechResource();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableOfContents createTableOfContents() {
		TableOfContentsImpl tableOfContents = new TableOfContentsImpl();
		return tableOfContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ListOfContents createListOfContents() {
		ListOfContentsImpl listOfContents = new ListOfContentsImpl();
		return listOfContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextToSpeechText createTextToSpeechText() {
		TextToSpeechTextImpl textToSpeechText = new TextToSpeechTextImpl();
		return textToSpeechText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextToSpeechResource createTextToSpeechResource() {
		TextToSpeechResourceImpl textToSpeechResource = new TextToSpeechResourceImpl();
		return textToSpeechResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentsPackage getComponentsPackage() {
		return (ComponentsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentsPackage getPackage() {
		return ComponentsPackage.eINSTANCE;
	}

} //ComponentsFactoryImpl
