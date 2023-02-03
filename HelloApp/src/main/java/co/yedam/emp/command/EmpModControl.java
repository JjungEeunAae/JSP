package co.yedam.emp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class EmpModControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		//service : int modEmp(EmpVO) 등록 -> serviceImpl:modEmp(EmpVO) 구현 -> dao 클래스 : updateEmp(EmpVO) 정의

	}

}
