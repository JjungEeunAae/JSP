package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.r;

public class MemberDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "dev";
	String pass = "dev";
	Connection conn;

	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	String sql;

	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			System.out.println("에러");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("에러");
			e.printStackTrace();
		}
	}
	//전체조회
	public List<MemberVO> VoList() {
		connect();
		sql = "select * from board_member";
		List<MemberVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setMemberId(rs.getString("member_id"));
				mem.setMemberPw(rs.getString("member_pw"));
				mem.setMemberName(rs.getString("member_name"));
				mem.setMemberAddr(rs.getString("member_addr"));
				mem.setMemberTel(rs.getString("member_tel"));
				mem.setMemberBirth(rs.getString("member_birth"));
				mem.setMemberEmail(rs.getString("member_email"));
				
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원 전체조회
	public MemberVO getMember(String id) {
		sql = "select * from board_member where member_id = " + id;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				MemberVO m = new MemberVO();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPw(rs.getString("member_pw"));
				m.setMemberName(rs.getString("member_name"));
				m.setMemberBirth(rs.getString("member_birth"));
				return m;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 로그인(단건조회)
	public int getlogin(String id, String pw) {
		sql = "SELECT member_pw FROM board_member WHERE member_id = ?";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			// 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).contentEquals(pw)) {
					return 1; // 로그인 성공
				} else {
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // 아이디가 없음
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -2; // DB오류
	}

	// 회원가입
	public int addMember(MemberVO member) {
		connect();
		sql = "insert into board_member(member_id, member_pw, member_name, member_addr, member_tel, member_birth, member_email)"
				+ "values (?,?,?,?,?,?,?)";
		int r = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMemberId());
			psmt.setString(2, member.getMemberPw());
			psmt.setString(3, member.getMemberName());
			psmt.setString(4, member.getMemberAddr());
			psmt.setString(5, member.getMemberTel());
			psmt.setString(6, member.getMemberBirth());
			psmt.setString(7, member.getMemberEmail());

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// 회원가입 중복체크
	public int confirmId(String id) {
		connect();
		int x = -1;
		sql = "select * from board_member where member_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				x = 1;
			} else {
				x = -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return x;
	}

	// 회원탈퇴
	public int deleteMember(MemberVO member) {
		connect();
		sql = "delete from board_member where member_id = ? and member_pw = ? and member_name = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getMemberId());
			psmt.setString(2, member.getMemberPw());
			psmt.setString(3, member.getMemberName());

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	// ID 중복체크

}
