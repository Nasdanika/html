/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Content panel displays the primary page conent and can have a navigation bar and several navigation panels.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getBreadcrumb <em>Breadcrumb</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getLeftNavigation <em>Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getRightNavigation <em>Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getFloatLeftNavigation <em>Float Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getFloatRightNavigation <em>Float Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getSections <em>Sections</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.ContentPanel#getSectionStyle <em>Section Style</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel()
 * @model
 * @generated
 */
public interface ContentPanel extends PagePart {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content title
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' containment reference.
	 * @see #setTitle(Label)
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_Title()
	 * @model containment="true"
	 * @generated
	 */
	Label getTitle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getTitle <em>Title</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' containment reference.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(Label value);

	/**
	 * Returns the value of the '<em><b>Breadcrumb</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Breadcrumb items. Displayed on the top of the content panel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Breadcrumb</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_Breadcrumb()
	 * @model containment="true"
	 * @generated
	 */
	EList<Label> getBreadcrumb();

	/**
	 * Returns the value of the '<em><b>Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation panel to display on the left of the content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Left Navigation</em>' containment reference.
	 * @see #setLeftNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_LeftNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getLeftNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getLeftNavigation <em>Left Navigation</em>}' containment reference.
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
	 * Navigation panel to display on the right of the content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Right Navigation</em>' containment reference.
	 * @see #setRightNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_RightNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getRightNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getRightNavigation <em>Right Navigation</em>}' containment reference.
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
	 * Navigation panel which float to the left of the content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Float Left Navigation</em>' containment reference.
	 * @see #setFloatLeftNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_FloatLeftNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getFloatLeftNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getFloatLeftNavigation <em>Float Left Navigation</em>}' containment reference.
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
	 * Navigation panel which floats to the right of the content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Float Right Navigation</em>' containment reference.
	 * @see #setFloatRightNavigation(NavigationPanel)
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_FloatRightNavigation()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	NavigationPanel getFloatRightNavigation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getFloatRightNavigation <em>Float Right Navigation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Right Navigation</em>' containment reference.
	 * @see #getFloatRightNavigation()
	 * @generated
	 */
	void setFloatRightNavigation(NavigationPanel value);

	/**
	 * Returns the value of the '<em><b>Sections</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.ContentPanel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content sections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sections</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_Sections()
	 * @model containment="true"
	 * @generated
	 */
	EList<ContentPanel> getSections();

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
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_SectionColumns()
	 * @model default="3"
	 * @generated
	 */
	int getSectionColumns();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getSectionColumns <em>Section Columns</em>}' attribute.
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
	 * @see org.nasdanika.html.model.app.AppPackage#getContentPanel_SectionStyle()
	 * @model default="Auto"
	 * @generated
	 */
	SectionStyle getSectionStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ContentPanel#getSectionStyle <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Style</em>' attribute.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see #getSectionStyle()
	 * @generated
	 */
	void setSectionStyle(SectionStyle value);

} // ContentPanel
