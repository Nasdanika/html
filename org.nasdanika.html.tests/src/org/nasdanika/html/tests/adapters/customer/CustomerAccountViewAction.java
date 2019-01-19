package org.nasdanika.html.tests.adapters.customer;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.bank.Account;
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * Returns context customer view action as its parent.
 * @author Pavel
 *
 */
public class CustomerAccountViewAction extends EObjectViewAction<Account> {

	private Supplier<Customer> customerSupplier;

	public CustomerAccountViewAction(Account value, Supplier<Customer> customerSupplier) {
		super(value);
		this.customerSupplier = customerSupplier;
	}
		
	@Override
	public String getIcon() {
		// TODO - replace with something like FontAwesome.Literals.CreditCard.regular() once the fontawesome literals generator is available
		return "far fa-credit-card"; 
	}

	@Override
	public Action getParent() {
		return (Action) EcoreUtil.getRegisteredAdapter(customerSupplier.get(), ViewAction.class);
	}
	
	/**
	 * No need in showing account category because it is the only one.
	 */
	@Override
	public Label getCategory() {
		return null;
	}
	
	@Override
	public List<? extends Action> getChildren() {
		// No navigation children, add context and section children as needed.
		return Collections.emptyList();
	}
	
	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		// TODO Auto-generated method stub
		return "BEBE: " + getParent().getText();
	}
	
}
