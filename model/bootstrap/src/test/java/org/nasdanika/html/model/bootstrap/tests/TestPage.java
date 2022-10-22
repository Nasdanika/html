package org.nasdanika.html.model.bootstrap.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;
import org.nasdanika.html.model.bootstrap.Page;

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
					Page page = (Page) obj;
					assertThat(page.getName()).isEqualTo("Test");
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
