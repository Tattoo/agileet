<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.HashMap" %>

<div class="errors"><s:actionerror/></div>
<div class="actionmessages"><s:actionmessage/></div>
<h1>Ranking</h1>
<br />

<s:iterator value="ranking">
	<s:property /> <br />
</s:iterator>


<jsp:include page="include/footer.jsp"></jsp:include>









