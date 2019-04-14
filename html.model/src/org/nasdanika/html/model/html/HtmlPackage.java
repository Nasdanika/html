/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * Model of HTML elements. It also contains base classes which are not specific to HTML such as ModelElement, Content, Facet, Iterator, ...
 * 
 * ## Generation context
 * 
 * When data bindings, data binding iterators and interpolations are used tt is assumed that a data context is available during model -> HTML generation.
 * 
 * Such data context is a function taking a single string argument and returning object value (``java.util.Function<String,Object>``). 
 * 
 * ## Interpolation
 * 
 * Text content and attributes may contain ``{{key[|default value]}}`` tokens which are evaluated to values by retrieving ``key`` from the context. 
 * If there is no context or no key then an optional default value is used. If there is no default value then the token remains uninterpolated.
 * 
 * For text interpolation and data binding can be used interchangeably. If both are present, data binding takes precedence. 
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.html.HtmlFactory
 * @model kind="package"
 * @generated
 */
public interface HtmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "html";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.html";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HtmlPackage eINSTANCE = org.nasdanika.html.model.html.impl.HtmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ModelElementImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__ANNOTATIONS = 1;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.PageImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LANG = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TITLE = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Stylesheets</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__STYLESHEETS = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Scripts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__SCRIPTS = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Head</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__HEAD = MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__BODY = MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__FACETS = MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ContentImpl <em>Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ContentImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContent()
	 * @generated
	 */
	int CONTENT = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__ITERATOR = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__FACETS = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT__DATA_BINDING = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ContentReferenceImpl <em>Content Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ContentReferenceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentReference()
	 * @generated
	 */
	int CONTENT_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__DESCRIPTION = CONTENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__ANNOTATIONS = CONTENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__ITERATOR = CONTENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__FACETS = CONTENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__DATA_BINDING = CONTENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE__TARGET = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Content Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Content Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_REFERENCE_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ResourceContentImpl <em>Resource Content</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ResourceContentImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceContent()
	 * @generated
	 */
	int RESOURCE_CONTENT = 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__DESCRIPTION = CONTENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__ANNOTATIONS = CONTENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__ITERATOR = CONTENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__FACETS = CONTENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__DATA_BINDING = CONTENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__LOCATION = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Request Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__REQUEST_PROPERTIES = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT__MODEL = CONTENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Resource Content</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_CONTENT_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.TextImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 5;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__DESCRIPTION = CONTENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ANNOTATIONS = CONTENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ITERATOR = CONTENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__FACETS = CONTENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__DATA_BINDING = CONTENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__TEXT = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ContainerImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DESCRIPTION = CONTENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ANNOTATIONS = CONTENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__ITERATOR = CONTENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__FACETS = CONTENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__DATA_BINDING = CONTENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__CONTENT = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.FacetImpl <em>Facet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.FacetImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getFacet()
	 * @generated
	 */
	int FACET = 7;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Is Facet For</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET___IS_FACET_FOR__OBJECT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FACET_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.IteratorImpl <em>Iterator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.IteratorImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getIterator()
	 * @generated
	 */
	int ITERATOR = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The number of structural features of the '<em>Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITERATOR_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.PropertyImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.DataBindingIteratorImpl <em>Data Binding Iterator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.DataBindingIteratorImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getDataBindingIterator()
	 * @generated
	 */
	int DATA_BINDING_ITERATOR = 10;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BINDING_ITERATOR__DESCRIPTION = ITERATOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BINDING_ITERATOR__ANNOTATIONS = ITERATOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BINDING_ITERATOR__DATA_BINDING = ITERATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Binding Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BINDING_ITERATOR_FEATURE_COUNT = ITERATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Data Binding Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_BINDING_ITERATOR_OPERATION_COUNT = ITERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ResourceIteratorImpl <em>Resource Iterator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ResourceIteratorImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceIterator()
	 * @generated
	 */
	int RESOURCE_ITERATOR = 11;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR__DESCRIPTION = ITERATOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR__ANNOTATIONS = ITERATOR__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR__LOCATION = ITERATOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Request Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR__REQUEST_PROPERTIES = ITERATOR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR__CONTENT_TYPE = ITERATOR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR_FEATURE_COUNT = ITERATOR_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Resource Iterator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ITERATOR_OPERATION_COUNT = ITERATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.SwitchImpl <em>Switch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.SwitchImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getSwitch()
	 * @generated
	 */
	int SWITCH = 12;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__CASES = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Otherwise</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__OTHERWISE = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Selector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__SELECTOR = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.CaseImpl <em>Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.CaseImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getCase()
	 * @generated
	 */
	int CASE = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__CONTENT = 1;

	/**
	 * The number of structural features of the '<em>Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.ResourceReference <em>Resource Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.ResourceReference
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceReference()
	 * @generated
	 */
	int RESOURCE_REFERENCE = 14;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE__LOCATION = 0;

	/**
	 * The feature id for the '<em><b>Request Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE__REQUEST_PROPERTIES = 1;

	/**
	 * The number of structural features of the '<em>Resource Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Resource Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.HTMLElementImpl <em>HTML Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.HTMLElementImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getHTMLElement()
	 * @generated
	 */
	int HTML_ELEMENT = 15;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__DESCRIPTION = CONTENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__ANNOTATIONS = CONTENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__ITERATOR = CONTENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__FACETS = CONTENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__DATA_BINDING = CONTENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__ATTRIBUTES = CONTENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__STYLES = CONTENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__CLASSES = CONTENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__ID = CONTENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__EVENT_HANDLERS = CONTENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>HTML Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT_FEATURE_COUNT = CONTENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>HTML Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT_OPERATION_COUNT = CONTENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.EventHandlerImpl <em>Event Handler</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.EventHandlerImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getEventHandler()
	 * @generated
	 */
	int EVENT_HANDLER = 16;

	/**
	 * The feature id for the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER__EVENT = 0;

	/**
	 * The number of structural features of the '<em>Event Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Event Handler</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_HANDLER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ScriptImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 17;

	/**
	 * The feature id for the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__EVENT = EVENT_HANDLER__EVENT;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__CODE = EVENT_HANDLER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = EVENT_HANDLER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_OPERATION_COUNT = EVENT_HANDLER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl <em>Script Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ScriptReferenceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptReference()
	 * @generated
	 */
	int SCRIPT_REFERENCE = 18;

	/**
	 * The feature id for the '<em><b>Event</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__EVENT = EVENT_HANDLER__EVENT;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__LOCATION = EVENT_HANDLER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Request Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__REQUEST_PROPERTIES = EVENT_HANDLER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Script Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE_FEATURE_COUNT = EVENT_HANDLER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Script Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE_OPERATION_COUNT = EVENT_HANDLER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.ContentType <em>Content Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.ContentType
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentType()
	 * @generated
	 */
	int CONTENT_TYPE = 19;

	/**
	 * The meta object id for the '<em>Event</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.Event
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 20;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see org.nasdanika.html.model.html.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.html.model.html.ModelElement#getDescription()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.ModelElement#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see org.nasdanika.html.model.html.ModelElement#getAnnotations()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Annotations();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.nasdanika.html.model.html.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.nasdanika.html.model.html.Page#getLang()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Lang();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.nasdanika.html.model.html.Page#getTitle()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Title();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.html.model.html.Page#getStylesheets <em>Stylesheets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Stylesheets</em>'.
	 * @see org.nasdanika.html.model.html.Page#getStylesheets()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Stylesheets();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.html.model.html.Page#getScripts <em>Scripts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Scripts</em>'.
	 * @see org.nasdanika.html.model.html.Page#getScripts()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Scripts();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Head</em>'.
	 * @see org.nasdanika.html.model.html.Page#getHead()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Head();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Page#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body</em>'.
	 * @see org.nasdanika.html.model.html.Page#getBody()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Page#getFacets <em>Facets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Facets</em>'.
	 * @see org.nasdanika.html.model.html.Page#getFacets()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Facets();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Content <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content</em>'.
	 * @see org.nasdanika.html.model.html.Content
	 * @generated
	 */
	EClass getContent();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.html.Content#getIterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Iterator</em>'.
	 * @see org.nasdanika.html.model.html.Content#getIterator()
	 * @see #getContent()
	 * @generated
	 */
	EReference getContent_Iterator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Content#getFacets <em>Facets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Facets</em>'.
	 * @see org.nasdanika.html.model.html.Content#getFacets()
	 * @see #getContent()
	 * @generated
	 */
	EReference getContent_Facets();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Content#getDataBinding <em>Data Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Binding</em>'.
	 * @see org.nasdanika.html.model.html.Content#getDataBinding()
	 * @see #getContent()
	 * @generated
	 */
	EAttribute getContent_DataBinding();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ContentReference <em>Content Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Reference</em>'.
	 * @see org.nasdanika.html.model.html.ContentReference
	 * @generated
	 */
	EClass getContentReference();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.html.model.html.ContentReference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.html.model.html.ContentReference#getTarget()
	 * @see #getContentReference()
	 * @generated
	 */
	EReference getContentReference_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ResourceContent <em>Resource Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Content</em>'.
	 * @see org.nasdanika.html.model.html.ResourceContent
	 * @generated
	 */
	EClass getResourceContent();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ResourceContent#isModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model</em>'.
	 * @see org.nasdanika.html.model.html.ResourceContent#isModel()
	 * @see #getResourceContent()
	 * @generated
	 */
	EAttribute getResourceContent_Model();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.nasdanika.html.model.html.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Text#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.nasdanika.html.model.html.Text#getText()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Text();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.nasdanika.html.model.html.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Container#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.nasdanika.html.model.html.Container#getContent()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Content();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Facet <em>Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Facet</em>'.
	 * @see org.nasdanika.html.model.html.Facet
	 * @generated
	 */
	EClass getFacet();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.html.model.html.Facet#isFacetFor(java.lang.Object) <em>Is Facet For</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Is Facet For</em>' operation.
	 * @see org.nasdanika.html.model.html.Facet#isFacetFor(java.lang.Object)
	 * @generated
	 */
	EOperation getFacet__IsFacetFor__Object();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Iterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator</em>'.
	 * @see org.nasdanika.html.model.html.Iterator
	 * @generated
	 */
	EClass getIterator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.nasdanika.html.model.html.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.html.model.html.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Property#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.html.model.html.Property#getValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Property#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.html.model.html.Property#getDescription()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Description();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.DataBindingIterator <em>Data Binding Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Binding Iterator</em>'.
	 * @see org.nasdanika.html.model.html.DataBindingIterator
	 * @generated
	 */
	EClass getDataBindingIterator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.DataBindingIterator#getDataBinding <em>Data Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data Binding</em>'.
	 * @see org.nasdanika.html.model.html.DataBindingIterator#getDataBinding()
	 * @see #getDataBindingIterator()
	 * @generated
	 */
	EAttribute getDataBindingIterator_DataBinding();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ResourceIterator <em>Resource Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Iterator</em>'.
	 * @see org.nasdanika.html.model.html.ResourceIterator
	 * @generated
	 */
	EClass getResourceIterator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ResourceIterator#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see org.nasdanika.html.model.html.ResourceIterator#getContentType()
	 * @see #getResourceIterator()
	 * @generated
	 */
	EAttribute getResourceIterator_ContentType();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Switch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch</em>'.
	 * @see org.nasdanika.html.model.html.Switch
	 * @generated
	 */
	EClass getSwitch();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Switch#getCases <em>Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cases</em>'.
	 * @see org.nasdanika.html.model.html.Switch#getCases()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Cases();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Switch#getOtherwise <em>Otherwise</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Otherwise</em>'.
	 * @see org.nasdanika.html.model.html.Switch#getOtherwise()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Otherwise();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Switch#getSelector <em>Selector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Selector</em>'.
	 * @see org.nasdanika.html.model.html.Switch#getSelector()
	 * @see #getSwitch()
	 * @generated
	 */
	EAttribute getSwitch_Selector();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Case <em>Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case</em>'.
	 * @see org.nasdanika.html.model.html.Case
	 * @generated
	 */
	EClass getCase();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Case#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.html.model.html.Case#getValue()
	 * @see #getCase()
	 * @generated
	 */
	EAttribute getCase_Value();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Case#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.nasdanika.html.model.html.Case#getContent()
	 * @see #getCase()
	 * @generated
	 */
	EReference getCase_Content();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ResourceReference <em>Resource Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Reference</em>'.
	 * @see org.nasdanika.html.model.html.ResourceReference
	 * @generated
	 */
	EClass getResourceReference();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ResourceReference#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.html.ResourceReference#getLocation()
	 * @see #getResourceReference()
	 * @generated
	 */
	EAttribute getResourceReference_Location();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.ResourceReference#getRequestProperties <em>Request Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Request Properties</em>'.
	 * @see org.nasdanika.html.model.html.ResourceReference#getRequestProperties()
	 * @see #getResourceReference()
	 * @generated
	 */
	EReference getResourceReference_RequestProperties();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.HTMLElement <em>HTML Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>HTML Element</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement
	 * @generated
	 */
	EClass getHTMLElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.HTMLElement#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement#getAttributes()
	 * @see #getHTMLElement()
	 * @generated
	 */
	EReference getHTMLElement_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.HTMLElement#getStyles <em>Styles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Styles</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement#getStyles()
	 * @see #getHTMLElement()
	 * @generated
	 */
	EReference getHTMLElement_Styles();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.html.model.html.HTMLElement#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Classes</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement#getClasses()
	 * @see #getHTMLElement()
	 * @generated
	 */
	EAttribute getHTMLElement_Classes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.HTMLElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement#getId()
	 * @see #getHTMLElement()
	 * @generated
	 */
	EAttribute getHTMLElement_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.HTMLElement#getEventHandlers <em>Event Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Handlers</em>'.
	 * @see org.nasdanika.html.model.html.HTMLElement#getEventHandlers()
	 * @see #getHTMLElement()
	 * @generated
	 */
	EReference getHTMLElement_EventHandlers();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.EventHandler <em>Event Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Handler</em>'.
	 * @see org.nasdanika.html.model.html.EventHandler
	 * @generated
	 */
	EClass getEventHandler();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.EventHandler#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event</em>'.
	 * @see org.nasdanika.html.model.html.EventHandler#getEvent()
	 * @see #getEventHandler()
	 * @generated
	 */
	EAttribute getEventHandler_Event();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see org.nasdanika.html.model.html.Script
	 * @generated
	 */
	EClass getScript();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Script#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.nasdanika.html.model.html.Script#getCode()
	 * @see #getScript()
	 * @generated
	 */
	EAttribute getScript_Code();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ScriptReference <em>Script Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Reference</em>'.
	 * @see org.nasdanika.html.model.html.ScriptReference
	 * @generated
	 */
	EClass getScriptReference();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.html.ContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Content Type</em>'.
	 * @see org.nasdanika.html.model.html.ContentType
	 * @generated
	 */
	EEnum getContentType();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Enumeration of HTML events.
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Event</em>'.
	 * @see org.nasdanika.html.Event
	 * @model instanceClass="org.nasdanika.html.Event"
	 * @generated
	 */
	EDataType getEvent();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HtmlFactory getHtmlFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ModelElementImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__DESCRIPTION = eINSTANCE.getModelElement_Description();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__ANNOTATIONS = eINSTANCE.getModelElement_Annotations();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.PageImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__LANG = eINSTANCE.getPage_Lang();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__TITLE = eINSTANCE.getPage_Title();

		/**
		 * The meta object literal for the '<em><b>Stylesheets</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__STYLESHEETS = eINSTANCE.getPage_Stylesheets();

		/**
		 * The meta object literal for the '<em><b>Scripts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__SCRIPTS = eINSTANCE.getPage_Scripts();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__HEAD = eINSTANCE.getPage_Head();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__BODY = eINSTANCE.getPage_Body();

		/**
		 * The meta object literal for the '<em><b>Facets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__FACETS = eINSTANCE.getPage_Facets();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ContentImpl <em>Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ContentImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContent()
		 * @generated
		 */
		EClass CONTENT = eINSTANCE.getContent();

		/**
		 * The meta object literal for the '<em><b>Iterator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT__ITERATOR = eINSTANCE.getContent_Iterator();

		/**
		 * The meta object literal for the '<em><b>Facets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT__FACETS = eINSTANCE.getContent_Facets();

		/**
		 * The meta object literal for the '<em><b>Data Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT__DATA_BINDING = eINSTANCE.getContent_DataBinding();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ContentReferenceImpl <em>Content Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ContentReferenceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentReference()
		 * @generated
		 */
		EClass CONTENT_REFERENCE = eINSTANCE.getContentReference();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_REFERENCE__TARGET = eINSTANCE.getContentReference_Target();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ResourceContentImpl <em>Resource Content</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ResourceContentImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceContent()
		 * @generated
		 */
		EClass RESOURCE_CONTENT = eINSTANCE.getResourceContent();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_CONTENT__MODEL = eINSTANCE.getResourceContent_Model();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.TextImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__TEXT = eINSTANCE.getText_Text();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ContainerImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__CONTENT = eINSTANCE.getContainer_Content();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.FacetImpl <em>Facet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.FacetImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getFacet()
		 * @generated
		 */
		EClass FACET = eINSTANCE.getFacet();

		/**
		 * The meta object literal for the '<em><b>Is Facet For</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation FACET___IS_FACET_FOR__OBJECT = eINSTANCE.getFacet__IsFacetFor__Object();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.IteratorImpl <em>Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.IteratorImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getIterator()
		 * @generated
		 */
		EClass ITERATOR = eINSTANCE.getIterator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.PropertyImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__DESCRIPTION = eINSTANCE.getProperty_Description();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.DataBindingIteratorImpl <em>Data Binding Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.DataBindingIteratorImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getDataBindingIterator()
		 * @generated
		 */
		EClass DATA_BINDING_ITERATOR = eINSTANCE.getDataBindingIterator();

		/**
		 * The meta object literal for the '<em><b>Data Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_BINDING_ITERATOR__DATA_BINDING = eINSTANCE.getDataBindingIterator_DataBinding();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ResourceIteratorImpl <em>Resource Iterator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ResourceIteratorImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceIterator()
		 * @generated
		 */
		EClass RESOURCE_ITERATOR = eINSTANCE.getResourceIterator();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ITERATOR__CONTENT_TYPE = eINSTANCE.getResourceIterator_ContentType();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.SwitchImpl <em>Switch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.SwitchImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getSwitch()
		 * @generated
		 */
		EClass SWITCH = eINSTANCE.getSwitch();

		/**
		 * The meta object literal for the '<em><b>Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__CASES = eINSTANCE.getSwitch_Cases();

		/**
		 * The meta object literal for the '<em><b>Otherwise</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__OTHERWISE = eINSTANCE.getSwitch_Otherwise();

		/**
		 * The meta object literal for the '<em><b>Selector</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH__SELECTOR = eINSTANCE.getSwitch_Selector();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.CaseImpl <em>Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.CaseImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getCase()
		 * @generated
		 */
		EClass CASE = eINSTANCE.getCase();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CASE__VALUE = eINSTANCE.getCase_Value();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE__CONTENT = eINSTANCE.getCase_Content();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.ResourceReference <em>Resource Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.ResourceReference
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getResourceReference()
		 * @generated
		 */
		EClass RESOURCE_REFERENCE = eINSTANCE.getResourceReference();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REFERENCE__LOCATION = eINSTANCE.getResourceReference_Location();

		/**
		 * The meta object literal for the '<em><b>Request Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_REFERENCE__REQUEST_PROPERTIES = eINSTANCE.getResourceReference_RequestProperties();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.HTMLElementImpl <em>HTML Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.HTMLElementImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getHTMLElement()
		 * @generated
		 */
		EClass HTML_ELEMENT = eINSTANCE.getHTMLElement();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HTML_ELEMENT__ATTRIBUTES = eINSTANCE.getHTMLElement_Attributes();

		/**
		 * The meta object literal for the '<em><b>Styles</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HTML_ELEMENT__STYLES = eINSTANCE.getHTMLElement_Styles();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTML_ELEMENT__CLASSES = eINSTANCE.getHTMLElement_Classes();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTML_ELEMENT__ID = eINSTANCE.getHTMLElement_Id();

		/**
		 * The meta object literal for the '<em><b>Event Handlers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HTML_ELEMENT__EVENT_HANDLERS = eINSTANCE.getHTMLElement_EventHandlers();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.EventHandlerImpl <em>Event Handler</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.EventHandlerImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getEventHandler()
		 * @generated
		 */
		EClass EVENT_HANDLER = eINSTANCE.getEventHandler();

		/**
		 * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_HANDLER__EVENT = eINSTANCE.getEventHandler_Event();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ScriptImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT__CODE = eINSTANCE.getScript_Code();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl <em>Script Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ScriptReferenceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptReference()
		 * @generated
		 */
		EClass SCRIPT_REFERENCE = eINSTANCE.getScriptReference();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.ContentType <em>Content Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.ContentType
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentType()
		 * @generated
		 */
		EEnum CONTENT_TYPE = eINSTANCE.getContentType();

		/**
		 * The meta object literal for the '<em>Event</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.Event
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getEvent()
		 * @generated
		 */
		EDataType EVENT = eINSTANCE.getEvent();

	}

} //HtmlPackage
