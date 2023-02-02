package co.yedam.emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DAO;
import co.yedam.emp.vo.EmpVO;

public class EmpDAO extends DAO{ //DAO 상속
	//------------------------------------------------------------------------
	/*싱글톤 방식, 메모리를 많이 사용하기 때문에 사용한다
	  생성자 : private, 메소드 : public getInstance*/
	private static EmpDAO instance = new EmpDAO();
	private EmpDAO() { //생성자
	}
	public static EmpDAO getInstance() {
		return instance;
	}
	//-------------------------------------------------------------------------
	
	//사원의 리스트를 보고싶다
	public List<EmpVO> empList(){
		List<EmpVO> emps = new ArrayList<>();
		connect();
		sql = "select * from emp_temp order by employee_id";
		// psmt : 쿼리를 실행하고 실행한 결과를 반환한다
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id")); // "" -> 칼럼이름
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn(); //연결을 다하면 끊어주기/ 그러지 않으면 오류가 난다
		}
		return emps;
	}
}
