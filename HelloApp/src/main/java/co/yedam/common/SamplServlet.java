package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample")
public class SamplServlet extends HttpServlet{
	//해당 클래스는 서블릿이라고 불린다
	//생명주기를 가지고 있다 모든 프로그램이 그렇다
	//Tomcat 서버의 규칙 생명주기 : 인스턴스 -> init(메소드) -> service -> destroy
	
	//인스턴스
	public SamplServlet() {
		System.out.println("생성자호출");
	}
	
	//init
	@Override
	public void init() throws ServletException {
		System.out.println("init 실행 : 서버 실행 후 한번만 실행됨");
	}
	
	//service
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service 실행 : 해당 url을 호출할 때 마다 실행됨");
	}
	
	//destroy
	@Override
	public void destroy() {
		System.out.println("서버가 종료될 때 한번 실행됨");
	}
	
	
	
	
	
	
	
	
	
	
	
}