package org.nasdanika.html.model.app.gen.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TestResources {
	
	@Test
	public void testSearchResource() {
		assertNotNull(getClass().getClassLoader().getResource("org/nasdanika/html/model/app/gen/search.html"));
	}

}
