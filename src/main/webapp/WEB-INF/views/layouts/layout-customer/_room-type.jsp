<%@page import="com.nhom6.qlks.hibernate.pojo.LoaiPhong"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    
<section class="room-type" id="room-type">

    <h1 class="heading"> Loại <span>phòng</span> </h1>

    <ul class="pagination">
      <!--   {% for i in range(1, page_num+1) %}
            <li class="page-item"><a class="page-link" href="/?page={{ i }}">{{ i }}</a></li>
        {% endfor %} -->
    </ul>

    <div class="row">
    	<c:forEach items="${loaiPhongs}" var="loaiPhong">
	        <div class="col-md-4 col-sm-6 col-xs-12 mb-5">
	            <div class="card">
	                <img class="card-img" src="<c:url value="${loaiPhong.getHinhAnh()}"/>"  
	                alt="${loaiPhong.tenLoaiPhong}">
	                <div class="card-body">
	                    <h3 class="card-title">${loaiPhong.getTenLoaiPhong()}</h3>
	                    <h3 class="card-title">Số người: ${loaiPhong.getSoNguoi()}</h3>                        
	                    <h4 class="card-title">${loaiPhong.getDonGia()} VND / 1 đêm</h4>
	                    <a href="<c:url value='room-type'/>?id=${loaiPhong.getIdLoaiPhong()}" class="btn">Đặt phòng</a>
	                </div>
	            </div>
	        </div>	         
        </c:forEach>
    </div>

</section>