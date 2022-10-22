package org.nasdanika.html.model.html.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Status;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.html.Tag;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestTag extends TestBase {
	
	@Test
	public void testReferences() throws Exception {	
		load(
				"tag.yml", 
				obj -> {
					Tag tag = (Tag) obj;
					assertThat(tag.getName()).isEqualTo("h1");
					assertThat(tag.getContent())
						.hasSize(1)
						.first()
							.isInstanceOf(Text.class);
					
					assertThat(tag.getAttributes()).hasSize(2);
					assertThat(tag.getAttributes().get("class")).isInstanceOf(Text.class);
					
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
