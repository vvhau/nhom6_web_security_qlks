package com.nhom6.qlks.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AntiClickjackingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	
	// type: SAMEORIGIN, DENY, ALLOW-FROM
	private String mode = "SAMEORIGIN";
	
    public AntiClickjackingFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		antiClickjacking(response);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String configMode = fConfig.getInitParameter("mode");
        if ( configMode != null ) {
            mode = configMode;
        }
	}

	private void antiClickjacking(ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;
		res.addHeader("X-FRAME-OPTIONS", mode);
	}
}
