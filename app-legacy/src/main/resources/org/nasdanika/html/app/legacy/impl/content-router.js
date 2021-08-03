/*
 * Content router script. Processes 'router/<target id>/<conent path>' route by loading content from the path to the element with target id.
 * For example: 
 * - https://myapp/mypage.html#router/content/some/info will load https://myapp/some/info into an HTML element with id 'content'.
 * - https://myapp/mypage.html#router/footer//other/stuff will load https://other/stuff into an HTML element with id 'footer'.
 * 
 * The router dims the target element and displays "Loading..." progress bar. 
 * If load fails it displays an error message.
 * 
 */ 
$(document).ready(
			function() {

				var Workspace = Backbone.Router.extend({

					routes : {
							"router/:targetId/*path" : function(targetId, path) {
								var target = $("#"+targetId);
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
								                
								target.load(path, null, function(responseText, textStatus, req) {									
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
				
				// Example of initial routing with the initial route taken from 'inital-route' token.
				//if (!document.location.hash) {
				//	workspace.navigate('router/{{initial-route}}', {
				//		trigger : true
				//	});
				//}
				
			}
); 
