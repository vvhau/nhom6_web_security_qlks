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
	<title>Thống kê mật độ sử dụng phòng</title>
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
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="<%=request.getContextPath()%>/static/js/script.js"></script>
</head>
<body>
	<div class="wrapper">

		<jsp:include
			page="/WEB-INF/views/layouts/layout-admin/_header-admin.jsp"></jsp:include>
		<script>		
			$( "li:nth-child(11)").addClass('menu-is-opening menu-open');
			$( "li:nth-child(11) > ul > li:nth-child(2)" ).children().addClass('active');
		</script>

		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="col-11 ">
						<h1 class="h3 text-center text-gray-800 mb-0">Thống kê mật độ sử dụng phòng</h1>
					</div>
				</div>
			</section>

			<section class="content">

				<div class="container-fluid">
				
					<div class="row">
					    <div class="col">
					        <form id="option">
					            <input type="month" name="month" id="month" onchange="document.getElementById('option').submit();">
					            <a href="<%=request.getContextPath()%>/admin/stats-usage" class="btn btn-primary">Tất cả</a>
					        </form>
					        
					        <script>
					            document.getElementById('month').value = '<%=request.getAttribute("month")%>';
					        </script>
					        
					        <table class="table table-striped">
					            <thead>
					                <tr>
					                    <th>Id Phòng</th>
					                    <th>Phòng</th>
					                    <th>Số Ngày Thuê</th>
					                    <th>Tỷ Lệ</th>
					                </tr>
					            </thead>
					            <tbody>
					            <c:forEach items="${thongKe}" var="phong">
					                <tr>
					                    <td><c:out value="${phong[0]}"></c:out></td>
					                    <td><c:out value="${phong[1]}"></c:out></td>
					                    <td><c:out value="${phong[2]}"></c:out> Ngày</td>
					                    <td>
					                    	<fmt:parseNumber var = "temp" type = "number" 
					                    		value = "${phong[2]}" />
					                    	<fmt:formatNumber type = "number" 
					                    				groupingUsed = "false" 
					                    				maxFractionDigits = "2"
					                    				value = "${100 * temp / tongTGSD}" /> %</td>
					                </tr>
					            </c:forEach>
					            	<tr>
					            		<td></td>
					            		<td><strong>Tổng</strong></td>
					            		<td>
					            			<strong>
					            				<fmt:formatNumber type = "number" 
					                    				groupingUsed = "false" 
					                    				maxFractionDigits = "0"
					                    				value = "${tongTGSD}" /> Ngày
					                    	</strong>
					                    </td>
					            		<td><strong>100 %</strong></td>
					            	</tr>
					            </tbody>
					        </table>
					    </div>
					    <div class="col">
					        <canvas id="usageChart"></canvas>
					        <script>
					            let labels = [], info = [];
					
					            <c:forEach items="${thongKe}" var="phong">
					                labels.push('${phong[1]}');
				                    info.push(${phong[2]});
					            </c:forEach>
					
					            var dynamicColors = function() {
					                var r = Math.floor(Math.random() * 255);
					                var g = Math.floor(Math.random() * 255);
					                var b = Math.floor(Math.random() * 255);
					                return 'rgb(' + r + ',' + g + ',' + b + ')';
					             };
					
					            var colors = [];
					            for (let i = 0; i < labels.length; i++) {
					                colors.push(dynamicColors());
					            }
					
					            const data = {
					              labels: labels,
					              datasets: [{
					                label: 'Thống kê doanh thu theo mật độ sử dụng phòng',
					                data: info,
					                backgroundColor: colors,
					                hoverOffset: 4
					              }]
					            };
					
					            const config = {
					              type: 'pie',
					              data: data,
					            };
					
					            window.onload = function() {
					                let ctx = document.getElementById('usageChart').getContext('2d');
					                new Chart(ctx, config);
					            }
					        </script>
					    </div>
					</div>
				
				</div>
				
			</section>
		</div>
	</div>
</body>
</html>
		