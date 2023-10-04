package com.ithm.web;

import com.ithm.pojo.Brand;
import com.ithm.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteByIdServlet")
public class DeleteByIdServlet extends HttpServlet {
	private BrandService service = new BrandService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  请求post请求处理乱码问题
		request.setCharacterEncoding("UTF-8");

		// 1、接收表单提交的数据，封装为一个Brand对象
		String id = request.getParameter("id");

		// 	2、调用service 完成修改
		service.deleteById(Integer.parseInt(id));

		// 	转发到查询所有Servlet
		request.getRequestDispatcher("/selectAllServlet").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
