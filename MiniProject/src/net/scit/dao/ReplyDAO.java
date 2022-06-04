package net.scit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.ReplyVO;

public class ReplyDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public List<ReplyVO> listReply(String b_num) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		List<ReplyVO> list = mapper.listReply(b_num);

		return list;
	}
	
	public List<ReplyVO> ganlistReply(String b_num) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		List<ReplyVO> list = mapper.ganlistReply(b_num);

		return list;
	}
	

	public int writeReply(ReplyVO vo) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = mapper.writeReply(vo);
		session.commit();

		return result;
	}

	public ReplyVO selectReply(String b_num) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		ReplyVO reply = mapper.selectReply(b_num);

		return reply;
	}

	public int updateReply(ReplyVO reply) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		int result = mapper.updateReply(reply);
		session.commit();

		return result;
	}

	public int deleteReply(String replynum) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);

		int result = mapper.deleteReply(replynum);
		session.commit();

		return result;
	}

	public ReplyVO readBoard(String b_num) {
		SqlSession session = null;
		session = factory.openSession();

		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		
		ReplyVO reply = mapper.readBoard(b_num);
		
		session.commit();
		return reply;
	}
}
