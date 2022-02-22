
<%@page import="com.nhom6.qlks.hibernate.pojo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Quản lý khách online</title>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<script
		src="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/js/adminlte.min.js"></script>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/css/adminlte.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/v4-shims.css">
	<script src="<%=request.getContextPath()%>/static/js/script.js"></script>
		
</head>
<body>
	<div class="wrapper">

		<jsp:include
			page="/WEB-INF/views/layouts/layout-admin/_header-admin.jsp"></jsp:include>
		<script>
			document.querySelector(".nav-sidebar").children[7].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Quản lý tài khoản khách hàng online</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">												
				
					<ul class="nav nav-tabs">
						<li class="nav-item">							
							<a class="nav-link active" href="<c:url value='/admin/online-customer'/>">Tất cả</a>
						</li>								
																							   
					</ul>									
					
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Họ tên</th>
									<th>Ngày sinh</th>
									<th>Giới tính</th>
									<th>Email</th>
									<th>SDT</th>
									<th>Kích hoạt</th>
									<th>CMND</th>
									<th>Quyền</th>
									<th>Chức năng</th>
								</tr>
							</thead>
							<tbody>																
								
								<c:forEach items="${users}" var="user">						
									<tr>
										<td><c:out value="${user.hoTen}"></c:out></td>
										<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${user.ngaySinh}"/></td>										
										<td><c:out value="${user.gioiTinh}"></c:out></td>
										<td><c:out value="${user.email}"></c:out></td>										
										<td><c:out value="${user.sdt}"></c:out></td>
										<c:if test="${user.kichHoat == true}">
										<td><i class="fas fa-check"></i></td>
										</c:if>
										<c:if test="${user.kichHoat == false}">
										<td><i class="fas fa-minus"></i></td>
										</c:if>
										
										<td><c:out value="${user.cmnd}"></c:out></td>
										<td><c:out value="${user.quyen.tenQuyen}"></c:out></td>							
										<td>
											<c:if test="${user.kichHoat == true}">
											<a class="btn btn-danger" href="<c:url value='online-customer/activate'/>?user-id=${user.id}">Vô hiệu hoá</a>
											</c:if>
											
											<c:if test="${user.kichHoat == false}">
											<a class="btn btn-success" href="<c:url value='online-customer/activate'/>?user-id=${user.id}">Kích hoạt</a>
											</c:if>
											
											
										</td>
									</tr>															
								</c:forEach>
																						
							</tbody>
						</table>
					</div>
					<!-- end table-responsive -->
				</div>

			</section>

		</div>
		<!-- end content-wrapper -->
	</div>


</body>
</html>