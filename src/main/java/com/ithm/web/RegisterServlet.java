package com.ithm.web;

import com.ithm.pojo.User;
import com.ithm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private UserService service = new UserService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1、获取对应的用户名和密码 的 数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		// 获取用户输入的验证码
		String checkCode = request.getParameter("checkCode"); // 从register.jsp 拿到的checkCode

		// 获取程序生成的验证码 从session中获取
		HttpSession session = request.getSession();
		String checkCodeGen = (String) session.getAttribute("checkCodeGen");// 强转为字符串

		// 	比对
		if (!checkCodeGen.equalsIgnoreCase(checkCode)) {  // 忽略大小写的比对
			// 如果比对不一样 不允许注册

			request.setAttribute("register_msg", "验证码错误 注册失败");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			// 直接返回
			return;
		}
		// 	2、调用对应的 service注册
		boolean flag = service.register(user);
		// 	3、判断注册成功吗
		if (flag) {
			// 	注册成功 跳转登陆页面
			request.setAttribute("register_msg", "注册成功请登录");
			request.getRequestDispatcher("/login.jsp").forward(request, response);

		} else {
			// 	注册失败 跳回注册页面
			request.setAttribute("register_msg", "用户名已存在 注册失败");
			request.getRequestDispatcher("/register.jsp").forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
