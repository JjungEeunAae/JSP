package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.notice.command.*;

public class FrontController extends HttpServlet{
	private Map<String, Command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<String, Command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		//web.xml -> <init-param><param-name>charset</param-name></init-param>
		charset = config.getInitParameter("charset");
		
		//test용
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SencondControl());
		//공지사항목록
		map.put("/noticeList.do", new NoticeList());
		//상세보기
		map.put("/noticeDetail.do", new NoticeDetail());
		//글등록 페이지
		map.put("/noticeForm.do", new NoticeForm());
		//글등록 처리
		map.put("/noticeAdd.do", new NoticeAdd());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//<param-value>UTF-8</param-value>
		req.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI(); //전체 url 중 도메인 뒤에 있는 Path를 읽는다, <HelloApp/aaa.do>
		String context = req.getContextPath(); // </HelloApp>
		//호출하는 nul 패턴
		String page = uri.substring(context.length()); // </aaa.do>
		
		System.out.println(page);
		
		Command command = map.get(page);
		// return 값(main/main.tiles)을 불러온다
		String viewPage = command.exec(req, resp);
		// notice/noticeList.tiles
		
		
		if(viewPage.endsWith(".tiles")) {
			//Control return 값이 .tiles 이면
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
		} else if(viewPage.endsWith(".do")) {
			//Control return 값이 .do 이면
			//리스트 페이지로 이동
			resp.sendRedirect(viewPage);
		}
		
		
	}
	
}
