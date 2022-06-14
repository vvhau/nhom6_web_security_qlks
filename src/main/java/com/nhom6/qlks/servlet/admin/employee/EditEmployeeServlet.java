package com.nhom6.qlks.servlet.admin.employee;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nhom6.qlks.hibernate.daos.LoaiPhongDao;
import com.nhom6.qlks.hibernate.daos.PhongDao;
import com.nhom6.qlks.hibernate.daos.QuyenDao;
import com.nhom6.qlks.hibernate.daos.TrangThaiDao;
import com.nhom6.qlks.hibernate.daos.UserDao;
import com.nhom6.qlks.hibernate.pojo.LoaiPhong;
import com.nhom6.qlks.hibernate.pojo.Phong;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.TrangThai;
import com.nhom6.qlks.hibernate.pojo.User;
import com.nhom6.qlks.utils.Utils;

/**
 * Servlet implementation class EditEmployeeServlet
 */
@WebServlet(name = "EditEmployee", urlPatterns = {"/admin/employee/edit"})
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String _csrf;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
		String idUserStr = request.getParameter("user-id");
		User user;
		if (idUserStr == null || idUserStr.equals("")) {
			user = null;
		} else {
			user = new UserDao().getUserByid(Integer.parseInt(idUserStr));
		}
		request.setAttribute("user", user);
		
		_csrf = Utils.randomString();
		request.setAttribute("_csrf", _csrf);
		
		List<Quyen> quyens = new QuyenDao().getAllQuyen();
		request.setAttribute("quyens", quyens);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/employee-admin/edit-employee-admin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");        
        
        String csrf = request.getParameter("_csrf");
        if(csrf == null || !csrf.equals(_csrf)){
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/error/UnvalidTokenCsrf.jsp");
    		dispatcher.forward(request, response);
    		return;
        }
		
       
        Integer userId = Integer.parseInt(request.getParameter("user-id"));
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
       
        boolean activate = true;    
        if(request.getParameter("user-activate") == null || request.getParameter("user-activate") == "") {        	
        	activate = false;        	
        }        
        Integer roleId = Integer.parseInt(request.getParameter("user-role"));
        
        QuyenDao quyenDao = new QuyenDao();
        Quyen role = quyenDao.getQuyenById(roleId);
        
        UserDao userDao = new UserDao();     
        userDao.updateUser(userId, name, dob, gender, cmnd, email, phone, username, activate, role);       
        
        response.sendRedirect(request.getContextPath() + "/admin/employee");      
	}

}
