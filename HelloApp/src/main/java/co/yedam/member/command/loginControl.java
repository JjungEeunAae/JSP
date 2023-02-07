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

public class loginControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//로그인이 되면 session 객체에 setAttribute('id') 담기
		String method = req.getMethod();
		System.out.println("요청방식 : " + method);
		//요청정보를 가지고 있음
		String id = req.getParameter("mid"); //form 하위에 있는 input에 해당되는 name 속성을 적는다
		String pw = req.getParameter("mpw");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		MemberService service = new MemberSeriviceMybatis();
		MemberVO rvo = service.login(member);
		
		if(rvo != null) {
			//값이 있다라는 의미(조회성공)
			try {
				resp.sendRedirect("empList.do"); //조회 성공시 empList.do로 이동
				HttpSession session = req.getSession(); //요청정보를 가져온다(쿠키정보(key,value))
				//웹브라우저를 닫기 전까지는 계속 유지한다
				session.setAttribute("id", rvo.getMemberId());
				session.setAttribute("name", rvo.getMemberName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//값이 없다라는 의미(조회실패)
			try {
				req.setAttribute("result", "회원 정보를 확인하세요!"); //조회 실패시 result 속성을 통해 유저에게 메세지 안내하기
				req.getRequestDispatcher("WEB-INF/Member/login.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
