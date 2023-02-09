package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberSeriviceMybatis;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		String mpass = req.getParameter("mpass");
		String mphone = req.getParameter("mphone");
		String maddr = req.getParameter("maddr");
		
		MemberService service = new MemberSeriviceMybatis();
		MemberVO m = new MemberVO();
		
		m.setMemberId(mid);
		m.setMemberName(mname);
		m.setMemberPw(mpass);
		m.setMemberPhone(mphone);
		m.setMemberAddr(maddr);
		System.out.println(m);
		int r = service.modifyMember(m);
		
		if(r > 0) {
			return "main/main.tiles";
		} else {
			req.setAttribute("updateResult", "처리실패");
			return "member/mypage.tiles";
		}

	}

}
