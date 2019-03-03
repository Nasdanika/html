package org.nasdanika.cdo.http;

/**
 * Exception indicating authentication failure and used to carry failure detail.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

}
