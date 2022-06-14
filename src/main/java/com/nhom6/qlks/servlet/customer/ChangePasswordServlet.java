package com.nhom6.qlks.servlet.customer;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.User;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/change-password.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
        String csrf = request.getParameter("_csrf");
        if(csrf == null || !csrf.equals(_csrf)){
    		RequestDispatcher dispatcher = 
    				request.getRequestDispatcher("/WEB-INF/views/error/UnvalidTokenCsrf.jsp");
    		dispatcher.forward(request, response);
    		return;
        }
		
		String username = request.getParameter("username");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmNewPassword = request.getParameter("confirmNewPassword");
		String error = "";
		UserDao userDao = new UserDao();
		User user = userDao.getUserByUsername(username);
		
		if (user == null) {
			request.setAttribute("errMessage", "Username không tồn tại");			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/change-password.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else if (!newPassword.equals(confirmNewPassword)) {
			request.setAttribute("errMessage", "Mật khẩu mới không khớp nhau");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/change-password.jsp");
			dispatcher.forward(request, response);		
			return;
		}
		else {
			newPassword = new Utils().strToMD5(newPassword);
			oldPassword = new Utils().strToMD5(oldPassword);
			
			error = userDao.changePassword(username, oldPassword, newPassword);
			request.setAttribute("errMessage", error);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/change-password.jsp");
			request.setAttribute("_csrf", _csrf);
			dispatcher.forward(request, response);
			return;
		}
	}

}
