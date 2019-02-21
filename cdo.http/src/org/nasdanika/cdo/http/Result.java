package org.nasdanika.cdo.http;

/**
 * Result of request processing
 * @author Pavel
 *
 */
public interface Result {
	
	Object getValue();
	
	/**
	 * If true the transaction shall be rolled back.
	 * @return
	 */
	default boolean isRollback() {
		return false;
	}
	
	/**
	 * Value content type.
	 * @return
	 */
	default String getContentType() {
		return null;
	};

}
