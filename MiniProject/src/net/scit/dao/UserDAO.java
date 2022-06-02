package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.UserVO;


public class UserDAO {
//회원가입, 로그인
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
		
	public UserVO findById(String usrid) {  //중복 체크 메소드
		SqlSession session = null;
		session = factory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		UserVO vo = mapper.findById(usrid);
		session.commit();
		return vo;
	}
	
	public int findByTeamnum(String teamnum) {
		SqlSession session = null;
		session = factory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		int result = mapper.findByTeamnum(teamnum);
		session.commit();
		return result;
	}

	public int joinBoard(UserVO uservo) { //회원가입 메소드
		SqlSession session = null;
		session = factory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		int result = mapper.joinBoard(uservo);
		session.commit();
	
		return result;
	}

	public UserVO Logins(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		mapper.Logins(vo);
		
		session.commit();
		return vo;
	}

	public List<UserVO> userReply(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserVO> list = mapper.userReply(vo);
		session.commit();
		return list;
		
	}

	public List<UserVO> allReply(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<UserVO> list = mapper.allReply(vo);
		session.commit();
		return list;
		
	}

	

	
	
	
	
	
	
	
}
