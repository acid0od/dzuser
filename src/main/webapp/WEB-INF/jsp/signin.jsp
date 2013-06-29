<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value="/resources/css/bootstrap-responsive.min.css" />"
	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"
	type="text/css" />

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>

<title>Insert title here</title>
</head>
<body>
	
	<div style="color: red">${message}</div>
	<div class="row-fluid">
	<div class="span5 offset2">
	<div class="hero-unit">
		<h3>Spring Security - Sign In</h3>
		<div class="container">

			<form class="form-horizontal" action="j_spring_security_check"
				method="post">
				<div class="control-group">
					<label class="control-label" for="j_username">User Name</label>
					<div class="controls">
						<input type="text" id="j_username" name="j_username"
							placeholder="User Name">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="j_password">Password</label>
					<div class="controls">
						<input type="password" id="j_password" name="j_password"
							placeholder="Password">
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<label class="checkbox"> <input type="checkbox"
							name="_spring_security_remember_me"> Remember me
						</label>
						<button type="submit" name="commit" class="btn" value="Login">Sign
							in</button>
					</div>
				</div>
			</form>

		</div>
	</div>
	</div>
	</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript"
		src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/json2.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/date.format.js" />"></script>
	<script type="text/javascript"
		src="<c:url value="/resources/js/main.js" />"></script>

</body>
</html>