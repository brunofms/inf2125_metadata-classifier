<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Classify Instance</title>
<link href="css/base.css" rel="stylesheet" media="screen"/>
<script type="text/javascript" src="js/base.js"></script>
</head>
<body>
	<div id="container">
		<div id="top"><jsp:include page="include/inc_top.jsp"/></div>
		<div id="left"><jsp:include page="include/inc_menu.jsp"/></div>
		<div id="right">
			<div id="r_header">
				<h2>Prediction Result</h2>
				<pre>
				<% 
				String predict = (String)request.getAttribute("predict");
				out.print(predict);
				%>
				</pre>
			</div>
		</div>
	</div>
</body>
</html>