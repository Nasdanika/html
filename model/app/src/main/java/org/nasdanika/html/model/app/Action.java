/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for concrete action classes - Action and Partition. These sub-classes are semantically equivalent and differ only in diagram representation - Action is represented by a node, Partition by a container node.
 * Action can be a child of another action or of an action category. It may contain content and action elements - abstract actions and action categories. It may also contain action mappings - logical names of actions referenced by this action's content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Action#getRole <em>Role</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isInline <em>Inline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isModal <em>Modal</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getSections <em>Sections</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContext <em>Context</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContentLeft <em>Content Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContentRight <em>Content Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getFloatLeft <em>Float Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getFloatRight <em>Float Right</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getAction()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sectionStyle'"
 * @generated
 */
public interface Action extends Link {
	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * The default value is <code>"Navigation"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Action roles are used in "wiring" of actions into the generated Web UI.
	 * 
	 * For the root action its children are displayed depending on their rolw as follows:
	 * 
	 * * Navigation: 
	 *     * The first navigation child is called "Principal" and is displayed in the navbar brand. 
	 *     * The remaining navigation children are displayed in navs on the right in the header.
	 * * Context children are displayed in the footer.
	 * 
	 * For the principal action (the first navigation child of the root action):
	 * 
	 * * Navigation actions are displayed in the navigation panel on the left.
	 * * Context actions are displayed in the navbar.
	 * 
	 * For other non-section actions navigation children are displayed in the navigation panel and context children are displayed in right-floating navs on the top of the content panel.
	 * 
	 * Section actions are displayed as part of the content panel body of their parent. Their navigation children are treated as sections and display of their context children depends on the section style.
	 * 
	 * Content left and Content right acitons are displayed on the left and right of the content body respectively.
	 * 
	 * View and Edit actions are applicable for properties and property sources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see #setRole(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Role()
	 * @model default="Navigation"
	 * @generated
	 */
	String getRole();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(String value);

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
	 * @see #setSectionStyle(org.nasdanika.html.model.app.SectionStyle)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_SectionStyle()
	 * @model default="Auto"
	 * @generated
	 */
	org.nasdanika.html.model.app.SectionStyle getSectionStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see #getSectionStyle()
	 * @generated
	 */
	void setSectionStyle(org.nasdanika.html.model.app.SectionStyle value);

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
	 * Returns the value of the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Confirmation to display in a confirmation dialog before action activation to give the user an opportunity to cancel the action. E.g. confirmation of deletion.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Confirmation</em>' attribute.
	 * @see #setConfirmation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Confirmation()
	 * @model
	 * @generated
	 */
	String getConfirmation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confirmation</em>' attribute.
	 * @see #getConfirmation()
	 * @generated
	 */
	void setConfirmation(String value);

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then action is displayed as disabled. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Disabled()
	 * @model
	 * @generated
	 */
	boolean isDisabled();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #isDisabled()
	 * @generated
	 */
	void setDisabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContent();

	/**
	 * Returns the value of the '<em><b>Sections</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sections</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Sections()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getSections();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Context()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getContext();

	/**
	 * Returns the value of the '<em><b>Content Left</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Left</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_ContentLeft()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getContentLeft();

	/**
	 * Returns the value of the '<em><b>Content Right</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content Right</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_ContentRight()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getContentRight();

	/**
	 * Returns the value of the '<em><b>Float Left</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Float Left</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_FloatLeft()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getFloatLeft();

	/**
	 * Returns the value of the '<em><b>Float Right</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Float Right</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_FloatRight()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getFloatRight();

	/**
	 * Returns the value of the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inline</em>' attribute.
	 * @see #setInline(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Inline()
	 * @model
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
	 * Returns the value of the '<em><b>Modal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modal</em>' attribute.
	 * @see #setModal(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Modal()
	 * @model
	 * @generated
	 */
	boolean isModal();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isModal <em>Modal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modal</em>' attribute.
	 * @see #isModal()
	 * @generated
	 */
	void setModal(boolean value);

} // Action
