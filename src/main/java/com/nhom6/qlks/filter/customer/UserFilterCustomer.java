package com.nhom6.qlks.filter.customer;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nhom6.qlks.hibernate.pojo.User;

/**
 * Servlet Filter implementation class UserFilterCustomer
 */
@WebFilter(urlPatterns = "/*")
public class UserFilterCustomer extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public UserFilterCustomer() {
        super();
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
		// place your code here

		// pass the request along the filter chain
//		chain.doFilter(request, response);
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		if (servletPath.equals("")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (servletPath.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (servletPath.equals("/register")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (servletPath.startsWith("/admin")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (servletPath.startsWith("/momo")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (servletPath.startsWith("/static")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		HttpServletRequest wrapRequest = request;
		
		User user = (User) session.getAttribute("user");
		if (user != null) {
			chain.doFilter(request, response);
			return;
		} else {
			response.sendRedirect(request.getContextPath().concat("/login"));
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/login.jsp");
//			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}