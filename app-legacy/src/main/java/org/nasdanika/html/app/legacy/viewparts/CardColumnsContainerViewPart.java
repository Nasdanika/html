package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Generates card columns container
 * @author Pavel
 *
 */
public class CardColumnsContainerViewPart implements ViewPart {

	private int cardColumns;
	private List<ViewPart> cardViewParts = new ArrayList<>();

	/**
	 * @param cardColumns Number of columns in the container. If less than one
	 * defaults to 3.
	 * @param cardViewParts view parts producing cards.
	 */
	public CardColumnsContainerViewPart(int cardColumns, List<ViewPart> cardViewParts) {
		this.cardColumns = cardColumns;
		if (cardViewParts != null) {
			this.cardViewParts.addAll(cardViewParts);
		}
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Tag ret = viewGenerator.getHTMLFactory().div();
		ret.addClass("card-columns");
		int columnCount = cardColumns;
		if (columnCount > 0 && columnCount != 3) {
			ret.style("column-count", columnCount);
		}

		for (ViewPart cvp: cardViewParts) {
			ret.content(cvp.generate(viewGenerator, progressMonitor));
		}
		
		return ret;
	}
	
	public int getCardColumns() {
		return cardColumns;
	}
	
	public void setCardColumns(int cardColumns) {
		this.cardColumns = cardColumns;
	}
	
	public List<ViewPart> getCardViewParts() {
		return cardViewParts;
	}

}
