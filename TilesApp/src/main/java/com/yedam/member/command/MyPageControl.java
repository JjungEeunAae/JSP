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

public class MyPageControl implements Command {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberService service = new MemberSeriviceMybatis();
		//리턴타입 MemberVO
		MemberVO member = service.getMember(id);
		
		req.setAttribute("vo", member);
		
		return "member/mypage.tiles";
	}
}
