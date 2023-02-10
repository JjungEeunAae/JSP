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
		
		//아래와 같이 MultipartRequest를 생성만 해주면 파일이 업로드 된다(파일 자체의 업로드 완료)			같은 이름의 파일명 방지 처리
		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
		
		//MultipartRequest로 전송받은 데이터를 불러온다.
		/*manage.js / 등록버튼 이벤트의 formData를 기반으로
		  데이터들은 request객체가 아닌 MultipartRequest객체로 불러와야 한다.*/
		String id = multi.getParameter("id");
		String name = multi.getParameter("name");
		String phone = multi.getParameter("phone");
		String addr = multi.getParameter("addr");
		String fileName = "";
		
		//Enumeration<?> ==> 순서를 가지고 있는 배열, 커서, 최초 0
		//multi.getFileNames() ==> 검색 패턴과 일치하는 파일 이름을 가져옴 파일을 여러개 다운로드 받으면 DB에 파일의 개수만큼 올린다
		Enumeration<?> files = multi.getFileNames();
		//hanMoreElement() ==> 현재 커서가 0이라면 첫번째 칸을 가리키기 때문에 데이타가 하나라도 들어있다면 true를 리턴
		// arr[0] == $('#mimage')[0].files[0]
		while (files.hasMoreElements()) { //true
			//nextElement() ==> 현재 커서가 가리키고 있는 데이타-객체(Object)-를 리턴해주고 커서의 위치를 다음 칸으로 옮깁니다
			//formData.append('img',$('#mimage')[0].files[0]);
			//$('#mimage')[0].files[1];
			String file = (String) files.nextElement(); // 파일의 이름만 읽어드림
			System.out.println("file: " + file);
			/*multi.getFilesystemName(file)
			  파일 이름이 아니고 사용자가 선택한 파일이 실제 서버상의 폴더에 저장되었을 때의 파일명을 반환
			  왜냐하면 파일명이 변경 될 수 있기 때문이다
			  == 바뀐 파일의 이름을 읽어드림, 리네임정책
			  예시) fileName: 369문제.txt
			 	   fileName: 369문제1.txt*/
			fileName = multi.getFilesystemName(file);
			System.out.println("fileName: " + fileName);
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
		//map 자료구조를 사용하면서 그 자료구조를 활용하기 위해 HashMap 인스턴스선언
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
