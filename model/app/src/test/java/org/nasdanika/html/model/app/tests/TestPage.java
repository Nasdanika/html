package org.nasdanika.html.model.app.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestPage extends TestBase {
	
	@Test
	public void test() throws Exception {	
		load(
				"page.yml", 
				obj -> {
					org.nasdanika.html.model.bootstrap.Page page = (org.nasdanika.html.model.bootstrap.Page) obj;
					assertThat(page.getBody()).hasSize(1);
					org.nasdanika.html.model.app.Page appPage = (org.nasdanika.html.model.app.Page) page.getBody().get(0);
					assertThat(appPage.getHeader()).isNotNull();
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
