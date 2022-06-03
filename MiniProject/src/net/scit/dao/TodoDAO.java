package net.scit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.TodoVO;

public class TodoDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public List<TodoVO> listByPerson(String usrid) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		List<TodoVO> list = mapper.listByPerson(usrid);

		return list;

	}

	public List<TodoVO> listByPersonSearch(Map<String, String> map) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		List<TodoVO> list = mapper.listByPersonSearch(map);

		return list;

	}

	// 팀별 조회
	public List<TodoVO> listByTeam(String teamnum) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		List<TodoVO> list = mapper.listByTeam(teamnum);

		return list;
	}
	
	public List<TodoVO> listByTeamSearch(Map<String, String> map) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		List<TodoVO> list = mapper.listByTeamSearch(map);

		return list;
	}

	// 상태별, 등록일, 중요도별 조회
	/*
	 * public List<TodoVO> listByOption(String state) { SqlSession session = null;
	 * session = factory.openSession();
	 * 
	 * TodoMapper mapper = session.getMapper(TodoMapper.class);
	 * 
	 * 
	 * 
	 * return null;
	 * 
	 * }
	 */

	// 등록일별 조회
	/*
	 * public List<TodoVO> listByDate(String t_regdate);
	 * 
	 * //중요도별 조회 public List<TodoVO> listByPriority(String t_priority);
	 */

	// 할 일 검색
	/*public List<TodoVO> searchTodo(Map<String, String> map) {

		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		List<TodoVO> list = mapper.searchTodo(map);

		return list;

	}*/

	// 등록
	public int insertTodo(TodoVO todo) {

		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		int result = mapper.insertTodo(todo);
		session.commit();

		return result;

	}

	// 수정
	public int updateTodo(TodoVO todo) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		int result = mapper.updateTodo(todo);
		session.commit();

		return result;
	}

	// 삭제
	public int deleteTodo(String t_num) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		int result = mapper.deleteTodo(t_num);
		session.commit();

		return result;
	}

	public TodoVO findByTnum(String t_num) {
		SqlSession session = null;
		session = factory.openSession();

		TodoMapper mapper = session.getMapper(TodoMapper.class);

		TodoVO todo = mapper.findByTnum(t_num);
		
		return todo;
	}
}
