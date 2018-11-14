package org.nasdanika.html.emf;

/**
 * An exception for wrapping checked exceptions thrown during rendering.
 * @author Pavel Vlasov
 *
 */
@SuppressWarnings("serial")
public class RenderingException extends RuntimeException {

	public RenderingException() {
	}

	public RenderingException(String message) {
		super(message);
	}

	public RenderingException(Throwable cause) {
		super(cause);
	}

	public RenderingException(String message, Throwable cause) {
		super(message, cause);
	}

}
