package org.nasdanika.html.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

public class TestHtml {

	@Test
	public void testTag() {
		Tag tag = HTMLFactory.INSTANCE.tag(TagName.a, "Hello");
		assertThat(tag.toString().trim()).isEqualTo("<a>Hello</a>");
	}

}
