<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>    

    <%-- <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin">Trang chủ</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/bill/insert">Lập hóa đơn</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/room">Phòng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/room-type">Loại Phòng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/room-type">Trạng thái phòng</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/booking">Booking</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/bill">Hóa đơn</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/online-customer">Khách hàng online</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/offline-customer">Khách hàng offline</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/employee">Nhân viên</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/role">Quyền</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/stats">Thống kê</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=request.getContextPath()%>/admin/login">Đăng nhập</a>
	    </li>
	  </ul>
	</nav> --%>
	
	
    <%--  		<%User user = (User) session.getAttribute("user");%>
    		<% if (user != null) { %>
    			<a class="fas fa-shopping-cart" href=""></a>
    			<a href="<%=request.getContextPath()%>/<%=user.getTenDangNhap()%>/">
	              <%= user.getHoTen() %>  
	            </a>
    			<a href="<%=request.getContextPath()%>/logout">Đăng xuất</a>
    		<% } else { %>  --%>
     <!--   <!--  {% if current_user.is_authenticated %} 
            <a class="fas fa-shopping-cart" href=""></a>
            <a href="/">
              {{ current_user.ho_ten }}  
            </a>
            <a class="nav-link" href="/logout">Đăng xuất</a> -->
       <!--  {% else %} -->
       		
            
       <%--      <% } %> --%>
       <!--  {% endif %} -->
       
<nav class="main-header navbar navbar-expand navbar-white navbar-light">

    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
      </li>
    </ul>

    <!-- Right navbar links -->
    <ul class="navbar-nav ml-auto">
		<li class="nav-item">
        	<a class="nav-link" data-widget="fullscreen" href="#" role="button">
          		<i class="fas fa-expand-arrows-alt" aria-hidden="true"></i>
        	</a>
      	</li>
    </ul>

</nav>  
  
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <h1 class="brand-link">
      <span class="brand-text font-weight-light font-weight-bold">MARIS</span>
    </h1>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="image">          
          <img src="<%=request.getContextPath()%>/static/image/avatar.svg" class="img-circle elevation-2" alt="User Image"/>   
        </div>
        <div class="info">
          <a href="#" class="d-block"><c:out value = "${ sessionScope.user.hoTen }"/></a>
        </div>
      </div>

      <!-- SidebarSearch Form -->
      <div class="form-inline">
        <div class="input-group" data-widget="sidebar-search">
          <input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
          <div class="input-group-append">
            <button class="btn btn-sidebar">
              <i class="fas fa-search fa-fw"></i>
            </button>
          </div>
        </div><div class="sidebar-search-results"><div class="list-group"><a href="#" class="list-group-item"><div class="search-title"><strong class="text-light"></strong>N<strong class="text-light"></strong>o<strong class="text-light"></strong> <strong class="text-light"></strong>e<strong class="text-light"></strong>l<strong class="text-light"></strong>e<strong class="text-light"></strong>m<strong class="text-light"></strong>e<strong class="text-light"></strong>n<strong class="text-light"></strong>t<strong class="text-light"></strong> <strong class="text-light"></strong>f<strong class="text-light"></strong>o<strong class="text-light"></strong>u<strong class="text-light"></strong>n<strong class="text-light"></strong>d<strong class="text-light"></strong>!<strong class="text-light"></strong></div><div class="search-path"></div></a></div></div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/room-search" class="nav-link">
              <i class="nav-icon fas fa-search"></i>
              <p>
                Tra cứu phòng             
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/bill/insert" class="nav-link">
              <i class="nav-icon fas fa-receipt"></i>
              <p>
                Lập hóa đơn                
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-copy"></i>
              <p>
                Đơn đặt phòng
                <i class="fas fa-angle-left right"></i>
                <span class="badge badge-info right">2</span>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
              	<a href="<%=request.getContextPath()%>/admin/booking-offline" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Đơn đặt phòng offline</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="<%=request.getContextPath()%>/admin/booking-online" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Đơn đặt phòng online</p>
                </a>
              </li>                        
            </ul>
          </li>                    
          
          
          <c:choose>
          	<c:when test="${ sessionScope.user.quyen.idQuyen == 1 }">
          		<li class="nav-header">QUẢN LÝ</li>
		          <li class="nav-item">
		            <a href="<%=request.getContextPath()%>/admin/bill" class="nav-link">
		              <i class="nav-icon fas fa-edit"></i>
		              <p>
		                Hóa đơn                
		              </p>
		            </a>            
		          </li>
		          
		          <li class="nav-item">
		            <a href="<%=request.getContextPath()%>/admin/room-type" class="nav-link">
		              <i class="nav-icon fas fa-table"></i>
		              <p>
		                Loại phòng
		              </p>
		            </a>           
		          </li>
		              
		          <li class="nav-item">
		            <a href="<%=request.getContextPath()%>/admin/room" class="nav-link">
		              <i class="nav-icon fa fa-bed"></i>
		              <p>
		                Phòng             
		              </p>
		            </a>           
		          </li>          
		          
		          <li class="nav-item">
		            <a href="<%=request.getContextPath()%>/admin/online-customer" class="nav-link">
		              <i class="nav-icon fas fa-users"></i>
		              <p>
		                Khách hàng online                
		              </p>
		            </a>            
		          </li>
		          
		          <li class="nav-item">
		            <a href="<%=request.getContextPath()%>/admin/employee" class="nav-link">
		              <i class="nav-icon fa fa-user-tie"></i>
		              <p>
		                Nhân viên  
		              </p>
		            </a>            
		          </li>
		          
		          <li class="nav-header">THỐNG KÊ</li>         
		          <li class="nav-item">
		            <a href="" class="nav-link">
		              <i class="nav-icon fas fa-chart-pie"></i>
		              <p>
		                Báo cáo tháng
		                <i class="right fas fa-angle-left"></i>
		                <span class="badge badge-info right">2</span>                            
		              </p>                                         
		            </a>
		            <ul class="nav nav-treeview">
		              <li class="nav-item">
		                <a href="<%=request.getContextPath()%>/admin/stats-revenue" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Doanh thu theo loại phòng</p>
		                </a>
		              </li>
		              <li class="nav-item">
		                <a href="<%=request.getContextPath()%>/admin/stats-usage" class="nav-link">
		                  <i class="far fa-circle nav-icon"></i>
		                  <p>Mật độ sử dụng phòng</p>
		                </a>
		              </li>              
		            </ul>
		          </li>
          	</c:when>
          	
          	<c:otherwise>
          	</c:otherwise>
          </c:choose>
          
          
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/admin/logout" class="nav-link">
              <i class="nav-icon fas fa-sign-out-alt"></i>
              <p>
                Đăng xuất              
              </p>
            </a>            
          </li>   
                                                   
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>
       
       
       
       
       