<%@page import="com.nhom6.qlks.hibernate.pojo.Phong"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.TrangThai"%>
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
		<title>Sửa phòng</title>
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
							<h1 class="h3 text-center text-gray-800 mb-0">Chỉnh sửa thông tin phòng</h1>
						</div>
					</div>
				</section>
	
				<section class="content">
	
					<div class="container-fluid">																													
						
						<div class="table-responsive">
					
							<form action="edit" method="post">
								<div class="form-group">
									<label for="room-id">Id phòng:</label>
									<input type="text" id="room-id" 
										class="form-control"
										name="room-id" value="${phong.idPhong}" readonly="readonly">
								</div>
							
								<div class="form-group">
									<label for="room-name">Tên phòng:</label>
									<input type="text" id="room-name" 
										class="form-control" placeholder="Nhập tên phòng" 
										name="room-name" value="${phong.tenPhong}" required>
								</div>
								
								<div class="form-group">
									<label for="room-type">Loại phòng:</label>
									<select id="roomType" name="room-type" class="custom-select">
										
										<option disabled>Chọn loại phòng</option>
										
										<c:forEach items="${loaiPhongs}" var="loaiPhong">
											<c:choose>
												<c:when test="${phong.loaiPhong.idLoaiPhong == loaiPhong.idLoaiPhong}">
													<option value="${loaiPhong.idLoaiPhong}" selected="selected">${phong.loaiPhong.tenLoaiPhong}</option>		
												</c:when>												
												<c:otherwise>
													<option value="${loaiPhong.idLoaiPhong}">${loaiPhong.tenLoaiPhong}</option>																						
												</c:otherwise>
											</c:choose>								
										</c:forEach>
								 										 			
								   	</select>
								</div>
								
								<div class="form-group">
									<label for="room-status">Trạng thái:</label>
									<select id="roomStatus" name="room-status" class="custom-select">
										<option disabled>Chọn trạng thái</option>
										
								   		<c:forEach items="${trangThais}" var="trangThai">								   													
											<c:choose>
												<c:when test="${phong.trangThai.idTrangThai == trangThai.idTrangThai}">
													<option value="${trangThai.idTrangThai}" selected="selected">${phong.trangThai.tenTrangThai}</option>		
												</c:when>												
												<c:otherwise>
													<option value="${trangThai.idTrangThai}">${trangThai.tenTrangThai}</option>
												</c:otherwise>
											</c:choose>																							
										</c:forEach>
								 	</select>								 	
								</div>
								
								<input type="submit" class="btn btn-success" value="Sửa">
								<a class="btn btn-danger" href="<c:url value='/admin/room'/>">Thoát</a>
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