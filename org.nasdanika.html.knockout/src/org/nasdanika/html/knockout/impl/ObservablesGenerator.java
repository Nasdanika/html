package org.nasdanika.html.knockout.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.html.knockout.KnockoutBindingsSource.Binding;

class ObservablesGenerator {
	
	Map<String, ObservablesGenerator> children = new LinkedHashMap<>();
	
	List<Binding> bindings = new ArrayList<>();

	private String[] excludes;
	
	private int level;
			
	ObservablesGenerator(String[] excludes) {
		this.excludes = excludes;
	}
	
	private ObservablesGenerator(int level) {
		this(new String[0]);
		this.level = level;
	}	
	
	void addBinding(Binding binding) {
		for (String ex: excludes) {
			if (ex.equals(binding.getName()) || binding.getName().startsWith(ex+".")) {
				return;
			}
		}
		int dotIdx = binding.getName().indexOf(".");
		if (dotIdx == -1) {
			bindings.add(binding);
		} else {
			String childName = binding.getName().substring(0, dotIdx);
			ObservablesGenerator child = children.get(childName);
			if (child == null) {
				child = new ObservablesGenerator(level+1);
				children.put(childName, child);
			}
			child.addBinding(new KnockoutBindingImpl(binding.getName().substring(dotIdx+1), binding.isArray(), binding.getInitialValue()));
		}		
	}
	
	void generateObservables(StringBuilder builder) {
		if (level == 0) {
			for (Binding binding: bindings) {
				builder.append("this.").append(binding.getName()).append(" = ");
				generateObservable(binding, builder);
				builder.append(";").append(System.lineSeparator());
			}
			for (Entry<String, ObservablesGenerator> childEntry: children.entrySet()) {
				builder.append("this.").append(childEntry.getKey()).append(" = ");
				childEntry.getValue().generateObservables(builder);
				builder.append(";").append(System.lineSeparator());
			}
		} else {
			builder.append("{").append(System.lineSeparator());
			for (int bi = 0; bi < bindings.size(); ++bi) {				
				for (int i=0; i<level; ++i) {
					builder.append("\t");
				}
				Binding binding = bindings.get(bi);				
				builder.append(binding.getName()).append(" : ");
				generateObservable(binding, builder);
				if (bi < bindings.size()-1 || !children.isEmpty()) {
					builder.append(",");
				}
				builder.append(System.lineSeparator());
			}
			List<Entry<String, ObservablesGenerator>> entryList = new ArrayList<>(children.entrySet());
			for (int ei = 0; ei < entryList.size(); ++ei) {				
				for (int i=0; i<level; ++i) {
					builder.append("\t");
				}
				Entry<String, ObservablesGenerator> childEntry = entryList.get(ei);
				builder.append(childEntry.getKey()).append(" : ");
				childEntry.getValue().generateObservables(builder);
				if (ei < entryList.size()-1) {
					builder.append(",");
				}
				builder.append(System.lineSeparator());
			}
			for (int i=0; i<level-1; ++i) {
				builder.append("\t");
			}
			builder.append("}");
		}
	}

	private static void generateObservable(Binding binding, StringBuilder builder) {
		builder.append("ko.observable");
		if (binding.isArray()) {
			builder.append("Array");
		}
		builder.append("(");
		if (binding.getInitialValue()!=null) {
			builder.append(binding.getInitialValue());
		}
		builder.append(")");
	}
	
//	public static void main(String[] args) {
//		ObservablesGenerator og = new ObservablesGenerator(new String[] { "kormushka" });
//		og.addBinding(new KnockoutBindingImpl("a", true, "koo"));
//		og.addBinding(new KnockoutBindingImpl("b.c", false, "33"));
//		og.addBinding(new KnockoutBindingImpl("b.d", false, "33"));
//		og.addBinding(new KnockoutBindingImpl("e.f", false, "33"));
//		og.addBinding(new KnockoutBindingImpl("e.g.h", false, "33"));
//		og.addBinding(new KnockoutBindingImpl("e.g.k", false, "33"));
//		StringBuilder sb = new StringBuilder();
//		og.generateObservables(sb);
//		System.out.println(sb);
//	}
}