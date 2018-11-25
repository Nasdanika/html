/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentActionImpl#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContentActionImpl extends ActionImpl implements ContentAction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.CONTENT_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return (String)eGet(AppPackage.Literals.CONTENT_ACTION__CONTENT, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		eSet(AppPackage.Literals.CONTENT_ACTION__CONTENT, newContent);

	}
	
	@Override
	public Object execute() {
		return getContent();
	}

} //ContentActionImpl
