package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;

/**
 * Generation of resource/page model from an action model, optional saving, and then generation of files.
 * @author Pavel
 *
 */
public class TestAction extends TestBase {
		
	@Test
	public void testGenerateResourceModel() throws Exception {
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};
		
		Context modelContext = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		String resource = "app/actions.yml";
		Action root = (Action) Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		dumpToYaml(root);
		Function<Action, URI> uriResolver = uriResolver(Context.singleton("base-uri", "temp://a/b"));
		System.out.println(uriResolver.apply(root));
		TreeIterator<EObject> cit = root.eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof Action) {
				System.out.println(uriResolver.apply((Action) next));
			}
		}
	}
	
	/**
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	private Function<Action,URI> uriResolver(Context context) {		
		return new Function<Action, URI>() {
			Map<Action, URI> cache = new HashMap<>();
			
			@Override
			public URI apply(Action action) {
				return cache.computeIfAbsent(action, this::compute);
			}
			
			private URI compute(Action action) {
				String uriString = context.interpolateToString(action.getLocation());
				if (Util.isBlank(uriString)) {
					return null;
				}
				if (action.eContainmentFeature() == AppPackage.Literals.ACTION__SECTIONS) {
					uriString = "#" + uriString;
				}
				URI uri = URI.createURI(uriString);
				if (uri.isRelative()) {
					URI base = getAncestorURI(action);				
					if (base != null) {					
						return uri.resolve(base);
					}
				}
				return uri;
			}
			
			private URI getAncestorURI(Action action) {
				for (EObject ancestor = action.eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof Action) {
						URI ancestorURI = apply((Action) ancestor);
						if (ancestorURI != null) {
							return ancestorURI;
						}
					}
				}		
				return null;
			}
			
		};
	}
	
}
