package org.nasdanika.html.bootstrap;

public interface Table extends RowContainer<org.nasdanika.html.Table> {
		
	interface TableHeader extends RowContainer<org.nasdanika.html.Table.TableHeader> {

		TableHeader dark();
		
		TableHeader dark(boolean dark);
		
		TableHeader light();
		
		TableHeader light(boolean light);				
		
	}
	
	interface TableBody extends RowContainer<org.nasdanika.html.Table.TableBody> {
		
	}

	interface TableFooter extends RowContainer<org.nasdanika.html.Table.TableFooter> {
		
	}	
	
	TableHeader header();
		
	TableBody body();
	
	TableFooter footer();	
	
	Table dark();
	
	Table dark(boolean dark);
	
	Table striped();
	
	Table striped(boolean striped);
	
	Table bordered();
	
	Table bordered(boolean bordered);
	
	Table borderless();
	
	Table borderless(boolean borderless);

	Table hover();
	
	Table hover(boolean hover);
	
	Table small();
	
	Table small(boolean small);

}
