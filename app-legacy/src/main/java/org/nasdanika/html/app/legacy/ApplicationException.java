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
		this(message);
		this.source = source;
	}

	public ApplicationException(Throwable cause, Object source) {
		this(cause);
		this.source = source;
	}

	public ApplicationException(String message, Throwable cause, Object source) {
		this(message, cause);
		this.source = source;
	}
	
	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}	
	
	@Override
	public String toString() {
		return super.toString() + ", source: "+source;
	}

}
