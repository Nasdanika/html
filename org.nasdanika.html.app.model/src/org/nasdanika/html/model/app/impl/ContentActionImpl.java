/**
 */
package org.nasdanika.html.model.app.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentAction;
import org.nasdanika.html.model.app.ContentType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentActionImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentActionImpl#getContentType <em>Content Type</em>}</li>
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
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContentType getContentType() {
		return (ContentType)eGet(AppPackage.Literals.CONTENT_ACTION__CONTENT_TYPE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContentType(ContentType newContentType) {
		eSet(AppPackage.Literals.CONTENT_ACTION__CONTENT_TYPE, newContentType);
	}
	
	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		// TODO - handle content type - plain text and markdown.
		return getContent();
	}

} //ContentActionImpl
