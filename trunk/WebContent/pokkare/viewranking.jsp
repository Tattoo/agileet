<%@page import="pokkare.service.ScoreDataWrapper.ScoreData"%>
<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg"%>
<%@ page import="pokkare.service.EventService,pokkare.model.*,pokkare.action.ViewRankingAction,pokkare.service.ScoreDataWrapper,pokkare.service.ScoreDataWrapper.ScoreData,java.util.List"%>

<h1>Ranking</h1>
<br />
<br />
<center>
<% 
ScoreDataWrapper scores = new ScoreDataWrapper();
int imageHeight = scores.getMaxScore() * 10;
int imageWidth = scores.getNumberOfGames() * 15;
session.setAttribute("image_height", imageHeight <= 500 ? imageHeight : 500);
session.setAttribute("image_width", imageWidth <= 500 ? imageWidth : 500);
%>

<bfg:axesgraph width="${image_width}" height="${image_height}" backwallpaint="stripe(#eeeeee,axis=bottom,line=#cccccc,altaxis=left)">
	<bfg:label font="16pt bold Arial">Pelaajien ranking</bfg:label>
	<bfg:axis pos="left" type="int">
		<bfg:label font="12pt bold Arial">Pelaajien pisteet</bfg:label>
	</bfg:axis>
	<bfg:axis pos="bottom" type="int">
		<bfg:label font="12pt bold Arial">Pelit</bfg:label>
	</bfg:axis>
	
	
	<s:iterator value="scoreDatas">
		<s:if test="firstInSeries == true">
			<bfg:lineseries name="<s:property value='player.name'/>" color="#193399" linethickness="2">		
		</s:if>
		
		<bfg:data x="<s:property value='positionInSeries'/>" y="<s:property value='score'/>"/>
		
		<s:if test="lastInSeries == true">
			</bfg:lineseries>		
		</s:if>
	</s:iterator>
	
	

</bfg:axesgraph>

</center>
<br />
<br />
<!-- 
<center> 
	<img src="../pokkaregraph.jpg" />
</center>
 -->
<jsp:include page="include/footer.jsp"></jsp:include>