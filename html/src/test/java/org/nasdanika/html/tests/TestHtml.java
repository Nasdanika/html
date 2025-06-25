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
	
	@Test
	public void testTagAsync() {
		Tag tag = HTMLFactory.INSTANCE.tag(TagName.a, "Hello");
		Object tagVal = tag.produceAsync(0).block();
		assertThat(tagVal instanceof String).isTrue();
		assertThat(((String) tagVal).trim()).isEqualTo("<a>Hello</a>");			
	}

	@Test
	public void testTagWithNullContentAsync() {
		Tag tag = HTMLFactory.INSTANCE.tag(TagName.a, "Hello");
		String nullStr = null;
		tag.content(nullStr);
		Object tagVal = tag.produceAsync(0).block();
		assertThat(tagVal instanceof String).isTrue();
		assertThat(((String) tagVal).trim()).isEqualTo("<a>Hello</a>");			
	}	
	
	@Test 
	public void testPageAsync() {
		HTMLPage page = HTMLFactory.INSTANCE.page();
//		page.prolog("<!-- I'm prolog -->");
		page.head("I'm head");
		page.body("I'm body");
//		page.epilog("I'm epilog");
		String result = page.produceAsync(0).block();
		System.out.println(result);
	}

}
