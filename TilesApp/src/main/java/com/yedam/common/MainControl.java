package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 반환값이 실행할 페이지를 지정
		// 처리하는 업무랑 페이지를 반환
		
		//tiles 확장자 호출
		return "main/main.tiles";
	}

}
