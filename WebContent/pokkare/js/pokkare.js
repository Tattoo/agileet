			(function(){
				$(document).ready(function(){
					$("#index").corner("30px");
					$("#menu").corner("4px sharp");
					$("#menu li:last").css("margin-right","0em");
					$("#menu li a").hover(function(){
						$(this).css("border-bottom", "1px dashed");
					}, function(){
						$(this).css("border-bottom", "none");
					});
					if(String(window.location).indexOf("addevent") != -1){ // check that we are in right url
						$("#time").datepicker({
							showOn: 'button', 
							buttonImage: '/agileet/pokkare/images/datepicker.gif', 
							buttonImageOnly: true,
							firstDay: 1,
							appendText: ' <small>(mm-dd-yyyy)</small>'
						});
					}
				});
			})();