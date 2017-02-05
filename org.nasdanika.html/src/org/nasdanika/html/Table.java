package org.nasdanika.html;

public interface Table extends UIElement<Table>, RowContainer<Table> {
	
	Table bordered(boolean bordered);
	
	Table hover(boolean hover);
	
	Table striped(boolean striped);
	
	Table condensed(boolean condensed);
	
	Table responsive(boolean responsive);

	Table bordered();
	
	Table hover();
	
	Table striped();
	
	Table condensed();
	
	Table responsive();

	RowContainer<?> header();

	RowContainer<?> body();
	
	RowContainer<?> footer();
	
	/**
	 * Creates ``col`` tag in the ``colgroup``.
	 * @return
	 */
	Tag col();
	
}
