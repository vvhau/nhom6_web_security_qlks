package com.nhom6.qlks.servlet.admin.room;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.TrangThaiDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class Room
 */
@WebServlet(name = "RoomServlet", urlPatterns = {"/admin/room"})
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomServlet() {
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
		
		new InsertRoomServlet(_csrf);
		
		List<LoaiPhong> lps = new LoaiPhongDao().getAllLoaiPhong();
		request.setAttribute("loaiPhongs", lps);
		
		List<TrangThai> tts = new TrangThaiDao().getAllTrangThai();
		request.setAttribute("trangThais", tts);
		
		List<Phong> phongs = new PhongDao().getAllPhong();
		request.setAttribute("phongs", phongs);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/room-admin/room-admin.jsp");
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
