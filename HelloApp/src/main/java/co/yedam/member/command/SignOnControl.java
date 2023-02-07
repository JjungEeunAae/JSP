package co.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import co.yedam.common.Command;
import co.yedam.member.service.MemberSeriviceMybatis;
import co.yedam.member.service.MemberService;
import co.yedam.member.vo.MemberVO;

public class SignOnControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//form:multipart/form-data => 처리(MultipartRequset(메소드))
		//생성자 매개값 : 1. 요청정보, 2. 저장경로, 3. 최대 파일사이즈 지정, 4. 파일이름의 인코딩 방식, 5.리네임정책(동일한 파일이나 이미지명을 덮어쓰지 않도록)
		String savePath = req.getServletContext().getRealPath("/images");
		//최대 파일사이즈 지정
		int maxSize = (1024 * 1024 * 10);
		//인코딩
		String encoding = "utf-8";
		
		try {
			MultipartRequest multi = //
					new MultipartRequest(req, savePath, maxSize, encoding,new DefaultFileRenamePolicy());
			String id = multi.getParameter("member_id");
			String pw = multi.getParameter("member_pw");
			String name = multi.getParameter("member_name");
			String ph = multi.getParameter("member_phone");
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames(); //파일을 여러개 다운로드 받으면 DB에 파일의 개수만큼 올린다?
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement(); //파일의 이름만 읽어드림
				System.out.println(file);
				fileName = multi.getFilesystemName(file); //바뀐파일의 이름을 읽어드림, 리네임정책 
			}
			
			MemberVO member = new MemberVO();
			member.setMemberId(id);
			member.setMemberPw(pw);
			member.setMemberName(name);
			member.setMemberPhone(ph);
			member.setImage(fileName);
			
			MemberService service = new MemberSeriviceMybatis();
			if(service.addMember(member) > 0) {
				resp.sendRedirect("empList.do");
			} else {
				resp.sendRedirect("errorPage.do");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
