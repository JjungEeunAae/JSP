package com.yedam.notice.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;
import com.yedam.notice.vo.ReplyVO;

public class ReplyList implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nid = req.getParameter("nid");
		
		NoticeService service = new NoticeServiceImpl();
		//원본글에 대한 정보가 들어와야한다, 페이지가 아닌 json 형식의 데이터가 필요하다
		List<ReplyVO> list = service.replyList(Integer.parseInt(nid));
		
		//json으로 만들어주는 방법 : Gson
		Gson gson = new GsonBuilder()
						.setDateFormat("yyyy-MM-dd HH:mm:ss")
						.create();
		String json = gson.toJson(list); // {"id":100,"reply":"test"...}
		
		//System.out.println(json);
		//
		return json + ".json";
	}

}
