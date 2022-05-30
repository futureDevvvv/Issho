package net.scit.word.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import net.scit.word.vo.Word;

public class WordDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory(); // ���̹�Ƽ�� ��ü

	// �ܾ� �߰�
	public int appendWord(Word word) {
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		
		int result = mapper.appendWord(word);
		session.commit();
		
		return result;
	}
	
	// �ܾ� ��� 	
	public List<Word> listWord(){
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		List<Word> list = mapper.listWord();
		
		return list;
	}
	
	// �ܾ� ã��
	public Word findByWord(String word){
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		
		Word w = mapper.findByWord(word);
		
		return w;
	}
	
	// �ܾ� ����
	public int deleteWord(int seq){
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		
		int result = mapper.deleteWord(seq);
		session.commit();
		return result;
	}
	
	
	// �ܾ� ����
	public int updateWord(Word word){
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		int result = mapper.updateWord(word);
		session.commit();
		
		return result;
	}
	
	
	// �� �ܾ��
	public int totalWords(){
		SqlSession session = null;
		session = factory.openSession();
		
		WordMapper mapper = session.getMapper(WordMapper.class);
		
		return 0;
	}
}
