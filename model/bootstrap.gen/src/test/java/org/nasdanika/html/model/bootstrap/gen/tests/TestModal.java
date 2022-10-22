package org.nasdanika.html.model.bootstrap.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.FileSystemContainer;

/**
 * @author Pavel
 *
 */
public class TestModal extends TestBase {
	
	@Test
	public void testSimple() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/modal"));
		Diagnostic generationDiagnostic = generate(
				"modal/simple.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntity file = container.get("simple.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
	}
	
	@Test
	public void testFull() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/modal"));
		Diagnostic generationDiagnostic = generate(
				"modal/full.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntity file = container.get("full.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
	}
	
}
