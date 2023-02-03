package co.yedam.emp.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpModFormControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//수정화면:WEB-INF/views/modify.jsp 호출
		String id = req.getParameter("id");
		
		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		req.setAttribute("updateVO", emp);
		
		try {
			req.getRequestDispatcher("WEB-INF/views/modify.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
