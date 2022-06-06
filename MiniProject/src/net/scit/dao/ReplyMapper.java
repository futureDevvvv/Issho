package net.scit.dao;

import java.util.List;

import net.scit.vo.ReplyVO;


public interface ReplyMapper {
	//댓글 목록
		public List<ReplyVO> listReply(String b_num);

		//댓글 쓰기
		public int writeReply(ReplyVO reply);
		
		//하나의 댓글 객체 불러오기
		public ReplyVO selectReply(String r_num);
		
		//댓글 삭제
		public int deleteReply(String r_num);
		
		//댓글 수정
		public int updateReply(ReplyVO reply);
		//댓글 불러오기
		public ReplyVO readBoard(String b_num);

		public List<ReplyVO> ganlistReply(String b_num);
		
}
