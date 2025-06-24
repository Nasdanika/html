import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.producer.factories.core.CoreProducerFactoryCapabilityFactory;

module org.nasdanika.html.producer {
		
	requires transitive org.nasdanika.html;
	requires transitive org.nasdanika.exec;
	requires org.apache.commons.text;
	
	exports org.nasdanika.html.producer;
	exports org.nasdanika.html.producer.factories.core;
	
	opens org.nasdanika.html.producer.factories.core;
	
	provides CapabilityFactory with CoreProducerFactoryCapabilityFactory;
	
}
