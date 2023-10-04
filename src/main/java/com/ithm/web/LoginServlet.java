package com.ithm.web;

import com.ithm.pojo.User;
import com.ithm.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private UserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 2、调用service查询
		User user = service.login(username, password);

		// 获取记住密码复选框数据
		String remember = request.getParameter("remember");

		// 3、判断
		if (user != null) {
			// 	登录成功 重定向到selectAllservlet

			// 将登录成功的 user对象存到session中   一次对话的两次请求之间共享数据 需要把数据存在ck 或者ssn里面
			HttpSession session = request.getSession();
			session.setAttribute("user", user);   // 这样之后通过el表达式在brand.jsp中展示用户名

			// 判断用户是否选择了“记住我”
			if (("1").equals(remember)) { // 把1写在前面防止空指针异常
				// 勾选了 发送cookie

				// 	1、创建ck对象
				Cookie c_username = new Cookie("username", username);
				Cookie c_password = new Cookie("password", password);

				// 设置ck存活时间
				c_username.setMaxAge(60*60*24*7);
				c_password.setMaxAge(60*60*24*7);
				// 	2、发送ck

				response.addCookie(c_username);
				response.addCookie(c_password);

			}


			// 因为是资源路径是虚拟目录 我们改为动态的
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/selectAllServlet");

		} else {
			// 登录失败 跳转到login.jsp  加上提示

			// 判断remember复选框是否被勾选


			// 	存储错误信息到request中
			request.setAttribute("login_msg", "用户名或密码错误");

			// 	跳转
			request.getRequestDispatcher("/login.jsp").forward(request, response);// 转发过去 因为在request域中的数据 只能用转发才能获取数据

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
