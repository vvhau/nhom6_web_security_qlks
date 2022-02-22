<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!-- <%@ page import="com.TransportPortal.MyFunctions.* "%> -->
<html>
<head>
<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Đăng ký</title>
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
			<form action="<%=request.getContextPath()%>/register" method="post" accept-charset="utf-8">
				<img src="static/image/avatar.svg">
				<h2 class="title">Đăng ký</h2>

				<div class="info">
					<div>
						<div class="input-div one">
							<div class="i">
									<i class="fas fa-user"></i>
							</div>
							<div class="div">
									<h5>Họ tên</h5>
									<input type="text" name="name" class="input">
							</div>
						 </div>

						 <div class="select-div one">
							<div class="i">
									<i class="fas fa-calendar-day"></i>
							</div>
							<div class="div">
									<h5>Ngày sinh</h5>
									<input type="date" name="dob" class="select">
							</div>
						 </div>

						 <div class="select-div one">
							<div class="i">
								<i class="fas fa-venus-mars"></i>
							</div>
							<div class="div">
								<h5>Giới tính</h5>
								<select name="gender" name="gender" class="select">
									<option selected disabled>Chọn giới tính</option>
									<option value="Nam">Nam</option>
									<option value="Nữ">Nữ</option>
								</select>
							</div>
						 </div>

						<div class="input-div one">
							<div class="i">
									<i class="fas fa-id-card"></i>
							</div>
							<div class="div">
									<h5>CMND</h5>
									<input type="text" name="id-card" class="input">
							</div>
						</div>

						<div class="input-div one">
							<div class="i">
									<i class="fas fa-envelope"></i>
							</div>
							<div class="div">
									<h5>Email</h5>
									<input type="email" name="email" class="input">
							</div>
						 </div>

						 <div class="input-div one">
							<div class="i">
									<i class="fas fa-phone"></i>
							</div>
							<div class="div">
									<h5>Số điện thoại</h5>
									<input type="text" name="phone" class="input">
							</div>
						 </div>
					</div>

					<div>
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
                    <div class="input-div pass">
                      <div class="i">
							   <i class="fas fa-lock"></i>
						  </div>
						  <div class="div">
							   <h5>Nhập lại mật khẩu</h5>
							   <input type="password" name="confirm-password" class="input">
						   </div>
						</div>
					</div>

				</div>
				
				
				<div>${errMessage}</div>
			
				
            	<input type="submit" class="btn" value="Đăng ký">
				<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
            </form>
        </div>
    </div>
    
    <jsp:include page="/WEB-INF/views/layouts/layout-customer/_footer.jsp"></jsp:include>
    
    <script type="text/javascript" src="static/js/login-customer.js"></script>
</body>
</html>