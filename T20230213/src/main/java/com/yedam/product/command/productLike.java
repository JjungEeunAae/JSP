package com.yedam.product.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.product.service.ProductService;
import com.yedam.product.service.ProductServiceImpl;

public class productLike implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//목록
		ProductService service = new ProductServiceImpl();
		//기능호출
		
		System.out.println(service.relateList());
		return null;//"productDetail.do";
	}

}
