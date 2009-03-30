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
				buttonImage: '/pokkare/pokkare/images/datepicker.gif', 
				buttonImageOnly: true,
				firstDay: 1,
				appendText: ' <small>(mm-dd-yyyy)</small>'
			});
		}
		
		if (String(window.location).indexOf("addplayer") != -1){ // check that we are in the right url
			// check that player name is not empty when creating player
			$("#add_players_form").submit(function(event){
				if ($("#add_player_name").attr("value") == "" ){
					alert("Pelaajan nimi ei saisi olla tyhjä");
					event.preventDefault();
				}
			});
			
			$("#deletePlayersList > li").each(function(){
				var el = $(this);
				el.hover(function(){
					el.css({
						"background-color": "rgb(133, 66, 66)", 
						"color":"#fff", 
						"cursor":"pointer"
					});
				}, function(){
					el.css({
						"background-color":"#ccc", 
						"color":"#000"
					});
				});
				
				el.click(function(event){
					var deletePlayer = jQuery.trim(el.text());
					if(deletePlayer == ""){
						console.debug("FFFFFFFFFFFFUUUUUUUUUUUUUUUU--");
						return;
					}
					var url = String(window.location).replace("add", "delete");
					var html = '<form id="deletePlayerForm" action="'+url+'" style="display:none;" method="post">'+
								'<input type="text" name="delete_player_name" id="delete_player_name" value="'+deletePlayer+'"/>'+
					  			'</form>'
					$("#index").append(html);
					$("#deletePlayerForm").submit();
				});
			});
		}
	});
})();