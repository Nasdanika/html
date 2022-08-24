package org.nasdanika.html.model.app.drawio;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.graph.Element;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;

public class ConnectionProcessor extends ElementProcessor {
	
	protected EReference defaultConnectionRole;

	public ConnectionProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
		
		((ConnectionProcessorConfig<ElementProcessor, Handler, Handler>) config).setSourceHandler(new Handler() {

			@Override
			public void setDefaultConnectionRole(EReference connectionRole, Predicate<Element> traversePredicate) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setSemanticParentInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
		
	@Override
	public ProcessorInfo<ElementProcessor> getSemanticParentInfo() {
		if (registry != null) {
			if (resourceFactory.getConnectionBase() == ConnectionBase.SOURCE) {
				Node source = ((Connection) config.getElement()).getSource();
				return source == null ? null : registry.get(source);
			}
			if (resourceFactory.getConnectionBase() == ConnectionBase.TARGET) {
				Node target = ((Connection) config.getElement()).getTarget();
				return target == null ? null : registry.get(target);
			}
		}
		return super.getSemanticParentInfo();
	}
	
	public void setDefaultConnectionRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, EReference defaultConnectionRole, Set<Node> traversed) {
		this.defaultConnectionRole = defaultConnectionRole;
		Connection connection = (Connection) config.getElement();
		Node target = connection.getTarget();
		if (target != null && !target.equals(connection.getSource())) {
			((NodeProcessor) registry.get(target).getProcessor()).setDefaultConnectionRole(registry, defaultConnectionRole, traversed);
		}
	}
	
	public EReference getConnectionRole(Connection connection) {
		String connectionRoleProperty = resourceFactory.getRoleProperty();		
		if (!Util.isBlank(connectionRoleProperty)) {
			String roleName = connection.getProperty(connectionRoleProperty);
			if (!Util.isBlank(roleName)) {
				return resourceFactory.resolveRole(roleName);
			}
		}
		
		return defaultConnectionRole;
	}
	
//	/**
//	 * Creates actions for documented connections.
//	 * @param resource
//	 * @param connection
//	 * @param childEntries
//	 * @param containmentReference
//	 * @return
//	 */
//	protected Action createConnectionAction(Resource resource, Connection connection, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
//		if (!shallCreateAction(resource, connection, childEntries, containmentReference)) {
//			return null;			
//		}
//		Action ret = createAction(resource, connection);
//		ret.setId(connection.getId());
//		String label = getModelElementLabel(connection);
//		if (Util.isBlank(label)) {
//			Node source = connection.getSource();
//			String sourceLabel = getModelElementLabel(source);
//			
//			Node target = connection.getTarget();
//			String targetLabel = getModelElementLabel(target);
//			
//			if (Util.isBlank(sourceLabel) && Util.isBlank(targetLabel)) {
//				ret.setText("(unlabeled)");				
//			} else {
//				StringBuilder labelBuilder = new StringBuilder();
//				if (!Util.isBlank(sourceLabel)) {
//					labelBuilder.append(sourceLabel).append(" ");
//				}
//				labelBuilder.append("->");
//				if (!Util.isBlank(targetLabel)) {
//					labelBuilder.append(" ").append(targetLabel);
//				}
//				ret.setText(labelBuilder.toString());
//			}
//		} else {
//			ret.setText(label);
//		}
//		String link = connection.getLink();
//		if (Util.isBlank(link) || connection.isPageLink()) {
//			String path = Objects.requireNonNull(getPath(connection), "Path is null for " + connection.getLabel() + " / " + connection.getId());
//			if (containmentReference == null) {
//				ret.setLocation(path + "/index.html");
//			} else {
//				ret.setLocation(containmentReference.getName() + "/" + path + "/index.html");
//			}
//		} else {
//			ret.setLocation(link);
//		}
//		ret.setDescription(connection.getTooltip());
//		String icon = getIcon(connection);
//		if (!Util.isBlank(icon)) {
//			ret.setIcon(icon);
//		}
//		
//		return ret;				
//	}	
	

}
