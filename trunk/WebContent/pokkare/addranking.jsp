<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="pokkare.service.EventService,java.util.ArrayList,pokkare.model.*" %>

<div class="addranking">
Lis‰‰ ranking peliin
</div>

<div class="errors"><s:actionerror value="testi"/></div>

${chosenGame}

<s:form name="formi" validate="true" method="POST">
	<s:radio name="chosenGame" list="gamesMap" label="Valitse peli" labelposition="top"/>
	
	
	
	<% 
	EventService e = new EventService();
	ArrayList<Player> a = (ArrayList<Player>)e.findPlayers();
	out.print("<table border=\"0\" cellpadding=\"5\"><tr>");
	for (int i = 0; i < a.size(); ++i) {
		out.print("<td>");
		out.print(a.get(i).getName() + ":<br/>");
		for (int j = 0; j < a.size(); ++j) {
			out.print("<input type=\"radio\" name=\"" + a.get(i).getName() + "\" value=\"" + (j + 1) + "\">" + (j + 1) + "<br />");
		}
		out.print("</td>");
	}
	out.print("</tr></table>");
	
	%>
	
	<!--<s:iterator value="playerList" status="testi">
		<s:property value="name"/>
		<s:radio list="{1,2,3,4,5,6,7,8}" label="???" />
		
		<br />
	</s:iterator>
-->
	<s:submit />
</s:form>



<jsp:include page="include/footer.jsp"></jsp:include>
