package com.nhom6.qlks.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nhom6.qlks.hibernate.daos.BookingDao;
import com.nhom6.qlks.hibernate.daos.KhachHangDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.KhachHang;

/**
 * Servlet implementation class GetCustomerBookingAPI
 */
@WebServlet("/api/customer-booking")
public class CustomerBookingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerBookingAPI() {
        super();
        // TODO Auto-generated constructor stub
        this.gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		List<Hashtable<String, Object>> rs = new ArrayList<Hashtable<String,Object>>();
		
		String idBookingStr = request.getParameter("idBooking");
		int idBooking = Integer.parseInt(idBookingStr);
		
		Booking booking = new BookingDao().getBookingById(idBooking);
		List<KhachHang> khachHangs = booking.getKhachHangs();
		
		for (KhachHang kh : khachHangs) {
			Hashtable<String, Object> temp = new Hashtable<String, Object>();
			
			temp.put("hoTen", kh.getHoTen());
			temp.put("cmnd", kh.getCmnd());
			temp.put("diaChi", kh.getDiaChi());
			
			rs.add(temp);
		}
		
		PrintWriter out = response.getWriter();
		
		String rsStr = this.gson.toJson(rs);
		
		out.write(rsStr);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Hashtable<String, Object> rs = new Hashtable<String, Object>();
		
		String idBookingStr = request.getParameter("idBooking");
		String dataKHStr = request.getParameter("dataKH");
		
		int idBooking = Integer.parseInt(idBookingStr);
		KhachHang[] dataKH = gson.fromJson(dataKHStr, KhachHang[].class);
		
		Booking booking = new BookingDao().getBookingById(idBooking);
		
		String err_msg = new KhachHangDao().insertKhachHangs(dataKH, booking);
		if (err_msg.equals("successed")) {
			rs.put("status", 200);
		} else {
			rs.put("status", 404);
		}
		
		PrintWriter out = response.getWriter();
		
		String rsStr = this.gson.toJson(rs);
		
		out.write(rsStr);
		out.flush();
	}

}
