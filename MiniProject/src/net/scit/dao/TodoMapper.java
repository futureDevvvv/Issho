package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.TodoVO;

public interface TodoMapper {

	// 개인별, 팀별 조회
	public List<TodoVO> listByPerson(Map<String,String> map);

	// 팀별 조회
	public List<TodoVO> listByTeam(Map<String,String> map);

	// 등록일별 조회
	/*
	 * public List<TodoVO> listByOption(String state);
	 * 
	 * public List<TodoVO> listByDate(String t_regdate);
	 * 
	 * //중요도별 조회 public List<TodoVO> listByPriority(String t_priority);
	 */

	// 할 일 검색
	public List<TodoVO> searchTodo(Map<String,String> map);

	// 등록
	public int insertTodo(TodoVO todo);

	// 수정
	public int updateTodo(TodoVO todo);

	// 삭제
	public int deleteTodo(String t_num);
}
