package com.yedam.notice.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeListTable implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB접근
		NoticeService service = new NoticeServiceImpl();
		//요청 정보에 담기
		req.setAttribute("noticeList", service.noticeList());
		
		return "notice/noticeListTable.tiles";
	}
}
