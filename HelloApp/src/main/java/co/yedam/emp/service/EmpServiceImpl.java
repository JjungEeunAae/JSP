package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import co.yedam.emp.dao.EmpDAO;
import co.yedam.emp.vo.EmpVO;

//jdbc를 활용하여 db를 처리
public class EmpServiceImpl implements EmpService{
	EmpDAO dao = EmpDAO.getInstance();

	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}

	@Override
	public int addEmp(EmpVO emp) {
		//한건 입력하는 기능을 EmpDAO에 등록해줘야함
		return dao.insertEmp(emp);
	}

	@Override
	public EmpVO getEmp(int empId) {
		return dao.searchEmp(empId);
	}
	
	//3단계-------------------------
	@Override
	public int modEmp(EmpVO emp) {
		//4단계---------------------
		return dao.updateEmp(emp);
	}

	@Override
	public Map<String, String> jobList() {
		return dao.jobList();
	}
	
	//삭제
	@Override
	public int removeEmp(int id) {
		return dao.removeEmp(id);
	}

}
