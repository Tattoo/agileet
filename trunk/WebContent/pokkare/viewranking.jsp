<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg" %>
<%@ page import="pokkare.service.EventService,pokkare.model.*,pokkare.action.ViewRankingAction,java.util.List" %>

<h1>Ranking</h1>
<br /><br />
<!-- 
<center>
	<bfg:axesgraph width="400" height="300">
		<bfg:barseries name="Test" barwidth="0.8">
			<s:iterator value="scores">
				<bfg:data x="${key}" y="${value}" />
			</s:iterator>
		</bfg:barseries>
	</bfg:axesgraph>
</center>
<br /><br />
 -->
<center> 
	<img src="../pokkaregraph.jpg" />
</center>
<jsp:include page="include/footer.jsp"></jsp:include>