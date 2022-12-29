// Script for full-text search of JsTree

window.nsdJsTreeSearchCallback = function(str, node) { 
    var sf = new $.vakata.search(str, true, { caseSensitive : false, fuzzy : false }); 
    if (sf.search(node.text).isMatch) {
		return true;
	} 
    let searchResult = this.search(str); 
    for (const sr in searchResult) {
        if (searchResult[sr].ref === node.original['data-nsd-action-uuid']) {
            return true;
        } 
    } 
    return false; 
}.bind(lunr(function () {
			  this.ref('id');
			  this.field('title');
			  this.field('content');

			  for (const key in searchDocuments) {
				  let doc = searchDocuments[key];
				this.add({
					id: doc['action-uuid'],
					title: doc.title,
					path: doc.path,
					content: doc.content
				});  
			  }
			}));