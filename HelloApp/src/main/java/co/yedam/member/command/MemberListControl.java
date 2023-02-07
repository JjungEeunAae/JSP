package co.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.member.service.MemberSeriviceMybatis;
import co.yedam.member.service.MemberService;

public class MemberListControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		MemberService service = new MemberSeriviceMybatis();
		req.setAttribute("memberList", service.memberList());
		
		try {
			req.getRequestDispatcher("WEB-INF/Member/memberList.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
