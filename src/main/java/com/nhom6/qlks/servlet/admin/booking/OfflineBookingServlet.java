package com.nhom6.qlks.servlet.admin.booking;

import java.beans.JavaBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nhom6.qlks.hibernate.daos.BookingDao;
import com.nhom6.qlks.hibernate.daos.HoaDonDao;
import com.nhom6.qlks.hibernate.daos.KhachHangDao;
import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.TrangThaiDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.KhachHang;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet implementation class OfflineBooking
 */
@WebServlet(name = "BookingOfflineServlet", urlPatterns = {"/admin/booking-offline"})
public class OfflineBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfflineBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");	
		
		String numPageStr = request.getParameter("page");
		int numPage;
		if (numPageStr != null) {
			numPage = Integer.parseInt(numPageStr);
		} else {
			numPage = 1;
		}
		request.setAttribute("numPage", numPage);
		
		List<Booking> bookings = new BookingDao().getAllBookingOffline(numPage);
		request.setAttribute("bookings", bookings);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/booking-admin/booking-offline-admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Hashtable<String, Object> result = new Hashtable<String, Object>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("user"); 
		
		String idPhongStr = request.getParameter("idPhong");
		String checkInStr = request.getParameter("checkIn");
		String checkOutStr = request.getParameter("checkOut");
		String soNguoiStr = request.getParameter("soNguoi");
		String dataKHStr = request.getParameter("dataKH");
		
		int idPhong = Integer.parseInt(idPhongStr);
		int soNguoi = Integer.parseInt(soNguoiStr);
		KhachHang[] dataKH = gson.fromJson(dataKHStr, KhachHang[].class);
		
//		for (KhachHang kh : dataKH) {
//			System.out.printf("ten: %s, cmnd: %s, diachi: %s", kh.getHoTen(), kh.getCmnd(), kh.getDiaChi());
//			
//		}
		
		Phong phong = new PhongDao().getPhongById(idPhong);
		try {
			Date checkIn = dateFormat.parse(checkInStr);
			Date checkOut = dateFormat.parse(checkOutStr);
			
			Booking booking = new Booking();
			booking.setCheckIn(checkIn);
			booking.setCheckOut(checkOut);
			booking.setSoNguoi(soNguoi);
			booking.setPhong(phong);
			booking.setUser(user);
			
			BookingDao bookingDao = new BookingDao();
			
			String err_msg = bookingDao.insertBooking(booking, dataKH);
			if (err_msg.equals("successed")) {
				result.put("status", 200);
			} else {
				result.put("status", 404);
			}
		} catch (Exception e) {
			// TODO: handle exception
			result.put("status", 404);
			
		}
		
		PrintWriter out = response.getWriter();
		
		String rs = this.gson.toJson(result);
		
		out.write(rs);
		out.flush();
	}

}
