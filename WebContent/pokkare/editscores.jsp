<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Editoi scoreja</h1>

<div style="float:left; width: 10em;" id="editScoresLeftPane">
	Valitse peli:<br /> 
	<form id="chosenGameForm" action="<s:url value='pokkare/editranking.action' />" method="post"> 
		<s:iterator value="games">
			<input type="radio" value="${id}" name="chosenGame" /><s:property value="gameDate" /> #<s:property value="gameNumber" /> <br />
		</s:iterator>
	</form>
</div>

<div id="editScoresRightPane">
	<s:if test="playerScores != null && playerScores.size() > 0">
		<form id="chosenScoresForm">
			<div id="hiddenEditScores" style="display: none">
				${chosenGame}|||<s:iterator value="playerScores">&quot;<s:property value="key"/>&quot;:&quot;<s:property value="value"/>&quot;,</s:iterator>
			</div>
		</form>
	</s:if>
	<s:else>
		<button id="chooseGameButton" style="margin-left: 3em; margin-top: 3em">Seuraava &gt;</button>
	</s:else>
</div>

<jsp:include page="include/footer.jsp"></jsp:include>

