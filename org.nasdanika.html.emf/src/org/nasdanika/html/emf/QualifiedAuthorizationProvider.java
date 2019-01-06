package org.nasdanika.html.emf;

/**
 * Delegates authorization to the target provider and adds a qualifier.
 * @author Pavel Vlasov
 *
 */
public class QualifiedAuthorizationProvider implements AuthorizationProvider {
	
	private AuthorizationProvider target;
	private String qualifier;

	public QualifiedAuthorizationProvider(AuthorizationProvider target, String qualifier) {
		this.target = target;
		this.qualifier = qualifier;
	}

	@Override
	public boolean authorize(String action, String qualifier) {
		if (qualifier == null || qualifier.trim().isEmpty()) {
			return target.authorize(action, this.qualifier);
		}
		if (this.qualifier == null || this.qualifier.trim().isEmpty()) {
			return target.authorize(action, qualifier);
		}
		return target.authorize(action, this.qualifier+"/"+qualifier);
	}

}
