<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="c" prefix="c"%>
<%@ taglib uri="fmt" prefix="fmt"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="css/base.css" rel="stylesheet" media="screen"/>
	</head>
	<body>
		<div id="container">
			<div id="top"><jsp:include page="include/inc_topo.jsp"/></div>
			<div id="left"><jsp:include page="include/inc_menu.jsp"/></div>
			<div id="right">
				<label>Ocorreu um erro inesperado no sistema.</label>
				<br/>
				<c:forEach var="erro" items="${erros}" varStatus="status">
					<c:out value="${erro}"/>
				</c:forEach>
			</div>
		</div>
		
		
	</body>
</html>