<%@page import="com.nhom6.qlks.hibernate.pojo.Phong"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.TrangThai"%>
<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
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
		<title>Sửa nhân viên</title>
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
		<script
			src="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/js/adminlte.min.js"></script>
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/admin-lte@3.1/dist/css/adminlte.min.css">
		<script src='https://kit.fontawesome.com/a076d05399.js'
			crossorigin='anonymous'></script>
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
							<h1 class="h3 text-center text-gray-800 mb-0">Chỉnh sửa thông tin nhân viên</h1>
						</div>
					</div>
				</section>
	
				<section class="content">
	
					<div class="container-fluid">																													
						
						<div class="table-responsive">
					
							<form action="edit" method="post">
								<input name="_csrf" type="hidden" value="${_csrf}">
								<div class="form-group">
									<label for="user-id">Id User:</label>
									<input type="text" id="user-id" 
										class="form-control"
										name="user-id" value="${user.id}" readonly="readonly">
								</div>
												
								<div class="form-group">
									<label for="user-name">Tên nhân viên:</label>
									<input type="text" id="user-name" 
										class="form-control" placeholder="Nhập tên nhân viên" 
										name="user-name" value="${user.hoTen}" required>
								</div>
								
								<div class="form-group">
									<label for="user-dob">Ngày sinh:</label>						
									<input type="date" id="user-dob" name="user-dob" value="<fmt:formatDate pattern = "yyyy-MM-dd" value="${user.ngaySinh}"/>">
								</div>
										
								<div class="form-group">
									<label for="user-gender">Giới tính:</label>
									<select id="user-gender" name="user-gender" class="custom-select">
										<option disabled>Chọn giới tính</option>
									
										<option value="Nam"<c:if test="${user.gioiTinh == 'Nam' || user.gioiTinh == 'nam'}">selected="selected"</c:if>>
                                                Nam
                                        </option>		
                                        <option value="Nữ"<c:if test="${user.gioiTinh == 'Nữ' || user.gioiTinh == 'nữ'}">selected="selected"</c:if>>
                                                Nữ
                                        </option>
								 	</select>								 	
								</div>
								<div class="form-group">
									<label for="user-cmnd">CMND:</label>
									<input type="text" id="user-cmnd" 
										class="form-control" placeholder="Nhập CMND" 
										name="user-cmnd" value="${user.cmnd}" required>
								</div>
								<div class="form-group">
									<label for="user-email">Email:</label>
									<input type="text" id="user-email" 
										class="form-control" placeholder="Nhập email" 
										name="user-email" value="${user.email}" required>
								</div>
								<div class="form-group">
									<label for="user-phone">SĐT:</label>
									<input type="text" id="user-phone" 
										class="form-control" placeholder="Nhập SDT" 
										name="user-phone" value="${user.sdt}" required>
								</div>
								
								<div class="form-group">
									<label for="user-username">Tên đăng nhập:</label>
									<input type="text" id="user-username" 
										class="form-control" name="user-username" 
										value="${user.tenDangNhap}" readonly="readonly">
								</div>
								
							
								<div class="form-group">
									<label for="user-activate" style="display: block">Kích hoạt:</label>
									<c:choose>
										<c:when test="${user.kichHoat}">
											<input checked="" class="form-control-lg" id="user-kh" name="user-activate" type="checkbox">
										</c:when>
										<c:otherwise>
											<input class="form-control-lg" id="user-kh" name="user-activate" type="checkbox">											
										</c:otherwise>
									</c:choose>
									
								</div>
								
								<div class="form-group">
									<label for="user-role">Quyền:</label>
									<select id="user-role" name="user-role" class="custom-select">
										<option disabled>Chọn quyền</option>
										
								   		<c:forEach items="${quyens}" var="quyen">								   													
											<c:choose>
												<c:when test="${user.quyen.idQuyen ==quyen.idQuyen}">
													<option value="${quyen.idQuyen}" selected="selected">${user.quyen.tenQuyen}</option>		
												</c:when>												
												<c:otherwise>
													<option value="${quyen.idQuyen}">${quyen.tenQuyen}</option>
												</c:otherwise>
											</c:choose>																							
										</c:forEach>
								 	</select>								 	
								</div>
								
								<input type="submit" class="btn btn-success" value="Lưu">		
								<a class="btn btn-danger" href="<c:url value='/admin/employee'/>">Thoát</a>
							

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