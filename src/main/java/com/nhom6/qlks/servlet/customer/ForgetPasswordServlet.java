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

/**
 * Servlet implementation class ForgetPasswordServlet
 */
@WebServlet("/forgot-password")
public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
//		request.setAttribute("errMessage", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/forgot-password.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		String username = request.getParameter("username");
		
		User user = new UserDao().getUserByUsername(username);
		
		if (user == null) {
			request.setAttribute("errMessage", "Username không tồn tại");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/forgot-password.jsp");
			dispatcher.forward(request, response);
			return;
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/reset-password.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
