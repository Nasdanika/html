// Tokens: searchInputSelector, timer, treeSelector
$('{{searchInputSelector}}').keyup(function () {
    if ({{timer}}) { 
		clearTimeout({{timer}}); 
	}
    {{timer}} = setTimeout(function () {
      var v = $('{{searchInputSelector}}').val();
      $('{{treeSelector}}').jstree(true).search(v);
    }, 250);
  });
