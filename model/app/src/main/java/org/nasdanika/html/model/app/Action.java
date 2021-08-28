/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.exec.resources.Resource;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Actions form a hierarchy. Application pages are generated from actions. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Action#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getNavigation <em>Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getLeftNavigation <em>Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getRightNavigation <em>Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getFloatLeftNavigation <em>Float Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getFloatRightNavigation <em>Float Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getAnonymous <em>Anonymous</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isInline <em>Inline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isModalActivator <em>Modal Activator</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getAction()
 * @model
 * @generated
 */
public interface Action extends Link {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action unique ID for cross-referencing.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Section Columns</b></em>' attribute.
	 * The default value is <code>"3"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Applicable to section style "Card". Defines how many columns shall be in a row of section cards.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Columns</em>' attribute.
	 * @see #setSectionColumns(int)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_SectionColumns()
	 * @model default="3"
	 * @generated
	 */
	int getSectionColumns();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getSectionColumns <em>Section Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Columns</em>' attribute.
	 * @see #getSectionColumns()
	 * @generated
	 */
	void setSectionColumns(int value);

	/**
	 * Returns the value of the '<em><b>Section Style</b></em>' attribute.
	 * The default value is <code>"Auto"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.html.model.app.SectionStyle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines how to generate section children.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see #setSectionStyle(SectionStyle)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_SectionStyle()
	 * @model default="Auto"
	 * @generated
	 */
	SectionStyle getSectionStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see #getSectionStyle()
	 * @generated
	 */
	void setSectionStyle(SectionStyle value);

	/**
	 * Returns the value of the '<em><b>Navigation</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.BootstrapElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation items are displayed in the footer the root action, in the navigation bar for the principal action, and in the content panel navigation bar for the active action.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Navigation</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Navigation()
	 * @model containment="true"
	 * @generated
	 */
	EList<BootstrapElement> getNavigation();

	/**
	 * Returns the value of the '<em><b>Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Left navigation panel
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Navigation</em>' containment reference.
	 * @see #setLeftNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_LeftNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getLeftNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getLeftNavigation <em>Left Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left Navigation</em>' containment reference.
	 * @see #getLeftNavigation()
	 * @generated
	 */
	void setLeftNavigation(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Right navigation panel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right Navigation</em>' containment reference.
	 * @see #setRightNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_RightNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getRightNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getRightNavigation <em>Right Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right Navigation</em>' containment reference.
	 * @see #getRightNavigation()
	 * @generated
	 */
	void setRightNavigation(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Float Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Float left navigation panel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Float Left Navigation</em>' containment reference.
	 * @see #setFloatLeftNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_FloatLeftNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getFloatLeftNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getFloatLeftNavigation <em>Float Left Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Left Navigation</em>' containment reference.
	 * @see #getFloatLeftNavigation()
	 * @generated
	 */
	void setFloatLeftNavigation(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Float Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Float right navigation panel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Float Right Navigation</em>' containment reference.
	 * @see #setFloatRightNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_FloatRightNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getFloatRightNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getFloatRightNavigation <em>Float Right Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Right Navigation</em>' containment reference.
	 * @see #getFloatRightNavigation()
	 * @generated
	 */
	void setFloatRightNavigation(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Anonymous</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Actions which are not shown in the containing action UI, but for which pages are generated and can be explicitly referenced, e.g. from content. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Anonymous</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Anonymous()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getAnonymous();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.exec.resources.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resources referenced by the page. Resource names are resolved relative to the page location.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Resources()
	 * @model containment="true"
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * Returns the value of the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Inline action's content is displayed instead of an action link in navigation panels.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inline</em>' attribute.
	 * @see #setInline(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Inline()
	 * @model annotation="urn:org.nasdanika exclusive-with='location binding script modal'"
	 * @generated
	 */
	boolean isInline();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isInline <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inline</em>' attribute.
	 * @see #isInline()
	 * @generated
	 */
	void setInline(boolean value);

	/**
	 * Returns the value of the '<em><b>Modal Activator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Inline action's content is displayed in a modal dialog which opens on a click on the action's link.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modal Activator</em>' attribute.
	 * @see #setModalActivator(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_ModalActivator()
	 * @model annotation="urn:org.nasdanika exclusive-with='location binding script inline'"
	 * @generated
	 */
	boolean isModalActivator();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isModalActivator <em>Modal Activator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modal Activator</em>' attribute.
	 * @see #isModalActivator()
	 * @generated
	 */
	void setModalActivator(boolean value);

} // Action
