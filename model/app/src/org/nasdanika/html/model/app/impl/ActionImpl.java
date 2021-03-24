/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionStyle <em>Section Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getMarkdownContent <em>Markdown Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getPageTemplate <em>Page Template</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ActionImpl extends LabelImpl implements Action {
	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_EDEFAULT = "Navigation";

	/**
	 * The default value of the '{@link #getSectionStyle() <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionStyle()
	 * @generated
	 * @ordered
	 */
	protected static final SectionStyle SECTION_STYLE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getSectionColumns() <em>Section Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionColumns()
	 * @generated
	 * @ordered
	 */
	protected static final int SECTION_COLUMNS_EDEFAULT = 3;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected static final String BINDING_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getConfirmation() <em>Confirmation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfirmation()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIRMATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getMarkdownContent() <em>Markdown Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkdownContent()
	 * @generated
	 * @ordered
	 */
	protected static final String MARKDOWN_CONTENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPageTemplate() <em>Page Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_TEMPLATE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRole() {
		return (String)eDynamicGet(AppPackage.ACTION__ROLE, AppPackage.Literals.ACTION__ROLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRole(String newRole) {
		eDynamicSet(AppPackage.ACTION__ROLE, AppPackage.Literals.ACTION__ROLE, newRole);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SectionStyle getSectionStyle() {
		return (SectionStyle)eDynamicGet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionStyle(SectionStyle newSectionStyle) {
		eDynamicSet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, newSectionStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSectionColumns() {
		return (Integer)eDynamicGet(AppPackage.ACTION__SECTION_COLUMNS, AppPackage.Literals.ACTION__SECTION_COLUMNS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionColumns(int newSectionColumns) {
		eDynamicSet(AppPackage.ACTION__SECTION_COLUMNS, AppPackage.Literals.ACTION__SECTION_COLUMNS, newSectionColumns);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(AppPackage.ACTION__LOCATION, AppPackage.Literals.ACTION__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(AppPackage.ACTION__LOCATION, AppPackage.Literals.ACTION__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScript() {
		return (String)eDynamicGet(AppPackage.ACTION__SCRIPT, AppPackage.Literals.ACTION__SCRIPT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScript(String newScript) {
		eDynamicSet(AppPackage.ACTION__SCRIPT, AppPackage.Literals.ACTION__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBinding() {
		return (String)eDynamicGet(AppPackage.ACTION__BINDING, AppPackage.Literals.ACTION__BINDING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBinding(String newBinding) {
		eDynamicSet(AppPackage.ACTION__BINDING, AppPackage.Literals.ACTION__BINDING, newBinding);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConfirmation() {
		return (String)eDynamicGet(AppPackage.ACTION__CONFIRMATION, AppPackage.Literals.ACTION__CONFIRMATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfirmation(String newConfirmation) {
		eDynamicSet(AppPackage.ACTION__CONFIRMATION, AppPackage.Literals.ACTION__CONFIRMATION, newConfirmation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisabled() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__DISABLED, AppPackage.Literals.ACTION__DISABLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(boolean newDisabled) {
		eDynamicSet(AppPackage.ACTION__DISABLED, AppPackage.Literals.ACTION__DISABLED, newDisabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMarkdownContent() {
		return (String)eDynamicGet(AppPackage.ACTION__MARKDOWN_CONTENT, AppPackage.Literals.ACTION__MARKDOWN_CONTENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarkdownContent(String newMarkdownContent) {
		eDynamicSet(AppPackage.ACTION__MARKDOWN_CONTENT, AppPackage.Literals.ACTION__MARKDOWN_CONTENT, newMarkdownContent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPageTemplate() {
		return (String)eDynamicGet(AppPackage.ACTION__PAGE_TEMPLATE, AppPackage.Literals.ACTION__PAGE_TEMPLATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPageTemplate(String newPageTemplate) {
		eDynamicSet(AppPackage.ACTION__PAGE_TEMPLATE, AppPackage.Literals.ACTION__PAGE_TEMPLATE, newPageTemplate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getContent() {
		return (String)eDynamicGet(AppPackage.ACTION__CONTENT, AppPackage.Literals.ACTION__CONTENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContent(String newContent) {
		eDynamicSet(AppPackage.ACTION__CONTENT, AppPackage.Literals.ACTION__CONTENT, newContent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.ACTION__ROLE:
				return getRole();
			case AppPackage.ACTION__SECTION_STYLE:
				return getSectionStyle();
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns();
			case AppPackage.ACTION__LOCATION:
				return getLocation();
			case AppPackage.ACTION__SCRIPT:
				return getScript();
			case AppPackage.ACTION__BINDING:
				return getBinding();
			case AppPackage.ACTION__CONFIRMATION:
				return getConfirmation();
			case AppPackage.ACTION__DISABLED:
				return isDisabled();
			case AppPackage.ACTION__MARKDOWN_CONTENT:
				return getMarkdownContent();
			case AppPackage.ACTION__PAGE_TEMPLATE:
				return getPageTemplate();
			case AppPackage.ACTION__CONTENT:
				return getContent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AppPackage.ACTION__ROLE:
				setRole((String)newValue);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle((SectionStyle)newValue);
				return;
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns((Integer)newValue);
				return;
			case AppPackage.ACTION__LOCATION:
				setLocation((String)newValue);
				return;
			case AppPackage.ACTION__SCRIPT:
				setScript((String)newValue);
				return;
			case AppPackage.ACTION__BINDING:
				setBinding((String)newValue);
				return;
			case AppPackage.ACTION__CONFIRMATION:
				setConfirmation((String)newValue);
				return;
			case AppPackage.ACTION__DISABLED:
				setDisabled((Boolean)newValue);
				return;
			case AppPackage.ACTION__MARKDOWN_CONTENT:
				setMarkdownContent((String)newValue);
				return;
			case AppPackage.ACTION__PAGE_TEMPLATE:
				setPageTemplate((String)newValue);
				return;
			case AppPackage.ACTION__CONTENT:
				setContent((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case AppPackage.ACTION__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle(SECTION_STYLE_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns(SECTION_COLUMNS_EDEFAULT);
				return;
			case AppPackage.ACTION__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case AppPackage.ACTION__SCRIPT:
				setScript(SCRIPT_EDEFAULT);
				return;
			case AppPackage.ACTION__BINDING:
				setBinding(BINDING_EDEFAULT);
				return;
			case AppPackage.ACTION__CONFIRMATION:
				setConfirmation(CONFIRMATION_EDEFAULT);
				return;
			case AppPackage.ACTION__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case AppPackage.ACTION__MARKDOWN_CONTENT:
				setMarkdownContent(MARKDOWN_CONTENT_EDEFAULT);
				return;
			case AppPackage.ACTION__PAGE_TEMPLATE:
				setPageTemplate(PAGE_TEMPLATE_EDEFAULT);
				return;
			case AppPackage.ACTION__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case AppPackage.ACTION__ROLE:
				return ROLE_EDEFAULT == null ? getRole() != null : !ROLE_EDEFAULT.equals(getRole());
			case AppPackage.ACTION__SECTION_STYLE:
				return SECTION_STYLE_EDEFAULT == null ? getSectionStyle() != null : !SECTION_STYLE_EDEFAULT.equals(getSectionStyle());
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns() != SECTION_COLUMNS_EDEFAULT;
			case AppPackage.ACTION__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case AppPackage.ACTION__SCRIPT:
				return SCRIPT_EDEFAULT == null ? getScript() != null : !SCRIPT_EDEFAULT.equals(getScript());
			case AppPackage.ACTION__BINDING:
				return BINDING_EDEFAULT == null ? getBinding() != null : !BINDING_EDEFAULT.equals(getBinding());
			case AppPackage.ACTION__CONFIRMATION:
				return CONFIRMATION_EDEFAULT == null ? getConfirmation() != null : !CONFIRMATION_EDEFAULT.equals(getConfirmation());
			case AppPackage.ACTION__DISABLED:
				return isDisabled() != DISABLED_EDEFAULT;
			case AppPackage.ACTION__MARKDOWN_CONTENT:
				return MARKDOWN_CONTENT_EDEFAULT == null ? getMarkdownContent() != null : !MARKDOWN_CONTENT_EDEFAULT.equals(getMarkdownContent());
			case AppPackage.ACTION__PAGE_TEMPLATE:
				return PAGE_TEMPLATE_EDEFAULT == null ? getPageTemplate() != null : !PAGE_TEMPLATE_EDEFAULT.equals(getPageTemplate());
			case AppPackage.ACTION__CONTENT:
				return CONTENT_EDEFAULT == null ? getContent() != null : !CONTENT_EDEFAULT.equals(getContent());
		}
		return super.eIsSet(featureID);
	}

} //ActionImpl
