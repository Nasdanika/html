package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestPage extends TestBase {
		
	@Test
	public void testSimple() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/page"));
		Diagnostic generationDiagnostic = generate(
				"page/simple.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("simple.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
	}
	
	@Test
	public void testItems() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/page"));
		Diagnostic generationDiagnostic = generate(
				"page/items.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntity file = container.get("items.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
	}
	
	@Test
	public void testNavPanelCards() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/page"));
		Diagnostic generationDiagnostic = generate(
				"page/nav-panel-cards.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntity file = container.get("nav-panel-cards.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
	}
	
}
