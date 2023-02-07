package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.common.DataSource;
import co.yedam.emp.vo.EmpVO;

//EmpServiceImpl : jdbc
//EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService{
	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(true);

	@Override
	public List<EmpVO> empList() {
		//mapper namespace + select id
		return session.selectList("co.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		//session.commit();
		int r =  session.insert("co.yedam.emp.mapper.EmpMapper.addEmp",emp);
		if(r>0) {
			session.commit();
		} else {
			session.rollback();
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) {
		return session.selectOne("co.yedam.emp.mapper.EmpMapper.getEmp", empId);
	}

	//건수수정
	@Override
	public int modEmp(EmpVO emp) {
		return session.update("co.yedam.emp.mapper.EmpMapper.modEmp",emp);
	}
	
	//직무리스트
	@Override
	public Map<String, String> jobList() {
		return null; //session.selectmap("co.yedam.emp.mapper.EmpMapper.jobList");
	}
	
	//건수삭제
	@Override
	public int removeEmp(int id) {
		return session.delete("co.yedam.emp.mapper.EmpMapper.removeEmp",id);
	}

}
