package org.nasdanika.html.tests.adapters.customer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * This class customizes bank view by providing context actions to be displayed in the footer and 
 * one navigation action for the context customer. 
 * @author Pavel
 *
 */
public class BankViewAction extends EObjectViewAction<Bank> {

	private Customer customer;

	public BankViewAction(Bank bank, Customer customer) {
		super(bank);
		this.customer = customer;
	}
	
	@Override
	public List<? extends Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		if (customer != null) {
			Action customerViewAction = (Action) EcoreUtil.getRegisteredAdapter((EObject) customer, ViewAction.class);
			if (customerViewAction != null) {
				ret.add(customerViewAction); // Shall have navigation role.
			}
		}
		
		// TODO - context actions for the footer.
		ActionImpl contactUs = new ActionImpl();
		contactUs.setText("Contact Us");
		contactUs.setActivator(new NavigationActionActivator() {
			
			@Override
			public String getUrl() {
				return "#";
			}
			
		});
		contactUs.getRoles().add(Action.Role.CONTEXT);
		ret.add(contactUs);
		
		return ret;
	}

}
