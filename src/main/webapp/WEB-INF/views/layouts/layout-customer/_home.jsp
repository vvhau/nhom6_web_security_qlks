<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<section class="home" id="home">

    <div class="content">
        <h3>MARIS</h3>
        <h4>Thiên Đường Nghỉ Dưỡng</h4>
        <p>Sự hài lòng là phương châm của chúng tôi</p>
    </div>

    <div class="form-container">
        <form action="search-room-type" method="get">
            <h3>Tìm phòng</h3>

            <div class="form-group">
                <label class="font-weight-bold col-sm control-label">Số người<strong
                        style="color: red">*</strong></label>
                <div class="col-sm-12">
                    <input id="numPeople"
                           class="form-control form-control-lg"
                           type="number"
                           name="num-people"
                           placeholder="Nhập số người"
                           min="1"
                           required>
                </div>
            </div>

            <div class="form-group">
                <label class="font-weight-bold col-sm control-label">Check in <strong
                        style="color: red">*</strong></label>
                <div class="col-sm-12">
                    <input id="check-in"
                           class="form-control form-control-lg"
                           type="date"
                           name="check-in"
                           required>
                </div>
            </div>
            <div class="form-group">
                <label class="font-weight-bold col-sm control-label">Check out <strong
                        style="color: red">*</strong></label>
                <div class="col-sm-12">
                    <input id="check-out"
                           class="form-control form-control-lg"
                           type="date"
                           name="check-out"
                           required>
                </div>
            </div>

            <script>
                document.querySelector("#check-in").valueAsDate = new Date()
                document.querySelector("#check-in").min = document.querySelector("#check-in").value
                document.querySelector("#check-out").valueAsDate = new Date()
                document.querySelector("#check-out").stepUp(1)
                document.querySelector("#check-out").min = document.querySelector("#check-out").value
                document.querySelector("#check-out").value = ''
            </script>
           

            <button id="btnSearchRoomTypes" type="submit">Tìm phòng</button>
        </form>
    </div>

    <div class="share">
        <a href="#" class="fab fa-facebook-f"></a>
        <a href="#" class="fab fa-twitter"></a>
        <a href="#" class="fab fa-instagram"></a>
        <a href="#" class="fab fa-linkedin"></a>
    </div>
</section>