define(['jquery', 'knockout', 'q', './../../toc.js', './../jstree/jstree.js', 'domReady!'], function(jQuery, ko, q, toc, jstree, doc) {
	var jToc = jQuery('#toc');
	
	var treeDeferred = q.defer();
	
	jToc.bind("ready.jstree", function(e, data) {
		treeDeferred.resolve(jToc);	
	});
	
	jToc.jstree({
		'core': { 
			'data': toc.tree 
			},
		'plugins' : ['search'],
		'search' : {
			show_only_matches : true,
			show_only_matches_children : true
		}
	}).bind("changed.jstree", function(e, data) {
		if (data.selected.length>0) {
			window.location = toc.idMap[data.selected[0]];
		}
	});

	var leftOverlay = jQuery("#left-overlay");		
	var jTocSearch = jQuery('#toc-search');
	var to = false;	
	jTocSearch.keyup(function () {
		if (to) { 
			clearTimeout(to); 
		}
		to = setTimeout(function () {
			var v = jTocSearch.val();
			jToc.jstree(true).search(v);
		}, 250);
	});	
		
	return treeDeferred.promise;	
});
