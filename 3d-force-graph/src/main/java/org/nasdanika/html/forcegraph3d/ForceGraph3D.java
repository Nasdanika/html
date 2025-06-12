package org.nasdanika.html.forcegraph3d;

import org.nasdanika.html.Producer;

/**
 * Fluent API to build and generates <a href="https://github.com/vasturiano/3d-force-graph?tab=readme-ov-file">3d-force-graph</a> ForceGraph3D declaration.
 * Documentation is copied verbatim from the above page.
 */
public interface ForceGraph3D extends Producer {
	
	enum ControlType { trackball, orbit, fly }
	
	ForceGraph3DFactory getFactory();
	
	// Config and initialization
	
	/**
	 * Which type of control to use to control the camera. Default <code>trackball</code>
	 * @param controlType
	 * @return
	 */
	ForceGraph3D controlType(ControlType controlType);
	
	/**
	 * Configuration parameters to pass to the <a href="https://threejs.org/docs/#api/en/renderers/WebGLRenderer">ThreeJS WebGLRenderer</a> constructor.
	 * Default <code>{ antialias: true, alpha: true }</code>
	 * @param renderConfig
	 * @return
	 */
	ForceGraph3D renderConfig(Object renderConfig);
	
	/**
	 * If you wish to include custom objects that require a dedicated renderer besides WebGL, 
	 * such as <a href="https://threejs.org/docs/#examples/en/renderers/CSS3DRenderer">CSS3DRenderer</a>, 
	 * add those extra renderer instances using this method.
	 * @param extraRenderer
	 * @return
	 */
	ForceGraph3D addExtraRederer(Object extraRenderer);
		
	/**
	 * @param selector expression evaluating to an element to pass to constructor.
	 * @return
	 */
	ForceGraph3D selector(String selector);
	
	/**
	 * Sets selector to document.getElementById('&lt;elementId&gt;')
	 * @param elementId
	 * @return
	 */
	default ForceGraph3D elementId(String elementId) {
		return selector("document.getElementById('" + elementId + "')");
	}	
	
	/**
	 * Force graph variable name. If set, generates a constant declaration <code>const &lt;name&gt; = new ForceGraph3D(...);</code>. 
	 * If not, just the right part without the closing semicolon.
	 * @param name
	 * @return
	 */
	ForceGraph3D name(String name);
	
	
//	### Data input

	/**
	 * Sets graph data. Mutually exclusive with <code>addNode()</code> and <code>addLink()</code> methods. 
	 */
	ForceGraph3D graphData(Object graphData);
	
	/**
	 * URL of JSON file to load graph data directly from, as an alternative to specifying <i>graphData</i> directly.
	 */
	ForceGraph3D jsonUrl(Object jsonUrl);	
	
	/**
	 * Node object accessor attribute for unique node id (used in link objects source/target). Default: <code>id</code>.
	 */
	ForceGraph3D nodeId(Object nodeId);	
	
	/**
	 * Link object accessor attribute referring to id of source node. Default: <code>source</code>
	 */
	ForceGraph3D linkSource(Object linkSource);	
	
	/**
	 * Link object accessor attribute referring to id of target node. Default: <code>target</code>
	 */
	ForceGraph3D linkTarget(Object linkTarget);	

//	### Container layout

	/**
	 * Canvas width. Default: <code>&lt;window width&gt;</code>
	 * @param width
	 * @return
	 */
	ForceGraph3D width(Object width);	
	
	/**
	 * Canvas height. Default: <code>&lt;window height&gt;</code>
	 */
	ForceGraph3D height(Object height);
	
	/**
	 * Chart background color. Default: <code>#000011</code>
	 */
	ForceGraph3D backgroundColor(Object backgroundColor);
	
	/**
	 * Whether to show the navigation controls footer info. Default: <code>true</code.
	 */
	ForceGraph3D showNavInfo(boolean showNavInfo);	

//	### Node styling

	/**
	 * The ratio of node sphere volume (cubic px) per value unit. Default: <code>4</code>
	 * @param nodeRelSize
	 * @return
	 */
	ForceGraph3D nodeRelSize(Object nodeRelSize);
	
	/**
	 * Node object accessor function, attribute or a numeric constant for the node numeric value (affects sphere volume). Default: <code>val</code>
	 * @param nodeVal
	 * @return
	 */	
	ForceGraph3D nodeVal(Object nodeVal);
	
	/**
	 * Node object accessor function or attribute for name (shown in label). 
	 * Supports plain text, HTML string content or an <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement">HTML Element</a>. Default: <code>name</code>
	 * @param nodeLabel
	 * @return
	 */
	ForceGraph3D nodeLabel(Object nodeLabel);	
	
	/**
	 * Node object accessor function, attribute or a boolean constant for whether to display the node. Default: <code>true</code>
	 * @param nodeVisibility
	 * @return
	 */
	ForceGraph3D nodeVisibility(Object nodeVisibility);	
	
	/**
	 * Node object accessor function or attribute for node color (affects sphere color). Default: <code>color</code>
	 * @param nodeColor
	 * @return
	 */
	ForceGraph3D nodeColor(Object nodeColor);	
	
	/**
	 * Node object accessor function (`fn(node)`) or attribute (e.g. `'type'`) to automatically group colors by. 
	 * Only affects nodes without a color attribute.
	 * @param nodeAutoColorBy
	 * @return
	 */
	ForceGraph3D nodeAutoColorBy(Object nodeAutoColorBy);
	
	/**
	 * ([<i>num</i>]) | Getter/setter for the nodes sphere opacity, between [0,1]. | 0.75   |
	 * @param nodeOpacity
	 * @return
	 */
	ForceGraph3D nodeOpacity(Object nodeOpacity);	
	/**
	 * ([<i>num</i>]) | Getter/setter for the geometric resolution of each node, expressed in how many slice segments to divide the circumference. Higher values yield smoother spheres. | 8 |
	 * @param nodeResolution
	 * @return
	 */
	ForceGraph3D nodeResolution(Object nodeResolution);	
	/**
	 * ([<i>Object3d</i>, <i>str</i> or <i>fn</i>]) | Node object accessor function or attribute for generating a custom 3d object to render as graph nodes. Should return an instance of [ThreeJS Object3d](https://threejs.org/docs/index.html#api/core/Object3D). If a <i>falsy</i> value is returned, the default 3d object type will be used instead for that node.  | *default node object is a sphere, sized according to `val` and styled according to `color`.* |
	 * @param nodeThreeObject
	 * @return
	 */
	ForceGraph3D nodeThreeObject(Object nodeThreeObject);	
	/**
	 * ([<i>bool</i>, <i>str</i> or <i>fn</i>]) | Node object accessor function, attribute or a boolean value for whether to replace the default node when using a custom `nodeThreeObject` (`false`) or to extend it (`true`).  | `false` |
	 * @param nodeThreeObjectExtend
	 * @return
	 */
	ForceGraph3D nodeThreeObjectExtend(Object nodeThreeObjectExtend);	
	/**
	 * ([<i>fn(nodeObject, coords, node)</i>]) | Getter/setter for the custom function to call for updating the position of nodes at every render iteration. It receives the respective node `ThreeJS Object3d`, the coordinates of the node (`{x,y,z}` each), and the node's `data`. If the function returns a truthy value, the regular position update function will not run for that node. | |
	 * @param nodePositionUpdate
	 * @return
	 */
	ForceGraph3D nodePositionUpdate(Object nodePositionUpdate);	

//	### Link styling

	/**
	 * Link object accessor function or attribute for name (shown in label). Supports plain text, HTML string content or an <a href="https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement">HTML Element</a>. Default: <code>name</code>
	 * @param linkLabel
	 * @return
	 */
	ForceGraph3D linkLabel(Object linkLabel);
	
	/**
	 * Link object accessor function, attribute or a boolean constant for whether to display the link line. A value of `false` maintains the link force without rendering it. Default: <code>true</code>
	 * @param linkVisibility
	 * @return
	 */
	ForceGraph3D linkVisibility(Object linkVisibility);
	
	/**
	 * Link object accessor function or attribute for line color. Default: <code>color</code>
	 * @param linkColor
	 * @return
	 */
	ForceGraph3D linkColor(Object linkColor);
	
	/**
	 * Link object accessor function (`fn(link)`) or attribute (e.g. `'type'`) to automatically group colors by. Only affects links without a color attribute.
	 * @param linkAutoColorBy
	 * @return
	 */
	ForceGraph3D linkAutoColorBy(Object linkAutoColorBy);
	
	/**
	 * Line opacity of links, between [0,1]. Default: <code>0.2</code>
	 * @param linkOpacity
	 * @return
	 */
	ForceGraph3D linkOpacity(Object linkOpacity);	
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the link line width. 
	 * A value of zero will render a <a href="https://threejs.org/docs/#api/objects/Line>ThreeJS Line</a> whose width is constant (<code>1px</code>) regardless of distance. 
	 * Values are rounded to the nearest decimal for indexing purposes. Default: <code>0</code>
	 * @param linkWidth
	 * @return
	 */
	ForceGraph3D linkWidth(Object linkWidth);	
	
	/**
	 * Geometric resolution of each link, expressed in how many radial segments to divide the cylinder. Higher values yield smoother cylinders. Applicable only to links with positive width. Default: <code>6</code>
	 * @param linkResolution
	 * @return
	 */
	ForceGraph3D linkResolution(Object linkResolution);	
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the curvature radius of the link line. 
	 * Curved lines are represented as 3D bezier curves, and any numeric value is accepted. A value of <code>0</code> renders a straight line. 
	 * <code>1</code> indicates a radius equal to half of the line length, causing the curve to approximate a semi-circle. 
	 * For self-referencing links (`source` equal to `target`) the curve is represented as a loop around the node, with length proportional to the curvature value. 
	 * Lines are curved clockwise for positive values, and counter-clockwise for negative values. 
	 * Note that rendering curved lines is purely a visual effect and does not affect the behavior of the underlying forces. 
	 * Default: <code>0</code>
	 * @param linkCurvature
	 * @return
	 */
	ForceGraph3D linkCurvature(Object linkCurvature);
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the rotation along the line axis to apply to the curve. 
	 * Has no effect on straight lines. 
	 * At <code>0</code> rotation, the curve is oriented in the direction of the intersection with the `XY` plane. 
	 * The rotation angle (in radians) will rotate the curved line clockwise around the "start-to-end" axis from this reference orientation. 
	 * Default: <code>0</code>
	 * @param graplinkCurveRotationhData
	 * @return
	 */
	ForceGraph3D linkCurveRotation(Object graplinkCurveRotationhData);
	
	/**
	 * Link object accessor function or attribute for specifying a custom material to style the graph links with. 
	 * Should return an instance of <a href="https://threejs.org/docs/#api/materials/Material">ThreeJS Material</a>.
	 * If a <i>falsy</i> value is returned, the default material will be used instead for that link. 
	 * Default: <a href="https://threejs.org/docs/#api/materials/MeshLambertMaterial">MeshLambertMaterial</a> styled according to `color` and `opacity`
	 * @param linkMaterial
	 * @return
	 */
	ForceGraph3D linkMaterial(Object linkMaterial);	
	/**
	 * Link object accessor function or attribute for generating a custom 3d object to render as graph links.
	 * Should return an instance of <a href="https://threejs.org/docs/index.html#api/core/Object3D">ThreeJS Object3d</a>.
	 * If a <i>falsy</i> value is returned, the default 3d object type will be used instead for that link.  
	 * Default: A line or cylinder, sized according to <code>width</code> and styled according to <code>material</code>.
	 * @param linkThreeObject
	 * @return
	 */
	ForceGraph3D linkThreeObject(Object linkThreeObject);
	
	/**
	 * Link object accessor function, attribute or a boolean value for whether to replace the default link when using a custom `linkThreeObject` (`false`) or to extend it (`true`).
	 * Default: <code>false</code>
	 * @param linkThreeObjectExtend
	 * @return
	 */
	ForceGraph3D linkThreeObjectExtend(Object linkThreeObjectExtend);
	
	/**
	 * Custom function to call for updating the position of links at every render iteration.
	 * It receives the respective link <code>ThreeJS Object3d</code>, the <code>start</code> and <code>end</code> coordinates of the link (`{x,y,z}` each), and the link's <code>data</code>.
	 * If the function returns a truthy value, the regular position update function will not run for that link.
	 * @param linkPositionUpdate
	 * @return
	 */
	ForceGraph3D linkPositionUpdate(Object linkPositionUpdate);	
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the length of the arrow head indicating the link directionality. 
	 * The arrow is displayed directly over the link line, and points in the direction of <code>source</code> -&gt; <code>target</code>. A value of `0` hides the arrow.
	 * Default: <code>0</code>
	 * @param linkDirectionalArrowLength
	 * @return
	 */
	ForceGraph3D linkDirectionalArrowLength(Object linkDirectionalArrowLength);
	
	/**
	 * Link object accessor function or attribute for the color of the arrow head. Default: <code>color</code>
	 * @param linkDirectionalArrowColor
	 * @return
	 */
	ForceGraph3D linkDirectionalArrowColor(Object linkDirectionalArrowColor);	
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the longitudinal position of the arrow head along the link line, 
	 * expressed as a ratio between <code>0</code> and <code>1</code>, where <code>0</code> indicates immediately next to the <code>source</code> node,
	 *  <code>1</code> next to the <code>target</code> node, and <code>0.5</code> right in the middle. Default: <code>0.5</code>
	 * @param linkDirectionalArrowRelPos
	 * @return
	 */
	ForceGraph3D linkDirectionalArrowRelPos(Object linkDirectionalArrowRelPos);	
	
	/**
	 * Geometric resolution of the arrow head, expressed in how many slice segments to divide the cone base circumference. Higher values yield smoother arrows.
	 * Default: <code>8</code>
	 * @param linkDirectionalArrowResolution
	 * @return
	 */
	ForceGraph3D linkDirectionalArrowResolution(Object linkDirectionalArrowResolution);
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the number of particles (small spheres) to display over the link line. 
	 * The particles are distributed equi-spaced along the line, travel in the direction <code>source</code> -&gt; <code>target</code>, and can be used to indicate link directionality. 
	 * Default: <code>0</code>
	 * @param linkDirectionalParticles
	 * @return
	 */
	ForceGraph3D linkDirectionalParticles(Object linkDirectionalParticles);
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the directional particles speed, expressed as the ratio of the link length to travel per frame. 
	 * Values above <code>0.5</code> are discouraged. 
	 * Default: <code>0.01</code>
	 * @param linkDirectionalParticleSpeed
	 * @return
	 */
	ForceGraph3D linkDirectionalParticleSpeed(Object linkDirectionalParticleSpeed);
	
	/**
	 * Link object accessor function, attribute or a numeric constant for the directional particles width. 
	 * Values are rounded to the nearest decimal for indexing purposes. 
	 * Default: <code>0.5</code>
	 * @param linkDirectionalParticleWidth
	 * @return
	 */
	ForceGraph3D linkDirectionalParticleWidth(Object linkDirectionalParticleWidth);
	
	/**
	 * Link object accessor function or attribute for the directional particles color. 
	 * Default: <code>color</code>
	 * @param linkDirectionalParticleColor
	 * @return
	 */
	ForceGraph3D linkDirectionalParticleColor(Object linkDirectionalParticleColor);
	
	/**
	 * Geometric resolution of each directional particle, expressed in how many slice segments to divide the circumference. 
	 * Higher values yield smoother particles.
	 * Default: <code>4</code>
	 * @param linkDirectionalParticleResolution
	 * @return
	 */
	ForceGraph3D linkDirectionalParticleResolution(Object linkDirectionalParticleResolution);	

	//	| <b>emitParticle</b>(<i>link</i>) | An alternative mechanism for generating particles, this method emits a non-cyclical single particle within a specific link. The emitted particle shares the styling (speed, width, color) of the regular particle props. A valid `link` object that is included in `graphData` should be passed as a single parameter. ||

//	### Render control
//
//	| Method | Description | Default |
//	| --- | --- | :--: |
//	| <b>pauseAnimation</b>() | Pauses the rendering cycle of the component, effectively freezing the current view and cancelling all user interaction. This method can be used to save performance in circumstances when a static image is sufficient. | |
//	| <b>resumeAnimation</b>() | Resumes the rendering cycle of the component, and re-enables the user interaction. This method can be used together with `pauseAnimation` for performance optimization purposes. | |
//	| <b>cameraPosition</b>([<i>{x,y,z}</i>], [<i>lookAt</i>], [<i>ms</i>]) | Getter/setter for the camera position, in terms of `x`, `y`, `z` coordinates. Each of the coordinates is optional, allowing for motion in just some dimensions. The optional second argument can be used to define the direction that the camera should aim at, in terms of an `{x,y,z}` point in the 3D space. The 3rd optional argument defines the duration of the transition (in ms) to animate the camera motion. A value of 0 (default) moves the camera immediately to the final position. | By default the camera will face the center of the graph at a `z` distance proportional to the amount of nodes in the system. |
//	| <b>zoomToFit</b>([<i>ms</i>], [<i>px</i>], [<i>nodeFilterFn</i>]) | Automatically moves the camera so that all of the nodes become visible within its field of view, aiming at the graph center (0,0,0). If no nodes are found no action is taken. It accepts three optional arguments: the first defines the duration of the transition (in ms) to animate the camera motion (default: 0ms). The second argument is the amount of padding (in px) between the edge of the canvas and the outermost node location (default: 10px). The third argument specifies a custom node filter: `node => <boolean>`, which should return a truthy value if the node is to be included. This can be useful for focusing on a portion of the graph. | `(0, 10, node => true)` |
//	| <b>postProcessingComposer</b>() | Access the [post-processing composer](https://threejs.org/docs/#examples/en/postprocessing/EffectComposer). Use this to add post-processing [rendering effects](https://github.com/mrdoob/three.js/tree/dev/examples/jsm/postprocessing) to the scene. By default the composer has a single pass ([RenderPass](https://github.com/mrdoob/three.js/blob/dev/examples/jsm/postprocessing/RenderPass.js)) that directly renders the scene without any effects. || 
//	| <b>lights</b>([<i>array</i>]) | Getter/setter for the list of lights to use in the scene. Each item should be an instance of [Light](https://threejs.org/docs/#api/en/lights/Light). | [AmbientLight](https://threejs.org/docs/?q=ambient#api/en/lights/AmbientLight) + [DirectionalLight](https://threejs.org/docs/#api/en/lights/DirectionalLight) (from above) |
//	| <b>scene</b>() | Access the internal ThreeJS [Scene](https://threejs.org/docs/#api/scenes/Scene). Can be used to extend the current scene with additional objects not related to 3d-force-graph. | |
//	| <b>camera</b>() | Access the internal ThreeJS [Camera](https://threejs.org/docs/#api/cameras/PerspectiveCamera). | |
//	| <b>renderer</b>() | Access the internal ThreeJS [WebGL renderer](https://threejs.org/docs/#api/renderers/WebGLRenderer). ||
//	| <b>controls</b>() | Access the internal ThreeJS controls object. ||
//	| <b>refresh</b>() | Redraws all the nodes/links. |
//
//	### Force engine configuration
//
//	| Method | Description | Default |
//	| --- | --- | :--: |
//	| <b>forceEngine</b>([<i>str</i>]) | Getter/setter for which force-simulation engine to use ([*d3*](https://github.com/vasturiano/d3-force-3d) or [*ngraph*](https://github.com/anvaka/ngraph.forcelayout)). | `d3` |
//	| <b>numDimensions</b>([<i>int</i>]) | Getter/setter for number of dimensions to run the force simulation on (1, 2 or 3). | 3 |
//	| <b>dagMode</b>([<i>str</i>]) | Apply layout constraints based on the graph directionality. Only works correctly for [DAG](https://en.wikipedia.org/wiki/Directed_acyclic_graph) graph structures (without cycles). Choice between `td` (top-down), `bu` (bottom-up), `lr` (left-to-right), `rl` (right-to-left), `zout` (near-to-far), `zin` (far-to-near), `radialout` (outwards-radially) or `radialin` (inwards-radially). | |
//	| <b>dagLevelDistance</b>([<i>num</i>]) | If `dagMode` is engaged, this specifies the distance between the different graph depths. | *auto-derived from the number of nodes* |
//	| <b>dagNodeFilter</b>([<i>fn</i>]) | Node accessor function to specify nodes to ignore during the DAG layout processing. This accessor method receives a node object and should return a `boolean` value indicating whether the node is to be included. Excluded nodes will be left unconstrained and free to move in any direction. | `node => true` |
//	| <b>onDagError</b>([<i>fn</i>]) | Callback to invoke if a cycle is encountered while processing the data structure for a DAG layout. The loop segment of the graph is included for information, as an array of node ids. By default an exception will be thrown whenever a loop is encountered. You can override this method to handle this case externally and allow the graph to continue the DAG processing. Strict graph directionality is not guaranteed if a loop is encountered and the result is a best effort to establish a hierarchy. | *throws exception* |
//	| <b>d3AlphaMin</b>([<i>num</i>]) | Getter/setter for the [simulation alpha min](https://github.com/vasturiano/d3-force-3d#simulation_alphaMin) parameter, only applicable if using the d3 simulation engine. | `0` |
//	| <b>d3AlphaDecay</b>([<i>num</i>]) | Getter/setter for the [simulation intensity decay](https://github.com/vasturiano/d3-force-3d#simulation_alphaDecay) parameter, only applicable if using the d3 simulation engine. | `0.0228` |
//	| <b>d3VelocityDecay</b>([<i>num</i>]) | Getter/setter for the nodes' [velocity decay](https://github.com/vasturiano/d3-force-3d#simulation_velocityDecay) that simulates the medium resistance, only applicable if using the d3 simulation engine. | `0.4` |
//	| <b>d3Force</b>(<i>str</i>, [<i>fn</i>]) | Getter/setter for the internal forces that control the d3 simulation engine. Follows the same interface as `d3-force-3d`'s [simulation.force](https://github.com/vasturiano/d3-force-3d#simulation_force). Three forces are included by default: `'link'` (based on [forceLink](https://github.com/vasturiano/d3-force-3d#forceLink)), `'charge'` (based on [forceManyBody](https://github.com/vasturiano/d3-force-3d#forceManyBody)) and `'center'` (based on [forceCenter](https://github.com/vasturiano/d3-force-3d#forceCenter)). Each of these forces can be reconfigured, or new forces can be added to the system. This method is only applicable if using the d3 simulation engine. | |
//	| <b>d3ReheatSimulation</b>() | Reheats the force simulation engine, by setting the `alpha` value to `1`. Only applicable if using the d3 simulation engine. | |
//	| <b>ngraphPhysics</b>([<i>object</i>]) | Specify custom physics configuration for ngraph, according to its [configuration object](https://github.com/anvaka/ngraph.forcelayout#configuring-physics) syntax. This method is only applicable if using the ngraph simulation engine. | *ngraph default* |
//	| <b>warmupTicks</b>([<i>int</i>]) | Getter/setter for number of layout engine cycles to dry-run at ignition before starting to render. | 0 |
//	| <b>cooldownTicks</b>([<i>int</i>]) | Getter/setter for how many build-in frames to render before stopping and freezing the layout engine. | Infinity |
//	| <b>cooldownTime</b>([<i>num</i>]) | Getter/setter for how long (ms) to render for before stopping and freezing the layout engine. | 15000 |
//	| <b>onEngineTick</b>(<i>fn</i>) | Callback function invoked at every tick of the simulation engine. | - |
//	| <b>onEngineStop</b>(<i>fn</i>) | Callback function invoked when the simulation engine stops and the layout is frozen. | - |
//
//	### Interaction

	/**
	 * Callback function for node (left-button) clicks. 
	 * The node object and the event object are included as arguments <code>onNodeClick(node, event)</code>
	 */
	ForceGraph3D onNodeClick(Object onNodeClick);	
	
	/**
	 * Callback function for node right-clicks. 
	 * The node object and the event object are included as arguments <code>onNodeRightClick(node, event)</code>
	 * @param onNodeRightClick
	 * @return
	 */
	ForceGraph3D onNodeRightClick(Object onNodeRightClick);	
	
	/**
	 * Callback function for node mouse over events. 
	 * The node object (or <code>null</code> if there's no node under the mouse line of sight) is included as the first argument, and the previous node object (or null) as second argument: <code>onNodeHover(node, prevNode)</code>.
	 * @param onNodeHover
	 * @return
	 */
	ForceGraph3D onNodeHover(Object onNodeHover);	
	
	/**
	 * Callback function for node drag interactions.
	 * This function is invoked repeatedly while dragging a node, every time its position is updated.
	 * The node object is included as the first argument, and the change in coordinates since the last iteration of this function are included as the second argument in format <coee>{x,y,z}</code>: <code>onNodeDrag(node, translate)</code>.
	 * @param onNodeDrag
	 * @return
	 */
	ForceGraph3D onNodeDrag(Object onNodeDrag);	
	
	/**
	 * Callback function for the end of node drag interactions. 
	 * This function is invoked when the node is released. 
	 * The node object is included as the first argument, and the entire change in coordinates from initial location are included as the second argument in format <code>{x,y,z}</code>: <code>onNodeDragEnd(node, translate)</code>.
	 * @param onNodeDragEnd
	 * @return
	 */
	ForceGraph3D onNodeDragEnd(Object onNodeDragEnd);	
	
	/**
	 * Callback function for link (left-button) clicks. 
	 * The link object and the event object are included as arguments <code>onLinkClick(link, event)</code>. 
	 * @param onLinkClick
	 * @return
	 */
	ForceGraph3D onLinkClick(Object onLinkClick);
	
	/**
	 * Callback function for link right-clicks. The link object and the event object are included as arguments <code>onLinkRightClick(link, event)</code>.
	 * @param onLinkRightClick
	 * @return
	 */
	ForceGraph3D onLinkRightClick(Object onLinkRightClick);
	
	/**
	 * Callback function for link mouse over events. 
	 * The link object (or <code>null</code> if there's no link under the mouse line of sight) is included as the first argument, and the previous link object (or null) as second argument: <code>onLinkHover(link, prevLink)</code>.
	 * @param onLinkHover
	 * @return
	 */
	ForceGraph3D onLinkHover(Object onLinkHover);
	
	/**
	 * Callback function for click events on the empty space between the nodes and links. The event object is included as single argument <code>onBackgroundClick(event)</code>.
	 * @param onBackgroundClick
	 * @return
	 */
	ForceGraph3D onBackgroundClick(Object onBackgroundClick);	
	
	/**
	 * Callback function for right-click events on the empty space between the nodes and links. The event object is included as single argument <code>onBackgroundRightClick(event)</code>.
	 * @param onBackgroundRightClick
	 * @return
	 */
	ForceGraph3D onBackgroundRightClick(Object onBackgroundRightClick);	
	
	/**
	 * Whether to display the link label when gazing the link closely (low value) or from far away (high value). 
	 * Default: <code>1</code>
	 * @param linkHoverPrecision
	 * @return
	 */
	ForceGraph3D linkHoverPrecision(Object linkHoverPrecision);
	
	/**
	 * Whether to enable the mouse tracking events. 
	 * This activates an internal tracker of the canvas mouse position and enables the functionality of object hover/click and tooltip labels, at the cost of performance. 
	 * If you're looking for maximum gain in your graph performance it's recommended to switch off this property. 
	 * Default: <code>true</code>
	 * @param enablePointerInteraction
	 * @return
	 */
	ForceGraph3D enablePointerInteraction(boolean enablePointerInteraction);
	
	/**
	 * Getter/setter for whether to enable the user interaction to drag nodes by click-dragging. 
	 * Only supported on the <code>d3</code> force engine. If enabled, every time a node is dragged the simulation is re-heated so the other nodes react to the changes.
	 * Only applicable if enablePointerInteraction is <code>true</code> and using the <code>d3</code> force engine. 
	 * Default: <code>true</code>
	 * @param enableNodeDrag
	 * @return
	 */
	ForceGraph3D enableNodeDrag(boolean enableNodeDrag);
	
	/**
	 * Whether to enable the trackball navigation controls used to move the camera using mouse interactions (rotate/zoom/pan). 
	 * Default: <code>true</code>
	 * @param enableNavigationControls
	 * @return
	 */
	ForceGraph3D enableNavigationControls(boolean enableNavigationControls);	
//
//	###  Utility
//
//	| Method | Description |
//	| --- | --- |
//	| <b>getGraphBbox</b>([<i>nodeFilterFn</i>]) | Returns the current bounding box of the nodes in the graph, formatted as `{ x: [<num>, <num>], y: [<num>, <num>], z: [<num>, <num>] }`. If no nodes are found, returns `null`. Accepts an optional argument to define a custom node filter: `node => <boolean>`, which should return a truthy value if the node is to be included. This can be useful to calculate the bounding box of a portion of the graph. |
//	| <b>graph2ScreenCoords</b>(<i>x</i>, <i>y</i>, <i>z</i>) | Utility method to translate node coordinates to the viewport domain. Given a set of `x`,`y`,`z` graph coordinates, returns the current equivalent `{x, y}` in viewport coordinates. |
//	| <b>screen2GraphCoords</b>(<i>x</i>, <i>y</i>, <i>distance</i>) | Utility method to translate viewport distance coordinates to the graph domain. Given a pair of `x`,`y` screen coordinates and distance from the camera, returns the current equivalent `{x, y, z}` in the domain of graph node coordinates. |
//

	//	### Input JSON syntax
	//	{
	//	    "nodes": [
	//	        {
	//	          "id": "id1",
	//	          "name": "name1",
	//	          "val": 1
	//	        },
	//	        {
	//	          "id": "id2",
	//	          "name": "name2",
	//	          "val": 10
	//	        },
	//	        ...
	//	    ],
	//	    "links": [
	//	        {
	//	            "source": "id1",
	//	            "target": "id2"
	//	        },
	//	        ...
	//	    ]
	//	}
	
	/**
	 * Adds a node to the nodes array.
	 * This method is mutually exclusive with <code>graphData</code>.
	 * @param node
	 * @return
	 */
	ForceGraph3D addNode(Object node);
	
	/**
	 * Adds a link to the links array.
	 * This method is mutually exclusive with <code>graphData</code>.
	 * @param node
	 * @return
	 */
	ForceGraph3D addLink(Object link);	
		
}
