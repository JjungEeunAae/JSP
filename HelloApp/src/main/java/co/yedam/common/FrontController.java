package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.command.EmpControl;
import co.yedam.emp.command.EmpDetailControl;
import co.yedam.emp.command.EmpForm;
import co.yedam.emp.command.EmpList;
import co.yedam.emp.command.EmpModControl;
import co.yedam.emp.command.EmpModFormControl;
import co.yedam.emp.command.EmpRemoveControl;
import co.yedam.emp.command.ServiceControl;
import co.yedam.emp.command.loginControl;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	//공통적으로 사용하기 위해서 필드로 선언
	Map<String, Command> map;
	
	public FrontController() {
		map = new HashMap<>(); //인스턴스 할당
	}
	
	@Override
	public void init() throws ServletException {
		//첫페이지 지정
		map.put("/main.do", new MainControl());
		map.put("/service.do", new ServiceControl());
		map.put("/login.do", new loginControl());
		map.put("errorPage.do", new ErrorPage());
		//get방식: 목록출력,(json), post방식 : 입력처리 / emp_temp 활용
		map.put("/employee.do", new EmpControl());
		//xxxForm.do : 페이지오픈
		map.put("/empForm.do", new EmpForm());
		//목록페이지
		map.put("/empList.do", new EmpList());
		//상세페이지(id 단건조회)결과를 받아오는 페이지, 파라미터를 넘겨줘야함
		map.put("/empDetail.do", new EmpDetailControl());
		//수정화면페이지
		map.put("/empModForm.do", new EmpModFormControl());
		//수정처리하는 페이지
		map.put("/empModify.do", new EmpModControl());
		//삭제처리하는 페이지
		map.put("/empRemove.do", new EmpRemoveControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //요청한 자료가 한글이면 한글로 인식
		resp.setCharacterEncoding("utf-8"); //전달될 자료가 한글이면 한글로 인식
		resp.setContentType("text/html;charset=utf-8");
		//요청하는 url을 찾아갈 수 있도록 도움을 주는 곳
		
		//url 패턴을 확인해서 요청하는 페이지가 어떤건지 찾으려고 하는 장소
		
		//예시 : http://localhost:8081/
		
		//요청정보 : req에 담겨져있음
		String url = req.getRequestURI(); //전체 url 중 도메인 뒤에 있는 Path를 읽는다, <HelloApp/aaa.do>
		String context = req.getContextPath(); // </HelloApp>
		String page = url.substring(context.length()); // </aaa.do>
		
//		System.out.println("url : " + url);
//		System.out.println("context : " + context);
//		System.out.println(page);
		
		Command comm = map.get(page);
		comm.exec(req, resp);
	}
	
	@Override
	public void destroy() {
		
	}
	
	
}
