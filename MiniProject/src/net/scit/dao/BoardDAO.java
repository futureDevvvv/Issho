package net.scit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class BoardDAO {
	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	
	public int getCount(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.getCount();
		 mapper.getCount();
		 if(mapper.getCount() == 0 ) {
			 System.out.print("아무것도 존재하지 않습니다.");
			 System.out.println();
			 return 0;
		 }
		System.out.println("게시판 개수는 " + mapper.getCount()+ " 개 입니다. ");
		return 1;
		}
	
	public List<BoardVO> listBoard(String teamnum) {
		SqlSession session = null;
		session = factory.openSession();
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.listBoard(teamnum);
		return list;
	}
	
	public List<BoardVO> adminlistBoard(UserVO vo) {
		SqlSession session = null;
		session = factory.openSession();
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.adminlistBoard();
		return list;
	}


	public int weiterBoard(BoardVO board) {
		
		return 0;
	}
	
}