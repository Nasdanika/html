/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Footer;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.Page;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.PageImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.PageImpl#getNavigationBar <em>Navigation Bar</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.PageImpl#getNavigationPanel <em>Navigation Panel</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.PageImpl#getContentPanel <em>Content Panel</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.PageImpl#getFooter <em>Footer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageImpl extends org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl implements Page {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Header getHeader() {
		return (Header)eDynamicGet(AppPackage.PAGE__HEADER, AppPackage.Literals.PAGE__HEADER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(Header newHeader, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newHeader, AppPackage.PAGE__HEADER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeader(Header newHeader) {
		eDynamicSet(AppPackage.PAGE__HEADER, AppPackage.Literals.PAGE__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationBar getNavigationBar() {
		return (NavigationBar)eDynamicGet(AppPackage.PAGE__NAVIGATION_BAR, AppPackage.Literals.PAGE__NAVIGATION_BAR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigationBar(NavigationBar newNavigationBar, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newNavigationBar, AppPackage.PAGE__NAVIGATION_BAR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNavigationBar(NavigationBar newNavigationBar) {
		eDynamicSet(AppPackage.PAGE__NAVIGATION_BAR, AppPackage.Literals.PAGE__NAVIGATION_BAR, newNavigationBar);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanel getNavigationPanel() {
		return (NavigationPanel)eDynamicGet(AppPackage.PAGE__NAVIGATION_PANEL, AppPackage.Literals.PAGE__NAVIGATION_PANEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNavigationPanel(NavigationPanel newNavigationPanel, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newNavigationPanel, AppPackage.PAGE__NAVIGATION_PANEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNavigationPanel(NavigationPanel newNavigationPanel) {
		eDynamicSet(AppPackage.PAGE__NAVIGATION_PANEL, AppPackage.Literals.PAGE__NAVIGATION_PANEL, newNavigationPanel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentPanel getContentPanel() {
		return (ContentPanel)eDynamicGet(AppPackage.PAGE__CONTENT_PANEL, AppPackage.Literals.PAGE__CONTENT_PANEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContentPanel(ContentPanel newContentPanel, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newContentPanel, AppPackage.PAGE__CONTENT_PANEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContentPanel(ContentPanel newContentPanel) {
		eDynamicSet(AppPackage.PAGE__CONTENT_PANEL, AppPackage.Literals.PAGE__CONTENT_PANEL, newContentPanel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Footer getFooter() {
		return (Footer)eDynamicGet(AppPackage.PAGE__FOOTER, AppPackage.Literals.PAGE__FOOTER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFooter(Footer newFooter, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFooter, AppPackage.PAGE__FOOTER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFooter(Footer newFooter) {
		eDynamicSet(AppPackage.PAGE__FOOTER, AppPackage.Literals.PAGE__FOOTER, newFooter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.PAGE__HEADER:
				return basicSetHeader(null, msgs);
			case AppPackage.PAGE__NAVIGATION_BAR:
				return basicSetNavigationBar(null, msgs);
			case AppPackage.PAGE__NAVIGATION_PANEL:
				return basicSetNavigationPanel(null, msgs);
			case AppPackage.PAGE__CONTENT_PANEL:
				return basicSetContentPanel(null, msgs);
			case AppPackage.PAGE__FOOTER:
				return basicSetFooter(null, msgs);
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
			case AppPackage.PAGE__HEADER:
				return getHeader();
			case AppPackage.PAGE__NAVIGATION_BAR:
				return getNavigationBar();
			case AppPackage.PAGE__NAVIGATION_PANEL:
				return getNavigationPanel();
			case AppPackage.PAGE__CONTENT_PANEL:
				return getContentPanel();
			case AppPackage.PAGE__FOOTER:
				return getFooter();
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
			case AppPackage.PAGE__HEADER:
				setHeader((Header)newValue);
				return;
			case AppPackage.PAGE__NAVIGATION_BAR:
				setNavigationBar((NavigationBar)newValue);
				return;
			case AppPackage.PAGE__NAVIGATION_PANEL:
				setNavigationPanel((NavigationPanel)newValue);
				return;
			case AppPackage.PAGE__CONTENT_PANEL:
				setContentPanel((ContentPanel)newValue);
				return;
			case AppPackage.PAGE__FOOTER:
				setFooter((Footer)newValue);
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
			case AppPackage.PAGE__HEADER:
				setHeader((Header)null);
				return;
			case AppPackage.PAGE__NAVIGATION_BAR:
				setNavigationBar((NavigationBar)null);
				return;
			case AppPackage.PAGE__NAVIGATION_PANEL:
				setNavigationPanel((NavigationPanel)null);
				return;
			case AppPackage.PAGE__CONTENT_PANEL:
				setContentPanel((ContentPanel)null);
				return;
			case AppPackage.PAGE__FOOTER:
				setFooter((Footer)null);
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
			case AppPackage.PAGE__HEADER:
				return getHeader() != null;
			case AppPackage.PAGE__NAVIGATION_BAR:
				return getNavigationBar() != null;
			case AppPackage.PAGE__NAVIGATION_PANEL:
				return getNavigationPanel() != null;
			case AppPackage.PAGE__CONTENT_PANEL:
				return getContentPanel() != null;
			case AppPackage.PAGE__FOOTER:
				return getFooter() != null;
		}
		return super.eIsSet(featureID);
	}

} //PageImpl
