package net.scit.dao;

import java.util.List;
import java.util.Map;

import net.scit.vo.BlikeVO;
import net.scit.vo.UserVO;

public interface BlikeMapper {

	List<BlikeVO> listLike();

	int Up(BlikeVO vo);
	
	BlikeVO ifLike(Map<String, String> map);
	
	int deleteLike(Map<String, String> map);

	Integer likeCount(String b_num);
	
}

