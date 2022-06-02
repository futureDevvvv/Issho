package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.ScheduleVO;
import net.scit.vo.TodoVO;

public interface ScheduleMapper {
	
	//개인별 조회
	
	
	//팀별 조회
	
	//월별 검색
	
	//일별 검색
	
	//내용으로 검색
	public List<ScheduleVO> searchSchedule(Map<String,String> map);
	
	//일정 등록
	public int insertSchedule(ScheduleVO schedule);
	
	//일정 수정
	public int updateSchedule(ScheduleVO schedule);
	
	//일정 삭제
	public int deleteSchedule(String s_num);
}
