package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.member.vo.MemberVO;

public class ErrorPage implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//새로고침하면 db수정 작업이 발생함
		//이 부분을 막기위해 sendRedirect로 처리하도록 진행
		MemberVO vo = new MemberVO();
		vo.setMemberId("user1");
		vo.setMemberName("Hong");
		vo.setMemberPhone("010-1111");
		vo.setMemberAddr("대구 중구 100번지");
		
		req.setAttribute("obj", vo);
		
		//컬렉션타입
		EmpService service = new EmpServiceMybatis();
		req.setAttribute("empList",service.empList());
		
		
		try {
			req.getRequestDispatcher("WEB-INF/result/errorResult.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}
