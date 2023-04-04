package org.nasdanika.html.ecore.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.LabelProvider;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class EcoreLabelProviderAdapterFactory extends ComposedAdapterFactory {
	
	/**
	 * 
	 * @param eModelElement
	 * @return Identity (seed) label for a model element. This implementation returns null.
	 * Override to customize, e.g. add sections.
	 */
	protected Label getIdentityLabel(EModelElement eModelElement) {
		// TODO - action (label) prototype or prototype reference
		return null;
	}
	
	public EcoreLabelProviderAdapterFactory(
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver) {
		
//		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<LabelProvider, EPackage>(
				EcorePackage.Literals.EPACKAGE, 
				LabelProvider.class, 
				this.getClass().getClassLoader(), 
				e -> new EPackageLabelBuilder(e, context).asProvider(getIdentityLabel(e), NcoreUtil.getIdentifiers(e))));
//						e, 
//						context, 
//						ePackagePathComputer, 
//						this::getEPackage, 
//						this::shallDocument,
//						this::getLabel,
//						this::getDiagramDialect,
//						this::getEClassifierRole)));	
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EClass>(
//				EcorePackage.Literals.ECLASS, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EClassActionSupplier(
//						e, 
//						context, 
//						ePackagePathComputer, 
//						javadocResolver, 
//						this::getEPackage, 
//						this::shallDocument,
//						this::getLabel,
//						this::isGenerateLoadSpecification, 
//						this::getDiagramDialect)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EDataType>(
//				EcorePackage.Literals.EDATA_TYPE, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EDataTypeActionSupplier(
//						e, 
//						context, 
//						ePackagePathComputer, 
//						javadocResolver, 
//						this::getEPackage,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EEnum>(
//				EcorePackage.Literals.EENUM, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EEnumActionSupplier(
//						e, 
//						context, 
//						ePackagePathComputer, 
//						javadocResolver,
//						this::getEPackage,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EEnumLiteral>(
//				EcorePackage.Literals.EENUM_LITERAL, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EEnumLiteralActionSupplier(
//						e, 
//						context, 
//						ePackagePathComputer,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EAttribute>(
//				EcorePackage.Literals.EATTRIBUTE, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EAttributeActionSupplier(
//						e,
//						context,
//						ePackagePathComputer,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EReference>(
//				EcorePackage.Literals.EREFERENCE, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EReferenceActionSupplier(
//						e, 
//						context,
//						ePackagePathComputer,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EOperation>(
//				EcorePackage.Literals.EOPERATION, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EOperationActionSupplier(
//						e, 
//						context, 
//						ePackagePathComputer,
//						this::shallDocument,
//						this::getLabel)));		
//
//		registerAdapterFactory(
//			new FunctionAdapterFactory<EcoreActionSupplier, EParameter>(
//				EcorePackage.Literals.EPARAMETER, 
//				EcoreActionSupplier.class, 
//				this.getClass().getClassLoader(), 
//				e -> new EParameterActionSupplier(
//						e,
//						context,
//						ePackagePathComputer,
//						this::shallDocument,
//						this::getLabel)));	
	}
//	
//	/**
//	 * Override to return false to suppress generation of load specification.
//	 * @return
//	 */
//	protected boolean isGenerateLoadSpecification() {
//		return true;
//	}
//	
//	protected String getDiagramDialect() {
//		return null;
//	}
//	
//	protected Object getEPackage(String nsURI) {
//		return EPackage.Registry.INSTANCE.get(nsURI);
//	}
//	
//	/**
//	 * @param modelElement
//	 * @return true if the element is documentable, its container shall be documented and for {@link ETypedElement}'s the type should be documented. 
//	 */
//	protected boolean shallDocument(EModelElement modelElement) {
//		if (!isDocumentable(modelElement)) {
//			return false;
//		}
//		if (modelElement instanceof EPackage) {
//			EPackage sp = ((EPackage) modelElement).getESuperPackage();
//			return sp == null || shallDocument(sp);
//		}
//		if (modelElement instanceof EClass) {
//			EPackage p = ((EClass) modelElement).getEPackage();
//			return shallDocument(p);
//		}
//		if (modelElement instanceof EStructuralFeature) {
//			EStructuralFeature sf = (EStructuralFeature) modelElement;
//			EClass c = sf.getEContainingClass();
//			EClassifier t = sf.getEType();
//			return shallDocument(c) && shallDocument(t);
//		}
//		if (modelElement instanceof EOperation) {
//			EOperation op = (EOperation) modelElement;
//			EClass c = op.getEContainingClass();
//			EClassifier t = op.getEType();
//			return shallDocument(c) && shallDocument(t);
//		}
//		if (modelElement instanceof EEnumLiteral) {
//			EEnum e = ((EEnumLiteral) modelElement).getEEnum();
//			return shallDocument(e);
//		}
//		
//		return true;
//	}
//	
//	/**
//	 * Override to selectively document models, i.e. provide a specific view.
//	 * @param modelElement
//	 * @return
//	 */
//	protected boolean isDocumentable(EModelElement modelElement) {
//		return true;
//	}
//	
//	protected EReference getEClassifierRole(EClassifier eClassifier) {
//		String roleName = NcoreUtil.getNasdanikaAnnotationDetail(eClassifier, "role", "children");
//		return (EReference) AppPackage.Literals.ACTION.getEStructuralFeature(roleName);
//	}
//
//	protected String getLabel(ENamedElement eNamedElement, String defaultLabel) {
//		return NcoreUtil.getNasdanikaAnnotationDetail(eNamedElement, "label", defaultLabel);
//	}
	
}
