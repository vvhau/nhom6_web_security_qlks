package com.nhom6.qlks.servlet.admin.employee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet implementation class ActivateEmployeeServlet
 */
// Dung chung cho vo hieu hoa va kich hoat tai khoan
@WebServlet(name="ActivateEmployee", urlPatterns = {"/admin/employee/activate"})
public class ActivateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivateEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Integer userId = Integer.parseInt(request.getParameter("user-id"));
        //gia su tai khoan dang duoc kich hoat
        boolean check = true;    
   
        UserDao userDao = new UserDao();
        User user = userDao.getUserByid(userId);
        //kiem tra neu tk dang kich hoat thi vo hieu hoa 
        //con neu dang bi vo hieu thi giu nguyen gia tri check = true
        if(user.getKichHoat() == true) {
        	check = false;
        }
        userDao.updateUserQuyen(userId, check);
        response.sendRedirect(request.getContextPath() + "/admin/employee");
          
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
