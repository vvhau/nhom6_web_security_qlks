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
	<title>Tra cứu phòng</title>
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
			document.querySelector(".nav-sidebar").children[0].classList
					.add('menu-open');
			document.querySelector(".menu-open .nav-link").classList
					.add('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Tra cứu phòng</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">												
				
					<div class="m-5">
						<form class="form-inline pb-6" id="search-form" action="">
					        <div class="form-inline">
					            <label class="mr-2" for="num-people">Số người/phòng:</label>
					            <select class="form-control mr-4" 
					            	id="num-people" name="num-people" 
					            	onchange="document.querySelector('#search-form').submit()">
					                <option value="0">Tất cả</option>
					                <option value="1">1</option>
					                <option value="2">2</option>
					                <option value="3">3</option>
					                <option value="4">4</option>
					                <option value="5">5</option>
					            </select>
					        </div>
					
					        <div class="form-inline">
					            <label class="mr-2">Check in:</label>
					            <input id="check-in" class="form-control mr-4" 
					            	type="date" name="check-in"  
					            	onchange="document.querySelector('#search-form').submit()">
					        </div>
					
					        <div class="form-inline">
					            <label class="mr-2">Check out:</label>
					            <input id="check-out" class="form-control mr-4" 
					            	type="date" name="check-out" 
					            	onchange="document.querySelector('#search-form').submit()">
					        </div>
					
					        <script>
					            document.querySelector("#check-in").valueAsDate = new Date()
					            document.querySelector("#check-in").min = document.querySelector("#check-in").value
					            document.querySelector("#check-out").valueAsDate = new Date()
					            document.querySelector("#check-out").stepUp(1)
					            document.querySelector("#check-out").min = document.querySelector("#check-out").value
					            document.querySelector("#check-out").value = ''
					            
					            var url = new URL(window.location.href);
					            var numPeople = url.searchParams.get('num-people');
					            var checkIn = url.searchParams.get('check-in');
					            var checkOut = url.searchParams.get('check-out');
					            
					            if (numPeople != 0 && numPeople != null) {
					            	document.querySelector("#num-people").value = numPeople;
					            }
					            if (checkIn != '' && checkIn != null) {
					            	document.querySelector("#check-in").value = checkIn;
					            }
					            if (checkOut != '' && checkOut != null) {
					            	document.querySelector("#check-out").value = checkOut;
					            }
					        </script>
					
					    </form>
					</div>									
					
					<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Id</th>
									<th>Tên phòng</th>
									<th>Loại phòng</th>
									<th>Số người</th>
									<th>Trạng thái</th>
									<th>Đơn giá</th>
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
										<td><c:out value="${phong.loaiPhong.soNguoi}"></c:out></td>
										<td><c:out value="${phong.trangThai.tenTrangThai}"></c:out></td>
										<td>
											<fmt:formatNumber type = "number" 
					                    		maxFractionDigits = "0" 
					                    		value = "${phong.loaiPhong.donGia}" /> VNĐ</td>
																	
										<td><a class="btn btn-primary" 
											onclick="openCreateBooking(
														<c:out value="${phong.idPhong}"></c:out>,
														<c:out value="${phong.tenPhong}"></c:out>,
														<c:out value="${phong.loaiPhong.soNguoi}"></c:out>
													)" 
											href="javascript:void(0)">Đặt phòng</a>
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
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Đặt phòng</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<div class="form-inline mb-2">
	                    <div style="display: inline;" class="mr-4">
	                        <label for="ten-phong-pt">Phòng</label>
	                        <input id="ten-phong-pt" type="text" disabled="">
	                    </div>
	                    <div style="display: inline;" class="mr-4">
	                        <label for="check-in-pt">Check in</label>
	                        <input id="check-in-pt" type="date" disabled="">
	                    </div>
	                    <div style="display: inline;" class="mr-4">
	                        <label for="check-out-pt">Check out</label>
	                        <input id="check-out-pt" type="date" disabled="">
	                    </div>
	                    <div style="display: inline;">
	                        <label for="so-nguoi-pt">Số người</label>
	                        <input id="so-nguoi-pt" type="number" onchange="createTableBooking(this.value)" min="1" max="3">
	                    </div>
	                </div>
	                
	                <div>
	                	<div class="table-responsive">
	                		<table class="table table-striped table-hover">
			                    <thead>
			                        <tr>
			                            <th>STT</th>
			                            <th>Khách hàng</th>
			                            <th>CMND</th>
			                            <th>Địa chỉ</th>
			                        </tr>
			                    </thead>
			                    <tbody id="tb-booking">
			                    </tbody>
			                </table>
	                	</div>
		                <input type="hidden" id="id-phong-pt">
	                </div>
	                
					<!-- Modal footer -->
					<div class="modal-footer">
						<input type="button" class="btn btn-success" value="Đặt" onclick="createBooking()">
						<input type="button" class="btn btn-danger" data-dismiss="modal" value="Thoát">							
					</div>
				</div>
				<script type="text/javascript">
					function openCreateBooking(roomId, roomName, numPeople) {
						if (document.getElementById('check-in').value == '' ||
						        document.getElementById('check-out').value == '') {
						            alert('Vui lòng chọn check in, check out');
						} else {
							document.getElementById('id-phong-pt').value = roomId;
							document.getElementById("ten-phong-pt").value = roomName;
							document.getElementById("check-in-pt").value = document.getElementById('check-in').value;
							document.getElementById("check-out-pt").value = document.getElementById('check-out').value;
							document.getElementById("so-nguoi-pt").value = numPeople;
							document.getElementById("so-nguoi-pt").max = numPeople + 1;
							createTableBooking(numPeople);
							$('#myModal').modal();
						}
					}
	
					function createBooking() {
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
								"<%=request.getContextPath()%>/admin/booking-offline", 
								{ 
									idPhong: document.getElementById('id-phong-pt').value,
									checkIn: document.getElementById("check-in-pt").value,
									checkOut: document.getElementById("check-out-pt").value,
									soNguoi: document.getElementById("so-nguoi-pt").value,
									dataKH: JSON.stringify(data),
								},
								function(data, status){
									if (data.status == 200) {
										alert('Đặt phòng thành công');
									} else {
										alert('Đã có lỗi xảy ra, vui lòng thử lại');
									}
									
						            location.reload();
								});
					}
				</script>
			</div>
		</div>
	</div>

</body>
</html>