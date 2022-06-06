package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BlikeVO;
import net.scit.vo.UserVO;

public class BlikeDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public List<BlikeVO> listLike(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		BlikeMapper mapper = session.getMapper(BlikeMapper.class);
		List<BlikeVO> list = mapper.listLike();
		session.commit();
		return list;
	}
	
	public int Up(BlikeVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		BlikeMapper mapper = session.getMapper(BlikeMapper.class);
		int result = mapper.Up(vo);
		session.commit();
		return result;
	}
	
}
