package org.nasdanika.html;


public interface FieldSet extends HTMLElement<FieldSet>, FieldContainer<FieldSet> {
	
	FieldSet disabled(boolean disabled);
	
	FieldSet disabled();
	
	Tag legend(Object... content);
	
}