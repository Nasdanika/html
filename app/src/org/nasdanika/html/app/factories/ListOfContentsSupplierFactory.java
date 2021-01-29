package org.nasdanika.html.app.factories;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.app.viewparts.ListOfContentsViewPart;

public class ListOfContentsSupplierFactory extends TableOfContentsBaseSupplierFactory<ListOfContentsViewPart> {
	
	protected SupplierFactoryFeature<OrderedListType> ordering;	
	
	public ListOfContentsSupplierFactory() {
		FunctionFactory<String, OrderedListType> orderedListTypeFactory = context -> Function.fromFunction(str -> str == null ? null : OrderedListType.valueOf(str.toUpperCase().replace("-", "_")), "Ordered list type from lower kebab case", 1);
		ordering = addFeature(new FunctionSupplierFactoryAttribute<String,OrderedListType>(new StringSupplierFactoryAttribute(new Attribute<String>("ordering", false, false, "rotate", null), true), orderedListTypeFactory));		
	}
	
	@Override
	protected ListOfContentsViewPart createTableOfContents(Context context, Map<Object, Object> data) throws Exception {
		return new ListOfContentsViewPart(				
				(String) role.get(data), 
				(String) header.get(data), 
				(Boolean) tooltip.get(data), 
				(Integer) depth.get(data), 
				(OrderedListType) ordering.get(data));
	}

}
