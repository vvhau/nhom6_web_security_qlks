<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>ADMIN</title>
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
			<jsp:include page="/WEB-INF/views/layouts/layout-admin/_header-admin.jsp"></jsp:include>
			<script>
				document.querySelector(".nav-sidebar").children[1].classList
						.add('menu-open');
				document.querySelector(".menu-open .nav-link").classList
						.add('active');
				
				$(document).ready(function() {
					showResult('');
				})
			</script>
			
			<div class="content-wrapper">
				<section class="content-header mb-5">
					<div class="container-fluid">
						<div class="col-11 ">
							<h1 class="h3 text-center text-gray-800 mb-0">Lập hóa đơn</h1>
						</div>
					</div>
				</section>
				
				<section class="content">
					<div class="container-fluid">
					<div class="row">
						<div class="col">
							<script type="text/javascript">
								var currentBooking;
							
								function showResult(roomName) {
									let table = document.getElementById("tb-booking");
								    table.innerHTML = '';
								    
									$.get(
											"<%=request.getContextPath()%>/api/find-booking?roomName=" + roomName,
											function(data){
											    for (let i = 0; i < data.length; i++) {
											        let r = table.insertRow(i);
											        
											        r.addEventListener("click", function() {
											        	seeBooking(data[i])
											        })

											        r.insertCell(0).innerText = data[i].idBooking;
											        r.insertCell(1).innerText = data[i].tenPhong;
											        r.insertCell(2).innerText = data[i].checkIn;
											        r.insertCell(3).innerText = data[i].checkOut;
											    }
											});
								}
								
								function seeBooking(data) {
									currentBooking = data;
									
									document.getElementById("ten-phong").value = data.tenPhong;
									document.getElementById("so-nguoi").value = data.soNguoi;
									document.getElementById("check-in").value = data.checkIn;
									document.getElementById("check-out").value = data.checkOut;
									document.getElementById("don-gia").value = data.donGia;
								}
								
								function addBookingToBill() {
									// document.querySelector('#tong-tien').innerText = data.total_price
									$.post(
											"<%=request.getContextPath()%>/api/add-booking-to-bill",
											{
												idBooking: currentBooking.idBooking
											}, 
											function(data) {
												console.log(data);
												
												if (data.status == 301) {
													alert("Phòng đã có trong chi tiết hóa đơn");
												} else if (data.status == 200) {
													let table = document.getElementById("tb-bill-detail");

										            let r = table.insertRow(table.rows.length);

										            r.insertCell(0).innerText = table.rows.length;
										            r.insertCell(1).innerText = currentBooking.tenPhong;
										            r.insertCell(2).innerText = currentBooking.soNgayThue;
										            r.insertCell(3).innerText = currentBooking.donGia + " VNĐ";
										            r.insertCell(4).innerText = currentBooking.thanhTien + " VNĐ";
										            
										            document.getElementById("tong-tien").innerText = data.totalPrice;
												}
											});
						            clear();
								}
								
								function clear() {
									document.getElementById("ten-phong").value = "";
									document.getElementById("so-nguoi").value = "";
									document.getElementById("check-in").value = "";
									document.getElementById("check-out").value = "";
									document.getElementById("don-gia").value = "";
								}
								
								function pay() {
									let billDetail = document.getElementById("bill-detail");
									
								    if (billDetail.rows.length <= 1) {
								        alert('Chưa có thông tin thanh toán');
								    } else {
								    	$.post(
								    			"<%=request.getContextPath()%>/admin/bill/insert",
								    			function(data) {
								    				console.info(data)
											        if (data.status == 200) {
											            alert('Thanh toán thành công');
											            printBill(data.idHD, data.ngayTao)
											            location.reload();
											        } else if (data.status == 404) {
											            alert('Thanh toán thất bại');
											        }
								    			});
								    }
								}
								
								function printBill(idHD, ngayTao) {
									document.getElementById('id-HD').innerHTML = 'Mã hóa đơn: ' + idHD;
								    document.getElementById('ngay-tao').innerHTML = 'Ngày tạo: ' + ngayTao;
								    var printContents = document.getElementById('printJS-form').innerHTML;
								    var originalContents = document.body.innerHTML;

								    document.body.innerHTML = printContents;

								    window.print();

								    document.body.innerHTML = originalContents;
								}
								
								function taiLai() {
									clear();
									
									showResult('');
								}
								
							</script>
							<h3>Thông tin booking</h3>
							<table class="m-auto">
					            <tbody>
					            <tr>
					                <td>Phòng</td>
					                <td style="position: relative;">
					                    <input class="form-control mr-sm-2" id="ten-phong" 
					                    	name="kw" type="text" placeholder="Nhập số phòng..." 
					                    	oninput="showResult(this.value)">
					                </td>
					            </tr>
					            <tr>
					                <td>Số người</td>
					                <td>
					                    <input type="number" id="so-nguoi" class="form-control" disabled="">
					                </td>
					            </tr>
					            <tr>
					                <td>Check in</td>
					                <td>
					                    <input type="date" id="check-in" class="form-control" disabled="">
					                </td>
					            </tr>
					            <tr>
					                <td>Check out</td>
					                <td>
					                    <input type="date" id="check-out" class="form-control" disabled="">
					                </td>
					            </tr>
					            <tr>
					                <td>Đơn giá</td>
					                <td>
					                    <input id="don-gia" class="form-control" disabled="">
					                </td>
					            </tr>
					            </tbody>
					        </table>
							<div class="mt-3 d-flex justify-content-around">
					            <button class="btn btn-primary ml-5" onclick="addBookingToBill()">
					                Thêm vào hóa đơn
					            </button>
					            <button class="btn btn-primary mr-5" onclick="taiLai()">
					                Tải lại
					            </button>
					
					        </div>
					        <div class="mt-3" style="height:250px; overflow-y: scroll;">
					        	<table class="table table-hover">
					        		<thead>
					        			<th>Id booking</th>
					        			<th>Phòng</th>
					                    <th>Check in</th>
					                    <th>Check out</th>
					        		</thead>
					        		<tbody id="tb-booking">
					        		</tbody>
					        	</table>
					        </div>
					        
						</div>
						<div class="col">
							<div id="printJS-form">
					            <h4>Chi tiết hóa đơn</h4>
					            <h5 id="id-HD"></h5>
					            <h6 id="ngay-tao"></h6>
					
					            <table id="bill-detail" class="table table-striped">
					                <thead>
					                <tr>
					                    <th>STT</th>
					                    <th>Phòng</th>
					                    <th>Số ngày thuê</th>
					                    <th>Đơn giá</th>
					                    <th>Thành tiền</th>
					                </tr>
					                </thead>
					                <tbody id="tb-bill-detail"></tbody>
					            </table>
					            <label>Tổng tiền <span id="tong-tien">0</span> VNĐ</label>
					        </div>
					        <div>
					            <button type="button" class="btn btn-primary mb-2" onclick="pay()">Thanh toán</button>
					        </div>
						</div>
					</div>
					</div>
				</section>
				
			</div>
			
		</div>
		
	</body>
</html>