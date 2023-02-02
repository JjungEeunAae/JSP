package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		map.put("/service.do", new ServiceControl());
		map.put("/login.do", new loginControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
