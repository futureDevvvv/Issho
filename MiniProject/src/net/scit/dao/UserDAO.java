package net.scit.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.UserVO;


public class UserDAO {
//회원가입, 로그인
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
		
	public UserVO findById(String usrid) {
		SqlSession session = null;
		session = factory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		UserVO vo = mapper.findById(usrid);
		return vo;
	}

	public int joinBoard(UserVO uservo) {
		SqlSession session = null;
		session = factory.openSession();
		
		UserMapper mapper = session.getMapper(UserMapper.class);
		int result = mapper.joinBoard(uservo); //받은 데이터를 데이터 베이스로 옴긴다. i / d /u 는 commit이 들어가야함
		session.commit();
		
		
		return result;
	}
	
	
	
}
