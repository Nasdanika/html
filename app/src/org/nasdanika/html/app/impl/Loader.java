package org.nasdanika.html.app.impl;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.CompoundCommandFactory;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.content.Base64;
import org.nasdanika.exec.content.Form;
import org.nasdanika.exec.content.FreeMarker;
import org.nasdanika.exec.content.HttpCall;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Json;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Mustache;
import org.nasdanika.exec.content.Replace;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.ScriptEvaluator;
import org.nasdanika.exec.content.Yaml;
import org.nasdanika.exec.content.ZipArchive;
import org.nasdanika.exec.java.Annotation;
import org.nasdanika.exec.java.CompilationUnit;
import org.nasdanika.exec.java.Constructor;
import org.nasdanika.exec.java.Field;
import org.nasdanika.exec.java.Interface;
import org.nasdanika.exec.java.Method;
import org.nasdanika.exec.java.SourceFolder;
import org.nasdanika.exec.java.TypeAdapter;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.Git;
import org.nasdanika.exec.resources.ZipResourceCollection;

/**
 * Loader of labels and actions. 
 * @author Pavel
 *
 */
public class Loader implements ObjectLoader {
	
	private org.nasdanika.common.ObjectLoader chain;

	public Loader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public Loader() {	}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			// General
			case "label":
				return new LabelFactory(loader, config, base, subMonitor, marker);
//			case "action":
//				return new ActionImpl(loader, config, base, subMonitor, marker);
				
			// TODO - application	
			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}
		
}
