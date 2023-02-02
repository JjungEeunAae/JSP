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

}
