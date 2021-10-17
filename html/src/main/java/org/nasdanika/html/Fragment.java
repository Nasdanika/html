package org.nasdanika.html;

import java.util.List;

/**
 * Fragments are used for grouping of elements. They do not produce their own HTML, only HTML produced by their content.
 * @author Pavel Vlasov
 *
 */
public interface Fragment extends Container<Fragment>, Producer {
	
	boolean isEmpty();
		
	List<Object> getContent();
	
	/**
	 * 
	 * @return Factory used to create this fragment.
	 */
	HTMLFactory getFactory();	
	
}
