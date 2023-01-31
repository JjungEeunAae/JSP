package com.yedam.emp;

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

// C(reate), R(ead), U(pdate), D(elete) 구현 클래스
public class EmpDAO {
	// 필드
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "hr";
	String pass = "hr";
	// 1. ojdbc,jar 라이브러리 추가하기
	// 2. Connection 객체 --> db연결 쿼리 실행 또는 실행 결과를 가지고 오는 통로
	// 모든 곳에 사용되어야하기 때문에 필드로 선언하였다
	Connection conn;

	PreparedStatement psmt = null;

	// 3. Statement 겍체 --> Connection으로 연결한 객체에게, Query 작업을 실행하기 위한 객체
	// 단점 : 파라미터 쓰기가 불편함
	Statement stmt = null;

	// 4. ResultSet 객체 --> executeQuery(String sql)을 통해 쿼리 실행하면 ResultSet타입으로 반환을
	// 해주어 결과값을 저장
	ResultSet rs = null;

	// 쿼리문을 담을 String타입 변수 초기화
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

	// ------------목록조회
	// Map 을 활용하여 CRUD 구현
	public List<Map<String, Object>> empList() {
		sql = "select * from emp_temp";
		connect();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<>();

				// Map을 이용하여 칼럼을 생성
				map.put("emp_id", rs.getInt("employee_id"));
				map.put("first_name", rs.getString("first_name"));
				map.put("last_name", rs.getString("last_name"));
				map.put("salary", rs.getInt("salary"));

				// 생성한 칼럼을 List를 이용하여 목록으로 변환
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 클래스를 이용한 CRUD 구현
	// List<Map<String, Object>>와 비교
	public List<EmpVO> empVoList() {
		connect();
		sql = "select * from emp_temp";
		List<EmpVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				emp.setJobId(rs.getString("job_id"));

				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// ------------단건조회
	public EmpVO getEmp(int id) {
		sql = "select * from emp_temp where employee_id = " + id;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			// 무조건 한건만 조회되기 때문에 while문이 필요없다
			if (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				emp.setHireDate(rs.getString("hire_date"));
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 조회된 데이터가 없습니다 라는 뜻
		return null;
	}

	// ------------입력
	public int addEmp(EmpVO emp) {
		connect();
		sql = "insert into emp_temp (employee_id, last_name, email, hire_date, job_id) " + "values(?,?,?,?,?)";
		int r = 0;
		// 파라미터의 ? 값을 처리해줄 때 아주 유용하다
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());

			// 처리된 건수
			// 정상적으로 처리되면 1를 반환함
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// ------------수정
	public int updateEmp(int id, int sal) {
		connect();
		sql = "update emp_temp set SALARY = ? where EMPLOYEE_ID = ?";
		int r = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, sal);
			psmt.setInt(2, id);

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// ------------수정
	public int updateEmp(EmpVO emp) {
		connect();
		sql = "update emp_temp set hire_date = ?, email = ?, job_id = ?, last_name =? where EMPLOYEE_ID = ?";
		int r = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getHireDate());
			psmt.setString(2, emp.getEmail());
			psmt.setString(3, emp.getJobId());
			psmt.setString(4, emp.getLastName());
			psmt.setInt(5, emp.getEmployeeId());
			
			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	// ------------삭제
	public int deleteEmp(int id) {
		connect();
		sql = "delete from emp_temp where EMPLOYEE_ID = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);

			r = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

}
