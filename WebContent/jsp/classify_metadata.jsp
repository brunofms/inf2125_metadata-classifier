<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Build Classification Model</title>
<link href="css/base.css" rel="stylesheet" media="screen"/>
<script type="text/javascript" src="js/base.js"></script>
</head>
<body>
	<form action="retrieve-prediction.cmd" name="form1" onsubmit="">
		<div id="container">
			<div id="top"><jsp:include page="include/inc_top.jsp"/></div>
			<div id="left"><jsp:include page="include/inc_menu.jsp"/></div>
			<div id="right">
				<div id="r_header">
					<h2>Classify Metadata</h2>
					<label>Classification Model</label><br>
						<input type="radio" name="rad_algo" id="rad_algo" value="NB" checked="checked" onchange="" />Naive Bayes
						<input type="radio" name="rad_algo" id="rad_algo" value="SVM" onchange="" />SVM
						<input type="radio" name="rad_algo" id="rad_algo" value="AB" onchange="" />AdaBoost
					<br/>
					<br/>
					<label>Title: </label>
						<input	type="text" 
								name="title_metadata" 
								id="title_metadata"
								size= "50" /><br>
					<label>Abstract: </label>
						<input	type="text" 
								name="abstract_metadata" 
								id="abstract_metadata"
								size= "50" /><br>
					<label>Label: </label>
						<input	type="text" 
								name="label_metadata" 
								id="label_metadata"
								size= "50"
								height="20" />
					<br/>
					<br/>
					<input type="submit" value="Classify"/>
					<br/>
					<br/>
				</div>
			</div>
		</div>
	</form>
</body>
</html>