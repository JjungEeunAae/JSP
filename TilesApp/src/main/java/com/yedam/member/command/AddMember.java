package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberSeriviceMybatis;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class AddMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mapper>addmember, service>addmamber
		//성공시 {"retCode":"Success"},{"retCode":"Fail"}
		//성공시 등록진행
		//실패시 처리중 에러를 발생하였습니다
		
		// 글등록처리
		String savePath = req.getServletContext().getRealPath("/upload");
		// 최대 파일사이즈 지정
		int maxSize = (1024 * 1024 * 10);
		// 인코딩
		String encoding = "utf-8";
		
		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		String id = multi.getParameter("id");
		String name = multi.getParameter("name");
		String phone = multi.getParameter("phone");
		String addr = multi.getParameter("addr");
		String fileName = "";
		
		Enumeration<?> files = multi.getFileNames(); // 파일을 여러개 다운로드 받으면 DB에 파일의 개수만큼 올린다?
		while (files.hasMoreElements()) {
			String file = (String) files.nextElement(); // 파일의 이름만 읽어드림
			System.out.println(file);
			fileName = multi.getFilesystemName(file); // 바뀐파일의 이름을 읽어드림, 리네임정책
		}
		
		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(id);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);
		vo.setResponsibility("user");
		vo.setImage(fileName);
		
		//결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", vo);
		Gson gson = new GsonBuilder().create();
		
		MemberService service = new MemberSeriviceMybatis();
		
		
		if(service.addMember(vo) > 0) { //성공
			resultMap.put("retCode", "Success");
			//return "{\"retCode\":\"Success\"}.json";
		} else { // 실패
			resultMap.put("retCode", "Fail");
			//return "{\"retCode\":\"Fail\"}.json";
		}
		//json포맷 : {"id":"user","name":"user"...}
		return gson.toJson(resultMap) + ".json";
	}

}
