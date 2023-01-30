package com.yedam.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empListJson")
public class EmpListJson extends HttpServlet {
	
	// 제어의 역전(IOC : Inversion Of Control)
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("del_id"); //요청페이지에서 del_id로  파라미터를 지정해야한다
		
		EmpDAO dao = new EmpDAO();
		if(dao.deleteEmp(Integer.parseInt(id))>0) {
			//제이슨 형식으로 넘겨준다
			//{"retCode" : "Success"}
			resp.getWriter().print("{\"retCode\" : \"Success\"}");
		} else{
			//{"retCode" : "Fail"}
			resp.getWriter().print("{\"retCode\" : \"Fail\"}");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text.json;charset=utf-8");
		
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		// json => [{"id" : 100, "firstName" : "Hong", "email" : "hong@ggg.aaa"...},{},{},...,{}]
		String json = "[";
		//반복문
		for(int i = 0; i < list.size() ; i++) {
			json += "{\"id\":"+list.get(i).getEmployeeId()+",\"firstName\":\""+list.get(i).getFirstName()+"\""+
					",\"firstName\":\"" + list.get(i).getFirstName() + "\""+
					",\"email\":\"" + list.get(i).getEmail() + "\""+
					",\"hireDate\":\"" + list.get(i).getHireDate() + "\""+
					",\"job\":\"" + list.get(i).getJobId() + "\"" +"}";
			//마지막 {} 뒤에는 ,를 넣지 않겠습니다
			//20건을 가지고 오면 인덱스가 20이면 ,를 넣지 않겠다
			if(i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";
		
		resp.getWriter().print(json);
	}
}
