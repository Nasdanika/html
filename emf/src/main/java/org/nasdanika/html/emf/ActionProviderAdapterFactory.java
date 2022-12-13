package org.nasdanika.html.emf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.DiagnosticProvider;
import org.nasdanika.emf.DiagnosticProviderAdapter;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.model.app.util.ActionProvider;
import org.nasdanika.ncore.Composite;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Temporal;

/**
 * @author Pavel
 *
 */
public class ActionProviderAdapterFactory extends ComposedAdapterFactory {
	
	public ActionProviderAdapterFactory(Context context) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionProvider, Temporal>(
				NcorePackage.Literals.TEMPORAL, 
				ActionProvider.class, 
				this.getClass().getClassLoader(), 
				e -> new TemporalActionBuilder(e, context) {
					
					@Override
					protected String getHtmlExtension() {
						return ActionProviderAdapterFactory.this.getHtmlExtension();
					}
					
				}.asActionProvider()));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Composite>(
					NcorePackage.Literals.COMPOSITE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new CompositeActionBuilder<Composite>(e, context) {
						
						@Override
						protected String getHtmlExtension() {
							return ActionProviderAdapterFactory.this.getHtmlExtension();
						}
						
					}.asActionProvider()));
	}
	
	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		if (type == DiagnosticProvider.class) {
			return new DiagnosticProviderAdapter(target, this::getDiagnostic, this::getFeatureDiagnostic);
		}
		return super.adaptNew(target, type);
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		return super.isFactoryForType(type) || type == DiagnosticProvider.class;
	}
	
	protected Collection<Diagnostic> getDiagnostic(Notifier target) {
		return Collections.emptyList();
	}

	protected Collection<Diagnostic> getFeatureDiagnostic(Notifier target, EStructuralFeature feature) {
		return Collections.emptyList();
	}
	
	/**
	 * Allows to customize html extension of actions. E.g. set it to aspx to be servable from OneDrive.
	 * @return
	 */
	protected String getHtmlExtension() {
		return "html";
	}
	
}
