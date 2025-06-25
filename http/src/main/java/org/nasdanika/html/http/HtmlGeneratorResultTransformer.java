package org.nasdanika.html.http;

import org.nasdanika.common.LoggerProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.producer.HtmlGenerator;
import org.nasdanika.http.ReflectiveHttpServerRouteBuilder.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlGeneratorResultTransformer implements ResultTransformer {
	
	private HtmlGenerator htmlGenerator;

	public HtmlGeneratorResultTransformer(HtmlGenerator htmlGenerator) {
		this.htmlGenerator = htmlGenerator;
	}
	
	private static Logger LOGGER = LoggerFactory.getLogger(HtmlGeneratorResultTransformer.class);
	
	protected ProgressMonitor createProgressMonitor() {
		return new LoggerProgressMonitor(LOGGER);
	}

	@Override
	public boolean canHandle(Object result) {
		return htmlGenerator.canHandle(result);
	}

	@Override
	public Object transform(Object result) {
		try (ProgressMonitor progressMonitor = createProgressMonitor()) {
			return htmlGenerator.createProducer(result, progressMonitor);
		}
	}

}
