package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
	// 필드
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pass = "hr";
	// 1. ojdbc,jar 라이브러리 추가하기
	// 2. Connection 객체 --> db연결 쿼리 실행 또는 실행 결과를 가지고 오는 통로
	// 모든 곳에 사용되어야하기 때문에 필드로 선언하였다
	public Connection conn;
	public PreparedStatement psmt = null;
	public Statement stmt = null; // Connection으로 연결한 객체에게, Query 작업을 실행하기 위한 객체
	public ResultSet rs = null; // executeQuery(String sql)을 통해 쿼리 실행하면 ResultSet타입으로 반환을

	// 쿼리문을 담을 String타입 변수 초기화
	public String sql;

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

	public void disconn() {
		// 사용한 리소스 환원
		try {
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
