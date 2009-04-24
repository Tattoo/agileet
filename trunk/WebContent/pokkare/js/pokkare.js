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
		
		// check that error box is visible on page, otherwise skip
		if($(".errors").height() != 0){
			// cornerize and set one-time event to hide the box when clicked 
			$(".errors").corner("5px").one("click", function(){
				$(".errors").slideUp();
			});
		}
		
		// check that notification box is visible on page, otherwise skip
		if($(".actionmessages").height() != 0 ){
			// cornerize and set one-time event to hide the box when clicked 
			$(".actionmessages").corner("5px").one("click", function(){
				$(".actionmessages").slideUp();
			});
		}
		
		var windowLocation = String(window.location);
		
		if(windowLocation.indexOf("addevent") != -1){ // check that we are in right url
			$("#time").datepicker({
				showOn: 'button', 
				buttonImage: '/agileet/pokkare/images/datepicker.gif', 
				buttonImageOnly: true,
				firstDay: 1,
				appendText: ' <small>(mm-dd-yyyy)</small>'
			});
		}
		
		// check that we are in the right url
		if (windowLocation.indexOf("addplayer") != -1 || windowLocation.indexOf("deleteplayer") != -1 
			|| windowLocation.indexOf("reactivateplayer") != -1){
			 
			// validate that player name is not empty when creating player
			$("#add_players_form").submit(function(event){
				if ($("#add_player_name").attr("value") == "" ){
				
					// add hide-event, show it, add error message and cornerize the error box
					$(".errors").one("click", function(){
						$(".errors").slideUp();
					}).animate({height: "1.7em", lineHeight: "1.7em"}, 1000).text("Pelaajan nimi ei saisi olla tyhjä").corner("5px");
					event.preventDefault();
				}
			});
			

			// attach either delete player- or reactivate player-functionality
			// to all list items in a page 
			$("#deletePlayersList > li, #reactivatePlayersList > li").each(function(){
				var el = $(this);
				
				// hovers effects for player lists				
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
				
				var parentId = el.parent().attr("id");
	
				el.click(function(event){
					var player = jQuery.trim(el.text());
					if(player == ""){
						alert("FFFFFFFFFFFFUUUUUUUUUUUUUUUU--");
						return;
					}
					// create form ad hoc and send it
					var url = windowLocation.substring(0, windowLocation.lastIndexOf("/")+1);
					var input = ""; 
					if (parentId == "deletePlayersList"){ // delete player-functionality
						url += "deleteplayer.action"
						input = '<input type="text" name="delete_player_name" id="delete_player_name" value="'+player+'"/>';
					}
					else if (parentId == "reactivatePlayersList"){ // reactivate player-functionality
						url += "reactivateplayer.action";
						input = '<input type="text" name="reactivate_player_name" id="reactivate_player_name" value="'+player+'"/>';
					}
					var html = '<form id="PlayerForm" action="'+url+'" style="display:none;" method="post">'+input+'</form>'
					$("#index").append(html);
					$("#PlayerForm").submit();
				});
			});
		}
		if (windowLocation.indexOf("viewranking") != -1){
			// show game number if there's more games with same date
			// by default, game number isn't shown
			$(".games_list > dl > dd").each(function(){
				var el = $(this);
				var thisGameDate = jQuery.trim(el.text().split(" ")[0]);
				var nextGameDate = jQuery.trim(el.next().next().text().split(" ")[0]);
				
				if (thisGameDate == nextGameDate){
					el.find("p > span.gameNumber").css("visibility", "visible");
					el.next().next().find("p > span.gameNumber").css("visibility", "visible");
				}
			});
		}
		if (String(window.location).indexOf("editranking") != -1){
			
			if ($("#hiddenEditScores").length > 0){
				var data = $("#hiddenEditScores").text();
				$("#hiddenEditScores").remove();
				
				var chosenGame = jQuery.trim(data.split("|||")[0]);
				var selector = 'input[value="'+chosenGame+'"]';
				$("#editScoresLeftPane > form > "+selector).attr("checked","checked");
				
				data = data.split("|||")[1];
				data = eval("({"+jQuery.trim(data.substr(0, data.lastIndexOf(",")))+"})");
		
				// create html from data
				var html = '<form action="editranking.action" method="post">';
				
				// get the length of object
				var datalength = 0;
				for (var x in data){
					datalength++;
				}
				
				// generate helper text
				html += '<div class="editScoresPlayerList"><b>Sija</b><br />';
				for (var i = 0; i < datalength; i++){
					html += '<span>'+(i+1)+'.</span><br />'
				}
				html += '<span>Poista</span><br />';
				html += '</div>';
				
				// generate player lists
				for (var key in data){
					html += '<div class="editScoresPlayerList">'
					html += '<b>'+key+'</b><br />';
					for (var i = 0; i < datalength; i++){
						if ((i+1) == new Number(data[key])){
							html += '<input type="radio" name="'+key+'" value="'+(i+1)+'" checked="checked" /><br />';
						}
						else{
							html += '<input type="radio" name="'+key+'" value="'+(i+1)+'" /><br />';
						}
					}
					html += '<input type="radio" name="'+key+'" value="-1" /><br />';;
					html += '</div>';
				}
				html += '<br class="clear" /><br />';
				html += '<button id="saveButton">Tallenna</button>';
				html += '</form>'
				$("#editScoresRightPane").html(html);
				
			}
			else {
				$("#chooseGameButton").click(function(e){
					e.preventDefault();
					var el = $("#editScoresLeftPane input:checked");
					
					if(el.length < 1){
						// add hide-event, show it, add error message and cornerize the error box
						$(".errors").one("click", function(){
							$(".errors").slideUp();
						}).animate({height: "1.7em", lineHeight: "1.7em"}, 1000).text("Et ole valinnut peliä.").corner("5px");
						return;
					}
					if (el.length > 1){
						// add hide-event, show it, add error message and cornerize the error box
						$(".errors").one("click", function(){
							$(".errors").slideUp();
						}).animate({height: "1.7em", lineHeight: "1.7em"}, 1000).text("Useampi peli valittuna, virhe.").corner("5px");
						return;
					}
					$("#chosenGameForm").submit();
	
				});
			}
		}
	});

})();