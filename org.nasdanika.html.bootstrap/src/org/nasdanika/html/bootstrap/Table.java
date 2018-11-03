package org.nasdanika.html.bootstrap;

/**
 * Bootstrap table.
 * @author Pavel Vlasov
 *
 */
public interface Table extends BootstrapElement<org.nasdanika.html.Table> {
	
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
}
