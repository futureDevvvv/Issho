package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.TodoVO;

public interface TodoMapper {
	
	//전체 조회
	public List<TodoVO> listAll();

	// 개인별 일반 조회
	public List<TodoVO> listByPerson(String usrid);
	
	// 개인별 검색 조회
	public List<TodoVO> listByPersonSearch(Map<String,String> map);

	// 팀별 일반 조회
	public List<TodoVO> listByTeam(String teamnum);
	
	// 팀별 검색 조회
	public List<TodoVO> listByTeamSearch(Map<String,String> map);

	// 등록일별 조회
	/*
	 * public List<TodoVO> listByOption(String state);
	 * 
	 * public List<TodoVO> listByDate(String t_regdate);
	 * 
	 * //중요도별 조회 public List<TodoVO> listByPriority(String t_priority);
	 

	// 할 일 검색
	public List<TodoVO> searchTodo(Map<String,String> map);*/

	// 등록
	public int insertTodo(TodoVO todo);

	// 수정
	public int updateTodo(TodoVO todo);

	// 삭제
	public int deleteTodo(String t_num);

	//글 번호로 객체 검색
	public TodoVO findByTnum(String t_num);
}
