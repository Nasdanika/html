package org.nasdanika.html.tests.adapters.customer;

import org.nasdanika.bank.Customer;
import org.nasdanika.html.emf.EObjectViewAction;

/**
 * This class customizes  
 * @author Pavel
 *
 */
public class CustomerViewAction extends EObjectViewAction<Customer> {

	public CustomerViewAction(Customer value) {
		super(value);
	}

}
