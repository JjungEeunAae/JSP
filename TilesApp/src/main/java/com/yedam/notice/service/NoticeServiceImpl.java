package com.yedam.notice.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.vo.NoticeVO;
import com.yedam.notice.vo.ReplyVO;

public class NoticeServiceImpl implements NoticeService{
	SqlSession session = DataSource.getInstance().openSession(true);
	
	//우리가 만들었던 맵퍼파일이랑 인터페이스를 맵핑한다
	//인터페이스               맵퍼파일
	NoticeMapper mapper = session.getMapper(NoticeMapper.class);
  //=NoticeService service = new NoticeServiceImpl();
  //=session.selectOne("com.yedam.notice.mapper.NoticeMapper.getNotice")

	@Override
	public List<NoticeVO> noticeList() {
		//인터페이스의 메소드를 호출
		return mapper.selectList();
	}

	@Override
	public NoticeVO getNotice(int nid) {
		mapper.increaseCnt(nid); //카운트증가
		return mapper.searchOne(nid);
	}

	@Override
	public int addNotice(NoticeVO notice) {
		return mapper.insertNotice(notice);
	}

	@Override
	public int modNotice(NoticeVO notice) {
		return mapper.updateNotice(notice);
	}

	@Override
	public int remNotice(int nid) {
		return mapper.deleteNotice(nid);
	}

	@Override
	public List<ReplyVO> replyList(int uid) {
		return mapper.replyList(uid);
	}

	@Override
	public int removeReply(int rid) {
		return mapper.deleteReply(rid);
	}

	@Override
	public int addReply(ReplyVO reply) {
		return mapper.insertReply(reply);
	}

}
