package com.yedam.project;

public class MemberVO {
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAddr;
	private String memberTel;
	private String memberBirth;
	private String memberEmail;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}

	public String getMemberTel() {
		return memberTel;
	}

	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}

	public String getMemberBirth() {
		return memberBirth;
	}

	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberemail) {
		this.memberEmail = memberemail;
	}

	@Override
	public String toString() {
		return "MemberVO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberAddr=" + memberAddr + ", memberTel=" + memberTel + ", memberBirth=" + memberBirth
				+ ", memberemail=" + memberEmail + "]";
	}

}
