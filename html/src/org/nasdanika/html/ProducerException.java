package org.nasdanika.html;

/**
 * Wrapper for exceptions thrown during HTML generation.
 * @author Pavel Vlasov
 *
 */
@SuppressWarnings("serial")
public class ProducerException extends RuntimeException {

	public ProducerException(String message) {
		super(message);
	}

	public ProducerException(Throwable cause) {
		super(cause);
	}

	public ProducerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProducerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
