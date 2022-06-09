package com.nhom6.qlks.servlet.customer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;

/**
 * Servlet implementation class FindRoomTypeServlet
 */
@WebServlet("/search-room-type")
public class FindRoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindRoomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

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
        
		try {
			int numPeople = Integer.parseInt(request.getParameter("num-people"));
			List<LoaiPhong> roomTypes = new PhongDao().searchRoomTypes(numPeople, checkin, checkout);
	        request.setAttribute("loaiPhongs", roomTypes);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (NumberFormatException e){
            System.out.println("Input String cannot be parsed to Integer.");
            response.sendError(404);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
	

}
