<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Lis‰‰ ranking peliin</h1>

<form id="addRankingForm" method="post" action="<s:url value='/pokkare/addranking.action' />">
	<fieldset>
		<legend>valitse sijoitukset</legend>
	
		<s:iterator value="playerList">
			<div class="radioButtons">
				<s:property value="name" /><br />
				<s:iterator status="i" value="(playerListSize).{ #this }">
					<input type="radio" name="<s:property value='name' />" value="<s:property value="#i.count" />" /><s:property value="#i.count" /> <br/>
				</s:iterator>
			</div>
		</s:iterator>
		<br class="clear" />
	</fieldset>
	<div class="bigGT">-&gt;</div>
	<fieldset>
		<legend>valitse peli</legend>
		<s:iterator value="gamesList">
			<input type="radio" id="addranking_chosenGame<s:property value='id'/>" name="chosenGame" value="<s:property value='id'/>"/>
			<label for="addranking_chosenGame<s:property value='id'/>"><s:property value='gameDate'/> #<s:property value='gameNumber'/></label><br /> 
		</s:iterator>
	</fieldset>
	<div class="bigGT">-&gt;</div>
	<button>Lis‰‰ ranking</button>
</form>


<jsp:include page="include/footer.jsp"></jsp:include>

