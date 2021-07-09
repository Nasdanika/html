package org.nasdanika.html.app;

public interface MutableAction extends Action, MutableLabel {
	
	public void setActivator(ActionActivator activator);
	
	public void setCategory(Label category);
	
	public void setParent(Action parent);

	public void setConfirmation(String confirmation);

	public void setSectionStyle(SectionStyle sectionStyle);

	public void setSectionColumns(int sectionColumns);
	
	void setDisabled(boolean disabled);
	
}
