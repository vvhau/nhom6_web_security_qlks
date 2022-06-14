<%@page import="com.nhom6.qlks.hibernate.pojo.TrangThai"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.Phong"%>
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
	<title>Quản lý nhân viên</title>
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
			document.querySelector(".nav-sidebar").children[8].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Quản lý nhân viên</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">												
				
					<ul class="nav nav-tabs">
						<li class="nav-item">							
							<a class="nav-link active" href="<c:url value='/admin/employee'/>">Tất cả</a>
						</li>								
						
						<li class="nav-item">
							<a class="nav-link" data-toggle="modal"
							href="#addEmployeeModal">Thêm</a>
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
										
											<a class="btn btn-primary"
											href="<c:url value='employee/edit'/>?user-id=${user.id}">Sửa</a>
											
											<c:if test="${user.kichHoat == true}">
											<a class="btn btn-danger" href="<c:url value='employee/activate'/>?user-id=${user.id}">Vô hiệu hoá</a>
											</c:if>
											
											<c:if test="${user.kichHoat == false}">
											<a class="btn btn-success" href="<c:url value='employee/activate'/>?user-id=${user.id}">Kích hoạt</a>
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

	<!-- The Modal -->

	<div class="modal fade" id="addEmployeeModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm nhân viên</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="employee/create" method="post">
						<input name="_csrf" type="hidden" value="${_csrf}">
						<div class="form-group">
							<label for="user-name">Tên nhân viên:</label>
							<input type="text" id="user-name" 
								class="form-control" placeholder="Nhập tên nhân viên" 
								name="user-name" required>
						</div>
						
						<div class="form-group">
							<label for="user-dob">Ngày sinh:</label>						
							<input type="date" id="user-dob" name="user-dob">
						</div>
										
						<div class="form-group">
							<label for="user-gender">Giới tính:</label>
							<select id="user-gender" name="user-gender" class="custom-select">
								<option disabled>Chọn giới tính</option>
								<option value="Nữ">Nữ</option>
                                <option value="Nam">Nam</option>
						 	</select>								 	
						</div>
						<div class="form-group">
							<label for="user-cmnd">CMND:</label>
							<input type="text" id="user-cmnd" 
								class="form-control" placeholder="Nhập CMND" 
								name="user-cmnd" required>
						</div>
						<div class="form-group">
							<label for="user-email">Email:</label>
							<input type="email" id="user-email" 
								class="form-control" placeholder="Nhập email" 
								name="user-email" required>
						</div>
						<div class="form-group">
							<label for="user-phone">SĐT:</label>
							<input type="text" id="user-phone" 
								class="form-control" placeholder="Nhập sdt" 
								name="user-phone" required>
						</div>
						<div class="form-group">
							<label for="user-username">Tên đăng nhập:</label>
							<input type="text" id="user-username" 
								class="form-control" placeholder="Nhập tên đăng nhập" 
								name="user-username" required>
						</div>
						<div class="form-group">
							<label for="user-password">Mật khẩu:</label>
							<input type="password" id="user-password" 
								class="form-control" placeholder="Nhập mật khẩu" 
								name="user-password" required>
						</div>
					
						<div class="form-group">
							<label for="user-activate" class="control-label" style="display: block">Kích hoạt:</label>
							<input type="checkbox" checked="" class="form-control-lg" 
							id="user-activate" name="user-activate" >
						</div>
						
						<div class="form-group">
							<label for="user-role">Quyền:</label>
							<select id="user-role" name="user-role" class="custom-select">
								<option disabled>Chọn quyền</option>
								
						   		<c:forEach items="${quyens}" var="quyen">
									<option value="${quyen.idQuyen}">${quyen.tenQuyen}</option>								
								</c:forEach>
						 	</select>								 	
						</div>
						<!-- Modal footer -->
						<div class="modal-footer">
							<input type="submit" class="btn btn-success" value="Thêm">
							<input type="button" class="btn btn-danger" data-dismiss="modal" value="Thoát">							
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>