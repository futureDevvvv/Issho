package net.scit.dao;

import java.util.List;

import net.scit.vo.UserVO;

public interface UserMapper {

	//회원가입	 : 아이디중복검사
	public UserVO findById(String usrid); //
	
	public int findByTeamnum(String teamnum); //
	
	//회원가입
	public int joinBoard(UserVO usrvo);//

	public List<UserVO> memberList(String teamnum); 
	
	//로그인
	public UserVO Logins(UserVO vo); //
	//팀원조회
	public List<UserVO> userReply(UserVO vo);//
	//관리자 전체조회
	public List<UserVO> allReply(UserVO vo);//
	
	//이름으로 팀원 조회
	public UserVO findByName(String usrname);//
	
	//전체 회원 조회
	public List<UserVO> AllMemList();
	
}

