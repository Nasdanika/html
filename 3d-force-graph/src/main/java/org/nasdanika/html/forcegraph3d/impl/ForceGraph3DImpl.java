package org.nasdanika.html.forcegraph3d.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.html.Producer;
import org.nasdanika.html.forcegraph3d.ForceGraph3D;
import org.nasdanika.html.forcegraph3d.ForceGraph3DFactory;

class ForceGraph3DImpl implements ForceGraph3D {
	
	private Map<String, Object> methodCalls = new LinkedHashMap<>();
	private Map<String, Object> config = new LinkedHashMap<>();
	private String selector;
	private List<Object> extraRenderers;
	private String name;
	
	private ForceGraph3DFactory factory;
	
	public ForceGraph3DImpl(ForceGraph3DFactory factory) {
		this.factory = factory;
	}

	@Override
	public Object produce(int indent) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < indent; ++i) {
			sb.append("  ");
		}
		if (!Util.isBlank(name)) {
			sb
				.append("const ")
				.append(name)
				.append(" = ");
		}
		sb.append("new ForceGraph3D(");		
		
		if (config.isEmpty()) {
			sb.append(selector);
		} else {
			sb.append(System.lineSeparator());
			int argIndent = indent + 4;
			for (int i = 0; i < argIndent; ++i) {
				sb.append("  ");
			}
			sb.append(selector);
			sb.append(", ");
			sb.append(System.lineSeparator());
			for (int i = 0; i < argIndent; ++i) {
				sb.append("  ");
			}
			sb.append("{");
			int configIndent = argIndent + 4;
			boolean first = true;
			for (Entry<String, Object> ce: config.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append(System.lineSeparator());
				for (int i = 0; i < configIndent; ++i) {
					sb.append("  ");
				}
				sb
					.append(ce.getKey())
					.append(": ");
				
				Object cVal = ce.getValue();
				if (cVal instanceof Producer) {
					cVal = ((Producer) cVal).produce(configIndent);  
				}
				if (cVal instanceof String) {
					cVal = ((String) cVal).trim();
				}
				sb.append(cVal);
			}
			sb.append(System.lineSeparator());
			for (int i = 0; i < argIndent; ++i) {
				sb.append("  ");
			}
			sb.append("}");
		}
			
		sb.append(")");
			
		int mcIndent = indent + 4;
		int mcArgIndent = indent + 4;
		for (Entry<String, Object> mce: methodCalls.entrySet()) {
			Object arg = mce.getValue();
			if (arg != null) {
				sb.append(System.lineSeparator());
				for (int i = 0; i < mcIndent; ++i) {
					sb.append("  ");
				}
				
				if (arg instanceof Producer) {
					arg = ((Producer) arg).produce(mcArgIndent);  
				}
				if (arg instanceof String) {
					arg = ((String) arg).trim();
				}
				sb
					.append(".")
					.append(mce.getKey())
					.append("(")
					.append(arg)
					.append(")");
			}
		}
		if (!Util.isBlank(name)) {
			sb.append(";");
		}

		return sb.toString();
	}

	@Override
	public ForceGraph3D selector(String selector) {
		this.selector = selector;
		return this;
	}

	@Override
	public ForceGraph3D controlType(ControlType controlType) {
		if (controlType == null) {
			config.remove("controlType");
		} else {
			config.put("controlType", "'" + controlType.name() + "'");
		}
		
		return this;
	}

	@Override
	public ForceGraph3D renderConfig(Object renderConfig) {
		if (renderConfig == null) {
			config.remove("renderConfig");
		} else {
			config.put("renderConfig", renderConfig);
		}
		return this;
	}

	@Override
	public ForceGraph3D addExtraRederer(Object extraRenderer) {
		if (extraRenderer != null) {
			if (extraRenderers == null) {
				 extraRenderers = new ArrayList<>();
				 config.put("extraRenderers", new Producer() {
	
					@Override
					public Object produce(int indent) {
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < indent; ++i) {
							sb.append("  ");
						}
						sb.append("[");						
						int elementIndent = indent + 4;
						boolean first = true;
						for (Object ee: extraRenderers) {
							if (first) {
								first = false;
							} else {
								sb.append(",");
							}
							sb.append(System.lineSeparator());
							for (int i = 0; i < elementIndent; ++i) {
								sb.append("  ");
							}
							if (ee instanceof Producer) {
								ee = ((Producer) ee).produce(elementIndent);  
							}
							if (ee instanceof String) {
								ee = ((String) ee).trim();
							}
							sb.append(ee);
						}
						sb.append(System.lineSeparator());
						for (int i = 0; i < indent; ++i) {
							sb.append("  ");
						}
						sb.append("]");
						return sb.toString();
					}
					 
				 });
			}
			extraRenderers.add(extraRenderer);
		}
		return this;
	}

	@Override
	public ForceGraph3D graphData(Object graphData) {
		methodCalls.put("graphData", graphData);
		return this;
	}

	@Override
	public ForceGraph3D jsonUrl(Object jsonUrl) {
		methodCalls.put("jsonUrl", jsonUrl);
		return this;
	}

	@Override
	public ForceGraph3D nodeId(Object nodeId) {
		methodCalls.put("nodeId", nodeId);
		return this;
	}

	@Override
	public ForceGraph3D linkSource(Object linkSource) {
		methodCalls.put("linkSource", linkSource);
		return this;
	}

	@Override
	public ForceGraph3D linkTarget(Object linkTarget) {
		methodCalls.put("linkTarget", linkTarget);
		return this;
	}

	@Override
	public ForceGraph3D width(Object width) {
		methodCalls.put("width", width);
		return this;
	}

	@Override
	public ForceGraph3D height(Object height) {
		methodCalls.put("height", height);
		return this;
	}

	@Override
	public ForceGraph3D backgroundColor(Object backgroundColor) {
		methodCalls.put("backgroundColor", backgroundColor);
		return this;
	}

	@Override
	public ForceGraph3D showNavInfo(boolean showNavInfo) {
		methodCalls.put("showNavInfo", showNavInfo);
		return this;
	}

	@Override
	public ForceGraph3D nodeRelSize(Object nodeRelSize) {
		methodCalls.put("nodeRelSize", nodeRelSize);
		return this;
	}

	@Override
	public ForceGraph3D nodeVal(Object nodeVal) {
		methodCalls.put("nodeVal", nodeVal);
		return this;
	}

	@Override
	public ForceGraph3D nodeLabel(Object nodeLabel) {
		methodCalls.put("nodeLabel", nodeLabel);
		return this;
	}

	@Override
	public ForceGraph3D nodeVisibility(Object nodeVisibility) {
		methodCalls.put("nodeVisibility", nodeVisibility);
		return this;
	}

	@Override
	public ForceGraph3D nodeColor(Object nodeColor) {
		methodCalls.put("nodeColor", nodeColor);
		return this;
	}

	@Override
	public ForceGraph3D nodeAutoColorBy(Object nodeAutoColorBy) {
		methodCalls.put("nodeAutoColorBy", nodeAutoColorBy);
		return this;
	}

	@Override
	public ForceGraph3D nodeOpacity(Object nodeOpacity) {
		methodCalls.put("nodeOpacity", nodeOpacity);
		return this;
	}

	@Override
	public ForceGraph3D nodeResolution(Object nodeResolution) {
		methodCalls.put("nodeResolution", nodeResolution);
		return this;
	}

	@Override
	public ForceGraph3D nodeThreeObject(Object nodeThreeObject) {
		methodCalls.put("nodeThreeObject", nodeThreeObject);
		return this;
	}

	@Override
	public ForceGraph3D nodeThreeObjectExtend(Object nodeThreeObjectExtend) {
		methodCalls.put("nodeThreeObjectExtend", nodeThreeObjectExtend);
		return this;
	}

	@Override
	public ForceGraph3D nodePositionUpdate(Object nodePositionUpdate) {
		methodCalls.put("nodePositionUpdate", nodePositionUpdate);
		return this;
	}

	@Override
	public ForceGraph3D linkLabel(Object linkLabel) {
		methodCalls.put("linkLabel", linkLabel);
		return this;
	}

	@Override
	public ForceGraph3D linkVisibility(Object linkVisibility) {
		methodCalls.put("linkVisibility", linkVisibility);
		return this;
	}

	@Override
	public ForceGraph3D linkColor(Object linkColor) {
		methodCalls.put("linkColor", linkColor);
		return this;
	}

	@Override
	public ForceGraph3D linkAutoColorBy(Object linkAutoColorBy) {
		methodCalls.put("linkAutoColorBy", linkAutoColorBy);
		return this;
	}

	@Override
	public ForceGraph3D linkOpacity(Object linkOpacity) {
		methodCalls.put("linkOpacity", linkOpacity);
		return this;
	}

	@Override
	public ForceGraph3D linkWidth(Object linkWidth) {
		methodCalls.put("linkWidth", linkWidth);
		return this;
	}

	@Override
	public ForceGraph3D linkResolution(Object linkResolution) {
		methodCalls.put("linkResolution", linkResolution);
		return this;
	}

	@Override
	public ForceGraph3D linkCurvature(Object linkCurvature) {
		methodCalls.put("linkCurvature", linkCurvature);
		return this;
	}

	@Override
	public ForceGraph3D linkCurveRotation(Object graplinkCurveRotationhData) {
		methodCalls.put("graplinkCurveRotationhData", graplinkCurveRotationhData);
		return this;
	}

	@Override
	public ForceGraph3D linkMaterial(Object linkMaterial) {
		methodCalls.put("linkMaterial", linkMaterial);
		return this;
	}

	@Override
	public ForceGraph3D linkThreeObject(Object linkThreeObject) {
		methodCalls.put("linkThreeObject", linkThreeObject);
		return this;
	}

	@Override
	public ForceGraph3D linkThreeObjectExtend(Object linkThreeObjectExtend) {
		methodCalls.put("linkThreeObjectExtend", linkThreeObjectExtend);
		return this;
	}

	@Override
	public ForceGraph3D linkPositionUpdate(Object linkPositionUpdate) {
		methodCalls.put("linkPositionUpdate", linkPositionUpdate);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalArrowLength(Object linkDirectionalArrowLength) {
		methodCalls.put("linkDirectionalArrowLength", linkDirectionalArrowLength);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalArrowColor(Object linkDirectionalArrowColor) {
		methodCalls.put("linkDirectionalArrowColor", linkDirectionalArrowColor);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalArrowRelPos(Object linkDirectionalArrowRelPos) {
		methodCalls.put("linkDirectionalArrowRelPos", linkDirectionalArrowRelPos);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalArrowResolution(Object linkDirectionalArrowResolution) {
		methodCalls.put("linkDirectionalArrowResolution", linkDirectionalArrowResolution);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalParticles(Object linkDirectionalParticles) {
		methodCalls.put("linkDirectionalParticles", linkDirectionalParticles);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalParticleSpeed(Object linkDirectionalParticleSpeed) {
		methodCalls.put("linkDirectionalParticleSpeed", linkDirectionalParticleSpeed);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalParticleWidth(Object linkDirectionalParticleWidth) {
		methodCalls.put("linkDirectionalParticleWidth", linkDirectionalParticleWidth);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalParticleColor(Object linkDirectionalParticleColor) {
		methodCalls.put("linkDirectionalParticleColor", linkDirectionalParticleColor);
		return this;
	}

	@Override
	public ForceGraph3D linkDirectionalParticleResolution(Object linkDirectionalParticleResolution) {
		methodCalls.put("linkDirectionalParticleResolution", linkDirectionalParticleResolution);
		return this;
	}

	@Override
	public ForceGraph3D onNodeClick(Object onNodeClick) {
		methodCalls.put("onNodeClick", onNodeClick);
		return this;
	}

	@Override
	public ForceGraph3D onNodeRightClick(Object onNodeRightClick) {
		methodCalls.put("onNodeRightClick", onNodeRightClick);
		return this;
	}

	@Override
	public ForceGraph3D onNodeHover(Object onNodeHover) {
		methodCalls.put("onNodeHover", onNodeHover);
		return this;
	}

	@Override
	public ForceGraph3D onNodeDrag(Object onNodeDrag) {
		methodCalls.put("onNodeDrag", onNodeDrag);
		return this;
	}

	@Override
	public ForceGraph3D onNodeDragEnd(Object onNodeDragEnd) {
		methodCalls.put("onNodeDragEnd", onNodeDragEnd);
		return this;
	}

	@Override
	public ForceGraph3D onLinkClick(Object onLinkClick) {
		methodCalls.put("onLinkClick", onLinkClick);
		return this;
	}

	@Override
	public ForceGraph3D onLinkRightClick(Object onLinkRightClick) {
		methodCalls.put("onLinkRightClick", onLinkRightClick);
		return this;
	}

	@Override
	public ForceGraph3D onLinkHover(Object onLinkHover) {
		methodCalls.put("onLinkHover", onLinkHover);
		return this;
	}

	@Override
	public ForceGraph3D onBackgroundClick(Object onBackgroundClick) {
		methodCalls.put("onBackgroundClick", onBackgroundClick);
		return this;
	}

	@Override
	public ForceGraph3D onBackgroundRightClick(Object onBackgroundRightClick) {
		methodCalls.put("onBackgroundRightClick", onBackgroundRightClick);
		return this;
	}

	@Override
	public ForceGraph3D linkHoverPrecision(Object linkHoverPrecision) {
		methodCalls.put("linkHoverPrecision", linkHoverPrecision);
		return this;
	}

	@Override
	public ForceGraph3D enablePointerInteraction(boolean enablePointerInteraction) {
		methodCalls.put("enablePointerInteraction", enablePointerInteraction);
		return this;
	}

	@Override
	public ForceGraph3D enableNodeDrag(boolean enableNodeDrag) {
		methodCalls.put("enableNodeDrag", enableNodeDrag);
		return this;
	}

	@Override
	public ForceGraph3D enableNavigationControls(boolean enableNavigationControls) {
		methodCalls.put("enableNavigationControls", enableNavigationControls);
		return this;
	}
	
	private List<Object> nodes;
	private List<Object> links;
	
	private void createNodesAndLinksGraphData() {
		links = new ArrayList<>();
		nodes = new ArrayList<>();				
		graphData(new Producer() {

				@Override
				public Object produce(int indent) {
					JSONObject data = new JSONObject();
					if (!nodes.isEmpty()) {
						JSONArray jNodes = new JSONArray();
						for (Object node: nodes) {
							jNodes.put(node);
						}
						data.put("nodes", jNodes);
					}
					if (!links.isEmpty()) {
						JSONArray jLinks = new JSONArray();
						for (Object link: links) {
							jLinks.put(link);
						}
						data.put("links", jLinks);
					}
					
					Writer sw = new StringWriter();
					try {
						try (sw) {
							data.write(sw, 2, indent);
						}
					} catch (IOException e) {
						throw new NasdanikaException(e); // Should never happen
					}
					return sw.toString();					
				}
				 
			 });
	}

	@Override
	public ForceGraph3D addNode(Object node) {
		if (nodes == null) {
			createNodesAndLinksGraphData();
		}
		nodes.add(node);
		return this;
	}
	
	

	@Override
	public ForceGraph3D addLink(Object link) {
		if (links == null) {
			createNodesAndLinksGraphData();
		}
		links.add(link);
		return this;
	}

	@Override
	public ForceGraph3D name(String name) {
		this.name = name;
		return this;
	}

	@Override
	public ForceGraph3DFactory getFactory() {
		return factory;
	}
	
}
