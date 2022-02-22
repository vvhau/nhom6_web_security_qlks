package com.nhom6.qlks.servlet.admin.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.BookingDao;
import com.nhom6.qlks.hibernate.daos.KhachHangDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.KhachHang;

/**
 * Servlet implementation class OnlineBooking
 */
@WebServlet(name = "BookingOnlineServlet", urlPatterns = {"/admin/booking-online"})
public class OnlineBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OnlineBookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");			
		
		List<KhachHang> customers = new KhachHangDao().getAllKhachHang();
		request.setAttribute("customers", customers);
		
		List<Booking> bookings;
		String idBookingStr = request.getParameter("id-booking");
		if (idBookingStr != null) {
			int idBooking = Integer.parseInt(idBookingStr);
			
			Booking booking = new BookingDao().getBookingOnlineById(idBooking);
			
			bookings = new ArrayList<Booking>();
			if (booking != null) {
				bookings.add(booking);
			}
		} else {
			String numPageStr = request.getParameter("page");
			int numPage;
			if (numPageStr != null) {
				numPage = Integer.parseInt(numPageStr);
			} else {
				numPage = 1;
			}
			request.setAttribute("numPage", numPage);
			
			bookings = new BookingDao().getAllBookingOnline(numPage);
		}
		
		request.setAttribute("bookings", bookings);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/booking-admin/booking-online-admin.jsp");
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
