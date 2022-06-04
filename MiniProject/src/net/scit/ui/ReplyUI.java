package net.scit.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import net.scit.dao.BoardDAO;
import net.scit.dao.ReplyDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.ReplyVO;
import net.scit.vo.UserVO;


public class ReplyUI {
	Scanner sc = new Scanner(System.in);
	UserDAO dao = new UserDAO();
	BoardDAO bdao = new BoardDAO();
	ReplyDAO rdao = new ReplyDAO();
	
	public ReplyUI(UserVO vo, String b_num) {
		readBoard(vo,b_num);
		submenu();
		String choice = sc.nextLine();

		switch (choice) {
		case "1":
			InsertReply(vo,b_num);
			break;

		case "2":
			UpdateReply(vo,b_num);
			break;

		case "3":
			deleteReply(vo,b_num);
			break;
		}
	}

	public void readBoard(UserVO vo, String b_num) {
		if(Integer.parseInt(vo.getTeamnum()) == 0) {
			List<ReplyVO> relist = rdao.ganlistReply(b_num);
			for (int i = 0; i < relist.size(); i++) {
				System.out.println("-----------덧글-----------");
				System.out.println(relist.get(i));
			}
		}
		List<ReplyVO> relist = rdao.listReply(b_num);
		for (int i = 0; i < relist.size(); i++) {
			System.out.println(relist.get(i));
		}
	}
	
	private void InsertReply(UserVO vo, String b_num) { //덧글입력
		String usrid, r_content;
		usrid = vo.getUsrid();
		System.out.println(" > 내용");
		r_content = sc.nextLine();
		if (r_content.trim().equals("")) {
			System.out.println("내용이 입력되지 않았습니다.");
			return;
		}
		ReplyVO ro = new ReplyVO(b_num,r_content,usrid);
		rdao.writeReply(ro);
		new ReplyUI(vo,b_num);
	}
	
	
	private void UpdateReply(UserVO vo, String b_num) {				//수정
		System.out.println("수정할 댓글 번호 >");
		String r_num = sc.nextLine();

		ReplyVO reply = rdao.selectReply(r_num);
		if(vo.getUsrid().equals(reply.getUsrid())) {
			System.out.println("수정할 댓글 내용 > ");
			String r_content = sc.nextLine();

			reply.setR_content(r_content);

			rdao.updateReply(reply);
		}else if(Integer.parseInt(vo.getTeamnum()) == 0) {
			System.out.println("수정할 댓글 내용 > ");
			String r_content = sc.nextLine();

			reply.setR_content(r_content);

			rdao.updateReply(reply);
		}else {
			System.out.println("작성자가 아닙니다.");
			return;
		}

	}
	
	
	private void deleteReply(UserVO vo, String b_num) {	//삭제
		String replynum;
		System.out.println("삭제할 댓글 번호");
		replynum = sc.nextLine();
		ReplyVO reply = rdao.selectReply(replynum);
		if (reply == null) {
			System.out.println("수정할 댓글이 없습니다.");
			return;
		}
		if(vo.getUsrid().equals(reply.getUsrid())){
			System.out.println(reply);

			System.out.println("정말 삭제하시겠습니까? (y/n)");
			String ans = sc.nextLine();
			if (!ans.equals("y")) {
				System.out.println("댓글 삭제를 취소합니다.");
				return;
			}
			int result = rdao.deleteReply(replynum);
			System.out.println(result + "건 삭제 완료");
			
		}else if(Integer.parseInt(vo.getTeamnum()) == 0){
			System.out.println("정말 삭제하시겠습니까? (y/n)");
			String ans = sc.nextLine();
			if (!ans.equals("y")) {
				System.out.println("댓글 삭제를 취소합니다.");
				return;
			}
			int result = rdao.deleteReply(replynum);
			System.out.println(result + "건 삭제 완료");
		}else {
			System.out.println("작성자가 아닙니다.");
			return;
		}
	}
	private void submenu() {
		System.out.println("=============[댓   글]=============");
		System.out.println("			1) 댓글 작성");
		System.out.println("			2) 댓글 수정");
		System.out.println("			3) 댓글 삭제");
		System.out.println("----------------------------------");
		System.out.println("   	> 선택");
	}
		
		
}
