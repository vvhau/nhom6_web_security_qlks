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
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String  _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ResetPasswordServlet(String _csrf) {
        super();
        this._csrf = _csrf;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		_csrf = Utils.randomString();
		request.setAttribute("_csrf", _csrf);
		
//		request.setAttribute("errMessage", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/reset-password.jsp");
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
		
		String newPassword = request.getParameter("password");
		String confirmNewPassword = request.getParameter("confirm-password");
		
		if (!newPassword.equals(confirmNewPassword)) {
			err_msg = "Xác nhận mật khẩu không đúng";
			request.setAttribute("errMessage", err_msg);
			request.setAttribute("_csrf", _csrf);
			request.getRequestDispatcher("/WEB-INF/views/customer/reset-password.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		String username =(String) session.getAttribute("username");
		
		newPassword = new Utils().strToMD5(newPassword);
		
		err_msg = new UserDao().resetPassword(username, newPassword);
		if (err_msg.equals("successed")) {
			String site = request.getContextPath();
			response.sendRedirect(site + "/login");
			return;
		} else {
			request.setAttribute("errMessage", err_msg);
			request.setAttribute("_csrf", _csrf);
			request.getRequestDispatcher("/WEB-INF/views/customer/reset-password.jsp").forward(request, response);
			return;
		}
	}

}
