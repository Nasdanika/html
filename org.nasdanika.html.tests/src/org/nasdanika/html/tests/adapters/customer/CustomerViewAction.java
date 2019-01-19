package org.nasdanika.html.tests.adapters.customer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * This class customizes the default view by showing accounts as navigation actions. 
 * In the banking model accounts belong to (contained by) the bank, but from the customer point of view
 * accounts belong to them.
 * 
 * This class customizes the content view by displaying a table of customer accounts with balances.
 * 
 * It also adds context actions such as bill pay and transfer funds. 
 * @author Pavel
 *
 */
public class CustomerViewAction extends EObjectViewAction<Customer> {

	public CustomerViewAction(Customer value) {
		super(value);
	}
		
	@Override
	public String getIcon() {
		// TODO - replace with something like FontAwesome.Literals.UserCircle.solid() once the fontawesome literals generator is available
		return "fas fa-user-circle"; 
	}
	
	@Override
	public List<? extends Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		value.getAccounts().stream()
			.map(account -> (Action) EcoreUtil.getRegisteredAdapter(account, ViewAction.class))
			.filter(a -> a != null)
			.forEach(ret::add);
		
		return ret;
	}
	
	// TODO context children - bill pay, transfer funds.
	
	// TODO - content panel (execute) - a table of accounts with balances.

}
