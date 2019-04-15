/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Theme;

import org.nasdanika.html.model.bootstrap.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BootstrapFactoryImpl extends EFactoryImpl implements BootstrapFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BootstrapFactory init() {
		try {
			BootstrapFactory theBootstrapFactory = (BootstrapFactory)EPackage.Registry.INSTANCE.getEFactory(BootstrapPackage.eNS_URI);
			if (theBootstrapFactory != null) {
				return theBootstrapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BootstrapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BootstrapPackage.BOOTSTRAP_CDN_FACET: return (EObject)createBootstrapCDNFacet();
			case BootstrapPackage.WRAP: return (EObject)createWrap();
			case BootstrapPackage.ALERT: return (EObject)createAlert();
			case BootstrapPackage.BADGE: return (EObject)createBadge();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case BootstrapPackage.THEME:
				return createThemeFromString(eDataType, initialValue);
			case BootstrapPackage.COLOR:
				return createColorFromString(eDataType, initialValue);
			case BootstrapPackage.DEVICE_SIZE:
				return createDeviceSizeFromString(eDataType, initialValue);
			case BootstrapPackage.DIRECTION:
				return createDirectionFromString(eDataType, initialValue);
			case BootstrapPackage.PLACEMENT:
				return createPlacementFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case BootstrapPackage.THEME:
				return convertThemeToString(eDataType, instanceValue);
			case BootstrapPackage.COLOR:
				return convertColorToString(eDataType, instanceValue);
			case BootstrapPackage.DEVICE_SIZE:
				return convertDeviceSizeToString(eDataType, instanceValue);
			case BootstrapPackage.DIRECTION:
				return convertDirectionToString(eDataType, instanceValue);
			case BootstrapPackage.PLACEMENT:
				return convertPlacementToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapCDNFacet createBootstrapCDNFacet() {
		BootstrapCDNFacetImpl bootstrapCDNFacet = new BootstrapCDNFacetImpl();
		return bootstrapCDNFacet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Wrap createWrap() {
		WrapImpl wrap = new WrapImpl();
		return wrap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Alert createAlert() {
		AlertImpl alert = new AlertImpl();
		return alert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Badge createBadge() {
		BadgeImpl badge = new BadgeImpl();
		return badge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Theme createThemeFromString(EDataType eDataType, String initialValue) {
		return (Theme)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertThemeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Color createColorFromString(EDataType eDataType, String initialValue) {
		return (Color)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertColorToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeviceSize createDeviceSizeFromString(EDataType eDataType, String initialValue) {
		return (DeviceSize)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeviceSizeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Direction createDirectionFromString(EDataType eDataType, String initialValue) {
		return (Direction)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Placement createPlacementFromString(EDataType eDataType, String initialValue) {
		return (Placement)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPlacementToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapPackage getBootstrapPackage() {
		return (BootstrapPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BootstrapPackage getPackage() {
		return BootstrapPackage.eINSTANCE;
	}

} //BootstrapFactoryImpl
