<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://big.faceless.org/products/graph" prefix="bfg" %>
<%@ page import="pokkare.service.EventService,pokkare.model.*,pokkare.action.ViewRankingAction,java.util.List" %>

<div class="viewranking">
Ranking
</div>
<br /><br />
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
<!--  

<img src="../pokkaregraph.jpg" />


 
<table border="0" cellpadding = 10>
<tr>

Zoom:

<td><a href='<s:url value="viewranking.action?size=minus" />'> - </a></td>
<td><a href='<s:url value="viewranking.action" />'> 0 </a></td>
<td><a href='<s:url value="viewranking.action?size=plus" />'> + </a></td>

<td>

</td>
</tr>
</table>
<br />

-->
 
<jsp:include page="include/footer.jsp"></jsp:include>