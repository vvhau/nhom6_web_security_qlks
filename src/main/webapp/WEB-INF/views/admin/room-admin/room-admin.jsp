<%@page import="com.nhom6.qlks.hibernate.pojo.TrangThai"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.Phong"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Quản lý phòng</title>
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
			document.querySelector(".nav-sidebar").children[6].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Quản lý phòng</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">												
				
					<ul class="nav nav-tabs">
						<li class="nav-item">							
							<a class="nav-link active" href="<c:url value="/admin/room"/>">Tất cả</a>
						</li>								
						
						<li class="nav-item">
							<a class="nav-link" data-toggle="modal"
							href="#myModal">Thêm</a>
						</li>
																		   
					</ul>									
					
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Id</th>
									<th>Tên phòng</th>
									<th>Loại phòng</th>
									<th>Trạng thái</th>
									<th>Chức năng</th>
									<th></th>
								</tr>
							</thead>
							<tbody>																
								
								<c:forEach items="${phongs}" var="phong">						
									<tr>
										<td><c:out value="${phong.idPhong}"></c:out></td>
										<td><c:out value="${phong.tenPhong}"></c:out></td>
										<td><c:out value="${phong.loaiPhong.tenLoaiPhong}"></c:out></td>
										<td><c:out value="${phong.trangThai.tenTrangThai}"></c:out></td>
																	
										<td><a class="btn btn-primary"
											href="<c:url value='room/edit'/>?room-id=${phong.idPhong}">Sửa</a>
											<a class="btn btn-danger"
											href="<c:url value='room/delete'/>?room-id=${phong.idPhong}">Xóa</a>
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
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm phòng</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="room/add" id="room-form" method="post">
						<input name="_csrf" type="hidden" value="${_csrf}">
						<div class="form-group">
							<label for="room-name">Tên phòng:</label> 
							<input type="text"
								id="room-name" class="form-control" placeholder="Nhập tên phòng"
								name="room-name" required>
						</div>
						<div class="form-group">
							<label for="room-type">Loại phòng:</label> 
							<select id="room-type"
								name="room-type" class="custom-select">
								<option disabled>Chọn loại phòng</option>							
								
								<c:forEach items="${loaiPhongs}" var="loaiPhong">
									<option value="${loaiPhong.idLoaiPhong}">${loaiPhong.tenLoaiPhong}</option>								
								</c:forEach>
								
							</select>
						</div>
						<div class="form-group">
							<label for="room-status">Trạng thái:</label> 
							<select
								id="room-status" name="room-status" class="custom-select">
								<option disabled>Chọn trạng thái</option>
								
								<c:forEach items="${trangThais}" var="trangThai">
									<option value="${trangThai.idTrangThai}">${trangThai.tenTrangThai}</option>								
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