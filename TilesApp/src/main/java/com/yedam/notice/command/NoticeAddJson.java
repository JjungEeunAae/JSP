package com.yedam.notice.command;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.NoticeVO;

public class NoticeAddJson implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글등록처리
		String savePath = req.getServletContext().getRealPath("/upload");
		// 최대 파일사이즈 지정
		int maxSize = (1024 * 1024 * 10);
		// 인코딩
		String encoding = "utf-8";
		// json 형식을 만들어주는 라이브러리
		Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd")
					.create();
		// NoticeVO 생성
		NoticeVO vo = new NoticeVO();

		try {
			MultipartRequest multi = //
					new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
			String title = multi.getParameter("title");
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String fileName = "";

			Enumeration<?> files = multi.getFileNames(); // 파일을 여러개 다운로드 받으면 DB에 파일의 개수만큼 올린다?
			while (files.hasMoreElements()) {
				String file = (String) files.nextElement(); // 파일의 이름만 읽어드림
				System.out.println(file);
				fileName = multi.getFilesystemName(file); // 바뀐파일의 이름을 읽어드림, 리네임정책
			}
			vo = new NoticeVO();
			vo.setAttachFile(fileName);
			vo.setNoticeSubject(subject);
			vo.setNoticeTitle(title);
			vo.setNoticeWriter(writer);
			vo.setNoticeDate(new Date());

			NoticeService service = new NoticeServiceImpl();
			service.addNotice(vo);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//위의 코드를 실행 후 결과를 JSON 형식의 데이터를 전송한다
		return gson.toJson(vo) + ".json";
	}

}
