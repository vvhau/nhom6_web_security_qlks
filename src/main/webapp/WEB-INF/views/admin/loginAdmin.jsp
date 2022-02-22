<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/register-login-customer.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
</head>
<body>
    <div class="mt-5">
	    <h1 class="text-center text-danger mb-5 pt-5">ĐĂNG NHẬP TRANG QUẢN LÝ KHÁCH SẠN</h1>
		<div class="row">
		    <form action="${pageContext.request.contextPath}/admin/login" method="post" class="col-sm- m-auto">
		        <div class="form-group">
		            <label for="username">Tên đăng nhập</label>
		            <input type="text"
		                   name="username"
		                   class="form-control"
		                   placeholder="Nhập tên đăng nhập"
		                   id="username" required>
		        </div>
		        <div class="form-group">
		            <label for="password">Mật khẩu</label>
		            <input type="password"
		                   name="password"
		                   class="form-control"
		                   placeholder="Nhập mật khẩu"
		                   id="password" required>
		        </div>
				
				<div>${errMessage}</div>
		        <input type="submit" value="Đăng nhập" class="btn btn-primary">
		    </form>
		</div>
    </div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/login-customer.js"></script>
</body>
</html>