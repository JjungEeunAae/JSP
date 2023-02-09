package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberService {
	//로그인
	public MemberVO login(MemberVO member);
	//회원가입
	public int addMember(MemberVO member);
	//회원목록
	public List<MemberVO> memberList();
	//회원정보 조회용
	public MemberVO getMember(String id);
	//회원정보 수정
	public int modifyMember(MemberVO member);
}
