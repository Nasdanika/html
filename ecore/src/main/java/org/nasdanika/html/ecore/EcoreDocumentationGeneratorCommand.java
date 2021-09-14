package org.nasdanika.html.ecore;

import java.io.File;
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
import org.nasdanika.cli.CommandBase;
import org.nasdanika.cli.ProgressMonitorMixin;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Generates Ecore model documentation as an action model",
		name = "ecore")
public class EcoreDocumentationGeneratorCommand extends CommandBase {
	
	static final String JAVADOC_CONTEXT_BUILDER_MOUNT = "javadoc-context-builder-mount";

	@Parameters(
			paramLabel = "URI", 
			arity = "1..*",
			description = "A list of gen-model URI's to generate documentation for. "
					+ "For each URI a YAML actions file is generated named after the root package in the model. "
					+ "If there are duplicate package names then argument index is added to de-dup the names. ")
	protected List<String> URIs = new ArrayList<>();	
	
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
	protected EcoreActionSupplierAdapterFactory createAdapterFactory() {
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
		
		return new EcoreActionSupplierAdapterFactory(context, useEPackageNameInPath ? this::getEPackagePath : null);
	}
	
	public List<ActionSupplier> loadGenModel() {
		resourceSet = new GenModelResourceSet();		
		resourceSet.getAdapterFactories().add(createAdapterFactory());
		
		List<Resource> resources = new ArrayList<>();
		for (String uri: URIs) {
			Resource genModel = resourceSet.getResource(URI.createURI(uri), true);
			if (genModel == null) {
				throw new IllegalArgumentException("Gen model not found: " + uri);
			}
			resources.add(genModel);
		}
		List<ActionSupplier> ret = new ArrayList<>();
		for (Resource resource: resources) {
			for (EObject contents: resource.getContents()) {
				if (contents instanceof GenModel) {
					for (GenPackage genPackage: ((GenModel) contents).getGenPackages()) {
						EPackage ecorePackage = genPackage.getEcorePackage();
						ret.add(EObjectAdaptable.adaptTo(ecorePackage, ActionSupplier.class));
					}
				}
			}
		}
		return ret;
	}

	@Override
	public Integer call() throws Exception {
		List<ActionSupplier> suppliers = loadGenModel();
		
		Set<String> names = new HashSet<>();
		
		try (ProgressMonitor pm = progressMonitorMixin.createProgressMonitor(suppliers.size())) { 
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

