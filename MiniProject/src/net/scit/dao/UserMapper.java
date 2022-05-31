package net.scit.dao;

import net.scit.vo.UserVO;

public interface UserMapper {

	//회원가입	 : 아이디중복검사
	public UserVO findById(String usrid); 
	
	//회원가입
	public int joinBoard(UserVO usrvo); 
	
	
}

