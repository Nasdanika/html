package org.nasdanika.html.impl;

enum KnockoutBindingInfo {
	
	ATTR("attr"),
	CHECKED("checked", false, true, false),
	CLICK("click"),
	COMPONENT("component"),
	CSS("css"),
	DISABLE("disable", false, true, false),
	ENABLE("enable", false, true, false),
	EVENT("event"),
	FOREACH("foreach", true, true, true),
	HAS_FOCUS("hasFocus", false, true, false),
	HTML("html", false, true, false),
	IF("if", false, true, false),
	IFNOT("ifnot", false, true, false),
	OPTIONS("options", true, true, false),
	SELECTED_OPTIONS("selectedOptions", true, true, false),
	STYLE("style"),
	SUBMIT("submit"),
	TEMPLATE("template"),
	TEXT("text", false, true, false),
	TEXT_INPUT("textInput", false, true, false),
	UNIQUE_NAME("uniqueName"),
	VALUE("value", false, true, false),
	VISIBLE("visible", false, true, false),
	WITH("with");	

	static boolean isArray(String name) {
		for (KnockoutBindingInfo kbi: KnockoutBindingInfo.values()) {
			if (kbi.name.equals(name)) {
				return kbi.isArray;
			}
		}
		return false;
	}
	
	static boolean isInitialValue(String name) {
		for (KnockoutBindingInfo kbi: KnockoutBindingInfo.values()) {
			if (kbi.name.equals(name)) {
				return kbi.isInitialValue;
			}
		}
		return false;
	}
	
	static boolean isNewScope(String name) {
		for (KnockoutBindingInfo kbi: KnockoutBindingInfo.values()) {
			if (kbi.name.equals(name)) {
				return kbi.isNewScope;
			}
		}
		return false;
	}
	
	static boolean isObservable(String name) {
		for (KnockoutBindingInfo kbi: KnockoutBindingInfo.values()) {
			if (kbi.name.equals(name)) {
				return kbi.isObservable;
			}
		}
		return false;
	}
	
	/**
	 * True if binding shall be an observable array, e.g. <code>foreach</code>
	 */
	final boolean isArray;
	
	/**
	 * True if binding accepts initial value.
	 */
	final boolean isInitialValue;	
	
	/**
	 * True if binding introduces new scope and as such nested elements shall not be included into
	 * the bindings list.
	 */
	final boolean isNewScope;
	
	/**
	 * True if binding binds to an observable or observable array (if isArray is true) and an observable shall be generated for this binding.
	 * E.g. <code>value</code> or <code>text</code> bind to an observable, but <code>click</code> binds to a function. 
	 */
	final boolean isObservable;
	
	/**
	 * Binding name
	 */
	final String name;
	
	/**
	 * Constructor for non-observable bindings.
	 * @param name
	 * @param isObservable
	 * @param isArray
	 * @param isInitialValue
	 * @param isNewScope
	 */
	private KnockoutBindingInfo(String name) {		
		this.name = name;
		this.isObservable = false;
		this.isArray = false;
		this.isInitialValue = false;
		this.isNewScope = false;
	}
	
	/**
	 * Constructor for observable bindings.
	 * @param name
	 * @param isArray
	 * @param isInitialValue
	 * @param isNewScope
	 */
	private KnockoutBindingInfo(
			String name,
			boolean isArray,
			boolean isInitialValue,
			boolean isNewScope) {
		
		this.name = name;
		this.isObservable = true;
		this.isArray = isArray;
		this.isInitialValue = isInitialValue;
		this.isNewScope = isNewScope;
	}
	
	
}
