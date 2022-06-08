package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.ScheduleVO;
import net.scit.vo.TodoVO;

public interface ScheduleMapper {

	//전체 일정
	public List<ScheduleVO> AllSchList();
	
	// 개인별 월별 검색
	public List<ScheduleVO> listByPersonMonth(Map<String, String> map);

	// 팀별 월별 검색
	public List<ScheduleVO> listByTeamMonth(Map<String, String> map);

	// 개인별 일별 검색
	public List<ScheduleVO> listByPersonDay(Map<String, String> map);

	// 팀별 일별 검색
	public List<ScheduleVO> listByTeamDay(Map<String, String> map);
	
	// PK 검색
	public ScheduleVO findByNum(String s_num);
	
	// 내용으로 검색
	// public List<ScheduleVO> searchSchedule(Map<String,String> map);

	// 일정 등록
	public int insertSchedule(ScheduleVO schedule);

	// 일정 수정
	public int updateSchedule(ScheduleVO schedule);

	// 일정 삭제
	public int deleteSchedule(String s_num);

	

}
