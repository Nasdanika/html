package org.nasdanika.html.ecore;

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
	
	public EcoreActionSupplierAdapterFactory(
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver) {
		
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EPackage>(
				EcorePackage.Literals.EPACKAGE, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EPackageActionSupplier(e, context, ePackagePathComputer, this::getDiagramDialect)));	

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EClass>(
				EcorePackage.Literals.ECLASS, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EClassActionSupplier(e, context, ePackagePathComputer, javadocResolver, this::getEPackage, this::isGenerateLoadSpecification, this::getDiagramDialect)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EDataType>(
				EcorePackage.Literals.EDATA_TYPE, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EDataTypeActionSupplier(e, context, ePackagePathComputer, javadocResolver, this::getEPackage)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EEnum>(
				EcorePackage.Literals.EENUM, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumActionSupplier(e, context, ePackagePathComputer, javadocResolver, this::getEPackage)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EEnumLiteral>(
				EcorePackage.Literals.EENUM_LITERAL, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumLiteralActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EAttribute>(
				EcorePackage.Literals.EATTRIBUTE, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EAttributeActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EReference>(
				EcorePackage.Literals.EREFERENCE, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EReferenceActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EOperation>(
				EcorePackage.Literals.EOPERATION, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EOperationActionSupplier(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<EcoreActionSupplier, EParameter>(
				EcorePackage.Literals.EPARAMETER, 
				EcoreActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new EParameterActionSupplier(e, context, ePackagePathComputer)));	
	}
	
	/**
	 * Override to return false to suppress generation of load specification.
	 * @return
	 */
	protected boolean isGenerateLoadSpecification() {
		return true;
	}
	
	protected String getDiagramDialect() {
		return null;
	}
	
	protected Object getEPackage(String nsURI) {
		return EPackage.Registry.INSTANCE.get(nsURI);
	}

}
