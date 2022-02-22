package com.nhom6.qlks.servlet.admin.bill;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.nhom6.qlks.hibernate.daos.HoaDonDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet implementation class CreateBill
 */
@WebServlet("/admin/bill/insert")
public class CreateBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBillServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		List<Booking> billDetail = new ArrayList<Booking>();
		
		session.setAttribute("billDetail", billDetail);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/bill-admin/create-bill-admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Hashtable<String, Object> rs = new Hashtable<String, Object>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		HttpSession session = request.getSession();
		
		List<Booking> billDetail = (List<Booking>) session.getAttribute("billDetail"); 
		
		User user = (User) session.getAttribute("user");
		
		HoaDonDao hoaDonDao = new HoaDonDao();
		HoaDon hoaDon = new HoaDon();
		hoaDon.setUser(user);
		
		Date currentDate = new Date();
		hoaDon.setNgayTao(currentDate);

		String err_msg = hoaDonDao.inserHoaDon(hoaDon, billDetail);
		if (err_msg.equals("successed")) {
			rs.put("status", 200);
			rs.put("idHD", hoaDon.getIdHD());
			rs.put("ngayTao", dateFormat.format(hoaDon.getNgayTao()));
		} else {
			rs.put("status", 404);
		}
		
		PrintWriter out = response.getWriter();
		
		String rsStr = this.gson.toJson(rs);
		
		out.write(rsStr);
		out.flush();
	}

}
