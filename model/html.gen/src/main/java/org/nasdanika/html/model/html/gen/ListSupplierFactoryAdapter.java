package org.nasdanika.html.model.html.gen;

import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Tag;
import org.nasdanika.ncore.EObjectProperty;
import org.nasdanika.ncore.Property;

public class ListSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {
	
	protected ListSupplierFactoryAdapter(org.nasdanika.ncore.List list) {
		setTarget(list);
	}

	@Override
	public Supplier<InputStream> create(Context ctx) {
		SupplierFactory<JSONArray> jsf = createJsonArraySupplierFactory((org.nasdanika.ncore.List) getTarget());
		Function<Object, String> toStringFunction = Function.fromFunction(Object::toString, "toString()", 1);
		return jsf.create(ctx).then(toStringFunction).then(Util.TO_STREAM.create(ctx));
	}
	
	/**
	 * Creates {@link SupplierFactory} of {@link JSONArray} from a list.
	 * The factory is aware of model classes in Ncore package. Model elements
	 * of unknown classes are adapted to SupplierFactory.  
	 * @return
	 */
	public static SupplierFactory<JSONArray> createJsonArraySupplierFactory(org.nasdanika.ncore.List list) {
		ListCompoundSupplierFactory<Object> elementsFactory = new ListCompoundSupplierFactory<>("Elements");
		for (EObject element: list.getValue()) {
			if (element instanceof org.nasdanika.ncore.List) {
				elementsFactory.add(createJsonArraySupplierFactory((org.nasdanika.ncore.List) element));				
			} else if (element instanceof org.nasdanika.ncore.Map) {
				elementsFactory.add(createJsonObjectSupplierFactory((org.nasdanika.ncore.Map) element));
			} else if (element instanceof org.nasdanika.ncore.Boolean) {
				elementsFactory.add(SupplierFactory.from(((org.nasdanika.ncore.Boolean) element).getValue(), "Boolean"));
			} else if (element instanceof org.nasdanika.ncore.Integer) {
				elementsFactory.add(SupplierFactory.from(((org.nasdanika.ncore.Integer) element).getValue(), "Integer"));
			} else if (element instanceof org.nasdanika.ncore.String) {
				elementsFactory.add(SupplierFactory.from(((org.nasdanika.ncore.String) element).getValue(), "String"));
			} else {
				elementsFactory.add(adaptToSupplierFactory(element));
			}
		}

		return elementsFactory.then(ListSupplierFactoryAdapter::createListToJsonArrayFunction);
	}
	
	/**
	 * Attempts to adapt to {@link Tag} factory first using {@link SupplierFactory.Provider}. Then to InputStream factory and chains with toString factory. 
	 * @param obj
	 * @return
	 */
	private static SupplierFactory<? extends Object> adaptToSupplierFactory(EObject obj) {
		Provider provider = EObjectAdaptable.adaptTo(obj, Provider.class);
		if (provider != null) {
			SupplierFactory<Tag> tagFactory = provider.getFactory(Tag.class);
			if (tagFactory != null) {
				return tagFactory;
			}
			
			SupplierFactory<InputStream> streamFactory = provider.getFactory(InputStream.class);
			if (streamFactory != null) {
				return streamFactory.then(Util.TO_STRING);
			}
		}
		
		
		return EObjectAdaptable.adaptToSupplierFactoryNonNull(obj, Object.class).then(Util.OBJECT_TO_STRING_FACTORY);
	}

	private static Function<java.util.List<Object>, JSONArray> createListToJsonArrayFunction(Context context) {
		return Function.fromFunction(JSONArray::new, "List to JSON array", 1);
	}

	private static Function<java.util.Map<String, Object>, JSONObject> createMapToJsonObjectFunction(Context context) {
		java.util.function.Function<Map<String, Object>, JSONObject> func = map -> {
			return new JSONObject(map);
		};
		return Function.fromFunction(func, "Map to JSON Object", 1);
	}

	/**
	 * Creates {@link SupplierFactory} of {@link JSONObject} from a map.
	 * The factory is aware of model classes in Ncore package. Model elements
	 * of unknown classes are adapted to SupplierFactory.  
	 * @return
	 */
	public static SupplierFactory<JSONObject> createJsonObjectSupplierFactory(org.nasdanika.ncore.Map map) {
		MapCompoundSupplierFactory<String,Object> entryFactory = new MapCompoundSupplierFactory<>("Entries");
		for (Property element: map.getValue()) {
			if (element instanceof org.nasdanika.ncore.ListProperty) {
				entryFactory.put(element.getName(), createJsonArraySupplierFactory((org.nasdanika.ncore.List) element));				
			} else if (element instanceof org.nasdanika.ncore.MapProperty) {
				entryFactory.put(element.getName(),createJsonObjectSupplierFactory((org.nasdanika.ncore.Map) element));
			} else if (element instanceof org.nasdanika.ncore.BooleanProperty) {
				entryFactory.put(element.getName(), SupplierFactory.from(((org.nasdanika.ncore.Boolean) element).getValue(), "Boolean"));
			} else if (element instanceof org.nasdanika.ncore.IntegerProperty) {
				entryFactory.put(element.getName(), SupplierFactory.from(((org.nasdanika.ncore.Integer) element).getValue(), "Integer"));
			} else if (element instanceof org.nasdanika.ncore.StringProperty) {
				entryFactory.put(element.getName(), SupplierFactory.from(((org.nasdanika.ncore.String) element).getValue(), "String"));
			} else if (element instanceof EObjectProperty) {
				entryFactory.put(element.getName(), adaptToSupplierFactory(((EObjectProperty) element).getValue()));
			} else {
				throw new UnsupportedOperationException("Unsupported property type: " + element);
			}
		}

		return entryFactory.then(ListSupplierFactoryAdapter::createMapToJsonObjectFunction);
	}
//
//	/**
//	 * Creates {@link SupplierFactory} of {@link BiSupplier} from a property.
//	 * The factory is aware of model classes in Ncore package. Model elements
//	 * of unknown classes are adapted to SupplierFactory assuming that the result is InputStream.  
//	 * @return
//	 */
//	public static SupplierFactory<BiSupplier<String, Object>> createPropertySupplierFactory(org.nasdanika.ncore.Property property) {
//		throw new UnsupportedOperationException();
//	}

}

