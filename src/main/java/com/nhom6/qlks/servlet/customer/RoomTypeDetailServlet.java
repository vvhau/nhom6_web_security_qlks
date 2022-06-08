package com.nhom6.qlks.servlet.customer;

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
 * Servlet implementation class RoomTypeDetailServlet
 */
@WebServlet(name = "RoomTypeCustomerServlet", urlPatterns = {"/room-type"})
public class RoomTypeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTypeDetailServlet() {
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
        
        try {
        	Integer idRoomType = Integer.parseInt(request.getParameter("id"));
        	LoaiPhong roomTypeDetail = new LoaiPhongDao().getLoaiPhongById(idRoomType);
    		
    		request.setAttribute("roomTypeDetail", roomTypeDetail);
    		
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/room-type-detail.jsp");
    		dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/404.jsp");
    		dispatcher.forward(request, response);
        }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
