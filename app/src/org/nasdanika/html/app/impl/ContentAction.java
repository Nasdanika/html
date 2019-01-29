package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Action with navigation activator and execute() which returns content value. 
 * @author Pavel Vlasov
 *
 */
public class ContentAction extends ActionImpl {
	
	private String content;
	
	public ContentAction() {
		
	}
	
	public ContentAction(Map<String, Object> data) {
		super(data);
		content = (String) data.get("content");
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		if (getContent() != null) {
			map.put("content", getContent());
		}
		return map;
	}

	@Override
	public Object execute(ViewGenerator viewGenerator) {
		return getContent();
	}

	@Override
	protected Action createAction(Map<String, Object> cd) {
		ContentAction ret = new ContentAction(cd);
		ret.setParent(this);
		return ret;
	}

}
