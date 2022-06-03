package net.scit.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;


public class BoardUI {
	Scanner keyin = new Scanner(System.in);
	Map<String, Object> map= new HashMap<>();
	UserDAO dao = new UserDAO();
	BoardDAO bdao = new BoardDAO();
	
	public BoardUI(UserVO vo) {
		String choice;
		while(true) {
			mainMenu();
			choice = keyin.nextLine();
			
			switch (choice) {
			case "1": input(vo); break;
			case "2": list(vo); break;
//			case "3": read(vo); break;
//			case "4": delete(vo); break;
//			case "5": update(vo); break;
//			case "6": search(vo); break;
			case "7": count(vo); break;
			case "0": 
				return;
			default:
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}
//			keyin.nextLine(); //버퍼 비우기
		}
	}

	private void count(UserVO vo) {
		bdao.getCount(vo);
	}

	private void input(UserVO vo) {
		String b_title;
		String b_content;
		String usrid;
		String teamnum;
		
		System.out.print(">제목을 입력하세요 : ");
		b_title = keyin.nextLine();
		if(b_title.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		
		System.out.println(">내용을 입력하세요 : ");
		b_content = keyin.nextLine();
		if(b_content.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		usrid = vo.getUsrid();
		teamnum = vo.getTeamnum();
		BoardVO board = new BoardVO(b_title, b_content,usrid, teamnum);
		
		int result = bdao.weiterBoard(board);
		map.put(board.getB_content(), board);
		System.out.printf("** %d 명 회원가입이 완료되었습니다.",result);
	}
	
	private void list(UserVO vo) {
		
		if(Integer.parseInt(vo.getTeamnum()) == 0) {
			
			List<BoardVO> list = bdao.adminlistBoard(vo);//관리자게시판으로
			Iterator<BoardVO> iter = list.iterator();
			
			while(iter.hasNext())
				System.out.println(iter.next());
			System.out.println();
		} else {
			List<BoardVO> list = bdao.listBoard(vo.getTeamnum());//일반게시판으로
			Iterator<BoardVO> iter = list.iterator();
			
			while(iter.hasNext())
				System.out.println(iter.next());
			System.out.println();
		}
		
	}

//	private void read(UserVO vo) {
//		int boardnum;
//		list();
//		System.out.print(">읽을 번호를 입력하세요 : ");
//		boardnum = keyin.nextInt();
//		keyin.nextLine();
//		BoardVO result = dao.readBoard(boardnum);
//		
//		System.out.println(result.getText());
//		
//		new ReplyUI(result.getBoardnum());
//		
//		
//		
//		
//	}
//
//	private void delete(UserVO vo) {
//		String answer;
//		int boardnum;
//		System.out.print("삭제할 ID는 무엇입니까 : ");
//		boardnum = keyin.nextInt();
//		keyin.nextLine();
//		BoardVO b = dao.findById(boardnum);
//		if(b == null) {
//			System.out.println("** 해당 아이디의 회원이 없습니다.");
//			return;
//		}
//		System.out.print("** 정말로 탈퇴하시겠습니까? (y/n)");
//		answer = keyin.next();
//		if(answer.equals("y")) {
//			dao.deletBoard(boardnum); // 리턴값을 받지 않아도 된다. ==> 
//			System.out.println("** 삭제 완료\n");
//			return;
//		}else {
//			System.out.println("** 삭제 작업이 취소되었습니다.");
//		}
//		
//	}
//
//	private void update(UserVO vo) {
//		int boardnum;
//		String title;
//		String text;
//		list();
//		System.out.print(">수정할 번호를 입력하세요 : ");
//		boardnum = keyin.nextInt();
//		keyin.nextLine();
//		if(boardnum == 0) {
//			System.out.println("** 해당 번호가 없습니다.");
//			return;
//		}
//		System.out.println("====수  정====");
//		System.out.print("> 제목은 무엇인가요 : ");
//		title = keyin.nextLine();
//		if(title.trim().equals("")) {
//			System.out.println("문자로 입력해주세요");
//			return;
//		}
//		System.out.println("> 내용을 입력하세요 : ");
//		text = keyin.nextLine();
//		if(text.trim().equals("")) {
//			System.out.println("문자로 입력해주세요");
//			return;
//		}
//		BoardVO board = dao.readBoard(boardnum);
//		board.setText(text);
//		board.setTitle(title);
//		
//		int result = dao.updateBoard(board);
//		System.out.printf("** %d 명 수정이 완료되었습니다.",result);
//		
//	}
//
//	private void search(UserVO vo) {
//		List<UserVO> list = dao.listBoard();
//		Iterator<UserVO> iter = list.iterator();
//		BoardVO board = new BoardVO();
//		keyin.nextLine();
//		System.out.print("> 내용중 찾을 단어를 입력하세요 : ");
//		String a = keyin.nextLine();
//		map.put(a, board);
//		while(iter.hasNext())
//			System.out.println(iter.next());
//		
//		dao.searchBoard(map);
//		System.out.println(map.size());
//	}

	private void mainMenu() {
		System.out.println();
		System.out.println("============[오픈 게시판]===============");
		System.out.println("      1) 게시글 쓰기");
		System.out.println("      2) 팀원 글목록 조회");
		System.out.println("      3) 게시글 읽기");
		System.out.println("      4) 게시글 삭제");
		System.out.println("      5) 게시글 수정");
		System.out.println("      6) 게시글 검색");
		System.out.println("      7) 총 게시글 수");
		System.out.println("      0) 프로그램 종료");
		System.out.println("==================================");
		System.out.print	("                선택 > ");
	}
}
