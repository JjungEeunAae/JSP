package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberSeriviceMybatis;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class UpdateMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//$.ajax > data > key를 읽어온다
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String addr = req.getParameter("addr");
		String auth = req.getParameter("resp");
		
		//DB 접근
		MemberService service = new MemberSeriviceMybatis();
		//클라이언트 요청정보를 받아온다
		MemberVO m = new MemberVO();
		
		m.setMemberId(id);
		m.setMemberName(name);
		m.setMemberPhone(phone);
		m.setMemberAddr(addr);
		m.setResponsibility(auth);
		System.out.println(m);
		
		//결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", m);
		Gson gson = new GsonBuilder().create();
		
		if(service.modifyMember(m) > 0) {
			resultMap.put("retCode", "Success");
		} else {
			resultMap.put("retCode", "Fail");
		}
		return gson.toJson(resultMap) + ".json";
	}

}
