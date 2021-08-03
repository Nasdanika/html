package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;

/**
 * Label with path. 
 * @author Pavel
 *
 */
public class Category extends LabelImpl {
	
	private List<Action> actions;

	public Category(Decorator decorator, List<Action> actions) {
		super(decorator);
		this.actions = actions;
	}
	
	private String path;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public List<Action> getActions() {
		return actions;
	}

}
