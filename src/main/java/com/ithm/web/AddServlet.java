package com.ithm.web;

import com.ithm.pojo.Brand;
import com.ithm.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
	private BrandService service = new BrandService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//  请求post请求处理乱码问题
		request.setCharacterEncoding("UTF-8");

		// 1、接收表单提交的数据，封装为一个Brand对象
		String brandName = request.getParameter("brandName");
		String companyName = request.getParameter("companyName");
		String ordered = request.getParameter("ordered");
		String description = request.getParameter("description");
		String status = request.getParameter("status");

		// 封装为一个Brand对象
		Brand brand = new Brand();

		brand.setBrandName(brandName);
		brand.setCompanyName(companyName);
		brand.setOrdered(Integer.parseInt(ordered));// 转换为int
		brand.setDescription(description);
		brand.setStatus(Integer.parseInt(status));


		// 	2、调用service 完成添加
		service.add(brand);

	// 	转发到查询所有Servlet
		request.getRequestDispatcher("/selectAllServlet").forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
