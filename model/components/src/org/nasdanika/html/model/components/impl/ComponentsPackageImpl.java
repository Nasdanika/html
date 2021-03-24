/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;

import org.nasdanika.html.model.components.ComponentsFactory;
import org.nasdanika.html.model.components.ComponentsPackage;
import org.nasdanika.html.model.components.ListOfContents;
import org.nasdanika.html.model.components.TableOfContents;
import org.nasdanika.html.model.components.TableOfContentsBase;
import org.nasdanika.html.model.components.TextToSpeech;
import org.nasdanika.html.model.components.TextToSpeechResource;
import org.nasdanika.html.model.components.TextToSpeechText;

import org.nasdanika.html.model.html.HtmlPackage;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentsPackageImpl extends EPackageImpl implements ComponentsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableOfContentsBaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableOfContentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listOfContentsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textToSpeechEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textToSpeechTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textToSpeechResourceEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.html.model.components.ComponentsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ComponentsPackageImpl() {
		super(eNS_URI, ComponentsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link ComponentsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ComponentsPackage init() {
		if (isInited) return (ComponentsPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredComponentsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ComponentsPackageImpl theComponentsPackage = registeredComponentsPackage instanceof ComponentsPackageImpl ? (ComponentsPackageImpl)registeredComponentsPackage : new ComponentsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		BootstrapPackage.eINSTANCE.eClass();
		HtmlPackage.eINSTANCE.eClass();
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theComponentsPackage.createPackageContents();

		// Initialize created meta-data
		theComponentsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComponentsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComponentsPackage.eNS_URI, theComponentsPackage);
		return theComponentsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTableOfContentsBase() {
		return tableOfContentsBaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTableOfContentsBase_Header() {
		return (EAttribute)tableOfContentsBaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTableOfContentsBase_Role() {
		return (EAttribute)tableOfContentsBaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTableOfContentsBase_Depth() {
		return (EAttribute)tableOfContentsBaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTableOfContents() {
		return tableOfContentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTableOfContents_Descriptions() {
		return (EAttribute)tableOfContentsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTableOfContents_Tooltips() {
		return (EAttribute)tableOfContentsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListOfContents() {
		return listOfContentsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListOfContents_Ordering() {
		return (EAttribute)listOfContentsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListOfContents_Tooltips() {
		return (EAttribute)listOfContentsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextToSpeech() {
		return textToSpeechEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Language() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Voice() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Format() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Interpolate() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTextToSpeech_Appearance() {
		return (EReference)textToSpeechEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Path() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeech_Embed() {
		return (EAttribute)textToSpeechEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextToSpeechText() {
		return textToSpeechTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeechText_Text() {
		return (EAttribute)textToSpeechTextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextToSpeechResource() {
		return textToSpeechResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextToSpeechResource_Location() {
		return (EAttribute)textToSpeechResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComponentsFactory getComponentsFactory() {
		return (ComponentsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		tableOfContentsBaseEClass = createEClass(TABLE_OF_CONTENTS_BASE);
		createEAttribute(tableOfContentsBaseEClass, TABLE_OF_CONTENTS_BASE__HEADER);
		createEAttribute(tableOfContentsBaseEClass, TABLE_OF_CONTENTS_BASE__ROLE);
		createEAttribute(tableOfContentsBaseEClass, TABLE_OF_CONTENTS_BASE__DEPTH);

		tableOfContentsEClass = createEClass(TABLE_OF_CONTENTS);
		createEAttribute(tableOfContentsEClass, TABLE_OF_CONTENTS__DESCRIPTIONS);
		createEAttribute(tableOfContentsEClass, TABLE_OF_CONTENTS__TOOLTIPS);

		listOfContentsEClass = createEClass(LIST_OF_CONTENTS);
		createEAttribute(listOfContentsEClass, LIST_OF_CONTENTS__ORDERING);
		createEAttribute(listOfContentsEClass, LIST_OF_CONTENTS__TOOLTIPS);

		textToSpeechEClass = createEClass(TEXT_TO_SPEECH);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__LANGUAGE);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__VOICE);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__FORMAT);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__INTERPOLATE);
		createEReference(textToSpeechEClass, TEXT_TO_SPEECH__APPEARANCE);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__PATH);
		createEAttribute(textToSpeechEClass, TEXT_TO_SPEECH__EMBED);

		textToSpeechTextEClass = createEClass(TEXT_TO_SPEECH_TEXT);
		createEAttribute(textToSpeechTextEClass, TEXT_TO_SPEECH_TEXT__TEXT);

		textToSpeechResourceEClass = createEClass(TEXT_TO_SPEECH_RESOURCE);
		createEAttribute(textToSpeechResourceEClass, TEXT_TO_SPEECH_RESOURCE__LOCATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		BootstrapPackage theBootstrapPackage = (BootstrapPackage)EPackage.Registry.INSTANCE.getEPackage(BootstrapPackage.eNS_URI);
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		tableOfContentsBaseEClass.getESuperTypes().add(theBootstrapPackage.getBootstrapElement());
		tableOfContentsEClass.getESuperTypes().add(this.getTableOfContentsBase());
		tableOfContentsEClass.getESuperTypes().add(theBootstrapPackage.getTableConfiguration());
		listOfContentsEClass.getESuperTypes().add(this.getTableOfContentsBase());
		textToSpeechEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		textToSpeechTextEClass.getESuperTypes().add(this.getTextToSpeech());
		textToSpeechResourceEClass.getESuperTypes().add(this.getTextToSpeech());

		// Initialize classes, features, and operations; add parameters
		initEClass(tableOfContentsBaseEClass, TableOfContentsBase.class, "TableOfContentsBase", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableOfContentsBase_Header(), ecorePackage.getEString(), "header", "", 0, 1, TableOfContentsBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableOfContentsBase_Role(), ecorePackage.getEString(), "role", "", 0, 1, TableOfContentsBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableOfContentsBase_Depth(), ecorePackage.getEInt(), "depth", "3", 0, 1, TableOfContentsBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableOfContentsEClass, TableOfContents.class, "TableOfContents", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableOfContents_Descriptions(), ecorePackage.getEBoolean(), "descriptions", "false", 0, 1, TableOfContents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTableOfContents_Tooltips(), ecorePackage.getEBoolean(), "tooltips", "false", 0, 1, TableOfContents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listOfContentsEClass, ListOfContents.class, "ListOfContents", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getListOfContents_Ordering(), ecorePackage.getEString(), "ordering", "", 0, 1, ListOfContents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getListOfContents_Tooltips(), ecorePackage.getEBoolean(), "tooltips", "false", 0, 1, ListOfContents.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textToSpeechEClass, TextToSpeech.class, "TextToSpeech", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextToSpeech_Language(), ecorePackage.getEString(), "language", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTextToSpeech_Voice(), ecorePackage.getEString(), "voice", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTextToSpeech_Format(), ecorePackage.getEString(), "format", "Text", 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTextToSpeech_Interpolate(), ecorePackage.getEBoolean(), "interpolate", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTextToSpeech_Appearance(), theBootstrapPackage.getAppearance(), null, "appearance", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTextToSpeech_Path(), ecorePackage.getEString(), "path", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTextToSpeech_Embed(), ecorePackage.getEBoolean(), "embed", null, 0, 1, TextToSpeech.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textToSpeechTextEClass, TextToSpeechText.class, "TextToSpeechText", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextToSpeechText_Text(), ecorePackage.getEString(), "text", null, 1, 1, TextToSpeechText.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textToSpeechResourceEClass, TextToSpeechResource.class, "TextToSpeechResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextToSpeechResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, TextToSpeechResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation", "Miscellaneous components."
		   });
		addAnnotation
		  (tableOfContentsBaseEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for tables of content."
		   });
		addAnnotation
		  (getTableOfContentsBase_Header(),
		   source,
		   new String[] {
			   "documentation", "Table of contents header"
		   });
		addAnnotation
		  (getTableOfContentsBase_Role(),
		   source,
		   new String[] {
			   "documentation", "Table of contents includes action children in the specified role - navigation or section."
		   });
		addAnnotation
		  (getTableOfContentsBase_Depth(),
		   source,
		   new String[] {
			   "documentation", "Table of contents depth."
		   });
		addAnnotation
		  (tableOfContentsEClass,
		   source,
		   new String[] {
			   "documentation", "Containing action content rendered in a table."
		   });
		addAnnotation
		  (getTableOfContents_Descriptions(),
		   source,
		   new String[] {
			   "documentation", "If selected, action descriptions are shown in the table of contents."
		   });
		addAnnotation
		  (getTableOfContents_Tooltips(),
		   source,
		   new String[] {
			   "documentation", "If selected and \"descriptions\" is not selected, action tooltips are shown in the table of contents."
		   });
		addAnnotation
		  (listOfContentsEClass,
		   source,
		   new String[] {
			   "documentation", "Containing action content rendered in a list."
		   });
		addAnnotation
		  (getListOfContents_Ordering(),
		   source,
		   new String[] {
			   "documentation", "Ordering style. \n\"Auto\" means starting with numbers and going over all available styles with each additional level."
		   });
		addAnnotation
		  (getListOfContents_Tooltips(),
		   source,
		   new String[] {
			   "documentation", "If selected, action tooltips are shown in the list."
		   });
		addAnnotation
		  (textToSpeechEClass,
		   source,
		   new String[] {
			   "documentation", "Uses [Google Text-to-Speech](https://cloud.google.com/text-to-speech) to synthesize \nvoice from text or [SSML](https://cloud.google.com/text-to-speech/docs/ssml). \nVoice is output to an mp3 file. File name is defined by the ``path`` attribute if it is not blank. \nIn this case path is resolved relative to the containing action. \nIf ``path`` attribute is blank then the file name is computed as a digest of the sound bytes.\n\nGenerates audio tag which plays the synthesized speech.\n\nUse of text to speech requires ``GOOGLE_APPLICATION_CREDENTIALS`` environment variable to be set to the location of the private key JSON file.\nSee https://developers.google.com/accounts/docs/application-default-credentials for more information.\n   "
		   });
		addAnnotation
		  (getTextToSpeech_Language(),
		   source,
		   new String[] {
			   "documentation", "Language and locale code, e.g. ``en-US``. If blank, then the contextual language and locale are used - hardcoded in the UI and configurable in the CLI. \nSee [Supported voices and languages](https://cloud.google.com/text-to-speech/docs/voices) for a list of locales and voices."
		   });
		addAnnotation
		  (getTextToSpeech_Voice(),
		   source,
		   new String[] {
			   "documentation", "Voice name, e.g. ``en-US-Wavenet-D``. If blank, then the contextual voice is used - hardcoded in the UI and configurable in the CLI. \nSee [Supported voices and languages](https://cloud.google.com/text-to-speech/docs/voices) for a list of locales and voices."
		   });
		addAnnotation
		  (getTextToSpeech_Format(),
		   source,
		   new String[] {
			   "documentation", "Text format - ``Text`` or ``SSML``."
		   });
		addAnnotation
		  (getTextToSpeech_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If true, text/ssml is interpolated before speech generation."
		   });
		addAnnotation
		  (getTextToSpeech_Appearance(),
		   source,
		   new String[] {
			   "documentation", "Appearance to apply to the generated audio tag."
		   });
		addAnnotation
		  (getTextToSpeech_Path(),
		   source,
		   new String[] {
			   "documentation", "MP3 resource location (file name) relative to the containing action context URI.\nIf this attribute is blank then the file name is computed as a digest of the sound bytes."
		   });
		addAnnotation
		  (getTextToSpeech_Embed(),
		   source,
		   new String[] {
			   "documentation", "If true, audio data is not stored to a file, but is embedded into the page using ``data:`` URI."
		   });
		addAnnotation
		  (textToSpeechTextEClass,
		   source,
		   new String[] {
			   "documentation", "Speaks ``text`` attribute."
		   });
		addAnnotation
		  (getTextToSpeechText_Text(),
		   source,
		   new String[] {
			   "documentation", "Text to speak, in case of  [SSML](https://cloud.google.com/text-to-speech/docs/ssml) format ``<speak>`` and  ``</speak>`` opening and closing tags are implied."
		   });
		addAnnotation
		  (textToSpeechResourceEClass,
		   source,
		   new String[] {
			   "documentation", "Speaks resource specified in the ``location`` attribute."
		   });
		addAnnotation
		  (getTextToSpeechResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Markdown resource location. The resource location is resolved relative to the model resource."
		   });
	}

} //ComponentsPackageImpl
