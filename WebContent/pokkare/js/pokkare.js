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
		
		if (String(window.location).indexOf("viewranking") != -1){ // check that we are in the right url
			
			var sortList = $(".games_list > dl").find("dt");
			var flag = true;
			while (flag){
				flag = false;
				for (var i = 0; i < sortList.length-1; i++){
					if (sortList[i].innerHTML > sortList[i+1].innerHTML){
						var temp = sortList[i];
						sortList[i] = sortList[i+1];
						sortList[i+1] = temp;
						flag = true;
					}
				}
			}
			var html = "";
			jQuery.each(sortList, function(i, element){
				html +=  "<dt>"+$(element).text()+"</dt>";
				html +=  "<dd>"+$(element).next().text()+"</dd><br />";
			});
			$(".games_list > dl").html(html);
			
			// remove the game number
			$(".games_list > dl > dd").each(function(){
				var content = $(this).text().split(" ");
				$(this).text(content[0]);
			});
		}
	});
})();