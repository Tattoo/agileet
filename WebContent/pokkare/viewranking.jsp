<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="pokkare.service.EventService,pokkare.model.*,pokkare.action.ViewRankingAction,java.util.List" %>

<div class="viewranking">
Ranking
</div>


<s:iterator value="ranking">
	<s:property />
</s:iterator>

<img src="pokkaregraph.jpg" />

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

<jsp:include page="include/footer.jsp"></jsp:include>