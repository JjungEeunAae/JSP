package co.yedam.member.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.yedam.common.DataSource;
import co.yedam.member.mapper.MemberMapper;
import co.yedam.member.vo.MemberVO;

public class MemberSeriviceMybatis implements MemberService{
	//자동커밋
	SqlSession session = DataSource.getInstance().openSession(true);
											//인터페이스타입
	MemberMapper mapper = session.getMapper(MemberMapper.class);
	
	
	//해당 메소드 이름(login)과 xml에 해당되는 쿼리문의 id(id="login")이 동일해야함
	@Override
	public MemberVO login(MemberVO member) {
		//return session.selectOne("네임스페이스.id",member);
		return mapper.login(member);
	}


	@Override
	public int addMember(MemberVO member) {
		return mapper.addMember(member);
	}


	@Override
	public List<MemberVO> memberList() {
		return mapper.memberList();
	}


	@Override
	public MemberVO getMember(String id) {
		return mapper.getMember(id);
	}

}
