package org.nasdanika.html.model.app.graph.drawio;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.ChildProcessor;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class NodeProcessor extends LayerElementProcessor<Node> {
		
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";	
	
	public NodeProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}
	
	@ChildProcessor(info = true)
	public void addChildInfo(ProcessorInfo<WidgetFactory> childInfo) {
		childInfos.put((ModelElement) childInfo.getElement(), childInfo);
	}
	
	@OutgoingEndpoint
	public void addOutgoingEndpoints(Connection connection, ConnectionProcessor connectionProcessor) {
		outgoingEndpoints.put(connection, CompletableFuture.completedStage(connectionProcessor).toCompletableFuture());
	}

	@ProcessorElement
	@Override
	public void setElement(Node element) {
		super.setElement(element);
		uri = URI.createURI(element.getId() + "/index.html");
	}
	
	@Override
	public void configureLabel(Label label, ProgressMonitor progressMonitor) {
		super.configureLabel(label, progressMonitor);
		
		Map<String, String> style = element.getStyle();
		String image = style.get("image");
		if (!Util.isBlank(image)) {
			// Drawio does not add ;base64 to the image URL, browsers don't understand. Fixing it here.
			if (image.startsWith(DATA_URI_PNG_PREFIX_NO_BASE_64)) {
				int insertIdx = DATA_URI_PNG_PREFIX_NO_BASE_64.length() - 1;
				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
			} else if (image.startsWith(DATA_URI_JPEG_PREFIX_NO_BASE_64)) {
				int insertIdx = DATA_URI_JPEG_PREFIX_NO_BASE_64.length() - 1;
				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
			} else {
				URI imageURI = URI.createURI(image);
				if (imageURI.isRelative()) {
					URI appBase = factory.getAppBase();
					if (appBase != null && !appBase.isRelative()) {
						imageURI = imageURI.resolve(appBase);
					}
				}
				image = imageURI.toString();				
			}
			
			try {
				label.setIcon(Util.scaleImage(factory.rewriteImage(image, progressMonitor), factory.getIconSize()));
			} catch (IOException e) {
				progressMonitor.worked(Status.WARNING, 1, "Could not scale image: " + e, e);
			}
		}
	}
	
}
