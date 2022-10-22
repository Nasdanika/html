package org.nasdanika.html.model.bootstrap.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.FileSystemContainer;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestAppearance extends TestBase {
	
	private static final String H1_HELLO = "<h1 class=\"nsd-test\">Hello</h1>";

	@Test
	public void testFile() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/appearance"));
		Diagnostic generationDiagnostic = generate(
				"appearance-file.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("appearance.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor))).contains(H1_HELLO);
	}
	
}
