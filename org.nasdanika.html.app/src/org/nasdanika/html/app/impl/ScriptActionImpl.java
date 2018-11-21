package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.ScriptAction;

public class ScriptActionImpl extends ActionImpl implements ScriptAction {
		
	private String code;
	
	public ScriptActionImpl() {
		super();
	}

	public ScriptActionImpl(Map<String, Object> data) {
		super(data);
		setCode((String) data.get("code"));
	}

	@Override
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
