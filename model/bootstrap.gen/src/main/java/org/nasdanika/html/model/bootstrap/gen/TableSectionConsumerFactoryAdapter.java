package org.nasdanika.html.model.bootstrap.gen;

/**
 * Creates a respective section in the table based on the containment reference - header, body, footer.
 * @author Pavel
 *
 */
public class TableSectionConsumerFactoryAdapter<M extends org.nasdanika.html.model.bootstrap.TableSection, T extends org.nasdanika.html.bootstrap.RowContainer<?,?>> extends TableRowContainerConsumerFactoryAdapter<M,T> {

	public TableSectionConsumerFactoryAdapter(M tableSection) {
		super(tableSection);
	}
	
}
