package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "dev";
	String pass = "dev";
	Connection conn;

	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;

	String sql;

	public void connect() {
		// jdbc driver정상.
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생");
			e.printStackTrace();
		}
	}

	// 전체목록조회
	public List<BoardVO> boardVoList() {
		connect();
		// sql = "select * from post";
		// 글번호 기준으로 오름차순 정렬
		sql = "select * from post order by post_num";
		List<BoardVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setPostNum(rs.getInt("post_num"));
				b.setPostTitle(rs.getString("post_title"));
				b.setPostContent(rs.getString("post_content"));
				b.setWriter(rs.getString("post_writer"));
				b.setPostDate(rs.getString("post_date"));

				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 단건조회
	public BoardVO getBoard(int num) {
		sql = "select * from post where post_num =" + num;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				BoardVO b = new BoardVO();
				b.setPostNum(rs.getInt("post_num"));
				b.setPostTitle(rs.getString("post_title"));
				b.setPostContent(rs.getString("post_content"));
				b.setWriter(rs.getString("post_writer"));
				b.setPostDate(rs.getString("post_date"));
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회된 데이터가 없습니다
		return null;
	}

	// 입력한 게시글 번호가 db에 없을 때 사용하는 클래스
	public BoardVO getPostNum(int num) {
		sql = "select nvl(max(post_num),0) post_num" + " from post" + " where post_num =" + num;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				BoardVO b = new BoardVO();
				b.setPostNum(rs.getInt("post_num"));
//				b.setPostTitle(rs.getString("post_title"));
//				b.setPostContent(rs.getString("post_content"));
//				b.setWriter(rs.getString("post_writer"));
//				b.setPostDate(rs.getString("post_date"));
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회된 데이터가 없습니다
		System.err.println("입력한 번호는 등록된 게시글 번호가 아닙니다");
		return null;
	}

	// 해당되는 아이디의 게시글 단건조회하는 클래스
	public List<BoardVO> getSelectBoard(String writer) {
		sql = "select post_num,post_date,post_writer,post_title,post_content from post where post_writer=" + writer;
		connect();
		List<BoardVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO b = new BoardVO();
				b.setPostNum(rs.getInt("post_num"));
				b.setPostDate(rs.getString("post_date"));
				b.setWriter(rs.getString("post_writer"));
				b.setPostTitle(rs.getString("post_title"));
				b.setPostContent(rs.getString("post_content"));
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 게시글작성
	public int postInsert(BoardVO board) {
		connect();
		sql = "insert into post(post_num, post_title, post_content, post_writer)"
				+ "values ((select NVL(MAX(post_num)+1,1)FROM post) ,?,?,?)";

		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getPostTitle());
			psmt.setString(2, board.getPostContent());
			psmt.setString(3, board.getWriter());
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;

	}

	// 게시글수정
	public int postUpdate(String title, String content, String writer, int num) {
		connect();
		int r = 0;
		sql = "update post set post_date = post_date";

		if (title != null) {
			sql += ", post_title = ?";
		}
		if (content != null) {
			sql += ",post_content = ?";
		}
		if (writer != null) {
			sql += ",post_writer = ?";
		}
		sql += "where post_num = ?";
		int cut = 1;

		try {
			psmt = conn.prepareStatement(sql);
			if (title != null) {
				psmt.setString(cut++, title);
			}
			if (content != null) {
				psmt.setString(cut++, content);
			}
			if (writer != null) {
				psmt.setString(cut++, writer);
			}
			psmt.setInt(cut++, num);

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// 게시글삭제
	// post_num number primary key not null, --게시글번호
	// post_title varchar2(100) not null, --글제목
	// post_content varchar2(1000), --글내용
	// post_writer VARCHAR2(50) not null, --작성자
	// post_date date default sysdate --작성일자

	public int deleteBoard(BoardVO board) {
		connect();
		sql = "delete from post where post_num = ? and post_writer = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board.getPostNum());
			psmt.setString(2, board.getWriter());

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
