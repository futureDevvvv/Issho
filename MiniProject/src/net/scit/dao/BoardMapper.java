package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.BoardVO;

public interface BoardMapper {
	//게시글 전체 출력
	public List<BoardVO> listBoard(String teamnum);
	
	public List<BoardVO> adminlistBoard();
	
	
	
	//게시글 등록
	public int weiterBoard(BoardVO board); //
	//게시글 조회
	public BoardVO readBoard(int boardunm); //
	//게시글 검색
	public List<BoardVO>  searchBoard(Map<String, Object> map);
	//게시글 삭제
	public int deletBoard(int boardunm); //
	//게시글 수정
	public int updateBoard(BoardVO board);
	//조회수 수정
	public int incrementHitcount(int boardaunm); //
	//전체 글 개수
	public int getCount();

	public BoardVO findById(int boardnum);

	
	
}

