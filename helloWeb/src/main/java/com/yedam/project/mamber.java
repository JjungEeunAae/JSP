package com.yedam.project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mameber")
public class mamber extends HttpServlet {
	MemberDAO dao = new MemberDAO();
	BoardDAO bdao = new BoardDAO();
	
	String id = "";
	// 당일날짜 받아오기
	Date date = new Date();
	SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
	SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy년 MM월 dd일");
	// System.out.println(formatter.format(date));
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
		String tel = req.getParameter("tel");
		String birth = req.getParameter("birth");
		String mail = req.getParameter("mail");
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);
		vo.setMemberName(name);
		vo.setMemberAddr(addr);
		vo.setMemberTel(tel);
		vo.setMemberBirth(birth);
		vo.setMemberEmail(mail);
		
		if(dao.addMember(vo) > 0) {
			resp.getWriter().print("{\"retCode\" : \"Success\"}");
		} else {
			resp.getWriter().print("{\"retCode\" : \"Fail\"}");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		List<MemberVO> list = dao.VoList();
		String json = "[";
		for(int i = 0 ; i < list.size(); i++) {
			json += "{"
					+ "\"id\":\"" + list.get(i).getMemberId() + "\""
					+ ",\"pw\":\"" + list.get(i).getMemberPw() + "\""
					+ ",\"name\":\"" + list.get(i).getMemberName() + "\""
					+ ",\"addr\":\"" + list.get(i).getMemberAddr() + "\""
					+ ",\"tel\":\"" + list.get(i).getMemberTel() + "\""
					+ ",\"birth\":\"" + list.get(i).getMemberBirth() + "\""
					+ ",\"mail\":\"" + list.get(i).getMemberEmail()+ "\""
					+ "}";
			if(i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";
		
		resp.getWriter().print(json);
	}
	

} // end of class()
