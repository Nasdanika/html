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
import org.nasdanika.html.emf.ViewActionStorable;
/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class EcoreViewActionStorableAdapterFactory extends ComposedAdapterFactory {
	
	public EcoreViewActionStorableAdapterFactory(Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EPackage>(
				EcorePackage.Literals.EPACKAGE, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EPackageViewActionStorable(e, context, ePackagePathComputer)));	

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EClass>(
				EcorePackage.Literals.ECLASS, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EClassViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EDataType>(
				EcorePackage.Literals.EDATA_TYPE, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EDataTypeViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EEnum>(
				EcorePackage.Literals.EENUM, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EEnumLiteral>(
				EcorePackage.Literals.EENUM_LITERAL, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EEnumLiteralViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EAttribute>(
				EcorePackage.Literals.EATTRIBUTE, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EAttributeViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EReference>(
				EcorePackage.Literals.EREFERENCE, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EReferenceViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EOperation>(
				EcorePackage.Literals.EOPERATION, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EOperationViewActionStorable(e, context, ePackagePathComputer)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewActionStorable, EParameter>(
				EcorePackage.Literals.EPARAMETER, 
				ViewActionStorable.class, 
				this.getClass().getClassLoader(), 
				e -> new EParameterViewActionStorable(e, context, ePackagePathComputer)));	
	}

}
