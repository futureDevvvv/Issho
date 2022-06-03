package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class UserDAO {
//회원가입, 로그인
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	// 중복 아이디 체크 메소드
	public UserVO findById(String usrid) { 
		SqlSession session = null;
		session = factory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);

		UserVO vo = mapper.findById(usrid);
		session.commit();
		return vo;
	}
	//팀번호 찾는 메소드
	public int findByTeamnum(String teamnum) {
		SqlSession session = null;
		session = factory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);

		int result = mapper.findByTeamnum(teamnum);
		session.commit();
		return result;
	}
	
	public int joinBoard(UserVO uservo) { // 회원가입 메소드
		SqlSession session = null;
		session = factory.openSession();

		UserMapper mapper = session.getMapper(UserMapper.class);
		int result = mapper.joinBoard(uservo);
		session.commit();

		return result;
	}
	//로그인세션
	public UserVO Logins(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);

		mapper.Logins(vo);

		session.commit();
		return vo;
	}

	// 팀원 목록(개인)
	public List<UserVO> userReply(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserVO> list = mapper.userReply(vo);
		session.commit();
		return list;

	}
	//관리자 기준 정보보기
	public List<UserVO> allReply(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserVO> list = mapper.allReply(vo);
		session.commit();
		return list;

	}

	// 이름으로 회원 조회
	public UserVO findByName(String usrname) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		UserVO vo = mapper.findByName(usrname);
		
		return vo;
		
	}





	

	
	
	
	
	

}
