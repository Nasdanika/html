package org.nasdanika.html.app.impl;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Action with navigation activator and generate() which returns content value. 
 * @author Pavel Vlasov
 *
 */
public class ContentAction extends ActionImpl {
	
	private String content;
	
	public ContentAction() {
		
	}
	
//	public ContentAction(Map<String, Object> data) {
//		super(data);
//		content = (String) data.get("content");
//	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return getContent();
	}

//	@Override
//	protected Action createAction(Map<String, Object> cd) {
//		ContentAction ret = new ContentAction(cd);
//		ret.setParent(this);
//		return ret;
//	}

}
