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
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class FindRoomsByRoomTypeAPI
 */
@WebServlet("/api/find-rooms-by-room-type")
public class FindRoomsByRoomTypeAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindRoomsByRoomTypeAPI() {
        super();
        this.gson = new Gson();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		
		int idRoomType = Integer.parseInt(request.getParameter("room-type"));
	
		
		Date checkin = new Date();
        String checkinStr = request.getParameter("check-in");
        try {        	        	
        	checkin = new SimpleDateFormat("yyyy-MM-dd").parse(checkinStr);
       
        } catch (java.text.ParseException e){
        	e.printStackTrace();
        }
        
        Date checkout = new Date();
        String checkoutStr = request.getParameter("check-out");
        try {        	        	
        	checkout = new SimpleDateFormat("yyyy-MM-dd").parse(checkoutStr);
        
        } catch (java.text.ParseException e){
        	e.printStackTrace();
        }
		
		List<Phong> rooms = new PhongDao().searchRoomsByRoomType(idRoomType, checkin, checkout);
		
		List<Hashtable<String, Object>> rs = new ArrayList<Hashtable<String,Object>>();
		
		if (rooms != null) {
			rooms.forEach(x -> {			
				String roomName = x.getTenPhong();
				int roomId = x.getIdPhong();
				
				Hashtable<String, Object> room = new Hashtable<String, Object>();
				room.put("idPhong", roomId);
				room.put("tenPhong", roomName);		
				
				rs.add(room);
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
