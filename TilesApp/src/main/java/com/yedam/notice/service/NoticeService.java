package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public interface NoticeService {
	public List<NoticeVO> noticeList();
	public NoticeVO getNotice(int nid);		//한건조회(단건)
	public int addNotice(NoticeVO notice);  //글등록
	public int modNotice(NoticeVO notice);  //글수정
	public int remNotice(int nid); 			//글삭제
	public List<ReplyVO> replyList(int uid);//댓글목록
	public int removeReply(int rid);//댓글삭제(return type: 댓글번호)
	public int addReply(ReplyVO reply);		//댓글등록
	
}
