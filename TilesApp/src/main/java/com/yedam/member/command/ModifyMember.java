package com.yedam.member.command;

import java.io.IOException;
import java.io.PrintWriter;
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

public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mid = req.getParameter("id");
		String mname = req.getParameter("name");
		String mpass = req.getParameter("pw");
		String mphone = req.getParameter("phone");
		String maddr = req.getParameter("addr");

		MemberService service = new MemberSeriviceMybatis();
		MemberVO m = new MemberVO();

		m.setMemberId(mid);
		m.setMemberName(mname);
		m.setMemberPw(mpass);
		m.setMemberPhone(mphone);
		m.setMemberAddr(maddr);

		// 결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		Gson gson = new GsonBuilder().create();

		if (service.modifyMember(m) > 0) {
			System.out.println("====member update success====");
			System.out.println("modifyMember : " + m);
			resultMap.put("retCode", "Success");
		} else {
			System.out.println("====member update fail====");
			resultMap.put("retCode", "Fail");
		}

		return gson.toJson(resultMap) + ".json";
	}
}
