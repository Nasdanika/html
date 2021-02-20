package org.nasdanika.html.ecore;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.nasdanika.cli.CommandBase;
import org.nasdanika.cli.ProgressMonitorMixin;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Storable;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewActionStorable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Generates Vinci {@link org.nasdanika.vinci.app.Action} tree documentation.
 * @author Pavel
 *
 */
@Command(
		description = "Generates Ecore model documentation as a YAML action tree",
		name = "ecore")
public class EcoreDocumentationGeneratorCommand extends CommandBase {
	
	static final String JAVADOC_CONTEXT_BUILDER_MOUNT = "javadoc-context-builder-mount";

	@Parameters(
			paramLabel = "NS-URI", 
			arity = "1..*",
			description = "A list of model NS URI's to generate documentation for. "
					+ "For each NS-URI a vinci file is generated named after the root package in the model. "
					+ "If there are duplicate package names then argument index is added to de-dup the names. ")
	protected List<String> nsURIs = new ArrayList<>();	
	
	@Mixin
	private ProgressMonitorMixin progressMonitorMixin;
		
	@Option(names = {"-o", "--output"}, description = "Output directory, defaults to the current directory.")
	private File outputDir;		
		
	@Option(names = {"-b", "--base-uri"}, description = "Base URI for resolving eclasifier references in diagram image maps. Resolved against the output directory URI. Defaults to the output directory URI.")
	private String baseUri;
	
	@Option(
			names = {"-J", "--javadoc-context-builder-mount"}, 
			arity = "0..1",
			fallbackValue = "javadoc/",
			description = "If specified model instance class names are output as tokens for expansion to links to JavaDoc. If specified without parameter option value is ${FALLBACK-VALUE}")
	private String javaDocContextBuilderMount;			
	
	@Option(
			names = {"-n", "--name"}, 
			description = "Use EPackage name in paths and URL's instead of encoded NS URI. Duplicate names are de-duplicated by adding -<number> suffix")
	private boolean useEPackageNameInPath;		
	
	// TODO - localizations - enum as they become available.

	protected GenModelResourceSet resourceSet;
	// protected ResourceLocator resourceLocator;
	
	/**
	 * Map of {@link EPackage} path to NS URI. 
	 */
	private Map<String,String> pathMap = new ConcurrentHashMap<>();
	
	private String getEPackagePath(EPackage ePackage) {
		for (int i = 0; i < Integer.MAX_VALUE; ++i) {
			String path = i == 0 ? ePackage.getName() : ePackage.getName() + "_" + i;
			if (pathMap.containsKey(path)) {
				if (ePackage.getNsURI().equals(pathMap.get(path))) {
					return path;
				}
			} else {
				pathMap.put(path, ePackage.getNsURI());
				return path;
			}
		}
		
		// Encoding NS URI as HEX. Shall never reach this point.
		return Hex.encodeHexString(ePackage.getNsURI().getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Override to customize the adapter factory.
	 * @return
	 */
	protected EcoreViewActionStorableAdapterFactory createAdapterFactory() {
		if (outputDir == null) {
			outputDir = new File(".");
		}
		URI outputURI = URI.createFileURI(outputDir.getAbsolutePath() + File.separator);
		URI baseURI = Util.isBlank(baseUri) ? outputURI : URI.createURI(baseUri).resolve(outputURI);
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		context.register(URI.class, baseURI);
		context.put("base-uri", baseURI);
		if (!Util.isBlank(javaDocContextBuilderMount)) {
			context.put(JAVADOC_CONTEXT_BUILDER_MOUNT, javaDocContextBuilderMount);
		}
		
		return new EcoreViewActionStorableAdapterFactory(context, useEPackageNameInPath ? this::getEPackagePath : null);
	}
	
	public List<ViewActionStorable> loadGenModel() {
		resourceSet = new GenModelResourceSet();		
		resourceSet.getAdapterFactories().add(createAdapterFactory());
		
		List<Resource> resources = new ArrayList<>();
		for (String nsURI: nsURIs) {
			Resource genModel = resourceSet.loadGenModel(nsURI);
			if (genModel == null) {
				throw new IllegalArgumentException("Gen model not found for NS URI " + nsURI);
			}
			resources.add(genModel);
		}
		List<ViewActionStorable> ret = new ArrayList<>();
		for (Resource resource: resources) {
			for (EObject contents: resource.getContents()) {
				if (contents instanceof GenModel) {
					for (GenPackage genPackage: ((GenModel) contents).getGenPackages()) {
						EPackage ecorePackage = genPackage.getEcorePackage();
						ret.add(EObjectAdaptable.adaptTo(ecorePackage, ViewActionStorable.class));
					}
				}
			}
		}
		return ret;
	}

	@Override
	public Integer call() throws Exception {
		List<ViewActionStorable> storables = loadGenModel();
		
		Set<String> names = new HashSet<>();
		
		try (ProgressMonitor pm = progressMonitorMixin.createProgressMonitor(storables.size())) { 
//			int pos = 0;
//			List<Object> data = new ArrayList<>();
//			for (ViewActionStorable storable: storables) {
//				data.add(storable.store(new URL(getURI().toString()), pm));
//				++pos;
//			}
//			
//			DumperOptions dumperOptions = new DumperOptions();
//			dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
//			dumperOptions.setIndent(4);
//			Yaml yaml = new Yaml(dumperOptions);
//			yaml.dump(data.size() == 1 ? data.get(0) : data, new OutputStreamWriter(outputStream));		
		}
		return 0;
	}
		
}

