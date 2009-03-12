<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="pokkare.service.EventService,pokkare.model.*,java.util.List" %>


<div class="addevent">
Lis‰‰ peli
</div>

<s:form name="formi" method="POST">
	<s:head theme="ajax" /> <!% mit‰ t‰m‰ edes tekee? datetimepicker ei toimi ilman... %>
	<s:textarea name="desc" label="kuvaus" />
	<s:datetimepicker name="time" label="aika" value="today" />
	<s:radio name="host" list="hosts" label="is‰nt‰"/>
	<s:submit />
</s:form>



<jsp:include page="include/footer.jsp"></jsp:include>




