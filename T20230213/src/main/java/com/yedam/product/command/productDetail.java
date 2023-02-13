package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;

public class productDetail implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pname = req.getParameter("pname");
		
		ProductService service = new ProductServiceImpl();
		req.setAttribute("vo", service.getProduct(pname));
		req.setAttribute("nameSelect", service.relateList());
		
		return "product/product.tiles";
	}

}
