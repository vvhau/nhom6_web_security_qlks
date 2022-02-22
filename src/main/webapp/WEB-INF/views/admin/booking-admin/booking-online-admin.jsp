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
	<title>Quản lý booking online</title>
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
			$( "li:nth-child(3)").addClass('menu-is-opening menu-open');
			$( "li:nth-child(3) > ul > li:nth-child(2)" ).children().addClass('active');
									
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Quản lý booking online</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">
					<ul class="nav nav-tabs">
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value="booking-online"/>">Tất cả</a></li>		
						<li class="nav-item ml-2">
							<form method="GET" action="" class="form-inline my-2 my-lg-0" role="search">
						    	<div class="form-inline">
						        	<input class="form-control col-auto" size="30" type="text" name="id-booking" value="" placeholder="Mã booking">
						            <button class="btn btn-secondary my-2 my-sm-0 ml-2" type="submit">Tìm</button>
						        </div>
						    </form>
						</li>				
					</ul>

					<table class="table table-striped">
						<thead>
							<tr>
								<th>Mã booking</th>
								<th>Số người</th>
								<th>Check in</th>
								<th>Check out</th>
								<th>Đơn giá</th>
								<th>Phòng</th>
								<th>Khách hàng online</th>
								<th>Mã hóa đơn</th>
								<th>Khách</th>															
							</tr>
						</thead>
						<tbody>							
							
							<c:forEach items="${bookings}" var="booking">						
								<tr>
									<td><c:out value="${booking.idBooking}"></c:out></td>
									<td><c:out value="${booking.soNguoi}"></c:out></td>								
									<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkIn}"/></td>
									<td><fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkOut}"/></td>			
									<td>
										<fmt:formatNumber type = "number" 
					                    		maxFractionDigits = "0" 
					                    		value = "${booking.phong.loaiPhong.donGia}" /> VNĐ</td>
									<td><c:out value="${booking.phong.tenPhong}"></c:out></td>
									<td><c:out value="${booking.user.hoTen}"></c:out></td>															
									<td><c:out value="${booking.hoaDon.idHD}"></c:out></td>															
									<td>
																
										<c:choose>
	         										
									         <c:when test = "${booking.khachHangs.size() > 0}">
									            <a class="btn btn-primary" 
									            	onclick="openCustomerList(${booking.idBooking},
									            		${booking.phong.tenPhong},
									            		'<fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkIn}"/>',
									            		'<fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkOut}"/>',
									            		${booking.soNguoi})" 
									            	href="javascript:void(0)">
									            	Xem
									            </a>
									         </c:when>										         										      
									         
									         <c:otherwise>
									            <a class="btn btn-success" 
									            	onclick="openAddCustomerList(${booking.idBooking},
									            		${booking.phong.tenPhong},
									            		'<fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkIn}"/>',
									            		'<fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.checkOut}"/>', 
									            		${booking.soNguoi})" 
									            	href="javascript:void(0)">
									            	Thêm
									            </a>
									            
									         </c:otherwise>
										         
										</c:choose>
									</td>															
								</tr>															
							</c:forEach>							
							
						</tbody>
					</table>
				</div>
				
				<c:choose>
					<c:when test="${numPage != null}">
						<c:choose>
							<c:when test="${numPage == 1}">
								<ul class="pagination justify-content-center">
								    <li class="page-item active"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage}">${numPage}</a></li>
								    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage + 1}">${numPage + 1}</a></li>
								    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage + 1}">Next</a></li>
								</ul>
							</c:when>
							
							<c:otherwise>
								<ul class="pagination justify-content-center">
									<li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage - 1}">Previous</a></li>
								    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage - 1}">${numPage - 1}</a></li>
								    <li class="page-item active"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage}">${numPage}</a></li>
								    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage + 1}">${numPage + 1}</a></li>
								    <li class="page-item"><a class="page-link" href="<%=request.getContextPath()%>/admin/booking-online?page=${numPage + 1}">Next</a></li>
								</ul>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				
			</section>
		</div>
	</div>
	
	
	<div class="modal fade" id="customerListModal">
	    <div class="modal-dialog modal-lg modal-dialog-centered">
	        <div class="modal-content">
	
	            <!-- Modal Header -->
	            <div class="modal-header">
	                <h4 class="modal-title">Thông tin khách hàng</h4>
	                <button type="button" class="close" data-dismiss="modal">×</button>
	            </div>
	
	            <!-- Modal body -->
	            <div class="modal-body">
	                <div class="row">
	                    <div class="col">
	                        <label for="ten-phong-pt">Phòng</label></br>
	                        <input id="ten-phong-pt" type="text" disabled/>
	                    </div>
	                    <div class="col">
	                        <label for="check-in-pt">Check in</label></br>
	                        <input id="check-in-pt" type="date" disabled/>
	                    </div>
	                    <div class="col">
	                        <label for="check-out-pt">Check out</label></br>
	                        <input id="check-out-pt" type="date" disabled/>
	                    </div>
	                    <div class="col">
	                        <label for="so-nguoi-pt">Số người</label></br>
	                        <input id="so-nguoi-pt" type="number" disabled>
	                    </div>
	                </div>
	                <br>
	                <table class="table table-striped">
	                    <thead>
	                    <tr>
	                        <th>STT</th>
	                        <th>Khách hàng</th>
	                        <th>CMND</th>
	                        <th>Địa chỉ</th>
	                    </tr>
	                    </thead>
	                    <tbody id="tb-booking">
	                    
		                    <tr>
		                        <td>i</td>
		                        <td>
		                            <input type="text"/>
		                        </td>
		                        <td>
		                            <input type="text"/>
		                        </td>
		                        <td>
		                            <input type="text"/>
		                        </td>
		                    </tr>
	                
	                    </tbody>
	                </table>
	              
	            </div>

				
				<!-- Modal footer -->
				<div class="modal-footer">
					<input type="button" class="btn btn-success" id="btn-save" value="Lưu" onclick="saveAddCustomerList()">
					<input type="button" class="btn btn-danger" data-dismiss="modal" value="Thoát">							
				</div>

	        </div>
	    </div>
	</div>

	<script type="text/javascript">
		var currentIdBooking;
	
		function openCustomerList(idBooking, tenPhong, checkIn, checkOut, soNguoi) {
			currentIdBooking = idBooking;
			
			$.get(
					"<%=request.getContextPath()%>/api/customer-booking?idBooking=" + idBooking,
					function(data) {
						console.log(data);
						
						document.getElementById('ten-phong-pt').value = tenPhong;
			            document.getElementById('check-in-pt').value = checkIn;
			            document.getElementById('check-out-pt').value = checkOut;
			            document.getElementById('so-nguoi-pt').value = soNguoi;
						
						let table = document.getElementById("tb-booking");
					    table.innerHTML = '';

					    for (let i = 0; i < data.length; i++) {
					        let r = table.insertRow(i);

					        r.insertCell(0).innerText = i + 1;
					        r.insertCell(1).innerText = data[i].hoTen;
					        r.insertCell(2).innerText = data[i].cmnd;
					        r.insertCell(3).innerText = data[i].diaChi;
					    }
						
					    document.getElementById('btn-save').disabled = true;
					    
						$("#customerListModal").modal();
					})
		}
		
		function openAddCustomerList(idBooking, tenPhong, checkIn, checkOut, soNguoi) {
			currentIdBooking = idBooking;
			
			document.getElementById('ten-phong-pt').value = tenPhong;
            document.getElementById('check-in-pt').value = checkIn;
            document.getElementById('check-out-pt').value = checkOut;
            document.getElementById('so-nguoi-pt').value = soNguoi;
            
            document.getElementById('btn-save').disabled = false;
			
			createTableBooking(soNguoi);
			$("#customerListModal").modal();
		}
		
		function saveAddCustomerList() {
			let table = document.getElementById("tb-booking");

		    if(table.rows.length < 1) {
		        alert('Số người phải lớn hơn 0');
		        return;
		    }

		    const data = [];

		    for (let r of table.children) {
		    	if (r.children[1].children[0].value == '' ||
		    			r.children[2].children[0].value == '' ||
		    			r.children[3].children[0].value == '') {
		    		alert('Nhập thiếu dữ liệu khách hàng');
		    		return;
		    	} 
		        data.push({
		            hoTen: r.children[1].children[0].value,
		            cmnd: r.children[2].children[0].value,
		            diaChi: r.children[3].children[0].value
		        });
		    }
			console.log(data);
				
			$.post(
					"<%=request.getContextPath()%>/api/customer-booking", 
					{ 
						idBooking: currentIdBooking,
						dataKH: JSON.stringify(data),
					},
					function(data){
						if (data.status == 200) {
							alert('Thêm khách hàng thành công');
						} else {
							alert('Đã có lỗi xảy ra, vui lòng thử lại');
						}
							
				        location.reload();
					});
		}
	</script>

</body>
</html>