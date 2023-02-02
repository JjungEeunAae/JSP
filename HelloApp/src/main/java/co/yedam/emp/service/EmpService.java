package co.yedam.emp.service;

import java.util.List;

import co.yedam.emp.vo.EmpVO;

//업무에 대한 정의 : interface에 정의하고 구현하는 클래스를 통해 실현.
//쓰는 이유 : ㅣ
public interface EmpService {
	public List<EmpVO> empList();
}
