<jsp:include page="include/header.jsp"></jsp:include>
<%@ taglib prefix="s" uri="/struts-tags" %>

<h1>Uusi peli</h1>
<form name="formi" id="addevent_form" method="post" action="<s:url value='/pokkare/addevent.action' />">
	<label for="desc">kuvaus:</label>
	<textarea name="desc" rows="2" cols="1" style="width: 11.4em;"></textarea> <!-- manual fix for the width needed-->
	<br class="clear" />
	<label for="time">aika:</label>
	<input type="text" name="time" id="time" value="" />
	<br class="clear" />
	
	<label for="hosts">isäntä:</label>
	<s:iterator value="hosts">
		<input type="radio" name="host" value="${key}" />${value} 
	</s:iterator>
	<br class="clear" />
	
	<input type="submit" value="Submit"/>
</form>



<jsp:include page="include/footer.jsp"></jsp:include>




