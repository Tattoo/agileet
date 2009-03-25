<?xml version="1.0" encoding="ISO-8859-15" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="defs.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>Pokkare : Poker Ranking System</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-15" />
		<link rel="stylesheet" type="text/css" href="<s:url value="/pokkare/styles/main.css" />" /> 
		<script type="text/javascript" src="<s:url value="/pokkare/js/jquery-1.3.2.min.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/pokkare/js/jquery.corner.js"/>"></script>
		<script type="text/javascript" src="<s:url value="/pokkare/js/jquery-ui-1.7.1.custom.min.js"/>"></script>
		<script type="text/javascript">
			(function(){
				$(document).ready(function(){
					$("#index").corner("30px");
					$("#menu").corner("4px sharp");
					$("#menu li:last").css("margin-right","0em");
					$("#menu li a").hover(function(){
						$(this).css("border-bottom", "1px dashed");
					}, function(){
						$(this).css("border-bottom", "none");
					});
					if(String(window.location).indexOf("addevent") != -1){ // check that we are in right url
						$("#time").datepicker({
							showOn: 'button', 
							buttonImage: '<s:url value="/pokkare/images/datepicker.gif" />', 
							buttonImageOnly: true,
							firstDay: 1,
							appendText: ' <small>(mm-dd-yyyy)</small>'
						});
					}
				});
			})();
		</script>
	</head>

	<body>
	
		<div id="index">
			<div id="content"> <!-- needed because jquery.corners-plugin breaks if #index has padding -->
				<ul id="menu">
					<center>
						<li><a href='<s:url value="index.action" />'>index</a></li>
						<li><a href='<s:url value="addevent.action" />'>uusi peli</a></li> 
						<li><a href='<s:url value="addranking.action" />'>lisää ranking peliin</a></li> 
						<li><a href='<s:url value="viewranking.action" />'>ranking</a></li>
					</center>
				</ul>