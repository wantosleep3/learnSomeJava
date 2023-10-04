package com.ithm.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.ReflectPermission;

/**
 * 登录验证的过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
		//  要把形参中的ServletRequest强制转化为httpRervletRequest
		HttpServletRequest req = (HttpServletRequest) request;

		// 判断访问资源路径是否和登陆注册相关
		String[] urls = {"/login.jsp", "/imgs/", "/css/", "/loginServlet", "/register.jsp", "/registerServlet", "/CheckCodeServlet"};
		// 获取当前资源访问路径
		String url = req.getRequestURL().toString();// 用URI也可以
		// 循环判断
		for (String u : urls) {
			if (url.contains(u)) {
				// 找到
				// 放行
				chain.doFilter(request, response);
				//不能用break //用return
				return;
			}
		}

		// 1、 判断session中有无user对象
		HttpSession session = req.getSession();
		Object user = session.getAttribute("user");

		// 2、判断user是否为null
		if (user != null) {
			// 登陆过了
			// 放行
			chain.doFilter(request, response);
		} else {
			// 没登陆 ,拦截   存储提示信息，跳转到登陆页面
			req.setAttribute("login_msg", "恁尚未登录");
			req.getRequestDispatcher("/login.jsp").forward(req, response);

		}
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}


}
