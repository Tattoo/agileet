<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg"%>
<%@ page import="java.util.ArrayList"%>

<%  ArrayList<String> colors = new ArrayList<String>();
	colors.add("#339900"); colors.add("#FF9900"); colors.add("#993399"); colors.add("#CC0033");
	colors.add("#3366FF");
%>

<h1>Ranking</h1>
<div class="errors"><s:actionerror/></div>
<div class="actionmessages"><s:actionmessage/></div>
<br />
<br />
<center>
<bfg:axesgraph width="500" alt="Ranking" height="500" backwallpaint="stripe(#eeeeee,axis=bottom,line=#cccccc,altaxis=left)">
	<bfg:label font="16pt bold Arial">Pelaajien ranking</bfg:label>
	<bfg:axis pos="left" type="int">
		<bfg:label font="12pt bold Arial">Pelaajien pisteet</bfg:label>
	</bfg:axis>
	<bfg:axis pos="bottom" type="int">
		<bfg:label font="12pt bold Arial">Pelit</bfg:label>
	</bfg:axis>
	

	<s:iterator value="scores">
		<bfg:lineseries name="${key}" color="<%= colors.remove(0) %>" linethickness="2">
			<s:iterator value="value">
				<bfg:data x="${key}" y="${value}"/>
			</s:iterator>
		</bfg:lineseries>
	</s:iterator>
	
	<bfg:key align="bottom" color="#eeeeee">
		<s:iterator value="scores">
  			<bfg:keyitem series="${key}"/>
		</s:iterator>
	</bfg:key>

</bfg:axesgraph>


<h1>Pelit</h1>
<div class="games_list">
	<dl>
		<s:iterator value="gamesList">
			<dt><s:property value="positionInSeries"/></dt>
			<dd>
				<p>
					<s:property value="gameDate"/>#<s:property value="gameNumber"/>
				</p>
			</dd>
		</s:iterator>
	</dl>
</div>

</center>
<br />
<br />

<jsp:include page="include/footer.jsp"></jsp:include>