package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;

//build 폴더 안에 들어감
//클래스 파일이 만들어지는 위치
//자바파일은 통킷이 읽어들여야 실행이 됨

@WebServlet("/myInfo") // =url 요청
public class FirstServlet extends HttpServlet { // http 통신 요청 - 응답
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청방식 : post요청일 경우에 tomcat 서버가 실행해줌
		
		//한글입력도 받아주는 방법
		req.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("emp_id"); //많이 쓰이는 방법, form태그의 네임속성의 값을 읽어들일 때 사용함
		String name = req.getParameter("last_name");
		String mail = req.getParameter("email");
		String job = req.getParameter("job_id");
		String hdate = req.getParameter("hire_date");
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id)); // setEmployeeId 변수타입을 int로 변환 
		emp.setLastName(name);
		emp.setEmail(mail);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		System.out.println(emp);
		
		EmpDAO dao = new EmpDAO();
		dao.addEmp(emp);
		
		doGet(req,resp); //자바연결, doGet 메소드를 호출한 부분
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);
		// 컨텐트타입 지정
		resp.setContentType("text/html;charset=utf-8");
		// resp.getWriter() : 클라이언트 쪽으로 출력하는 스트림을 만들어주는 메소드
		resp.getWriter().print("<h3>servlet page</h3>");
		resp.getWriter().print("<a href='Info/resume.html'>이력서 </a><br>");
		resp.getWriter().print("<a href='index.html'>첫 페이지로 이동</a><br>");
		resp.getWriter().print("<a href='Info/employee.html'> 등록</a>");

		// jdbc 연결하는 방법
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();

		resp.getWriter().print("<u1>");
		for (EmpVO emp : list) {
			resp.getWriter().print(
					"<li>" + emp.getEmployeeId() + "/ " + emp.getLastName() + "/ " + emp.getEmployeeId() + "</li>");
		}
		resp.getWriter().print("</u1>");
	}
}
