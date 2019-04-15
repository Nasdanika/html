/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.EventHandler;
import org.nasdanika.html.model.html.HTMLElement;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>HTML Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.HTMLElementImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.HTMLElementImpl#getStyles <em>Styles</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.HTMLElementImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.HTMLElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.HTMLElementImpl#getEventHandlers <em>Event Handlers</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class HTMLElementImpl extends ModelElementImpl implements HTMLElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HTMLElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.HTML_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getAttributes() {
		return (EList<Property>)eGet(HtmlPackage.Literals.HTML_ELEMENT__ATTRIBUTES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getStyles() {
		return (EList<Property>)eGet(HtmlPackage.Literals.HTML_ELEMENT__STYLES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getClasses() {
		return (EList<String>)eGet(HtmlPackage.Literals.HTML_ELEMENT__CLASSES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return (String)eGet(HtmlPackage.Literals.HTML_ELEMENT__ID, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		eSet(HtmlPackage.Literals.HTML_ELEMENT__ID, newId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EventHandler> getEventHandlers() {
		return (EList<EventHandler>)eGet(HtmlPackage.Literals.HTML_ELEMENT__EVENT_HANDLERS, true);
	}

} //HTMLElementImpl
