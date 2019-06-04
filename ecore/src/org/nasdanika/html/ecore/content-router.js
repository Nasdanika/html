/*
 * Router customization service paths under 'content' and loading content from under 'doc' 
 */ 
$(document).ready(
			function() {

				var Workspace = Backbone.Router.extend({

					routes : {
							"content/*path" : function(path) {
								var target = $("#content");
								if (target.length) {
									var loadingImage = "<table style='width: 100%; height:"+target.height()+"px; background:#EEE'>"
										+ "<tr valign='middle'>" + "<td align='center'>"
										+ '<div class="progress progress-striped active" style="width: 120px">'
										+ '<div class="progress-bar"  role="progressbar" style="width: 100%">'
										+ 'Loading...'
										+ '</div>'
										+ '</div>'
										+ "</td>" + "</tr>" + "</table>";
	
								target.html(loadingImage);
								                
								target.load("doc/"+path, null, function(responseText, textStatus, req) {									
									if (textStatus == "error") {
										if (responseText) {
											var errorMessage = '<div class="card text-white bg-danger">'
												+ '  <div class="card-header">Error loading "'+path+'"</div>'
												+ '  <div class="card-body">'
												+ '    <p class="card-text">'+responseText+'</p>'
												+ '  </div>'
												+ '</div>';
											target.html(errorMessage);
										} else {
											target.html("<div class=\"alert alert-danger\">Error loading <b>"+path+"</b></div>");
										}
									}
								});
							} else {
								alert("Element not found: " + targetId);
							}
						}
					}

				});

				workspace = new Workspace();
				Backbone.history.start();
				
				workspace.navigate('content/{{initial-route}}', {
					trigger : true
				});
				
			}
); 
