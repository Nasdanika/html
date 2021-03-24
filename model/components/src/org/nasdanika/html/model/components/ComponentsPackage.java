/**
 */
package org.nasdanika.html.model.components;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;

import org.nasdanika.ncore.NcorePackage;

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
 * Miscellaneous components.
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.components.ComponentsFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "components";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.html.model.components";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.model.components";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentsPackage eINSTANCE = org.nasdanika.html.model.components.impl.ComponentsPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl <em>Table Of Contents Base</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTableOfContentsBase()
	 * @generated
	 */
	int TABLE_OF_CONTENTS_BASE = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__TITLE = BootstrapPackage.BOOTSTRAP_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__APPEARANCE = BootstrapPackage.BOOTSTRAP_ELEMENT__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__HEADER = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__ROLE = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE__DEPTH = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Table Of Contents Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE_FEATURE_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Table Of Contents Base</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_BASE_OPERATION_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl <em>Table Of Contents</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.TableOfContentsImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTableOfContents()
	 * @generated
	 */
	int TABLE_OF_CONTENTS = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__TITLE = TABLE_OF_CONTENTS_BASE__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__DESCRIPTION = TABLE_OF_CONTENTS_BASE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__APPEARANCE = TABLE_OF_CONTENTS_BASE__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__HEADER = TABLE_OF_CONTENTS_BASE__HEADER;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__ROLE = TABLE_OF_CONTENTS_BASE__ROLE;

	/**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__DEPTH = TABLE_OF_CONTENTS_BASE__DEPTH;

	/**
	 * The feature id for the '<em><b>Dark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__DARK = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Striped</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__STRIPED = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Bordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__BORDERED = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Borderless</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__BORDERLESS = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hover</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__HOVER = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Small</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__SMALL = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__DESCRIPTIONS = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Tooltips</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS__TOOLTIPS = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Table Of Contents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_FEATURE_COUNT = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Table Of Contents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OF_CONTENTS_OPERATION_COUNT = TABLE_OF_CONTENTS_BASE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.ListOfContentsImpl <em>List Of Contents</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.ListOfContentsImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getListOfContents()
	 * @generated
	 */
	int LIST_OF_CONTENTS = 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__TITLE = TABLE_OF_CONTENTS_BASE__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__DESCRIPTION = TABLE_OF_CONTENTS_BASE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__APPEARANCE = TABLE_OF_CONTENTS_BASE__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Header</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__HEADER = TABLE_OF_CONTENTS_BASE__HEADER;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__ROLE = TABLE_OF_CONTENTS_BASE__ROLE;

	/**
	 * The feature id for the '<em><b>Depth</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__DEPTH = TABLE_OF_CONTENTS_BASE__DEPTH;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__ORDERING = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tooltips</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS__TOOLTIPS = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>List Of Contents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS_FEATURE_COUNT = TABLE_OF_CONTENTS_BASE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>List Of Contents</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OF_CONTENTS_OPERATION_COUNT = TABLE_OF_CONTENTS_BASE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl <em>Text To Speech</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.TextToSpeechImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeech()
	 * @generated
	 */
	int TEXT_TO_SPEECH = 3;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__LANGUAGE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Voice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__VOICE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__FORMAT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__INTERPOLATE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__APPEARANCE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__PATH = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Embed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH__EMBED = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Text To Speech</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Text To Speech</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechTextImpl <em>Text To Speech Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.TextToSpeechTextImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeechText()
	 * @generated
	 */
	int TEXT_TO_SPEECH_TEXT = 4;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__TITLE = TEXT_TO_SPEECH__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__DESCRIPTION = TEXT_TO_SPEECH__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__LANGUAGE = TEXT_TO_SPEECH__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Voice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__VOICE = TEXT_TO_SPEECH__VOICE;

	/**
	 * The feature id for the '<em><b>Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__FORMAT = TEXT_TO_SPEECH__FORMAT;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__INTERPOLATE = TEXT_TO_SPEECH__INTERPOLATE;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__APPEARANCE = TEXT_TO_SPEECH__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__PATH = TEXT_TO_SPEECH__PATH;

	/**
	 * The feature id for the '<em><b>Embed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__EMBED = TEXT_TO_SPEECH__EMBED;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT__TEXT = TEXT_TO_SPEECH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text To Speech Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT_FEATURE_COUNT = TEXT_TO_SPEECH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Text To Speech Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_TEXT_OPERATION_COUNT = TEXT_TO_SPEECH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechResourceImpl <em>Text To Speech Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.components.impl.TextToSpeechResourceImpl
	 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeechResource()
	 * @generated
	 */
	int TEXT_TO_SPEECH_RESOURCE = 5;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__TITLE = TEXT_TO_SPEECH__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__DESCRIPTION = TEXT_TO_SPEECH__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__LANGUAGE = TEXT_TO_SPEECH__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Voice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__VOICE = TEXT_TO_SPEECH__VOICE;

	/**
	 * The feature id for the '<em><b>Format</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__FORMAT = TEXT_TO_SPEECH__FORMAT;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__INTERPOLATE = TEXT_TO_SPEECH__INTERPOLATE;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__APPEARANCE = TEXT_TO_SPEECH__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__PATH = TEXT_TO_SPEECH__PATH;

	/**
	 * The feature id for the '<em><b>Embed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__EMBED = TEXT_TO_SPEECH__EMBED;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE__LOCATION = TEXT_TO_SPEECH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text To Speech Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE_FEATURE_COUNT = TEXT_TO_SPEECH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Text To Speech Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_TO_SPEECH_RESOURCE_OPERATION_COUNT = TEXT_TO_SPEECH_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.TableOfContentsBase <em>Table Of Contents Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Of Contents Base</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContentsBase
	 * @generated
	 */
	EClass getTableOfContentsBase();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TableOfContentsBase#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Header</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContentsBase#getHeader()
	 * @see #getTableOfContentsBase()
	 * @generated
	 */
	EAttribute getTableOfContentsBase_Header();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TableOfContentsBase#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContentsBase#getRole()
	 * @see #getTableOfContentsBase()
	 * @generated
	 */
	EAttribute getTableOfContentsBase_Role();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TableOfContentsBase#getDepth <em>Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Depth</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContentsBase#getDepth()
	 * @see #getTableOfContentsBase()
	 * @generated
	 */
	EAttribute getTableOfContentsBase_Depth();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.TableOfContents <em>Table Of Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Of Contents</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContents
	 * @generated
	 */
	EClass getTableOfContents();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TableOfContents#isDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Descriptions</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContents#isDescriptions()
	 * @see #getTableOfContents()
	 * @generated
	 */
	EAttribute getTableOfContents_Descriptions();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TableOfContents#isTooltips <em>Tooltips</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltips</em>'.
	 * @see org.nasdanika.html.model.components.TableOfContents#isTooltips()
	 * @see #getTableOfContents()
	 * @generated
	 */
	EAttribute getTableOfContents_Tooltips();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.ListOfContents <em>List Of Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Of Contents</em>'.
	 * @see org.nasdanika.html.model.components.ListOfContents
	 * @generated
	 */
	EClass getListOfContents();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.ListOfContents#getOrdering <em>Ordering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ordering</em>'.
	 * @see org.nasdanika.html.model.components.ListOfContents#getOrdering()
	 * @see #getListOfContents()
	 * @generated
	 */
	EAttribute getListOfContents_Ordering();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.ListOfContents#isTooltips <em>Tooltips</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltips</em>'.
	 * @see org.nasdanika.html.model.components.ListOfContents#isTooltips()
	 * @see #getListOfContents()
	 * @generated
	 */
	EAttribute getListOfContents_Tooltips();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.TextToSpeech <em>Text To Speech</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text To Speech</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech
	 * @generated
	 */
	EClass getTextToSpeech();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#getLanguage()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Language();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#getVoice <em>Voice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Voice</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#getVoice()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Voice();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#getFormat <em>Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Format</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#getFormat()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Format();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#isInterpolate()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Interpolate();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.components.TextToSpeech#getAppearance <em>Appearance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Appearance</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#getAppearance()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EReference getTextToSpeech_Appearance();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#getPath()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeech#isEmbed <em>Embed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Embed</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeech#isEmbed()
	 * @see #getTextToSpeech()
	 * @generated
	 */
	EAttribute getTextToSpeech_Embed();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.TextToSpeechText <em>Text To Speech Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text To Speech Text</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeechText
	 * @generated
	 */
	EClass getTextToSpeechText();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeechText#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeechText#getText()
	 * @see #getTextToSpeechText()
	 * @generated
	 */
	EAttribute getTextToSpeechText_Text();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.components.TextToSpeechResource <em>Text To Speech Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text To Speech Resource</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeechResource
	 * @generated
	 */
	EClass getTextToSpeechResource();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.components.TextToSpeechResource#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.components.TextToSpeechResource#getLocation()
	 * @see #getTextToSpeechResource()
	 * @generated
	 */
	EAttribute getTextToSpeechResource_Location();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentsFactory getComponentsFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl <em>Table Of Contents Base</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTableOfContentsBase()
		 * @generated
		 */
		EClass TABLE_OF_CONTENTS_BASE = eINSTANCE.getTableOfContentsBase();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_OF_CONTENTS_BASE__HEADER = eINSTANCE.getTableOfContentsBase_Header();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_OF_CONTENTS_BASE__ROLE = eINSTANCE.getTableOfContentsBase_Role();

		/**
		 * The meta object literal for the '<em><b>Depth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_OF_CONTENTS_BASE__DEPTH = eINSTANCE.getTableOfContentsBase_Depth();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.TableOfContentsImpl <em>Table Of Contents</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.TableOfContentsImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTableOfContents()
		 * @generated
		 */
		EClass TABLE_OF_CONTENTS = eINSTANCE.getTableOfContents();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_OF_CONTENTS__DESCRIPTIONS = eINSTANCE.getTableOfContents_Descriptions();

		/**
		 * The meta object literal for the '<em><b>Tooltips</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_OF_CONTENTS__TOOLTIPS = eINSTANCE.getTableOfContents_Tooltips();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.ListOfContentsImpl <em>List Of Contents</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.ListOfContentsImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getListOfContents()
		 * @generated
		 */
		EClass LIST_OF_CONTENTS = eINSTANCE.getListOfContents();

		/**
		 * The meta object literal for the '<em><b>Ordering</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_OF_CONTENTS__ORDERING = eINSTANCE.getListOfContents_Ordering();

		/**
		 * The meta object literal for the '<em><b>Tooltips</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_OF_CONTENTS__TOOLTIPS = eINSTANCE.getListOfContents_Tooltips();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl <em>Text To Speech</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.TextToSpeechImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeech()
		 * @generated
		 */
		EClass TEXT_TO_SPEECH = eINSTANCE.getTextToSpeech();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__LANGUAGE = eINSTANCE.getTextToSpeech_Language();

		/**
		 * The meta object literal for the '<em><b>Voice</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__VOICE = eINSTANCE.getTextToSpeech_Voice();

		/**
		 * The meta object literal for the '<em><b>Format</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__FORMAT = eINSTANCE.getTextToSpeech_Format();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__INTERPOLATE = eINSTANCE.getTextToSpeech_Interpolate();

		/**
		 * The meta object literal for the '<em><b>Appearance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEXT_TO_SPEECH__APPEARANCE = eINSTANCE.getTextToSpeech_Appearance();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__PATH = eINSTANCE.getTextToSpeech_Path();

		/**
		 * The meta object literal for the '<em><b>Embed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH__EMBED = eINSTANCE.getTextToSpeech_Embed();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechTextImpl <em>Text To Speech Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.TextToSpeechTextImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeechText()
		 * @generated
		 */
		EClass TEXT_TO_SPEECH_TEXT = eINSTANCE.getTextToSpeechText();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH_TEXT__TEXT = eINSTANCE.getTextToSpeechText_Text();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.components.impl.TextToSpeechResourceImpl <em>Text To Speech Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.components.impl.TextToSpeechResourceImpl
		 * @see org.nasdanika.html.model.components.impl.ComponentsPackageImpl#getTextToSpeechResource()
		 * @generated
		 */
		EClass TEXT_TO_SPEECH_RESOURCE = eINSTANCE.getTextToSpeechResource();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_TO_SPEECH_RESOURCE__LOCATION = eINSTANCE.getTextToSpeechResource_Location();

	}

} //ComponentsPackage
