<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Đăng nhập</title>

	<!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <!-- bootstrap 4 cdn link  -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <!-- link icon favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value='static/image/favicon-32x32.png'/>">
    <!-- custom css file link  -->
    <link rel="stylesheet" href="<c:url value='static/css/style.css'/>">
	<link rel="stylesheet" href="static/css/register-login-customer.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_header.jsp"></jsp:include>

	<div class="container">
		<div class="img">
			<img src="static/image/background-login.svg">
		</div>
		<div class="login-content">
			<form action="login" method="post">
				<input name="next" type="hidden" value="${param.next}"/>
				<img src="static/image/avatar.svg" alt="logo-login">
				<h2 class="title">Maris</h2>
           		<div class="input-div one">
           		   <div class="i">
           		   		<i class="fas fa-user"></i>
           		   </div>
           		   <div class="div">
           		   		<h5>Tên đăng nhập</h5>
           		   		<input type="text" name="username" class="input">
           		   </div>
           		</div>
           		<div class="input-div pass">
           		   <div class="i">
           		    	<i class="fas fa-lock"></i>
           		   </div>
           		   <div class="div">
           		    	<h5>Mật khẩu</h5>
           		    	<input type="password" name="password" class="input">
            	   </div>
            	</div>
            	
           
            	<div>${errMessage}</div>
				
				<div class="link">
					<a href="${pageContext.request.contextPath}/register">Đăng ký</a>
					<a href="${pageContext.request.contextPath}/forgot-password">Quên mật khẩu</a>
				</div>
            	<input type="submit" class="btn" value="Đăng nhập">        
            </form>
        </div>
    </div>
  
  	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_footer.jsp"></jsp:include>
    
	<script type="text/javascript" src="static/js/login-customer.js"></script>
</body>
</html>