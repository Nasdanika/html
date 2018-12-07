{ 
	'core' : { 
		'data' : { 
			'url' : function (node) {
				return {{nodesUrl}}; // Expression, use node.id to access node id.
			},
			'data' : function (node) {
				return { 'id' : node.id }; 
			} 
		} 
	} 
}