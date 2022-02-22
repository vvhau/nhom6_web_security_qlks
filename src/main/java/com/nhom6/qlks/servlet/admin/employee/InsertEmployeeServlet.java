package com.nhom6.qlks.servlet.admin.employee;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.QuyenDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.User;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class InsertEmployeeServlet
 */
@WebServlet(name = "InsertEmployee", urlPatterns = {"/admin/employee/create"})
public class InsertEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
	}
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");        
       
        String name = request.getParameter("user-name");
 
        Date dob = new Date();
        String dobStr = request.getParameter("user-dob");
        try {        	        	
        	dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobStr);  
        } catch (java.text.ParseException e){
        	e.printStackTrace();
        }
        
        String gender = request.getParameter("user-gender");
        String cmnd = request.getParameter("user-cmnd");
        String email = request.getParameter("user-email");
        String phone = request.getParameter("user-phone");
        String username = request.getParameter("user-username");
        String password = new Utils().strToMD5(request.getParameter("user-password"));
        boolean activate = true;    
        if(request.getParameter("user-activate") == null || request.getParameter("user-activate") == "") {        	
        	activate = false;        	
        }        
        Integer roleId = Integer.parseInt(request.getParameter("user-role"));
        
        QuyenDao quyenDao = new QuyenDao();
        Quyen role = quyenDao.getQuyenById(roleId);
        
        UserDao userDao = new UserDao();
        User user = new User(name, dob, gender, cmnd, email, phone, username, password, activate, role);
        userDao.insertUser(user);   
        
        response.sendRedirect(request.getContextPath() + "/admin/employee");      
	}

}
