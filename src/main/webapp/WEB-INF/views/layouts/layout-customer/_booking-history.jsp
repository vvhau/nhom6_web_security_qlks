<%@page import="com.nhom6.qlks.hibernate.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="container" style="min-height:500px">
<h1 class="text-center mt-5 text-info display-4 font-weight-bold">Lịch sử đặt phòng</h1>

<table class="table mt-5 h3 ">
    <tr>
        <th>Mã booking</th>
        <th>Phòng</th>
        <th>Số người</th>
        <th>Check-in</th>
        <th>Check-out</th>
        <th>Đơn giá</th>
        <th></th>
    </tr>

	<%User user = (User) session.getAttribute("user");%>
	<% if (user != null) { %>
		<c:choose>
			<c:when test="${numBooking == 0}">
		        <tr>
		            <td colspan="7" class="text-center">Không có booking nào</td>
		        </tr>
	   		 </c:when>
	   		 <c:otherwise>
		   		<c:forEach items="${bookings}" var="booking">				       
			        <tr id="booking-id">
			            <td> <c:out value = "${booking.getIdBooking()}"/></td>				         
			            <td> <c:out value = "${booking.getPhong().getTenPhong()}"/></td>				         
			            <td> <c:out value = "${booking.getSoNguoi()}"/></td>				         
			            <td><fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.getCheckIn()}"/></td>			         
			            <td><fmt:formatDate pattern = "yyyy-MM-dd" value="${booking.getCheckOut()}"/></td>			        
			            
			        	<td>
			        		<fmt:setLocale value = "vi_VN"/>
			        		<fmt:formatNumber value="${booking.getPhong().getLoaiPhong().getDonGia()}" type="currency"  maxFractionDigits = "0"/>
			        	</td>
			        </tr>
				</c:forEach> 
	   		 </c:otherwise>			
		</c:choose>
		
    <% } %>
</table>

</div>
