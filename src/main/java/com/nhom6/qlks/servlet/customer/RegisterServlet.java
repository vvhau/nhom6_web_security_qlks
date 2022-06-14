package com.nhom6.qlks.servlet.customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhom6.qlks.hibernate.daos.QuyenDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.User;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
        String csrf = request.getParameter("_csrf");
        if(csrf == null || !csrf.equals(_csrf)){
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("/WEB-INF/views/error/UnvalidTokenCsrf.jsp");
    		dispatcher.forward(request, response);
    		return;
        }
		
		String err_msg = "";
		
		String hoTen = request.getParameter("name");
		String gioiTinh = request.getParameter("gender");
		String cmnd = request.getParameter("id-card");
		String email = request.getParameter("email");
		String sdt = request.getParameter("phone");
		String tenDangNhap = request.getParameter("username");
		String matKhau = request.getParameter("password");
		String matKhau2 = request.getParameter("confirm-password");
		Quyen quyen = new QuyenDao().getQuyenById(3);
		boolean kichHoat = true;
		
		Date ngaySinh;
		try {
			ngaySinh =  new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
		} catch (Exception e) {
			// TODO: handle exception
			err_msg = "Ngày không hợp lệ";
			request.setAttribute("errMessage", err_msg);
			request.getRequestDispatcher("/WEB-INF/views/customer/register.jsp").forward(request, response);
			return;
		}
		
		if (!matKhau.equals(matKhau2)) {
			err_msg = "Xác nhận mật khẩu không đúng";
			request.setAttribute("errMessage", err_msg);
			request.getRequestDispatcher("/WEB-INF/views/customer/register.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		user.setHoTen(hoTen);
		user.setNgaySinh(ngaySinh);
		user.setGioiTinh(gioiTinh);
		user.setCmnd(cmnd);
		user.setEmail(email);
		user.setSdt(sdt);
		user.setTenDangNhap(tenDangNhap);
		user.setMatKhau(matKhau);
		user.setQuyen(quyen);
		user.setKichHoat(kichHoat);
		
		String errCheckUser = new Utils().checkRegister(user);
		if (errCheckUser != null) {
			request.setAttribute("errMessage", errCheckUser);
			request.getRequestDispatcher("/WEB-INF/views/customer/register.jsp").forward(request, response);
			return;
		}
		
		user.setMatKhau(new Utils().strToMD5(matKhau));
		
		UserDao userDao = new UserDao();
		
		err_msg = userDao.insertUser(user);
		if (err_msg.equals("successed")) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			String site = request.getContextPath();
			 
//	        response.setStatus(response.SC_MOVED_TEMPORARILY);
//	        response.setHeader("Location", site);
//			
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
			response.sendRedirect(site);
			return;
		} else {
			request.setAttribute("errMessage", err_msg);
			request.getRequestDispatcher("/WEB-INF/views/customer/register.jsp").forward(request, response);
			return;
		}
		
		
	}

}
