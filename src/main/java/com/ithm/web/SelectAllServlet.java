package com.ithm.web;

import com.ithm.pojo.Brand;
import com.ithm.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAllServlet")
public class SelectAllServlet extends HttpServlet {
    private BrandService service = new BrandService();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    //1、调用对应的BrandService来查询
     //   BrandService service = new BrandService(); 提升到成员位置
        List<Brand> brands = service.selectAll();

    //2、存入request域中
        request.setAttribute("brands",brands);

    //3、转发到brand.jsp
        request.getRequestDispatcher("/brand.jsp").forward(request,response);

    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
