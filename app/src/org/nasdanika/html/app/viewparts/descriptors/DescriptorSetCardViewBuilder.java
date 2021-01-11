package org.nasdanika.html.app.viewparts.descriptors;

import java.util.Map;

import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

/**
 * Creates a {@link Card} from a {@link DescriptorSet}
 * @author Pavel
 *
 */
public class DescriptorSetCardViewBuilder extends DescriptorSetConsumerViewBuilder {
	
	@Override
	public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Card card = bootstrapFactory.card();
		
		Tag hTag = null;
		if (!Util.isBlank(descriptorSet.getLabel())) {
			HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
			hTag = htmlFactory.tag("H3"+Math.min(6, getHeaderLevel()));
			viewGenerator.label(new DescriptorLabel(descriptorSet, null), hTag);
			hTag.addClass("card-header");
		}
		
		Status status = null;
		
		if (diagnose) {
			Diagnostic diagnostic = descriptorSet.diagnose(progressMonitor);
			
			if (listener != null) {
				listener.onDiagnostic(descriptorSet, diagnostic, progressMonitor);
			}
			
			status = diagnostic.getStatus();
		}		
		
		DescriptorLabel label = new DescriptorLabel(descriptorSet, status);

		if (label.getColor() != null) {
			card.border(label.getColor());
			if (hTag != null) {
				TagBootstrapElement wrapped = bootstrapFactory.wrap(hTag);
				wrapped.background(label.getColor());
				wrapped.border(label.getColor());
			}
		}
		card.toHTMLElement().content(hTag);
		
		super.build(card.getBody().toHTMLElement(), viewGenerator, progressMonitor);
		asConsumer(target).accept(card);
	}

	protected int getHeaderLevel() {
		return 3;
	}

	/**
	 * 
	 * @param descriptorSet
	 * @param horizontalLabelWidths
	 * @param diagnose
	 */
	public DescriptorSetCardViewBuilder(
			DescriptorSet descriptorSet, 
			Map<Breakpoint, Size> horizontalLabelWidths, 
			boolean diagnose,
			Listener listener) {
		super(descriptorSet, horizontalLabelWidths, diagnose, listener);
	}
	
	@Override
	protected Object generateLabel(ViewGenerator viewGenerator, Status status, ProgressMonitor progressMonitor) {
		return null; // Using legend
	}
	
	@Override
	protected ViewBuilder createDescriptorSetViewBuilder(DescriptorSet descriptorSet) {
		throw new UnsupportedOperationException();
	}	

}
