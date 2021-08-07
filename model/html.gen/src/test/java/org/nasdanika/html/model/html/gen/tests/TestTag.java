package org.nasdanika.html.model.html.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Status;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestTag extends TestBase {
	
	@Test
	public void test() throws Exception {	
		Object obj = load(
				"tag.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(org.nasdanika.html.Tag.class);
		org.nasdanika.html.Tag tag = (org.nasdanika.html.Tag) obj;
		assertThat(tag.toString().trim()).isEqualTo("<h1 class=\"row\">Hello</h1>");		
	}
	
}
