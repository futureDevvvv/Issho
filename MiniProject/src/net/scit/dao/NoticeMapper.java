package net.scit.dao;

import java.util.List;
import net.scit.vo.NoticeVO;

public interface NoticeMapper {
	//게시글 전체 출력
	public List<NoticeVO> listBoards();
	//게시글 등록
	public int weiterBoards(NoticeVO notice); //
	//게시글 삭제
	public int deletBoards(String n_num); //
	//게시글 수정
	public int updateBoards(NoticeVO notice);
	//게시글 조회
	public NoticeVO readNotices(String n_num); //

}

