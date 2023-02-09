package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	//값을 넘겨서 처리
	public String exec(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
