package net.scit.word.dao;

import java.util.List;

import net.scit.word.vo.Word;

/**
 * Mapper XML의 SQL문 사용 메서드
 */

public interface WordMapper {
	// 단어 추가
	public int appendWord(Word word);
	
	// 단어 목록 	
	public List<Word> listWord();
	
	// 단어 찾기
	public Word findByWord(String word);
	
	// 단어 삭제
	public int deleteWord(int seq);
	
	// 단어 수정
	public int updateWord(Word word);
	
	// 총 단어수
	public int totalWords();
}
