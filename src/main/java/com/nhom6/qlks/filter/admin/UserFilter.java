package com.nhom6.qlks.filter.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhom6.qlks.hibernate.daos.QuyenDao;
import com.nhom6.qlks.hibernate.pojo.Quyen;
import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter(urlPatterns = {"/admin/*"})
public class UserFilter implements Filter {
	private String[] urlRoleEmployee = {
				"/admin/login",
				"/admin/room-search",
				"/admin/bill/insert",
				"/admin/logout",
				"/admin/booking-online",
				"/admin/booking-offline"
			};

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
//		chain.doFilter(req, resp);
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("/admin/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		HttpServletRequest wrapRequest = request;
		
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Quyen quyen = user.getQuyen();
			if (quyen.getIdQuyen() == 1) {
				chain.doFilter(request, response);
			} else if (quyen.getIdQuyen() == 2) {
				for (String url: urlRoleEmployee) {
					if (url.equals(servletPath)) {
						chain.doFilter(request, response);
					}
				}
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/loginAdmin.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/loginAdmin.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
