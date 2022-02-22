package com.nhom6.qlks.servlet.admin.stats;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.BookingDao;

/**
 * Servlet implementation class StatsUsageServlet
 */
@WebServlet("/admin/stats-usage")
public class StatsUsageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatsUsageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		String monthStr = request.getParameter("month");
		
		BookingDao bookingDao = new BookingDao();
		List<Object[]> thongKe = bookingDao.thongKeMatDoSuDungPhong(monthStr);
		float tongTGSD = (float) bookingDao.tongSuDungPhong(monthStr);
		
		request.setAttribute("thongKe", thongKe);
		request.setAttribute("tongTGSD", tongTGSD);
		request.setAttribute("month", monthStr);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/stats/stats-usage-admin.jsp");
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
