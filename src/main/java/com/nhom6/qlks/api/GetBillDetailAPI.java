package com.nhom6.qlks.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.nhom6.qlks.hibernate.daos.HoaDonDao;
import com.nhom6.qlks.hibernate.pojo.Booking;
import com.nhom6.qlks.hibernate.pojo.HoaDon;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class GetBillDetailAPI
 */
@WebServlet("/api/get-bill-detail")
public class GetBillDetailAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBillDetailAPI() {
        super();
        // TODO Auto-generated constructor stub
        this.gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		List<Hashtable<String, Object>> rs = new ArrayList<Hashtable<String,Object>>();
//		Hashtable<String, Object> rs = new Hashtable<String, Object>();
		
		String idHDStr = request.getParameter("idHD");
		int idHD = Integer.parseInt(idHDStr);
		
		HoaDon hoaDon = new HoaDonDao().getHoaDonById(idHD);
		
		if (hoaDon != null) {
			List<Booking> billDetail = hoaDon.getBookings();
			
			for (Booking bk : billDetail) {
				Hashtable<String, Object> temp = new Hashtable<String, Object>();
				
				temp.put("idBooking", bk.getIdBooking());
				temp.put("tenPhong", bk.getPhong().getTenPhong());
				temp.put("soNgayThue", Utils.getRentalDays(bk.getCheckIn(), bk.getCheckOut()));
				temp.put("donGia", bk.getPhong().getLoaiPhong().getDonGia());
				temp.put("thanhTien", Utils.calcTotalPriceBooking(bk));
				
				rs.add(temp);
			}
		} else {
			
		}
		
		PrintWriter out = response.getWriter();
		
		String rsStr = this.gson.toJson(rs);
		
		out.write(rsStr);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
