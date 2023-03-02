package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.Property;

/**
 * A wrapper interface for semantic-element annotation
 * @author Pavel
 *
 */
public interface SemanticElementAnnotation {
	
	String KEY = "semantic-element";	
	
	List<URI> getURIs();
	
	String getUUID();
	
	interface ContainerInfo {
		
		List<URI> getURIs();
		
		String getUUID();
		
		String getReference();
		
	}
	
	ContainerInfo getContainerInfo();
	
	interface TypeInfo {
		
		String getNsURI();
		
		String getName(); 
	}
	
	TypeInfo getTypeInfo();
	
	/**
	 * @param eObj
	 * @return {@link SemanticElementAnnotation} if eObj is a {@link ModelElement} and has semantic-element annotation
	 */
	static SemanticElementAnnotation get(EObject eObj) {
		if (eObj instanceof ModelElement) {
			Property annotation = ((ModelElement) eObj).getAnnotation(KEY);
			if (annotation instanceof MapProperty) {
				MapProperty semanticElementAnnotation = (MapProperty) annotation;
				Property urisProperty = semanticElementAnnotation.get(NcoreActionBuilder.URIS_KEY);
				List<URI> uris = urisProperty == null ? Collections.emptyList() : ((ListProperty) urisProperty).getValue()
					.stream()
					.map(org.nasdanika.ncore.String.class::cast)
					.map(org.nasdanika.ncore.String::getValue)
					.map(URI::createURI)
					.collect(Collectors.toList());
								
				return new SemanticElementAnnotation() {

					@Override
					public List<URI> getURIs() {
						return uris;
					}

					@Override
					public String getUUID() {
						Property uuidProperty = semanticElementAnnotation.get(NcoreActionBuilder.UUID_KEY);						
						return uuidProperty == null ? null : ((org.nasdanika.ncore.String) uuidProperty).getValue();
					}

					@Override
					public ContainerInfo getContainerInfo() {
						Property containerProperty = semanticElementAnnotation.get(NcoreActionBuilder.CONTAINER_KEY);
						if (containerProperty == null) {
							return null;
						}
						MapProperty containerMap = (MapProperty) containerProperty;
						Property containerUrisProperty = containerMap.get(NcoreActionBuilder.URIS_KEY);
						List<URI> containerUris = containerUrisProperty == null ? Collections.emptyList() : ((ListProperty) containerUrisProperty).getValue()
							.stream()
							.map(org.nasdanika.ncore.String.class::cast)
							.map(org.nasdanika.ncore.String::getValue)
							.map(URI::createURI)
							.collect(Collectors.toList());
						
						return new ContainerInfo() {

							@Override
							public List<URI> getURIs() {
								return containerUris;
							}

							@Override
							public String getUUID() {
								Property uuidProperty = containerMap.get(NcoreActionBuilder.UUID_KEY);						
								return uuidProperty == null ? null : ((org.nasdanika.ncore.String) uuidProperty).getValue();
							}

							@Override
							public String getReference() {
								Property referenceProperty = containerMap.get(NcoreActionBuilder.REFERENCE_KEY);						
								return referenceProperty == null ? null : ((org.nasdanika.ncore.String) referenceProperty).getValue();
							}
							
						};
					}

					@Override
					public TypeInfo getTypeInfo() {
						Property typeProperty = semanticElementAnnotation.get(NcoreActionBuilder.TYPE_KEY);
						if (typeProperty == null) {
							return null;
						}
						MapProperty typeMap = (MapProperty) typeProperty;
						
						return new TypeInfo() {

							@Override
							public String getNsURI() {
								Property nsUriProperty = typeMap.get(NcoreActionBuilder.NS_URI_KEY);						
								return nsUriProperty == null ? null : ((org.nasdanika.ncore.String) nsUriProperty).getValue();
							}

							@Override
							public String getName() {
								Property nameProperty = typeMap.get(NcoreActionBuilder.NAME_KEY);						
								return nameProperty == null ? null : ((org.nasdanika.ncore.String) nameProperty).getValue();
							}
							
						};
					}
					
				};
			}
		}
		
		return null;
	}

}
