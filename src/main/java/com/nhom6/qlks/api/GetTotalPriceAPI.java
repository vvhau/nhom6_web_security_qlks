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
import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class GetTotalPriceAPI
 */
@WebServlet("/api/get-total-price")
public class GetTotalPriceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Gson gson;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTotalPriceAPI() {
        super();
        this.gson = new Gson();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
        
        int rentalDays = Utils.getRentalDays(checkin, checkout);
        
        LoaiPhongDao roomTypeDao = new LoaiPhongDao();
        LoaiPhong roomType = roomTypeDao.getLoaiPhongById(idRoomType);
        float unitPrice = roomType.getDonGia();
		
        float totalPrice = unitPrice * rentalDays;
		System.out.println("------>>>>>>>>>>");
		System.out.println(totalPrice);
		Hashtable<String, Object> rs = new Hashtable<String,Object>();
		rs.put("totalPrice", totalPrice);
		
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
