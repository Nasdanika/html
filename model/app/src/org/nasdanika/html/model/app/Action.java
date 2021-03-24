/**
 */
package org.nasdanika.html.model.app;

import org.nasdanika.html.app.SectionStyle;


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
 *   <li>{@link org.nasdanika.html.model.app.Action#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getMarkdownContent <em>Markdown Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getPageTemplate <em>Page Template</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getAction()
 * @model abstract="true" superTypes="org.nasdanika.html.model.app.Label org.nasdanika.html.model.app.IAction"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='sectionStyle'"
 * @generated
 */
public interface Action extends Label, org.nasdanika.html.app.Action {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines how to generate section children.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Section Style</em>' attribute.
	 * @see #setSectionStyle(SectionStyle)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_SectionStyle()
	 * @model dataType="org.nasdanika.html.model.app.SectionStyle"
	 * @generated
	 */
	SectionStyle getSectionStyle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Section Style</em>' attribute.
	 * @see #getSectionStyle()
	 * @generated
	 */
	void setSectionStyle(SectionStyle value);

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
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Script()
	 * @model
	 * @generated
	 */
	String getScript();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(String value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see #setBinding(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Binding()
	 * @model
	 * @generated
	 */
	String getBinding();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(String value);

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
	 * Returns the value of the '<em><b>Markdown Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * [Markdown](https://en.wikipedia.org/wiki/Markdown) text. If this attribute contains text, the text is converted to HTML, interpolated and used as the first content element.
	 * The primary purpose of this attribute is rapid development/prototyping of web applications/sites. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Markdown Content</em>' attribute.
	 * @see #setMarkdownContent(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_MarkdownContent()
	 * @model annotation="urn:org.nasdanika content-type='text/markdown'"
	 * @generated
	 */
	String getMarkdownContent();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getMarkdownContent <em>Markdown Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Markdown Content</em>' attribute.
	 * @see #getMarkdownContent()
	 * @generated
	 */
	void setMarkdownContent(String value);

	/**
	 * Returns the value of the '<em><b>Page Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * URI of the page template to use for generation of a Web Application. The URI is resolved relative to the action's containing resource.
	 * Defaults to ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary.vinci`` built-in template.
	 * 
	 * ## Built-in page templates
	 * 
	 * ``org.nasdanika.vinci.templates`` plugin, which is part of the Vinci distribution, provides the following page templates (organized by theme and header color):
	 * 
	 * * Default
	 *     * Primary
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary.vinci``
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary-fluid.vinci`` - fluid container.
	 * * Cerulean
	 *     * Primary
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary.vinci``
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-fluid.vinci`` - fluid container.
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-dark.vinci`` - dark navigation bar.
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-dark-fluid.vinci`` - dark navigation bar, fluid container.
	 *     * Dark 
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/dark.vinci``
	 *         * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/dark-fluid.vinci`` - fluid container.
	 *     
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Page Template</em>' attribute.
	 * @see #setPageTemplate(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_PageTemplate()
	 * @model
	 * @generated
	 */
	String getPageTemplate();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getPageTemplate <em>Page Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Page Template</em>' attribute.
	 * @see #getPageTemplate()
	 * @generated
	 */
	void setPageTemplate(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Content()
	 * @model
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

} // Action
