package org.nasdanika.html.flow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class EcoreActionSupplierAdapterFactory extends ComposedAdapterFactory {
	
	public EcoreActionSupplierAdapterFactory(Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EPackage>(
				EcorePackage.Literals.EPACKAGE, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EPackageActionSupplier(e, context, ePackagePathComputer)));	

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EClass>(
				EcorePackage.Literals.ECLASS, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EClassActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EDataType>(
				EcorePackage.Literals.EDATA_TYPE, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EDataTypeActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EEnum>(
				EcorePackage.Literals.EENUM, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EEnumLiteral>(
				EcorePackage.Literals.EENUM_LITERAL, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumLiteralActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EAttribute>(
				EcorePackage.Literals.EATTRIBUTE, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EAttributeActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EReference>(
				EcorePackage.Literals.EREFERENCE, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EReferenceActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EOperation>(
				EcorePackage.Literals.EOPERATION, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EOperationActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, EParameter>(
				EcorePackage.Literals.EPARAMETER, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EParameterActionSupplier(e, context, ePackagePathComputer)));	
	}

}
