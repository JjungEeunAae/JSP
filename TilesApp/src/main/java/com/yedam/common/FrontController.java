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

import com.yedam.member.command.*;
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
		
		// test용
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SencondControl());
		// 공지사항목록
		map.put("/noticeList.do", new NoticeList());
		// 공지사항 API 활용(bxslider)
		map.put("/noticeListWithTables.do", new NoticeListTable());
		// 상세보기
		map.put("/noticeDetail.do", new NoticeDetail());
		// 글등록 페이지
		map.put("/noticeForm.do", new NoticeForm());
		// 글등록 처리
		map.put("/noticeAdd.do", new NoticeAdd());
		// 글등록 API 활용(datatable)
		map.put("/noticeAddJson.do", new NoticeAddJson());
		// 글목록 API 활용(datatable)
		map.put("/noticeListJson.do", new NoticeListJson());
		// 글목록 API 활용 view Page
		map.put("/noticeListAjax.do", new NoticeListAjax());
		
		// 댓글-------------------------------------
		//댓글 목록
		map.put("/replyList.do", new ReplyList());
		//댓글 삭제
		map.put("/removeReply.do", new RemoveReply());
		//댓글 등록
		map.put("/addReply.do", new AddReply());
		
		// 회원관련-----------------------------------
		//로그인 페이지
		map.put("/loginForm.do", new LoginForm());
		//로그인 처리
		map.put("/login.do", new Login());
		//마이페이지
		map.put("/myPageForm.do", new MyPageControl());
		//로그아웃 처리
		map.put("/logout.do", new LogoutControl());
		//회원 정보수정
		map.put("/modifyMember.do", new ModifyMember());
		//이미지 업로드
		map.put("/imageUpload.do", new ImageUpload());
		
		// 관리자 관련-----------------------------------
		//관리자 회원관리 페이지
		map.put("/memberManageForm.do", new MemberManage());
		//회원 리스트
		map.put("/memberList.do", new MemberList());
		//회원 등록
		map.put("/addMember.do", new AddMember());
		//회원삭제
		map.put("/removeMember.do", new RemoveMember());
		//회원정보수정
		map.put("/updateMember.do", new UpdateMember());
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
			//.jsp, view page
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
		} else if(viewPage.endsWith(".do")) {
			//Control return 값이 .do 이면
			//리스트 페이지로 이동
			//control class에 해당되는 .do url로 이동
			resp.sendRedirect(viewPage);
		} else if(viewPage.endsWith(".json")) {
			//Control return 값이 .json이면
			//결과를 json 형식으로 만들고
			resp.setContentType("text/json;charset=utf-8");
			//.json 부분을 빼고 화면에 출력
			resp.getWriter().print(viewPage.substring(0, viewPage.length()-5));
		}
		
		
	}
	
}
