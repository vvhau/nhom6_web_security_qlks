<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
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
		<title>Chỉnh sửa thông tin loại phòng</title>
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
				document.querySelector(".nav-sidebar").children[5].classList
						.add('menu-open');
				document.querySelector(".menu-open .nav-link").classList
						.add('active');
			</script>
	
			<div class="content-wrapper">
				<section class="content-header">
					<div class="container-fluid">
						<div class="col-11 ">
							<h1 class="h3 text-center text-gray-800 mb-0">Chỉnh sửa thông tin loại phòng</h1>
						</div>
					</div>
				</section>
	
				<section class="content">
	
					<div class="container-fluid">																													
						
						<div class="table-responsive">
					
							<form action="edit" method="post">
								<input name="_csrf" type="hidden" value="${_csrf}">
								<div class="form-group">
									<label for="room-type-id">Id loại phòng:</label>
									<input type="text" id="room-type-id" 
										class="form-control"
										name="room-type-id" value="${roomType.idLoaiPhong}" readonly="readonly">
								</div>
							
								<div class="form-group">
									<label for="room-type-name">Tên loại phòng:</label>
									<input type="text" id="room-type-name" 
										class="form-control" placeholder="Nhập tên loại phòng" 
										name="room-type-name" value="${roomType.tenLoaiPhong}" required>
								</div>
								
								<div class="form-group">																
									<label for="room-type-image">Hình ảnh:</label> 
									<input type="text" id="room-type-image" class="form-control" placeholder="Link hình ảnh loại phòng"
									name="room-type-image" required>							
								</div>
								
								<div class="form-group">
									<label for="room-type-unit-price">Đơn giá:</label>
									<input type="text" id="room-type-unit-price" 
										class="form-control" placeholder="Nhập đơn giá" 
										name="room-type-unit-price" value="${roomType.donGia}" required>
								</div>
								
								<div class="form-group">
									<label for="room-type-num-people">Số người:</label>
									<input type="text" id="room-type-num-people" 
										class="form-control" placeholder="Nhập số người" 
										name="room-type-num-people" value="${roomType.soNguoi}" required>
								</div>					
								
								<div class="form-group">
									<label for="room-type-note">Ghi chú:</label> 
									<input type="text" id="room-type-note" 
										class="form-control" placeholder="Nhập ghi chú"
										name="room-type-note" value="${roomType.ghiChu}">
								</div>
								
								<input type="submit" class="btn btn-success" value="Sửa">
								<a class="btn btn-danger" href="<c:url value='/admin/room-type'/>">Thoát</a>

							</form>
							<!-- end form -->			
						</div>	
					</div>
	
				</section>
	
			</div>
			<!-- end content-wrapper -->
		</div>
		
	</body>
</html>