/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.html.model.html.HtmlPackage;

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
 * A model of Bootstrap elements.
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.bootstrap.BootstrapFactory
 * @model kind="package"
 * @generated
 */
public interface BootstrapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "bootstrap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.html.bootstrap";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.bootstrap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BootstrapPackage eINSTANCE = org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl <em>CDN Facet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapCDNFacet()
	 * @generated
	 */
	int BOOTSTRAP_CDN_FACET = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET__DESCRIPTION = HtmlPackage.FACET__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET__ANNOTATIONS = HtmlPackage.FACET__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET__THEME = HtmlPackage.FACET_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CDN Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET_FEATURE_COUNT = HtmlPackage.FACET_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Is Facet For</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET___IS_FACET_FOR__OBJECT = HtmlPackage.FACET___IS_FACET_FOR__OBJECT;

	/**
	 * The number of operations of the '<em>CDN Facet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_CDN_FACET_OPERATION_COUNT = HtmlPackage.FACET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapElement()
	 * @generated
	 */
	int BOOTSTRAP_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__DESCRIPTION = HtmlPackage.HTML_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__ANNOTATIONS = HtmlPackage.HTML_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__ATTRIBUTES = HtmlPackage.HTML_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__STYLES = HtmlPackage.HTML_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__CLASSES = HtmlPackage.HTML_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__ID = HtmlPackage.HTML_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT__EVENT_HANDLERS = HtmlPackage.HTML_ELEMENT__EVENT_HANDLERS;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT_FEATURE_COUNT = HtmlPackage.HTML_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOTSTRAP_ELEMENT_OPERATION_COUNT = HtmlPackage.HTML_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl <em>Content Bootstrap Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getContentBootstrapElement()
	 * @generated
	 */
	int CONTENT_BOOTSTRAP_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__DESCRIPTION = BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__ANNOTATIONS = BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__ATTRIBUTES = BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__STYLES = BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__CLASSES = BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__ID = BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__EVENT_HANDLERS = BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__ITERATOR = BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__FACETS = BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING = BOOTSTRAP_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Content Bootstrap Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT = BOOTSTRAP_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Content Bootstrap Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_BOOTSTRAP_ELEMENT_OPERATION_COUNT = BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.ContainerBootstrapElementImpl <em>Container Bootstrap Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.ContainerBootstrapElementImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getContainerBootstrapElement()
	 * @generated
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__DESCRIPTION = CONTENT_BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__ANNOTATIONS = CONTENT_BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__ATTRIBUTES = CONTENT_BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__STYLES = CONTENT_BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__CLASSES = CONTENT_BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__ID = CONTENT_BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__EVENT_HANDLERS = CONTENT_BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__ITERATOR = CONTENT_BOOTSTRAP_ELEMENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__FACETS = CONTENT_BOOTSTRAP_ELEMENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__DATA_BINDING = CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT__CONTENT = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Bootstrap Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Container Bootstrap Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_BOOTSTRAP_ELEMENT_OPERATION_COUNT = CONTENT_BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.WrapImpl <em>Wrap</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.WrapImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getWrap()
	 * @generated
	 */
	int WRAP = 4;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__DESCRIPTION = CONTENT_BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__ANNOTATIONS = CONTENT_BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__ATTRIBUTES = CONTENT_BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__STYLES = CONTENT_BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__CLASSES = CONTENT_BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__ID = CONTENT_BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__EVENT_HANDLERS = CONTENT_BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__ITERATOR = CONTENT_BOOTSTRAP_ELEMENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__FACETS = CONTENT_BOOTSTRAP_ELEMENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__DATA_BINDING = CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Html Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP__HTML_ELEMENT = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Wrap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP_FEATURE_COUNT = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Wrap</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WRAP_OPERATION_COUNT = CONTENT_BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.AlertImpl <em>Alert</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.AlertImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getAlert()
	 * @generated
	 */
	int ALERT = 5;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__DESCRIPTION = CONTAINER_BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__ANNOTATIONS = CONTAINER_BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__ATTRIBUTES = CONTAINER_BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__STYLES = CONTAINER_BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__CLASSES = CONTAINER_BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__ID = CONTAINER_BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__EVENT_HANDLERS = CONTAINER_BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__ITERATOR = CONTAINER_BOOTSTRAP_ELEMENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__FACETS = CONTAINER_BOOTSTRAP_ELEMENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__DATA_BINDING = CONTAINER_BOOTSTRAP_ELEMENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__CONTENT = CONTAINER_BOOTSTRAP_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT__COLOR = CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Alert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT_FEATURE_COUNT = CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Alert</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALERT_OPERATION_COUNT = CONTAINER_BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.ListGroupImpl <em>List Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.ListGroupImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getListGroup()
	 * @generated
	 */
	int LIST_GROUP = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__DESCRIPTION = CONTENT_BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__ANNOTATIONS = CONTENT_BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__ATTRIBUTES = CONTENT_BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__STYLES = CONTENT_BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__CLASSES = CONTENT_BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__ID = CONTENT_BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__EVENT_HANDLERS = CONTENT_BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__ITERATOR = CONTENT_BOOTSTRAP_ELEMENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__FACETS = CONTENT_BOOTSTRAP_ELEMENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__DATA_BINDING = CONTENT_BOOTSTRAP_ELEMENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP__ITEMS = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>List Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_FEATURE_COUNT = CONTENT_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>List Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_OPERATION_COUNT = CONTENT_BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl <em>List Group Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getListGroupItem()
	 * @generated
	 */
	int LIST_GROUP_ITEM = 7;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__DESCRIPTION = HtmlPackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__ANNOTATIONS = HtmlPackage.MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__DISABLED = HtmlPackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__ACTIVE = HtmlPackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__COLOR = HtmlPackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM__CONTENT = HtmlPackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>List Group Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM_FEATURE_COUNT = HtmlPackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>List Group Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_GROUP_ITEM_OPERATION_COUNT = HtmlPackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.bootstrap.impl.BadgeImpl <em>Badge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.bootstrap.impl.BadgeImpl
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBadge()
	 * @generated
	 */
	int BADGE = 8;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__DESCRIPTION = CONTAINER_BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__ANNOTATIONS = CONTAINER_BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__ATTRIBUTES = CONTAINER_BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Styles</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__STYLES = CONTAINER_BOOTSTRAP_ELEMENT__STYLES;

	/**
	 * The feature id for the '<em><b>Classes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__CLASSES = CONTAINER_BOOTSTRAP_ELEMENT__CLASSES;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__ID = CONTAINER_BOOTSTRAP_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Event Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__EVENT_HANDLERS = CONTAINER_BOOTSTRAP_ELEMENT__EVENT_HANDLERS;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__ITERATOR = CONTAINER_BOOTSTRAP_ELEMENT__ITERATOR;

	/**
	 * The feature id for the '<em><b>Facets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__FACETS = CONTAINER_BOOTSTRAP_ELEMENT__FACETS;

	/**
	 * The feature id for the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__DATA_BINDING = CONTAINER_BOOTSTRAP_ELEMENT__DATA_BINDING;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__CONTENT = CONTAINER_BOOTSTRAP_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Pill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__PILL = CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE__COLOR = CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Badge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE_FEATURE_COUNT = CONTAINER_BOOTSTRAP_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Badge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BADGE_OPERATION_COUNT = CONTAINER_BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Theme</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getTheme()
	 * @generated
	 */
	int THEME = 9;


	/**
	 * The meta object id for the '<em>Color</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Color
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 10;

	/**
	 * The meta object id for the '<em>Device Size</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.DeviceSize
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getDeviceSize()
	 * @generated
	 */
	int DEVICE_SIZE = 11;

	/**
	 * The meta object id for the '<em>Direction</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Direction
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 12;

	/**
	 * The meta object id for the '<em>Placement</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Placement
	 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getPlacement()
	 * @generated
	 */
	int PLACEMENT = 13;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.BootstrapCDNFacet <em>CDN Facet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CDN Facet</em>'.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapCDNFacet
	 * @generated
	 */
	EClass getBootstrapCDNFacet();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.BootstrapCDNFacet#getTheme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Theme</em>'.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapCDNFacet#getTheme()
	 * @see #getBootstrapCDNFacet()
	 * @generated
	 */
	EAttribute getBootstrapCDNFacet_Theme();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.BootstrapElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapElement
	 * @generated
	 */
	EClass getBootstrapElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.ContentBootstrapElement <em>Content Bootstrap Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Bootstrap Element</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ContentBootstrapElement
	 * @generated
	 */
	EClass getContentBootstrapElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.ContainerBootstrapElement <em>Container Bootstrap Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Bootstrap Element</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ContainerBootstrapElement
	 * @generated
	 */
	EClass getContainerBootstrapElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.Wrap <em>Wrap</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wrap</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Wrap
	 * @generated
	 */
	EClass getWrap();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.bootstrap.Wrap#getHtmlElement <em>Html Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Html Element</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Wrap#getHtmlElement()
	 * @see #getWrap()
	 * @generated
	 */
	EReference getWrap_HtmlElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.Alert <em>Alert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alert</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Alert
	 * @generated
	 */
	EClass getAlert();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.Alert#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Alert#getColor()
	 * @see #getAlert()
	 * @generated
	 */
	EAttribute getAlert_Color();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.ListGroup <em>List Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Group</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroup
	 * @generated
	 */
	EClass getListGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.bootstrap.ListGroup#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroup#getItems()
	 * @see #getListGroup()
	 * @generated
	 */
	EReference getListGroup_Items();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.ListGroupItem <em>List Group Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Group Item</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroupItem
	 * @generated
	 */
	EClass getListGroupItem();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.ListGroupItem#isDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroupItem#isDisabled()
	 * @see #getListGroupItem()
	 * @generated
	 */
	EAttribute getListGroupItem_Disabled();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.ListGroupItem#isActive <em>Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Active</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroupItem#isActive()
	 * @see #getListGroupItem()
	 * @generated
	 */
	EAttribute getListGroupItem_Active();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.ListGroupItem#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroupItem#getColor()
	 * @see #getListGroupItem()
	 * @generated
	 */
	EAttribute getListGroupItem_Color();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.bootstrap.ListGroupItem#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.nasdanika.html.model.bootstrap.ListGroupItem#getContent()
	 * @see #getListGroupItem()
	 * @generated
	 */
	EReference getListGroupItem_Content();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.bootstrap.Badge <em>Badge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Badge</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Badge
	 * @generated
	 */
	EClass getBadge();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.Badge#isPill <em>Pill</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pill</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Badge#isPill()
	 * @see #getBadge()
	 * @generated
	 */
	EAttribute getBadge_Pill();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.bootstrap.Badge#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.nasdanika.html.model.bootstrap.Badge#getColor()
	 * @see #getBadge()
	 * @generated
	 */
	EAttribute getBadge_Color();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Theme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Theme</em>'.
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @model instanceClass="org.nasdanika.html.bootstrap.Theme"
	 * @generated
	 */
	EDataType getTheme();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Color</em>'.
	 * @see org.nasdanika.html.bootstrap.Color
	 * @model instanceClass="org.nasdanika.html.bootstrap.Color"
	 * @generated
	 */
	EDataType getColor();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.DeviceSize <em>Device Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Device Size</em>'.
	 * @see org.nasdanika.html.bootstrap.DeviceSize
	 * @model instanceClass="org.nasdanika.html.bootstrap.DeviceSize"
	 * @generated
	 */
	EDataType getDeviceSize();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Direction</em>'.
	 * @see org.nasdanika.html.bootstrap.Direction
	 * @model instanceClass="org.nasdanika.html.bootstrap.Direction"
	 * @generated
	 */
	EDataType getDirection();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Placement <em>Placement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Placement</em>'.
	 * @see org.nasdanika.html.bootstrap.Placement
	 * @model instanceClass="org.nasdanika.html.bootstrap.Placement"
	 * @generated
	 */
	EDataType getPlacement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BootstrapFactory getBootstrapFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl <em>CDN Facet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapCDNFacetImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapCDNFacet()
		 * @generated
		 */
		EClass BOOTSTRAP_CDN_FACET = eINSTANCE.getBootstrapCDNFacet();

		/**
		 * The meta object literal for the '<em><b>Theme</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOTSTRAP_CDN_FACET__THEME = eINSTANCE.getBootstrapCDNFacet_Theme();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBootstrapElement()
		 * @generated
		 */
		EClass BOOTSTRAP_ELEMENT = eINSTANCE.getBootstrapElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl <em>Content Bootstrap Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.ContentBootstrapElementImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getContentBootstrapElement()
		 * @generated
		 */
		EClass CONTENT_BOOTSTRAP_ELEMENT = eINSTANCE.getContentBootstrapElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.ContainerBootstrapElementImpl <em>Container Bootstrap Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.ContainerBootstrapElementImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getContainerBootstrapElement()
		 * @generated
		 */
		EClass CONTAINER_BOOTSTRAP_ELEMENT = eINSTANCE.getContainerBootstrapElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.WrapImpl <em>Wrap</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.WrapImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getWrap()
		 * @generated
		 */
		EClass WRAP = eINSTANCE.getWrap();

		/**
		 * The meta object literal for the '<em><b>Html Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WRAP__HTML_ELEMENT = eINSTANCE.getWrap_HtmlElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.AlertImpl <em>Alert</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.AlertImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getAlert()
		 * @generated
		 */
		EClass ALERT = eINSTANCE.getAlert();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALERT__COLOR = eINSTANCE.getAlert_Color();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.ListGroupImpl <em>List Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.ListGroupImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getListGroup()
		 * @generated
		 */
		EClass LIST_GROUP = eINSTANCE.getListGroup();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_GROUP__ITEMS = eINSTANCE.getListGroup_Items();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl <em>List Group Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getListGroupItem()
		 * @generated
		 */
		EClass LIST_GROUP_ITEM = eINSTANCE.getListGroupItem();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_GROUP_ITEM__DISABLED = eINSTANCE.getListGroupItem_Disabled();

		/**
		 * The meta object literal for the '<em><b>Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_GROUP_ITEM__ACTIVE = eINSTANCE.getListGroupItem_Active();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST_GROUP_ITEM__COLOR = eINSTANCE.getListGroupItem_Color();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST_GROUP_ITEM__CONTENT = eINSTANCE.getListGroupItem_Content();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.bootstrap.impl.BadgeImpl <em>Badge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.bootstrap.impl.BadgeImpl
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getBadge()
		 * @generated
		 */
		EClass BADGE = eINSTANCE.getBadge();

		/**
		 * The meta object literal for the '<em><b>Pill</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BADGE__PILL = eINSTANCE.getBadge_Pill();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BADGE__COLOR = eINSTANCE.getBadge_Color();

		/**
		 * The meta object literal for the '<em>Theme</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Theme
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getTheme()
		 * @generated
		 */
		EDataType THEME = eINSTANCE.getTheme();

		/**
		 * The meta object literal for the '<em>Color</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Color
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getColor()
		 * @generated
		 */
		EDataType COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '<em>Device Size</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.DeviceSize
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getDeviceSize()
		 * @generated
		 */
		EDataType DEVICE_SIZE = eINSTANCE.getDeviceSize();

		/**
		 * The meta object literal for the '<em>Direction</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Direction
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getDirection()
		 * @generated
		 */
		EDataType DIRECTION = eINSTANCE.getDirection();

		/**
		 * The meta object literal for the '<em>Placement</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Placement
		 * @see org.nasdanika.html.model.bootstrap.impl.BootstrapPackageImpl#getPlacement()
		 * @generated
		 */
		EDataType PLACEMENT = eINSTANCE.getPlacement();

	}

} //BootstrapPackage
