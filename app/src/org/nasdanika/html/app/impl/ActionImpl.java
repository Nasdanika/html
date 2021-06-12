package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.MutableAction;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Bean action implementation.
 * @author Pavel Vlasov
 *
 */
public class ActionImpl extends LabelImpl implements MutableAction {
	
	private List<Action> children = new ArrayList<>();
	private String confirmation;
	private Action parent;
	private boolean disabled;
//	private boolean floatRight;
	private ActionActivator activator;
	private Label category;
	private Set<String> roles = new HashSet<>();
	private SectionStyle sectionStyle = SectionStyle.AUTO;
	private int sectionColumns = 3;
	
	public ActionImpl() {
		
	}
	
	public ActionImpl(Decorator decorator) {
		super(decorator);
	}

	public Set<String> getRoles() {
		return roles;
	}
	
	@Override
	public boolean isInRole(String role) {
		return getRoles().contains(role);
	}
	
	@Override
	public ActionActivator getActivator() {
		return activator;
	}
	
	@Override
	public void setActivator(ActionActivator activator) {
		this.activator = activator;
	}
	
	@Override
	public Label getCategory() {
		return category;
	}
	
	@Override
	public void setCategory(Label category) {
		this.category = category;
	}
	
//	protected Action createAction(Map<String, Object> cd) {
//		return new ActionImpl(cd);
//	}

	@Override
	public void setParent(Action parent) {
		this.parent = parent;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public String getConfirmation() {
		return confirmation;
	}

//	@Override
//	public boolean isFloatRight() {
//		return floatRight;
//	}

	@Override
	public List<Action> getChildren() {
		return children;
	}

	@Override
	public Action getParent() {
		return parent;
	}

	@Override
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

//	public void setFloatRight(boolean floatRight) {
//		this.floatRight = floatRight;
//	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return null;
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public SectionStyle getSectionStyle() {
		return sectionStyle;
	}
	
	@Override
	public void setSectionStyle(SectionStyle sectionStyle) {
		this.sectionStyle = sectionStyle;
	}
	
	@Override
	public int getSectionColumns() {
		return sectionColumns;
	}
	
	@Override
	public void setSectionColumns(int sectionColumns) {
		this.sectionColumns = sectionColumns;
	}
	
}
