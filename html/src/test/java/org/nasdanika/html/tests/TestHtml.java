package org.nasdanika.html.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

public class TestHtml {

	@Test
	public void testTag() {
		Tag tag = HTMLFactory.INSTANCE.tag(TagName.a, "Hello");
		assertThat(tag.toString().trim()).isEqualTo("<a>Hello</a>");
	}
	
	@Test 
	public void testPage() {
		HTMLPage page = HTMLFactory.INSTANCE.page();
		page.prolog("<!-- I'm prolog -->");
		page.head("I'm head");
		page.body("I'm body");
		page.epilog("I'm epilog");
		System.out.println(page);
	}

}
