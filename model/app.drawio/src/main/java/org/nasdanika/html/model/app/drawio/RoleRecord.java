package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.processor.ProcessorConfig;

public record RoleRecord(ProcessorConfig config, ElementProcessor processor, EReference role) {

}
