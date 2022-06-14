package com.nhom6.qlks.servlet.admin.roomType;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.servlet.admin.room.InsertRoomServlet;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class RoomType
 */
@WebServlet(name = "RoomTypeServlet", urlPatterns = {"/admin/room-type"})
public class RoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		_csrf = Utils.randomString();
		request.setAttribute("_csrf", _csrf);
		
		new InsertRoomTypeServlet(_csrf);
		
		List<LoaiPhong> lps = new LoaiPhongDao().getAllLoaiPhong();
		
		request.setAttribute("loaiPhongs", lps);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/room-type-admin/room-type-admin.jsp");
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
