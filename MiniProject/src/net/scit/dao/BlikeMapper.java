package net.scit.dao;

import java.util.List;

import net.scit.vo.BlikeVO;
import net.scit.vo.UserVO;

public interface BlikeMapper {

	List<BlikeVO> listLike();

	int Up(BlikeVO vo);
	
}

