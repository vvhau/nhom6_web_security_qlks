package com.nhom6.qlks.servlet.admin.room;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.pojo.Phong;

/**
 * Servlet implementation class RoomSearch
 */
@WebServlet("/admin/room-search")
public class RoomSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String soNguoiStr = request.getParameter("num-people");
		String checkInStr = request.getParameter("check-in");
		String checkOutStr = request.getParameter("check-out");
		
		int soNguoi;
		try {
			soNguoi = (soNguoiStr != null) ? Integer.parseInt(soNguoiStr) : 0;
		} catch (Exception e) {
			// TODO: handle exception
			soNguoi = 0;
		}
		
		Date checkIn, checkOut;
		try {
			checkIn = (checkInStr != null) ? dateFormat.parse(checkInStr) : null;
		} catch (Exception e) {
			// TODO: handle exception
			checkIn = null;
		}
		
		try {
			checkOut = (checkOutStr != null) ? dateFormat.parse(checkOutStr) : null;
		} catch (Exception e) {
			// TODO: handle exception
			checkOut = null;
		}
		
		List<Phong> phongs = new PhongDao().timPhong(soNguoi, checkIn, checkOut);
		request.setAttribute("phongs", phongs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/room-admin/room-search-admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
