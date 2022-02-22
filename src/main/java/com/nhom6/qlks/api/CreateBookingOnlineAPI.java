package com.nhom6.qlks.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nhom6.qlks.hibernate.daos.BookingDao;
import com.nhom6.qlks.hibernate.daos.KhachHangDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.KhachHang;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet implementation class CreateBookingOnlineAPI
 */
@WebServlet("/api/booking-online")
public class CreateBookingOnlineAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBookingOnlineAPI() {
        super();
        this.gson = new Gson();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Hashtable<String, Object> rs = new Hashtable<String, Object>();	
		int numPeople = Integer.parseInt(request.getParameter("so_nguoi"));
		Date checkin = new Date();
        String checkinStr = request.getParameter("check_in");
        try {        	        	
        	checkin = new SimpleDateFormat("yyyy-MM-dd").parse(checkinStr);
       
        } catch (java.text.ParseException e){
        	e.printStackTrace();
        }
        
        Date checkout = new Date();
        String checkoutStr = request.getParameter("check_out");
        try {        	        	
        	checkout = new SimpleDateFormat("yyyy-MM-dd").parse(checkoutStr);
        
        } catch (java.text.ParseException e){
        	e.printStackTrace();
        }
		
		int idRoom = Integer.parseInt(request.getParameter("id_phong"));
		int idCustomer = Integer.parseInt(request.getParameter("id_khach_hang"));
		boolean isBookingOnline = true;
		
		User customer = new UserDao().getUserByid(idCustomer);
		Phong room = new PhongDao().getPhongById(idRoom);
		
		Booking booking = new Booking(numPeople, checkin, checkout, isBookingOnline, customer, room);
		
		String err_msg = new BookingDao().insertBookingOnline(booking);
		System.out.println(err_msg);
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
