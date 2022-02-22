<%@page import="com.nhom6.qlks.utils.Utils"%>
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
	<title>Hóa đơn</title>
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
	<script src='https://kit.fontawesome.com/a076d05399.js'
		crossorigin='anonymous'></script>
	<script src="<%=request.getContextPath()%>/static/js/script.js"></script>
</head>
<body>
	<div class="wrapper">

		<jsp:include
			page="/WEB-INF/views/layouts/layout-admin/_header-admin.jsp"></jsp:include>
		<script>
			document.querySelector(".nav-sidebar").children[4].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Hóa đơn</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">
					
					<ul class="nav nav-tabs">
						<li class="nav-item">							
							<a class="nav-link active" href="<c:url value='/admin/room'/>">Tất cả</a>
						</li>
																		   
					</ul>									
					
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Id</th>
									<th>Người tạo</th>
									<th>Ngày tạo</th>
									<th>Tổng tiền</th>
									<th>Chức năng</th>
								</tr>
							</thead>
							<tbody>																
								
								<c:forEach items="${hoaDons}" var="hoaDon">						
									<tr>
										<td><c:out value="${hoaDon.idHD}"></c:out></td>
										<td><c:out value="${hoaDon.user.hoTen}"></c:out></td>
										<td><c:out value="${hoaDon.ngayTao}"></c:out></td>
										<td>
											<fmt:formatNumber type = "number" 
					                    		maxFractionDigits = "0" 
					                    		value = "${Utils.calcTotalPriceBill(hoaDon)}" /> VNĐ</td> 
											</td>
																	
										<td><a class="btn btn-primary" 
											onclick="showBillDetail(${hoaDon.idHD})" 
											href="javascript:void(0)">Xem chi tiết</a>
										</td>
									</tr>															
								</c:forEach>
																						
							</tbody>
						</table>
						<script type="text/javascript">
							function showBillDetail(idBill) {
								$.get(
										"<%=request.getContextPath()%>/api/get-bill-detail?idHD=" + idBill,
										function(data) {
											console.log(data);
											
											let table = document.getElementById('tb-bill-detail');
											table.innerHTML = '';
											
											for (let i = 0; i < data.length; i++) {
										        let r = table.insertRow(i);

										        r.insertCell(0).innerText = i + 1;
										        r.insertCell(1).innerText = data[i].idBooking;
										        r.insertCell(2).innerText = data[i].tenPhong;
										        r.insertCell(3).innerText = data[i].soNgayThue;
										        r.insertCell(4).innerText = data[i].donGia + " VNĐ";
										        r.insertCell(5).innerText = data[i].thanhTien + " VNĐ";
										    }
											
											
											$('#myModal').modal();
										});
							}
						</script>
						
					</div>
				
				</div>
				
			</section>
		</div>
	</div>
	
	<!-- The Modal -->
	<div class="modal fade" id="myModal">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Chi tiết hóa đơn</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					
	                <div>
	                	<div class="table-responsive">
	                		<table class="table table-striped table-hover">
			                    <thead>
			                        <tr>
			                            <th>STT</th>
			                            <th>Mã Booking</th>
			                            <th>Phòng</th>
			                            <th>Số ngày thuê</th>
			                            <th>Đơn giá</th>
			                            <th>Thành tiền</th>
			                        </tr>
			                    </thead>
			                    <tbody id="tb-bill-detail">
			                    </tbody>
			                </table>
	                	</div>
	                </div>
	                
					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="button" class="btn btn-danger" data-dismiss="modal" value="Thoát">							
					</div>
				</div>
				
			</div>
		</div>
	</div>
	
</body>
</html>
