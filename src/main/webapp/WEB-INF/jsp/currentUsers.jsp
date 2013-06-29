<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"	rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet"	type="text/css" />

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>


<body>

   	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
      	<div class="container-fluid">
<!--  -->


<a class="btn btn-navbar" data-toggle="collapse"
        data-target=".nav-collapse"> <span class="icon-bar"></span> <span
        class="icon-bar"></span> <span class="icon-bar"></span>
</a>
<a class="brand" href="index.html">Your Friendly Insurance</a>
<div class="nav-collapse collapse">
        <p class="navbar-text pull-right">
        <!-- Login -->
        <form class="form-inline">
                <input type="text" class="input-small" placeholder="Email"> <input
                        type="password" class="input-small" placeholder="Password"> <label
                        class="checkbox"> <input type="checkbox"> Remember me
                </label>
                <button type="submit" class="btn">Sign in</button>
        </form>

        <div id="loggedout" style="float: right; display: none">
                (<a href="#" style="color: #ffffff" id="logoutlink"> <strong>logout</strong></a>)
        </div>
        <div id="loggedin" style="float: right; display: none"></div>

        <ul class="nav">
                <li class="active"><a href="<c:url value="/" />"> Home</a></li>
                <li><a href="<c:url value="/about" />">About</a></li>
                <li><a href="<c:url value="/hotels/main" />">Policies</a></li>
        </ul>
        
        <a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
</div>
<!--  -->


        </div>			      		 
      </div>
   	</div>

   	<div class="container-fluid">
	  <div class="row-fluid">
        <div class="span2">
          <div class="well sidebar-nav">

          </div>
        </div>
        
        <div class="span10">         
            <div class="hero-unit">

            </div>
            <div class="row-fluid">
              <div class="span12">
              
				<table class="table table-bordered">
				<thead>
				<tr>
					<th>N</th>
					<th>User Name</th>
					<th>IP</th>
					<th>NASID</th>
					<th>Start Time</th>
					<th>#</th>
					
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${currentUsers}" var="currentUser"	varStatus="theCount">
				
				<tr>
					<td>${theCount.count}</td>
					<td>${currentUser.userName}</td>
					<td>${currentUser.userIP}</td>
					<td>${currentUser.nasid}</td>
					<td>${currentUser.dateStartString}</td>
					
					<td>
							<a class="btn-mini" href="#"><i class="icon-tags"></i></a>
							<a class="btn-mini" href="#"><i class="icon-trash"></i></a>
					</td>
				</tr>
				</c:forEach>
				</tbody>	
				</table>
               
                
              </div>
              </div><!--/span-->
            </div><!--/row-->                 
        </div><!--/span-->
      </div><!--/row-->

      <hr>

  	  <footer>


  	  </footer>

    </div>

	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script> 
    <script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/main.js" />"></script>
    
</body>

</html>
