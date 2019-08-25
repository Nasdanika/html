package org.nasdanika.html.ecore;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ResourceLocator;
import org.nasdanika.common.resources.Container;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Generates bare content and toc.xml
 * @author Pavel
 *
 */
public class EcoreHelpGenerator extends EcoreDocumentationGenerator {

	private String tocLabel;
	private String tocLinkTo;
	private String contentPath;

	/**
	 * 
	 * @param title 
	 * @param description
	 * @param resourceLocator
	 * @param tocLabel
	 * @param tocLinkTo
	 * @param contentPath Path to help content relative to the plugin root.
	 */
	public EcoreHelpGenerator(
			String title, 
			String description, 
			ResourceLocator resourceLocator, 
			String tocLabel, 
			String tocLinkTo,
			String contentPath) {
		super(title, description, resourceLocator, true);
		this.tocLabel = tocLabel;
		this.tocLinkTo = tocLinkTo;
		this.contentPath = contentPath;
	}
	
	@Override
	public void generate(Container<Object> resourceConsumer, ProgressMonitor progressMonitor) throws Exception {
		super.generate(resourceConsumer, progressMonitor);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.newDocument();

		Element root = doc.createElement("toc");
		root.setAttribute("label", tocLabel);
		root.setAttribute("link_to", tocLinkTo);
		
		doc.appendChild(root);
		
		root.appendChild(actionTopic(principalAction, doc));
		
	    // Use a Transformer for output
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    
	    DOMSource source = new DOMSource(doc);
	    StringWriter sw = new StringWriter();
	    StreamResult out = new StreamResult(sw);
	    transformer.transform(source, out);
	    sw.close();
	    resourceConsumer.getEntity("toc.xml").setState(sw.toString(), progressMonitor.split("Writing toc.xml", 100));		
	}
	
	private Element actionTopic(Action action, Document document) {
		Element ret = document.createElement("topic");
		ret.setAttribute("href", contentPath + "index.html"+((NavigationActionActivator) action.getActivator()).getUrl());
		ret.setAttribute("label", action.getText());
		for (Action child: action.getNavigationChildren()) {
			ret.appendChild(actionTopic(child, document));
		}
		return ret;		
	}

}
