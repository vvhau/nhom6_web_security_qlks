package com.nhom6.qlks.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nhom6.qlks.hibernate.daos.BookingDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class BookingAPI
 */
@WebServlet("/api/find-booking")
public class FindBookingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindBookingAPI() {
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
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String roomName = request.getParameter("roomName");
		
		List<Booking> bookings = new BookingDao().getBookingsByRoomName(roomName);
		
		List<Hashtable<String, Object>> rs = new ArrayList<Hashtable<String,Object>>();
		
		if (bookings != null) {
			bookings.forEach(x -> {
				Date checkIn = x.getCheckIn();
				Date checkOut = x.getCheckOut();
				float donGia = x.getPhong().getLoaiPhong().getDonGia();
				int soNgayThue = Utils.getRentalDays(checkIn, checkOut);
				
				Hashtable<String, Object> bk = new Hashtable<String, Object>();
				bk.put("idBooking", x.getIdBooking());
				bk.put("idPhong", x.getPhong().getIdPhong());
				bk.put("tenPhong", x.getPhong().getTenPhong());
				bk.put("checkIn", dateFormat.format(checkIn));
				bk.put("checkOut", dateFormat.format(checkOut));
				bk.put("soNguoi", x.getSoNguoi());
				bk.put("donGia", donGia);
				bk.put("soNgayThue", soNgayThue);
				bk.put("thanhTien", donGia * soNgayThue);
				
				rs.add(bk);
			});
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
		doGet(request, response);
	}

}
