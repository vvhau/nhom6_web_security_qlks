<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Maris</title>
	
 	<!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <!-- bootstrap 4 cdn link  -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <!-- link icon favicon -->
    <link rel="icon" type="image/png" sizes="32x32" href="<c:url value='static/image/favicon-32x32.png'/>">
    <!-- custom css file link  -->
    <link rel="stylesheet" href="<c:url value='static/css/style.css'/>">

	<link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css" />
    <script src="<c:url value='static/js/home.js'/>"></script>
    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
    <script src="static/js/script.js"></script>
</head>
<body>
	<button id="btn-scroll">&#8593;</button>
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_header.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_home.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_room-type.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_review.jsp"></jsp:include>
	
	<jsp:include page="/WEB-INF/views/layouts/layout-customer/_footer.jsp"></jsp:include>
	
</body>
</html>