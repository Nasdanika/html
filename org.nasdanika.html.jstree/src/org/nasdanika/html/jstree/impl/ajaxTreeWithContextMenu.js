{ 
	'core' : { 
		'data' : { 
			'url' : '{{nodesUrl}}',
			'data' : function (node) {
				return { 'id' : node.id }; 
			} 
		} 
	},
	'plugins' : [ 'contextmenu' ],
	'contextmenu' : {
		'items' : function(node, callback) {
			$.getJSON("{{contextMenuUrl}}?id="+node.id, function(data, status) {
		        if ("success" == status) {
		        	// TODO - action to function - how - all actions are URL's or JSON objects and switch(type)?
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