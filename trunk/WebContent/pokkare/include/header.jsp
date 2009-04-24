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
		<script type="text/javascript" src="<s:url value="/pokkare/js/pokkare.js"/>"></script>
	</head>

	<body>
	
		<div id="index">
			<div id="content"> <!-- needed because jquery.corners-plugin breaks if #index has padding -->
				<center>
					<ul id="menu">

						<li><a href='<s:url value="index.action" />'>index</a></li>
						<li><a href='<s:url value="addevent.action" />'>uusi peli</a></li> 
						<li><a href='<s:url value="editranking.action" />'>editoi scoreja</a></li> 
						<li><a href='<s:url value="addplayer.action" />'>lisää tai poista pelaaja</a></li> 
						<li><a href='<s:url value="viewranking.action" />'>ranking</a></li>

					</ul>
				</center>
				<div class="errors"><s:actionerror/></div>
				<div class="actionmessages"><s:actionmessage/></div>
				<br class="clear" />