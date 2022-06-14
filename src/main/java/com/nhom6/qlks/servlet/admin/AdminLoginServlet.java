package com.nhom6.qlks.servlet.admin;

import java.io.IOException;

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
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public AdminLoginServlet(String _csrf) {
        super();
        this._csrf = _csrf;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		_csrf = Utils.randomString();
		request.setAttribute("_csrf", _csrf);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/loginAdmin.jsp");
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
		
		String tenDangNhap = request.getParameter("username");
		String matKhau = new Utils().strToMD5(request.getParameter("password"));
		Quyen quyenAdmin = new QuyenDao().getQuyenById(1);
		Quyen quyenEmployee = new QuyenDao().getQuyenById(2);
		
		UserDao userDao = new UserDao();
		if (userDao.loginUser(tenDangNhap, matKhau, quyenAdmin) ||
				userDao.loginUser(tenDangNhap, matKhau, quyenEmployee)) {
			
			System.out.println("login sucessed");
			
			User user = userDao.getUserByUsername(tenDangNhap);
			
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			
			String site = request.getContextPath() + "/admin/room-search";

			response.sendRedirect(site);
			return;
		} else {
			request.setAttribute("errMessage", "Tên đăng nhập hoặc mật khẩu không đúng hoặc tài khoản đã bị khóa");
			request.setAttribute("_csrf", _csrf);
			request.getRequestDispatcher("/WEB-INF/views/admin/loginAdmin.jsp").forward(request, response);
			return;
		}
	}

}
