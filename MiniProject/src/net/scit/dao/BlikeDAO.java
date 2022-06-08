package net.scit.dao;

import java.util.List;
import java.util.Map;

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

	// 좋아요 여부 확인
	public BlikeVO ifLike(Map<String, String> map) {
		SqlSession session = null;
		session = factory.openSession();

		BlikeMapper mapper = session.getMapper(BlikeMapper.class);

		BlikeVO vo = mapper.ifLike(map);

		return vo;
	}

	// 좋아요 취소
	public int deleteLike(Map<String, String> map) {
		SqlSession session = null;
		session = factory.openSession();

		BlikeMapper mapper = session.getMapper(BlikeMapper.class);

		int result = mapper.deleteLike(map);
		session.commit();

		return result;
	}

	public Integer likeCount(String b_num) {
		SqlSession session = null;
		session = factory.openSession();

		BlikeMapper mapper = session.getMapper(BlikeMapper.class);

		int result;
		
		if(mapper.likeCount(b_num) == null) {
			result = 0;
		} else {
			result = mapper.likeCount(b_num);
		}

		return result;
	}

}
