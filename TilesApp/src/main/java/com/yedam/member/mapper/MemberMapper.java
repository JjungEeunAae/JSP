package com.yedam.member.mapper;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberMapper {
	//데이터베이스 처리 장소
	
	public MemberVO login(MemberVO member);
	//등록
	public int addMember(MemberVO member);
	//회원전체목록
	public List<MemberVO> memberList();
	//회원정보 조회용
	public MemberVO getMember(String id);
	//회원정보수정
	public int updateMember(MemberVO member);
}
