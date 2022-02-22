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

/**
 * Servlet implementation class EditRoomServlet
 */
@WebServlet(name = "EditRoom", urlPatterns = {"/admin/room/edit"})
public class EditRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		String idPhongStr =request.getParameter("room-id");
		Phong phong;
		if (idPhongStr == null || idPhongStr.equals("")) {
			phong = null;
		} else {
			phong = new PhongDao().getPhongById(Integer.parseInt(idPhongStr));
		}
		request.setAttribute("phong", phong);
		List<LoaiPhong> roomTypeList = new LoaiPhongDao().getAllLoaiPhong();
		request.setAttribute("loaiPhongs", roomTypeList);
		
		List<TrangThai> statusRoomList = new TrangThaiDao().getAllTrangThai();
		request.setAttribute("trangThais", statusRoomList);
		
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/room-admin/edit-room-admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Integer roomId = Integer.parseInt(request.getParameter("room-id"));
        String roomName = request.getParameter("room-name");
        Integer roomTypeId = Integer.parseInt(request.getParameter("room-type"));
        Integer roomStatusId = Integer.parseInt(request.getParameter("room-status"));          
        
        LoaiPhongDao roomTypeList = new LoaiPhongDao();
        LoaiPhong roomType = roomTypeList.getLoaiPhongById(roomTypeId); 
        
        TrangThaiDao roomStatusList = new TrangThaiDao();
        TrangThai roomStatus = roomStatusList.getTrangThaiById(roomStatusId); 
        
        PhongDao roomDao = new PhongDao();
        roomDao.updatePhong(roomId, roomName, roomType, roomStatus);
        
        response.sendRedirect(request.getContextPath() + "/admin/room");      
	}

}
