/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.html.Event;

import org.nasdanika.html.model.html.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HtmlFactoryImpl extends EFactoryImpl implements HtmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HtmlFactory init() {
		try {
			HtmlFactory theHtmlFactory = (HtmlFactory)EPackage.Registry.INSTANCE.getEFactory(HtmlPackage.eNS_URI);
			if (theHtmlFactory != null) {
				return theHtmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HtmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HtmlFactoryImpl() {
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
			case HtmlPackage.PAGE: return (EObject)createPage();
			case HtmlPackage.CONTENT_REFERENCE: return (EObject)createContentReference();
			case HtmlPackage.RESOURCE_CONTENT: return (EObject)createResourceContent();
			case HtmlPackage.TEXT: return (EObject)createText();
			case HtmlPackage.CONTENT_GENERATOR: return (EObject)createContentGenerator();
			case HtmlPackage.DATA: return (EObject)createData();
			case HtmlPackage.CONTAINER: return (EObject)createContainer();
			case HtmlPackage.PROPERTY: return (EObject)createProperty();
			case HtmlPackage.DATA_BINDING_ITERATOR: return (EObject)createDataBindingIterator();
			case HtmlPackage.RESOURCE_ITERATOR: return (EObject)createResourceIterator();
			case HtmlPackage.SWITCH: return (EObject)createSwitch();
			case HtmlPackage.CASE: return (EObject)createCase();
			case HtmlPackage.SCRIPT: return (EObject)createScript();
			case HtmlPackage.SCRIPT_REFERENCE: return (EObject)createScriptReference();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case HtmlPackage.CONTENT_TYPE:
				return createContentTypeFromString(eDataType, initialValue);
			case HtmlPackage.EVENT:
				return createEventFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case HtmlPackage.CONTENT_TYPE:
				return convertContentTypeToString(eDataType, instanceValue);
			case HtmlPackage.EVENT:
				return convertEventToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentReference createContentReference() {
		ContentReferenceImpl contentReference = new ContentReferenceImpl();
		return contentReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceContent createResourceContent() {
		ResourceContentImpl resourceContent = new ResourceContentImpl();
		return resourceContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentGenerator createContentGenerator() {
		ContentGeneratorImpl contentGenerator = new ContentGeneratorImpl();
		return contentGenerator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data createData() {
		DataImpl data = new DataImpl();
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.html.model.html.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Property createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataBindingIterator createDataBindingIterator() {
		DataBindingIteratorImpl dataBindingIterator = new DataBindingIteratorImpl();
		return dataBindingIterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceIterator createResourceIterator() {
		ResourceIteratorImpl resourceIterator = new ResourceIteratorImpl();
		return resourceIterator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Switch createSwitch() {
		SwitchImpl switch_ = new SwitchImpl();
		return switch_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Case createCase() {
		CaseImpl case_ = new CaseImpl();
		return case_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Script createScript() {
		ScriptImpl script = new ScriptImpl();
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScriptReference createScriptReference() {
		ScriptReferenceImpl scriptReference = new ScriptReferenceImpl();
		return scriptReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentType createContentTypeFromString(EDataType eDataType, String initialValue) {
		ContentType result = ContentType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContentTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEventFromString(EDataType eDataType, String initialValue) {
		return (Event)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEventToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HtmlPackage getHtmlPackage() {
		return (HtmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HtmlPackage getPackage() {
		return HtmlPackage.eINSTANCE;
	}

} //HtmlFactoryImpl
