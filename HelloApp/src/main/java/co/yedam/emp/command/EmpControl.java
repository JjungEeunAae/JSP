package co.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher rd = null;
		
		String method = req.getMethod();
		System.out.println("요청방식 : " + method);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (method.equals("GET")) {
			// 목록출력
			try {
				//resp.sendRedirect("https://www.daum.net");
				resp.sendRedirect("result/empList.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 전체목록을 가져오는 기능이 있어야 함
			out.print("");
		} else if (method.equals("POST")) {
			// 입력처리
			String eid = req.getParameter("eid"); // form>input>name속성
			String lName = req.getParameter("last_name");
			String jod = req.getParameter("job");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("email");

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setLastName(lName);
			emp.setJodId(jod);
			emp.setHireDate(hire);
			emp.setEmail(mail);

			// 서비스 로직
			EmpService service = new EmpServiceImpl();
			int r = service.addEmp(emp);
			
			//요청 재지정
			try {
				if( r > 0) {
					resp.sendRedirect("empList.do"); //이동하고 싶은 곳을 지정할 수 있음
					//rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
					//rd.forward(req, resp); //페이지 재지정, 이거 없으면 화면이 나오지 않음
				} else {
					//resp.sendRedirect("result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("error!");
			try {
				resp.sendRedirect("https://www.daum.net");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
