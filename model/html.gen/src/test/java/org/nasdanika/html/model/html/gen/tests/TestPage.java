package org.nasdanika.html.model.html.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.html.HTMLPage;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestPage extends TestBase {
	
	private static final String H1_HELLO = "<h1 class=\"row\">Hello</h1>";

	@Test
	public void test() throws Exception {	
		Object obj = load(
				"page.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(HTMLPage.class);
		HTMLPage page = (HTMLPage) obj;
		assertThat(page.toString()).contains(H1_HELLO);		
		assertThat(page.toString()).contains(H1_HELLO);		
	}
	
	@Test
	public void testFile() throws Exception {	
		BinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		Diagnostic generationDiagnostic = generate(
				"page-file.yml",
				container,
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(generationDiagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
		assertThat(myContainer).isNotNull();
		assertThat(myContainer.exists(progressMonitor)).isTrue();
		
		BinaryEntity file = myContainer.get("my-page.html", progressMonitor);
		assertThat(file).isNotNull();
		assertThat(file.exists(progressMonitor)).isTrue();
		assertThat(DefaultConverter.INSTANCE.toString(file.getState(progressMonitor))).contains(H1_HELLO);
	}
	
}
