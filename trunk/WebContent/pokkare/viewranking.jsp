<%@page import="pokkare.service.ScoreDataWrapper.ScoreData"%>
<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg"%>
<%@ page import="java.util.ArrayList"%>

<%  ArrayList<String> colors = new ArrayList<String>();
	colors.add("#339900"); colors.add("#FF9900"); colors.add("#993399"); colors.add("#CC0033");
	colors.add("#3366FF");
%>

<h1>Ranking</h1>
<br />
<br />
<center>
<bfg:axesgraph width="${image_width}" height="${image_height}" backwallpaint="stripe(#eeeeee,axis=bottom,line=#cccccc,altaxis=left)">
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

</center>
<br />
<br />

<jsp:include page="include/footer.jsp"></jsp:include>