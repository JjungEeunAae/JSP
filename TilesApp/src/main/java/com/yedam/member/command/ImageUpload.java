package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberSeriviceMybatis;
import com.yedam.member.service.MemberService;
import com.yedam.member.vo.MemberVO;

public class ImageUpload implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("/upload");
		// 최대 파일사이즈 지정
		int maxSize = (1024 * 1024 * 10);
		// 인코딩
		String encoding = "utf-8";

		try {
			MultipartRequest multi = //
					new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
			String id = multi.getParameter("id");
			String fileName = "";

			Enumeration<?> files = multi.getFileNames(); // 파일을 여러개 다운로드 받으면 DB에 파일의 개수만큼 올린다?
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement(); // 파일의 이름만 읽어드림
				System.out.println(file);
				fileName = multi.getFilesystemName(file); // 바뀐파일의 이름을 읽어드림, 리네임정책
			}
			
			MemberVO vo = new MemberVO();
			vo.setMemberId(id);
			vo.setImage(fileName);
			
			// DB반영하는 작업
			MemberService service = new MemberSeriviceMybatis();
			service.modifyMember(vo);	// DB 정보수정
			
			System.out.println(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//{"retCode" : "Success"}
		//위의 코드를 실행 후 다음 경로로 넘어간다
		return "{\"retCode\" : \"Success\"}";
	}

}
