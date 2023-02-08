package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.notice.vo.NoticeVO;

public interface NoticeMapper {
	public List<NoticeVO> selectList(); 		//목록
	public NoticeVO searchOne(int nid); 		//한건조회
	public int insertNotice(NoticeVO notice); 	//등록
	public int updateNotice(NoticeVO notice);	//수정
	public int deleteNotice(int nid);			//삭제
	public int increaseCnt(int nid);			//조회수증가
	//댓글목록
	//댓글등록
}
