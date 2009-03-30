<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1>Lis‰‰ pelaajia</h1>

<form id="add_players_form" action="<s:url value='pokkare/addplayer.action'/>" method="post" >
	<label for="add_player_name">Uuden pelaajan nimi:</label>
	<input type="text" id="add_player_name" name="add_player_name" value="" /> <br />
	<input type="submit" value="Lis‰‰ pelaaja">
</form>

<h1>Poista pelaajia</h1>

<jsp:include page="include/footer.jsp"></jsp:include>