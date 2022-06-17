<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--  No scriptlets!!! 
	  See http://download.oracle.com/javaee/5/tutorial/doc/bnakc.html 
-->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="model" class="presentation.web.model.NewCheckOcupationModel" scope="request"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/resources/app.css"> 
<title>EspacoGes: Check Ocupation</title>
</head>
<body>
<h2>Check Ocupation</h2>
<form action="visualizacao" method="post">

	<div class="mandatory_field">
		<label for="desconto">Space Name:</label>
		<select name="space">  
			<c:forEach var="space" items="${model.spaces}">
				<c:choose>
    				<c:when test="${model.space == space.name}">
						<option selected = "selected" value="${space.name}">${space.name}</option>
				    </c:when>
				    <c:otherwise>
				    	<option value = "${space.name}">${space.name }</option>
				    </c:otherwise>
				</c:choose>
			</c:forEach> 
		</select>
   </div>

    <div class="mandatory_field">
    	<label for="space">Start Date:</label> 
    	<input type="text" name="startDate" value="${model.startDate}"/> 
    </div>
    <div class="mandatory_field">
		<label for="startTime">Start Time:</label> 
		<input type="text" name="startTime" value="${model.startTime}"/> <br/>
    </div>
   <div class="optional_field">
   		<label for="endDate">End Date:</label> 
   		<input type="text" name="endDate" value="${model.endDate}"/>
   </div>
   
   <div class="optional_field">
   		<label for="endTime">End Time:</label> 
   		<input type="text" name="endTime" value="${model.endTime}"/>
   </div>
   
   <div class="button" align="right">
   		<input type="submit" value="Check ocupation">
   </div>
</form>
<c:if test="${model.hasMessages}">
	<p>Mensagens</p>
	<ul>
	<c:forEach var="mensagem" items="${model.messages}">
		<li>${mensagem} 
	</c:forEach>
	</ul>
</c:if>
</body>
</html>