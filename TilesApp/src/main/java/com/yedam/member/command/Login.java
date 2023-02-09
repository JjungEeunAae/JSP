package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberSeriviceMybatis;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String id  = req.getParameter("mid"); //form 하위에 있는 input에 해당되는 name 속성을 적는다
		String pw = req.getParameter("mpw");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		MemberService service = new MemberSeriviceMybatis();
		MemberVO rvo = service.login(member);
		
		
		if(rvo != null) {
			//로그인 성공하면 mypage로 이동
			HttpSession session = req.getSession(); //요청정보를 가져온다(쿠키정보(key,value))
			//웹브라우저를 닫기 전까지는 계속 유지한다
			session.setAttribute("id", rvo.getMemberId());
			session.setAttribute("name", rvo.getMemberName());
			
			MemberVO member2 = service.getMember(id);
			
			session.setAttribute("vo", member2);
			return "member/mypage.tiles";
		} else {
			//로그인 실패하면 다시 로그인 화면으로 이동할 때 "아이디와 패스워드 확인" 안내하기
			req.setAttribute("result", "회원 정보를 확인하세요!"); //조회 실패시 result 속성을 통해 유저에게 메세지 안내하기
			return "member/login.tiles";
		}
	}

}
