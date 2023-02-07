package co.yedam.emp.command;

import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;

public class EmpForm implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//-------------직무리스트
		EmpService service = new EmpServiceImpl();
		//EmpService service = new EmpServiceMybatis();
		//문자열로 받아올려면 String
		Map<String, String> jobList = service.jobList();
		req.setAttribute("jobList", jobList);
		
		//------------view페이지
		// 요청 재지정, 
		// request에 담긴 정보를 저장하고 있다가 그 다음 페이지 그 다`음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/emp.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
