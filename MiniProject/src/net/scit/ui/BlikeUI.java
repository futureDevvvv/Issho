package net.scit.ui;

import java.util.List;
import java.util.Scanner;

import net.scit.dao.BlikeDAO;
import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BlikeVO;
import net.scit.vo.ReplyVO;
import net.scit.vo.UserVO;

public class BlikeUI {
	Scanner sc = new Scanner(System.in);
	UserDAO dao = new UserDAO();
	BoardDAO bdao = new BoardDAO();
	BlikeDAO gdao = new BlikeDAO();
	
	public BlikeUI(UserVO vo, String b_num) {
		String choice = sc.nextLine();
		likeMenu();

		switch (choice) {
		case "1":
			Up(vo,b_num);
			break;

		case "2":
//			Re(vo,b_num);
			break;
			
		case "3":
			List(vo,b_num);
			break;
			
		case "0":
			//new BoardUI(vo);
		}
	}

	public void List(UserVO vo, String b_num) {
		List<BlikeVO> relist = gdao.listLike(vo);
		
		 gdao.listLike(vo);
		 
	}
	
	private void Up(UserVO vo, String b_num) { //덧글입력
		String usrid,bnum;
		usrid = vo.getUsrid();
		bnum = b_num;
		
		BlikeVO vo1 = (new BlikeVO(b_num, usrid));
		gdao.Up(vo1);
		
		
	}
	
	private void likeMenu() {
		System.out.println();
		System.out.println("============[추천 하시겟습니까?]===============");
		System.out.println("      1) 추천!				2)추천취소!			3)돌아가기");
		System.out.println("==================================");
		System.out.print	("                선택 > ");
	}
}
