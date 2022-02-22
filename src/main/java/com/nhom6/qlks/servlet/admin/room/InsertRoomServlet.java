package com.nhom6.qlks.servlet.admin.room;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class InsertRoomServlet
 */
@WebServlet(name = "AddRoom", urlPatterns = {"/admin/room/add"})
public class InsertRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String roomName = request.getParameter("room-name");
        Integer roomTypeId = Integer.parseInt(request.getParameter("room-type"));
        Integer roomStatusId = Integer.parseInt(request.getParameter("room-status"));     
                       
        LoaiPhongDao roomTypeList = new LoaiPhongDao();
        LoaiPhong roomType = roomTypeList.getLoaiPhongById(roomTypeId); 
        
        TrangThaiDao roomStatusList = new TrangThaiDao();
        TrangThai roomStatus = roomStatusList.getTrangThaiById(roomStatusId); 
        
        PhongDao roomDao= new PhongDao();
        Phong room = new Phong(roomName, roomType, roomStatus);
        
        roomDao.insertPhong(room);
        
        response.sendRedirect(request.getContextPath() + "/admin/room");
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		processRequest(request, response);
	}

}
