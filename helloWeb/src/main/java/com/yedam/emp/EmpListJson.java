package com.yedam.emp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/empListJson")
public class EmpListJson extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 입력값의 한글처리
		resp.setCharacterEncoding("utf-8");

		// 입력 값 등록하는 장소
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String hDate = req.getParameter("hire");
		String job = req.getParameter("job");

		// 입력하는 값을 받아오는 방법
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setHireDate(hDate);
		vo.setJobId(job);

		System.out.println(vo);

		resp.getWriter().print("complete");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		// 입력 값 등록하는 장소
		String param = req.getParameter("param");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String hDate = req.getParameter("hire");
		String job = req.getParameter("job");

		// 입력하는 값을 받아오는 방법
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setHireDate(hDate);
		vo.setJobId(job);

		// 받아온 값을 데이터베이스로 전송하는 방법
		EmpDAO dao = new EmpDAO();

		// param 값이 있으면 updata => DB update
		// 없으면 => DB insert
		if (param.equals("update")) {
			if (dao.updateEmp(vo) > 0) {
				resp.getWriter().print("{\"retCode\" : \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\" : \"Fail\"}");
			}
		} else {
			if (dao.addEmp(vo) > 0) {
				resp.getWriter().print("{\"retCode\" : \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\" : \"Fail\"}");
			}
		}
	}

	// 제어의 역전(IOC : Inversion Of Control)
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("del_id"); // 요청페이지에서 del_id로 파라미터를 지정해야한다
		// {id:101,retCode:Success or Fail} => map
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);

		EmpDAO dao = new EmpDAO();
		if (dao.deleteEmp(Integer.parseInt(id)) > 0) {
			map.put("retCode", "Success");
		} else {
			map.put("retCode", "Fail");
		}
		// map 데이터타입을 json으로 변환
		// 추가한 라이브러리 사용
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(map));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text.json;charset=utf-8");

		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		// json => [{"id" : 100, "firstName" : "Hong", "email" :
		// "hong@ggg.aaa"...},{},{},...,{}]
		String json = "[";
		// 반복문
		for (int i = 0; i < list.size(); i++) {
			json += "{\"id\":" + list.get(i).getEmployeeId() + ",\"lastName\":\"" + list.get(i).getLastName() + "\""
					+ ",\"firstName\":\"" + list.get(i).getFirstName() + "\"" + ",\"email\":\"" + list.get(i).getEmail()
					+ "\"" + ",\"hireDate\":\"" + list.get(i).getHireDate().substring(0, 10) + "\"" + ",\"job\":\""
					+ list.get(i).getJobId() + "\"" + "}";
			// 마지막 {} 뒤에는 ,를 넣지 않겠습니다
			// 20건을 가지고 오면 인덱스가 20이면 ,를 넣지 않겠다
			if (i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";

		resp.getWriter().print(json);
	}
}
