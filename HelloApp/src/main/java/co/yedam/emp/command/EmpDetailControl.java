package co.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.emp.vo.EmpVO;

public class EmpDetailControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 상세조회 : service -> serviceImpl(구현객체) -> dao(처리)
		String id = req.getParameter("eid");
		
		//EmpService service = new EmpServiceImpl(); //jdbc
		EmpService service = new EmpServiceMybatis();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		//아이디 값을 searchVO라는 속성을 생성
		req.setAttribute("searchVO", emp);
		req.setAttribute("myAge", 100);
		req.setAttribute("loginId", "user1");
		
		try {
			//요청정보가 가지고있는 디스페처 페이지에다가 페이지 재지정
			//이전요청한걸 전달하려고 req,와 resp가 있음
			req.getRequestDispatcher("WEB-INF/result/empDetail.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
