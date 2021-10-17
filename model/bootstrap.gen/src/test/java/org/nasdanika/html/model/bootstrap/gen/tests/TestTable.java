package org.nasdanika.html.model.bootstrap.gen.tests;

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
 * @author Pavel
 *
 */
public class TestTable extends TestBase {
	
	@Test
	public void testSimple() throws Exception {	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/table"));
		Diagnostic generationDiagnostic = generate(
				"table/simple.yml",
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
	
//	@Test
//	public void testFull() throws Exception {	
//		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/modal"));
//		Diagnostic generationDiagnostic = generate(
//				"modal/full.yml",
//				container,
//				diagnostic -> {
//					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
//				});
//		
//		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
//		
//		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
//		
//		BinaryEntity file = container.get("full.html", progressMonitor);
//		assertThat(file).isNotNull();
//		assertThat(file.exists(progressMonitor)).isTrue();
//	}
	
}
