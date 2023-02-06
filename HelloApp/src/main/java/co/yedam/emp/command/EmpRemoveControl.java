package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;

public class EmpRemoveControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		//삭제처리 : EmpService -> EmpServiceImpl -> EmpDAO
		EmpService service = new EmpServiceImpl();
		int r = service.removeEmp(Integer.parseInt(id));
		
		try {
			if( r > 0) { //정상처리되면 목록으로 이동
				resp.sendRedirect("empList.do"); //이동하고 싶은 곳을 지정할 수 있음
				System.out.println("remove");
			} else {
				resp.sendRedirect("errorPage.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
