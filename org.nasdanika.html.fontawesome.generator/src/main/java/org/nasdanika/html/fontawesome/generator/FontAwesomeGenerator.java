package org.nasdanika.html.fontawesome.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.yaml.snakeyaml.Yaml;


/**
 * Generates Font Awesome enumerations from metadata YAML. 
 *
 * @author Pavel Vlasov
 *
 */
@Mojo(name = "generate-font-awesome", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class FontAwesomeGenerator extends AbstractMojo {
	
	@Parameter(defaultValue = "src-gen", property = "outputDirectory", required = false)
	private File outputDirectory;
	
	@Parameter(property = "metadataDirectory", required = true)
	private File metadataDirectory;
	
	public File getOutputDirectory() {
		return outputDirectory;
	}
	
	public void setOutputDirectory(File outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
		
	public File getMetadataDirectory() {
		return metadataDirectory;
	}
	
	public void setMetadataDirectory(File metadataDir) {
		this.metadataDirectory = metadataDir;
	}
		
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			Yaml yaml = new Yaml();		
			Map<String, Object> categories = yaml.load(new FileInputStream(new File(metadataDirectory, "categories.yml")));
			Map<String, Object> icons = yaml.load(new FileInputStream(new File(metadataDirectory, "icons.yml")));
			
			// TODO - generate Java bindings from metadata
			// Categories with methods to create icons
			// Icon classes with style methods e.g. solid() and regular() returning FontsAwesome instances 
			// E.g. Business.INSTANCE.addressBook().solid();
		} catch (IOException e) {
			throw new MojoExecutionException("Generation failed", e);
		}
	}
	
}
