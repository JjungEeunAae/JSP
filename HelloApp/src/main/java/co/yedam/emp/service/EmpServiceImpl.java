package co.yedam.emp.service;

import java.util.List;

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

//	@Override
//	public int modEmp(EmpVO emp) {
//		return dao.updateEmp(emp);
//	}

}
