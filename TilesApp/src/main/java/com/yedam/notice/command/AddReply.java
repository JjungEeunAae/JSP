package com.yedam.notice.command;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.ReplyVO;

public class AddReply implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String subject = req.getParameter("subject");
		String writer = req.getParameter("writer");
		String nid = req.getParameter("nid");
		
		ReplyVO vo = new ReplyVO();
		vo.setNoticeId(Integer.parseInt(nid));
		vo.setReplyTitle(title);
		vo.setReplySubject(subject);
		vo.setReplyWriter(writer);
		
		NoticeService service = new NoticeServiceImpl();
		service.addReply(vo);
		
		//vo.setNoticeId(222);
		vo.setReplyDate(new Date());
		System.out.println(vo);
		
		Gson gson = new GsonBuilder()
						.setDateFormat("yyyy-MM-dd HH:mm:ss")
						.create();
		String json = gson.toJson(vo); //vo => json
		
		return json + ".json";
	}
}
