package org.nasdanika.html.app.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ApplicationRenderer;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

public class ApplicationRendererImpl implements ApplicationRenderer {
	
	protected JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;

	@Override
	public Tag link(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tag badge(Action action, boolean isPill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button<org.nasdanika.html.Button> button(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup buttonGroup(Iterable<Action> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup buttonGroup(Action... action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button<org.nasdanika.html.Button> iconButton(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup iconButtonGroup(Iterable<Action> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup iconButtonGroup(Action... action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsTreeNode jsTreeNode(Action action, boolean ajax) {
		JsTreeNode ret = jsTreeFactory.jsTreeNode();
		
		ret.icon(action.getIcon());
		ret.text(action.getText());
		ret.anchorAttribute("title", action.getTooltip());
		ret.id(action.getId());
		ret.disabled(action.isDisabled());
		if (ajax) {
			if (!action.getChildren().isEmpty()) {
				ret.hasChildren();
			}
		} else {
			for (Action child: action.getChildren()) {
				ret.children().add(jsTreeNode(child, ajax));
			}
		}
		
		return ret;
	}

}
