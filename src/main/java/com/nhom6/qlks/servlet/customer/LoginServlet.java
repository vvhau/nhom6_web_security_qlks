package com.nhom6.qlks.servlet.customer;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String err_msg = "";		
		String tenDangNhap = request.getParameter("username");
		String matKhau = new Utils().strToMD5(request.getParameter("password"));
		Quyen quyen = new QuyenDao().getQuyenById(3);
		
		UserDao userDao = new UserDao();
		if (userDao.loginUser(tenDangNhap, matKhau, quyen)) {
			User user = userDao.getUserByUsername(tenDangNhap);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			String next = request.getParameter("next");
			String site = request.getContextPath() + next ;
			request.setAttribute("site", site);		
			
			response.sendRedirect(site);
			return;
		} else {
			request.setAttribute("errMessage", "Tên đăng nhập hoặc mật khẩu không đúng hoặc tài khoản bị khóa");
			request.getRequestDispatcher("/WEB-INF/views/customer/login.jsp").forward(request, response);
			return;
		}
	}

}
