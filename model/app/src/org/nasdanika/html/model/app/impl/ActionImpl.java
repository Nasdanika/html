/**
 */
package org.nasdanika.html.model.app.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Category;

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
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isInline <em>Inline</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionImpl extends LabelImpl implements Action {
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
	protected static final SectionStyle SECTION_STYLE_EDEFAULT = (SectionStyle)AppFactory.eINSTANCE.createFromString(AppPackage.eINSTANCE.getSectionStyle(), "AUTO");

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
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isInline() <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INLINE_EDEFAULT = false;

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
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getElements() {
		return (EList<EObject>)eDynamicGet(AppPackage.ACTION__ELEMENTS, AppPackage.Literals.ACTION__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInline() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__INLINE, AppPackage.Literals.ACTION__INLINE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInline(boolean newInline) {
		eDynamicSet(AppPackage.ACTION__INLINE, AppPackage.Literals.ACTION__INLINE, newInline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.ACTION__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case AppPackage.ACTION__CONTENT:
				return getContent();
			case AppPackage.ACTION__ELEMENTS:
				return getElements();
			case AppPackage.ACTION__INLINE:
				return isInline();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
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
			case AppPackage.ACTION__CONTENT:
				setContent((String)newValue);
				return;
			case AppPackage.ACTION__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends EObject>)newValue);
				return;
			case AppPackage.ACTION__INLINE:
				setInline((Boolean)newValue);
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
			case AppPackage.ACTION__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
			case AppPackage.ACTION__ELEMENTS:
				getElements().clear();
				return;
			case AppPackage.ACTION__INLINE:
				setInline(INLINE_EDEFAULT);
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
			case AppPackage.ACTION__CONTENT:
				return CONTENT_EDEFAULT == null ? getContent() != null : !CONTENT_EDEFAULT.equals(getContent());
			case AppPackage.ACTION__ELEMENTS:
				return !getElements().isEmpty();
			case AppPackage.ACTION__INLINE:
				return isInline() != INLINE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean isEmpty() {
		ViewPart delegate = (ViewPart) EcoreUtil.getRegisteredAdapter(this, ViewPart.class);
		return delegate == null && Util.isBlank(getContent());
	}
	
	@Override
	public Label getCategory() {
		return eContainer() instanceof Category ? (Category) eContainer : null;
	}
	
	private List<org.nasdanika.html.app.Action> children;	
	
	@Override
	public List<org.nasdanika.html.app.Action> getChildren() {
		if (children == null) {
			children = new ArrayList<>();
			for (EObject e: getElements()) {
				if (e instanceof Category) {
					children.addAll(((Category) e).getActions());
				} else {
					org.nasdanika.html.app.Action action = EObjectAdaptable.adaptTo(e, org.nasdanika.html.app.Action.class);
					if (action == null) {
						throw new ConfigurationException("Cannot adapt " + e + " to " + org.nasdanika.html.app.Action.class.getName(), EObjectAdaptable.adaptTo(this, Marked.class));
					}
					children.add(action);
				}
			}
		}
		return children;
	}

	@Override
	public boolean isInRole(String role) {
		return role.equals(getRole());
	}

	@Override
	public org.nasdanika.html.app.Action getParent() {
		EObject c = eContainer();
		if (c instanceof org.nasdanika.html.app.Action) {
			return (org.nasdanika.html.app.Action) c;
		}
		if (c instanceof Category) {
			EObject cc = c.eContainer();
			if (cc instanceof org.nasdanika.html.app.Action) {
				return (org.nasdanika.html.app.Action) cc;
			}
		}
		return null;
	}

	@Override
	public ActionActivator getActivator() {
		if (eIsSet(AppPackage.Literals.ACTION__SCRIPT)) {
			 return new ScriptActionActivator() {
				
				@Override
				public String getCode() {
					return getScript();
				}
				
				@Override
				public boolean inline() {
					return ActionImpl.this.isInline();
				}
				
			};
		}
		
		Marked marked = (Marked) EcoreUtil.getRegisteredAdapter(this, Marked.class);
		
		if (eIsSet(AppPackage.Literals.ACTION__LOCATION)) {
			Context context = (Context) EcoreUtil.getRegisteredAdapter(this, Context.class);
			return new PathNavigationActionActivator(this, context == null ? null : context.getString(Context.BASE_URI_PROPERTY), getLocation(), marked == null ? null : marked.getMarker()) {
				
				@Override
				public boolean inline() {
					return ActionImpl.this.isInline();
				}
				
			};			
		}
		
		if (eIsSet(AppPackage.Literals.ACTION__BINDING)) { 
			throw new ConfigurationException("Binding activator is not yet supported", marked);
		}
		
		return null;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ViewPart delegate = (ViewPart) EcoreUtil.getRegisteredAdapter(this, ViewPart.class);
		return delegate == null ? getContent() : delegate.generate(viewGenerator, progressMonitor);
	}

} //ActionImpl
