<%@page import="com.nhom6.qlks.hibernate.pojo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    
<header class="header">

    <a href="<c:url value='/'/>" class="logo"> <i class="fas fa-hotel"></i> Maris </a>

    <nav class="navbar">
        <a href="<c:url value='/#home'/>">Trang chủ</a>
        <a href="<c:url value='/#home'/>">Tìm phòng</a>
        <a href="<c:url value='/#room-type'/>">Loại phòng</a>
        <a href="<c:url value='/#review'/>">Đánh giá</a>
        <a href="<c:url value='/#contact'/>">Liên hệ</a>
    </nav>

	<nav class="navbar">
        <%User user = (User) session.getAttribute("user");%>
        <% if (user != null) { %>
	        <div class="dropdown">
	            <a class="nav-link dropdown-toggle" id="dropdownAccount" data-toggle="dropdown"
	               aria-haspopup="true" aria-expanded="false" href="/">
	                <i class="fa fa-user" aria-hidden="true"></i>
	                <%= user.getHoTen() %>	                
	            </a>
	            <div class="dropdown-menu" aria-labelledby="dropdownAccount">
	                <a class="dropdown-item" href="<%=request.getContextPath()%>/change-password">Đổi mật khẩu </a>
	                <a class="dropdown-item" href="<%=request.getContextPath()%>/booking-history">Lịch sử đặt phòng </a>
	                <a class="dropdown-item" href="<%=request.getContextPath()%>/logout">Đăng xuất</a>
	            </div>
	        </div>
		<% } else { %>       		
            <a href="<%=request.getContextPath()%>/login">Đăng nhập</a>
            <a href="<%=request.getContextPath()%>/register">Đăng ký</a>
        <% } %>
        
    </nav>

</header>