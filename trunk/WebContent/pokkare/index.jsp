<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.HashMap" %>

<div class="index">
<table border="0" cellpadding = 10>
<tr>
<td><a href='<s:url value="addevent.action" />'> uusi peli </a></td>
<td><a href='<s:url value="addranking.action" />'> lisää ranking peliin </a></td>
<td><a href='<s:url value="viewranking.action" />'> ranking </a> </td>
</tr>
</table>
</div>

<h2>Ranking:</h2>
<br />

<s:iterator value="ranking">
	<s:property />
</s:iterator>











