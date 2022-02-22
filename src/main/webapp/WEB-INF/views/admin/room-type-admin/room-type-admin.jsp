<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
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
	<title>Quản lý loại phòng</title>
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
	<!-- link fontawesome -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/v4-shims.css">
	<script src="<%=request.getContextPath()%>/static/js/script.js"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include
			page="/WEB-INF/views/layouts/layout-admin/_header-admin.jsp"></jsp:include>
		<script>
			document.querySelector(".nav-sidebar").children[5].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Quản lý loại phòng</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value="/admin/room-type"/>">Tất cả</a></li>
						<li class="nav-item"><a class="nav-link" data-toggle="modal"
							href="#addTypeRoomModal">Thêm</a></li>
					</ul>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>Id</th>
								<th>Tên loại phòng</th>
								<th>Hình ảnh</th>
								<th>Đơn giá</th>
								<th>Số người</th>
								<th>Ghi chú</th>
								<th>Chức năng</th>				
								<th></th>
							</tr>
						</thead>
						<tbody>							
							
							<c:forEach items="${loaiPhongs}" var="loaiPhong">						
								<tr>
									<td><c:out value="${loaiPhong.idLoaiPhong}"></c:out></td>
									<td><c:out value="${loaiPhong.tenLoaiPhong}"></c:out></td>								
									<td style="width:300px"><img src="<c:url value="${loaiPhong.hinhAnh}"/>" alt="${loaiPhong.tenLoaiPhong}" width="250" height="150"></td>
									<td>
										<fmt:formatNumber type = "number" 
					                    		maxFractionDigits = "0" 
					                    		value = "${loaiPhong.donGia}" /> VNĐ</td>
									<td><c:out value="${loaiPhong.soNguoi}"></c:out></td>
									<td><c:out value="${loaiPhong.ghiChu}"></c:out></td>															
																
									<td><a class="btn btn-primary"
										href="<c:url value='room-type/edit'/>?room-type-id=${loaiPhong.idLoaiPhong}">Sửa</a>
										<a class="btn btn-danger"
										href="<c:url value='room-type/delete'/>?room-type-id=${loaiPhong.idLoaiPhong}">Xóa</a>
									</td>
								</tr>															
							</c:forEach>							
							
						</tbody>
					</table>
				</div>

			</section>
		</div>
	</div>

	<!-- Add Room Type Modal -->
	<div class="modal fade" id="addTypeRoomModal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Thêm loại phòng</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<form action="room-type/add"" id="room-type-form" enctype="multipart/form-data">
						<div class="form-group">
							<label for="room-type-name">Tên loại phòng:</label> 
							<input type="text"
								id="room-type-name" class="form-control" placeholder="Nhập tên loại phòng"
								name="room-type-name" required>
						</div>
						
						<div class="form-group">
							<label for="room-type-image">Hình ảnh:</label> 
							<input type="text"
								id="room-type-image" class="form-control" placeholder="Link hình ảnh loại phòng"
								name="room-type-image" required>
						</div>
						
						<div class="form-group">
							<label for="room-type-unit-price">Đơn giá:</label> 
							<input type="number"
								id="room-type-unit-price" class="form-control" placeholder="Nhập đơn giá"
								name="room-type-unit-price" required>
						</div>
						
						<div class="form-group">
							<label for="room-type-num-people">Số người:</label> 
							<input type="number"
								id="room-type-num-people" class="form-control" placeholder="Nhập số người"
								name="room-type-num-people" required>
						</div>
						
						<div class="form-group">
							<label for="room-type-note">Ghi chú:</label> 
							<input type="text"
								id="room-type-note" class="form-control" placeholder="Nhập ghi chú"
								name="room-type-note">
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