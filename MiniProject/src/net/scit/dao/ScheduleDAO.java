package net.scit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.ScheduleVO;

public class ScheduleDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	//월별 검색
	
	
	//일별 검색
	
	
	// 내용으로 검색
	public List<ScheduleVO> searchSchedule(Map<String, String> map){
		return null;
		
	}

	// 일정 등록
	public int insertSchedule(ScheduleVO schedule) {
		return 0;
		
	}

	// 일정 수정
	public int updateSchedule(ScheduleVO schedule) {
		return 0;
		
	}

	// 일정 삭제
	public int deleteSchedule(String s_num) {
		return 0;
		
	}

}
