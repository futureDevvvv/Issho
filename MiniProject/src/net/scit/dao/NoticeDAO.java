package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BoardVO;
import net.scit.vo.NoticeVO;

public class NoticeDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	public List<NoticeVO> listBoards() {
		SqlSession session = null;
		session = factory.openSession();
		
		NoticeMapper mapper = session.getMapper(NoticeMapper.class);
		List<NoticeVO> list = mapper.listBoards();
		session.commit();
		return list;
	}

	public int weiterBoards(NoticeVO board) {
		SqlSession session = null;
		session = factory.openSession();
		
		NoticeMapper mapper = session.getMapper(NoticeMapper.class);
		int result = mapper.weiterBoards(board);
		session.commit();
		return result;
	}

	public void deletBoards(String b_num) {
		SqlSession session = null;
		session = factory.openSession();
		
		NoticeMapper mapper = session.getMapper(NoticeMapper.class);
		mapper.deletBoards(b_num);

		session.commit();
	}

	public int updateBoards(NoticeVO board) {
		SqlSession session = null;
		session = factory.openSession();

		NoticeMapper mapper = session.getMapper(NoticeMapper.class);

		int result = mapper.updateBoards(board);
		session.commit();

		return result;
	}

	public NoticeVO readNotices(String n_num) {
		SqlSession session = null;
		session = factory.openSession();
		
		NoticeMapper mapper = session.getMapper(NoticeMapper.class);
		NoticeVO result = mapper.readNotices(n_num);
		session.commit();
		return result;
	}
	
}