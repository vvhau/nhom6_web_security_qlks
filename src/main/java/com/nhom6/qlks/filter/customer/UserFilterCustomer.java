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
	private String[] urlPublicSingle = {
			"",
			"/login",
			"/register",
			"/room-type",
			"/search-room-type",
			"/forgot-password"
		};
	private String[] urlPublicMultiple = {
			"/admin",
			"/static"
	};
	
	// type: SAMEORIGIN, DENY, ALLOW-FROM
	private String mode = "SAMEORIGIN";
	
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
		
		antiClickjacking(response);
		
		String servletPath = request.getServletPath();
		System.out.println(request.getMethod() + " - " + servletPath + " - " + request.getQueryString());
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			if (servletPath.equals("/login")) {
				response.sendRedirect(request.getContextPath().concat("/"));
				return;
			}
			
			chain.doFilter(request, response);
			return;
		} else {
			for (String url: urlPublicSingle) {
				if (servletPath.equals(url)) {
					chain.doFilter(request, response);
					return;
				}
			}
			
			for (String url: urlPublicMultiple) {
				if (servletPath.startsWith(url)) {
					chain.doFilter(request, response);
					return;
				}
			}
			
			response.sendRedirect(request.getContextPath().concat("/login"));
			return;
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private void antiClickjacking(HttpServletResponse response) {
		response.addHeader("X-FRAME-OPTIONS", mode);
	}

}