package org.nasdanika.html.app;

/**
 * Exception for wrapping lower-level exceptions.
 * @author Pavel Vlasov
 *
 */
public class ApplicationException extends RuntimeException {
	
	Object source;
	
	public Object getSource() {
		return source;
	}

	public ApplicationException(String message, Object source) {
		super(message);
		this.source = source;
	}

	public ApplicationException(Throwable cause, Object source) {
		super(cause);
		this.source = source;
	}

	public ApplicationException(String message, Throwable cause, Object source) {
		super(message, cause);
		this.source = source;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", source: "+source;
	}

}
