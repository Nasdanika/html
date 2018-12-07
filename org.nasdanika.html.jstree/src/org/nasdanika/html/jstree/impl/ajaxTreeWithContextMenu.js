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
	},
	'plugins' : [ 'contextmenu' ],
	'contextmenu' : {
		'items' : function(node, callback) {
			$.getJSON({{contextMenuUrl}}, function(data, status) { // Expression, use node.id to access node id. 
		        if ("success" == status) {
		        	for (var key in data) {
		        	    if (data.hasOwnProperty(key)) {
		        	    	var item = data[key];
		        	    	if (item.action) {
		        	    		item.action = function(action) { eval(action); }.bind(item, item.action);		        	    			
		        	    	}
		        	    }
		        	}
		        	callback(data);
		        } else {
		        	console.error("Error loading context menu: "+status);
		        }
		    });			
		}
	}
}