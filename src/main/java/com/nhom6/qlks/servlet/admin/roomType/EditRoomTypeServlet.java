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
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.TrangThaiDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class RoomType
 */
@WebServlet(name = "EditRoomType", urlPatterns = {"/admin/room-type/edit"})
public class EditRoomTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditRoomTypeServlet() {
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
        
		_csrf = Utils.randomString();
		request.setAttribute("_csrf", _csrf);
        
		String idRoomTypeStr = request.getParameter("room-type-id");
		
		LoaiPhong roomType;
		if (idRoomTypeStr == null || idRoomTypeStr.equals("")) {
			roomType = null;
		} else {
			roomType = new LoaiPhongDao().getLoaiPhongById(Integer.parseInt(idRoomTypeStr));
		}
		request.setAttribute("roomType", roomType);
		
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/room-type-admin/edit-room-type-admin.jsp");
		dispatcher.forward(request, response);				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String csrf = request.getParameter("_csrf");
        if(csrf == null || !csrf.equals(_csrf)){
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("/WEB-INF/views/error/UnvalidTokenCsrf.jsp");
    		dispatcher.forward(request, response);
    		return;
        }
        
        Integer roomTypeId = Integer.parseInt(request.getParameter("room-type-id"));
        String roomTypeName = request.getParameter("room-type-name");
        String roomTypeImage = request.getParameter("room-type-image");
        Float roomTypeUnitPrice = Float.parseFloat(request.getParameter("room-type-unit-price"));
        Integer roomTypeNumPeople = Integer.parseInt(request.getParameter("room-type-num-people"));
        String roomTypeNote = request.getParameter("room-type-note");           
        
        LoaiPhongDao roomTypeDao = new LoaiPhongDao();
        roomTypeDao.updateLoaiPhong(roomTypeId, roomTypeName, roomTypeImage, roomTypeUnitPrice, roomTypeNumPeople, roomTypeNote);
        
        response.sendRedirect(request.getContextPath() + "/admin/room-type");      
	}

}
