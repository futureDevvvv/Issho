package net.scit.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.vo.ScheduleVO;

public class ScheduleDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	// 월별 검색(개인)
	public List<ScheduleVO> listByPersonMonth(Map<String, String> map) {

		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleVO> list = mapper.listByPersonMonth(map);

		return list;
	}

	// 월별 검색(팀별)
	public List<ScheduleVO> listByTeamMonth(Map<String, String> map) {
		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleVO> list = mapper.listByTeamMonth(map);

		return list;
	}

	// 일별 검색(개인)
	public List<ScheduleVO> listByPersonDay(Map<String, String> map) {

		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleVO> list = mapper.listByPersonDay(map);

		return list;
	}

	// 일별 검색(팀별)
	public List<ScheduleVO> listByTeamDay(Map<String, String> map) {

		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleVO> list = mapper.listByTeamDay(map);

		return list;
	}

	// PK로 검색

	public ScheduleVO findByNum(String s_num) {

		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		ScheduleVO vo = mapper.findByNum(s_num);

		return vo;
	}

	// 내용으로 검색
	/*
	 * public List<ScheduleVO> searchSchedule(Map<String, String> map) { return
	 * null;
	 * 
	 * }
	 */

	// 일정 등록
	public int insertSchedule(ScheduleVO schedule) {
		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		int result = mapper.insertSchedule(schedule);
		session.commit();

		return result;

	}

	// 일정 수정
	public int updateSchedule(ScheduleVO schedule) {
		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		int result = mapper.updateSchedule(schedule);
		session.commit();

		return result;

	}

	// 일정 삭제
	public int deleteSchedule(String s_num) {
		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		int result = mapper.deleteSchedule(s_num);
		session.commit();

		return result;

	}

	public List<ScheduleVO> AllSchList() {
		SqlSession session = null;
		session = factory.openSession();

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleVO> list = mapper.AllSchList();
		return list;
	}

}
