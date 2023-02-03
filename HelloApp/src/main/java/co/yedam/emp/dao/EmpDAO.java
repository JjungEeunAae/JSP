package co.yedam.emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;
import co.yedam.emp.vo.EmpVO;

public class EmpDAO extends DAO { // DAO 상속
	// ------------------------------------------------------------------------
	/*
	 * 싱글톤 방식, 메모리를 많이 사용하기 때문에 사용한다 생성자 : private, 메소드 : public getInstance
	 */
	private static EmpDAO instance = new EmpDAO();

	private EmpDAO() { // 생성자
	}

	public static EmpDAO getInstance() {
		return instance;
	}

	// -------------------------------------------------------------------------
	// 아이디 값을 읽어서 수정하는 기능(수정)
//	public int updateEmp(EmpVO emp) {
//		
//	}
	
	// 아이디를 넣으면 한건 조회해오는 기능(단건조회)
	public EmpVO searchEmp(int empId) {
		connect();
		sql = "select * from emp_temp where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empId);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJodId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null; //값이 없으면 null
	}
	
	// 한건입력(등록/단건조회)
	public int insertEmp(EmpVO emp) {
		connect();
		sql = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id) values(?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJodId());

			int r = psmt.executeUpdate(); // 에러가 안나면 1이 나온다
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return 0; // 한 건도 처리되지 않았다
	}

	// 전체 목록(사원의 리스트를 보고싶다)
	public List<EmpVO> empList() {
		List<EmpVO> emps = new ArrayList<>();
		connect();
		sql = "select * from emp_temp order by employee_id";
		// psmt : 쿼리를 실행하고 실행한 결과를 반환한다
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id")); // "" -> 칼럼이름
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJodId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); // 연결을 다하면 끊어주기/ 그러지 않으면 오류가 난다
		}
		return emps;
	}
}