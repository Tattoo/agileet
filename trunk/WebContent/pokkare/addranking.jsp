<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="pokkare.service.EventService,java.util.ArrayList,pokkare.model.*" %>

<h1>Lis‰‰ ranking peliin</h1>

<div class="errors"><s:actionerror value="testi"/></div>

${chosenGame}

<s:form name="formi" validate="true" method="POST">
	valitse is‰nt‰:<br /><br />
	
	<s:iterator value="playerList">
		<div class="radioButtons">
			<s:property value="name" /><br />
			<s:iterator status="i" value="(playerListSize).{ #this }">
				<input type="radio" name="<s:property value='name' />" value="<s:property value="#i.count" />" /><s:property value="#i.count" /> <br/>
			</s:iterator>
		</div>
	</s:iterator>
	<br class="clear" /><br />

	<s:radio name="chosenGame" list="gamesMap" label="valitse peli" labelposition="top"/>
	<s:submit />
</s:form>


<jsp:include page="include/footer.jsp"></jsp:include>

