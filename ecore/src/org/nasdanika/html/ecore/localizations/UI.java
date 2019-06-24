package org.nasdanika.html.ecore.localizations;

import org.nasdanika.common.ResourceLocator;

/**
 * Holds built-in localizations
 * @author Pavel
 *
 */
public enum UI implements ResourceLocator {
	
	RU() {

		@Override
		public Object get(String key) {
			switch (key) {
			case "ui/summary":
				return "Краткое описание";
			case "ui/name":
				return "Наименование";
			case "ui/type":
				return "Тип";
			case "ui/cardinality":
				return "Мощность";
			case "ui/package":
				return "Пакет";
			case "ui/supertypes":
				return "Супертипы";
			case "ui/subtypes":
				return "Подтипы";						
			case "ui/diagram":
				return "Диаграмма";						
			case "ui/contents":
				return "Содержание";						
			case "ui/namespace-uri":
				return "URI пространства имён";						
			case "ui/opposite":
				return "Противоположная сссылка";						
			case "ui/abstract":
				return "Абстрактный";						
			case "ui/interface":
				return "Интерфейс";						
			default:
				return null;
			}
		}
		
	};
	

	@Override
	public <T> T get(Class<T> type) {
		return null;
	}

}
