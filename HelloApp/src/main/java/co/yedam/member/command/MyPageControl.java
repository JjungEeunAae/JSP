package co.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.yedam.common.Command;
import co.yedam.member.service.MemberSeriviceMybatis;
import co.yedam.member.service.MemberService;
import co.yedam.member.vo.MemberVO;

public class MyPageControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberService service = new MemberSeriviceMybatis();
		//리턴타입 MemberVO
		MemberVO member = service.getMember(id);
		
		req.setAttribute("vo", member);
		
		try {
			req.getRequestDispatcher("WEB-INF/Member/mypage.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
