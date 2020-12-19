package org.nasdanika.html.app.viewparts.descriptors;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

public class DescriptorLabel implements Label {
	
	private Descriptor descriptor;
	private Status status;

	public DescriptorLabel(Descriptor descriptor, Status status) {
		this.descriptor = descriptor;
		this.status = status;
	}

	@Override
	public Object getId() {
		return null;
	}

	@Override
	public String getIcon() {
		return descriptor.getIcon();
	}

	@Override
	public String getText() {
		return StringEscapeUtils.escapeHtml4(descriptor.getLabel());
	}

	@Override
	public String getTooltip() {
		return Util.firstSentence(getDescription(), 50, 250);
	}

	@Override
	public Color getColor() {
		if (status != null) {
			switch (status) {
			case CANCEL:
				return Color.MUTED;
			case ERROR:
				return Color.DANGER;
			case INFO:
				return Color.INFO;
			case SUCCESS:
				return Color.SUCCESS;
			case WARNING:
				return Color.WARNING;
			default:
				return null;
			}
		}
		return null;
	}

	@Override
	public boolean isOutline() {
		return false;
	}

	@Override
	public String getDescription() {
		return StringEscapeUtils.escapeHtml4(descriptor.getDescription());
	}

	@Override
	public String getNotification() {
		return null;
	}

}
