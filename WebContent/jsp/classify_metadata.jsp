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
					<label>Classification Algorithm</label><br>
						<input type="radio" name="algorithm_name" id="algorithm_name" value="NaiveBayes" checked="checked" onchange="" />Naive Bayes
						<input type="radio" name="algorithm_name" id="algorithm_name" value="SVM" onchange="" />SVM
						<input type="radio" name="algorithm_name" id="algorithm_name" value="AdaBoost" onchange="" />AdaBoost
					<br/>
					<br/>
					<label>Classification Model: </label>
						<input	type="text" 
								name="model_path" 
								id="model_path"
								size= "50"
								value= "/tmp/classifier/NaiveBayes.model" /><br>
					<label>Title: </label>
						<input	type="text" 
								name="media_title" 
								id="media_title"
								size= "50" /><br>
					<label>Abstract: </label>
						<input	type="text" 
								name="media_abstract" 
								id="media_abstract"
								size= "50" /><br>
					<label>Label: </label>
						<input	type="text" 
								name="media_label" 
								id="media_label"
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