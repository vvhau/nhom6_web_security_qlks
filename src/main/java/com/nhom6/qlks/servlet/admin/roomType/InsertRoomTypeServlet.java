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

/**
 * Servlet implementation class InsertRoomTypeServlet
 */
@WebServlet(name = "AddRoomType", urlPatterns = {"/admin/room-type/add"})
public class InsertRoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRoomTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String roomTypeName = request.getParameter("room-type-name");
        String roomTypeImage = request.getParameter("room-type-image");
        Float roomTypeUnitPrice = Float.parseFloat(request.getParameter("room-type-unit-price"));
        Integer roomTypeNumPeople = Integer.parseInt(request.getParameter("room-type-num-people"));
        String roomTypeNote = request.getParameter("room-type-note");   
                       
        
        LoaiPhongDao roomTypeDao = new LoaiPhongDao();
        LoaiPhong roomType = new LoaiPhong(roomTypeName, roomTypeImage, roomTypeUnitPrice, roomTypeNumPeople, roomTypeNote);
        
        roomTypeDao.insertLoaiPhong(roomType);
        
        response.sendRedirect(request.getContextPath() + "/admin/room-type");
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
