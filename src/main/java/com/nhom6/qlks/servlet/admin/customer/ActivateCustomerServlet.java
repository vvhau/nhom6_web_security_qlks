package com.nhom6.qlks.servlet.admin.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet implementation class ActivateCustomerServlet
 */
@WebServlet(name="ActivateCustomer", urlPatterns = {"/admin/online-customer/activate"})
public class ActivateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void ActivateCustomerRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Integer userId = Integer.parseInt(request.getParameter("user-id"));
        boolean check= true;    
   
        UserDao userDao = new UserDao();
        User user = userDao.getUserByid(userId);
        if(user.getKichHoat() == true) {
        	check = false;
        }
        userDao.updateUserQuyen(userId, check);
        response.sendRedirect(request.getContextPath() + "/admin/online-customer");
      
    
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActivateCustomerRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActivateCustomerRequest(request,response);
	}

}
