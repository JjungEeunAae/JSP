package co.yedam.emp.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class EmpForm implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 요청 재지정, 
		// request에 담긴 정보를 저장하고 있다가 그 다음 페이지 그 다음 페이지에도 해당 정보를 볼 수 있게 계속 저장하는 기능
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/emp.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
