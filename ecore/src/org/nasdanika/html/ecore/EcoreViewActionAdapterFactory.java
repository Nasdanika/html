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
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.emf.ViewAction;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class EcoreViewActionAdapterFactory extends ComposedAdapterFactory {
	
	public EcoreViewActionAdapterFactory(Action topLevelPackageParentAction) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EPackage>(
				EcorePackage.Literals.EPACKAGE, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				ePackage -> new EPackageViewAction(ePackage, topLevelPackageParentAction)));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EClass>(
				EcorePackage.Literals.ECLASS, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EClassViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EDataType>(
				EcorePackage.Literals.EDATA_TYPE, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EDataTypeViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EEnum>(
				EcorePackage.Literals.EENUM, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EEnumViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EEnumLiteral>(
				EcorePackage.Literals.EENUM_LITERAL, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EEnumLiteralViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EAttribute>(
				EcorePackage.Literals.EATTRIBUTE, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EAttributeViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EReference>(
				EcorePackage.Literals.EREFERENCE, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EReferenceViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EOperation>(
				EcorePackage.Literals.EOPERATION, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EOperationViewAction::new));		

		registerAdapterFactory(
			new FunctionAdapterFactory<ViewAction, EParameter>(
				EcorePackage.Literals.EPARAMETER, 
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EParameterViewAction::new));		
	}

}
