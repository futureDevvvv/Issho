//package net.scit.ui;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//import net.scit.dao.UserDAO;
//import net.scit.vo.UserVO;
//
//
//public class BoardUI {
//	Scanner keyin = new Scanner(System.in);
//	Map<String, Object> map= new HashMap<>();
//	UserDAO dao = new UserDAO();
////	ReplyDAO ReplyDao = new ReplyDAO();
//	
//	public BoardUI(UserVO vo) {
//		String choice;
//		while(true) {
//			mainMenu();
//			choice = keyin.nextLine();
//			
//			switch (choice) {
//			case "1": input(vo); break;
//			case "2": list(vo); break;
//			case "3": read(vo); break;
//			case "4": delete(vo); break;
//			case "5": update(vo); break;
//			case "6": search(vo); break;
//			case "7": count(vo); break;
//			case "0": 
//				return;
//			default:
//				System.out.println("err) 메뉴를 다시 선택해 주세요");
//			}
////			keyin.nextLine(); //버퍼 비우기
//		}
//	}
//
//	private void count(UserVO vo) {
//		dao.getCount();
//		
//	}
//
//	private void input(UserVO vo) {
//		String writer;
//		String title;
//		String text;
//		
//		System.out.print(">제목을 입력하세요 : ");
//		title = keyin.nextLine();
//		if(title.trim().equals("")) {	//글자만 입력할수있게 다른게 들어오면 입력안되게
//			System.out.println("문자로 입력해주세요");
//			return;
//		}
//		System.out.print(">작성자를 입력하세요 : ");
//		writer = keyin.nextLine();
//		if(writer.trim().equals("")) {	//글자만 입력할수있게 다른게 들어오면 입력안되게
//			System.out.println("문자로 입력해주세요");
//			return;
//		}
//		System.out.println(">내용을 입력하세요 : ");
//		text = keyin.nextLine();
//		if(text.trim().equals("")) {	//글자만 입력할수있게 다른게 들어오면 입력안되게
//			System.out.println("문자로 입력해주세요");
//			return;
//		}
//		BoardVO board = new BoardVO(writer, title, text);
//		
//		int result = dao.weiterBoard(board);
//		map.put(board.getText(), board);
//		System.out.printf("** %d 명 회원가입이 완료되었습니다.",result);
//	}
//
//	private void list(UserVO vo) {
//		List<BoardVO> list = dao.listBoard();
//		Iterator<BoardVO> iter = list.iterator();
//		
//		while(iter.hasNext())
//			System.out.println(iter.next());
//		System.out.println();
//	}
//
//	private void read(UserVO vo) {
//		int boardnum;
//		list();
//		System.out.print(">읽을 번호를 입력하세요 : ");
//		boardnum = keyin.nextInt();
//		keyin.nextLine();
//		Board result = boardDao.readBoard(boardnum);
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
//		Board b = boardDao.findById(boardnum);
//		if(b == null) {
//			System.out.println("** 해당 아이디의 회원이 없습니다.");
//			return;
//		}
//		System.out.print("** 정말로 탈퇴하시겠습니까? (y/n)");
//		answer = keyin.next();
//		if(answer.equals("y")) {
//			boardDao.deletBoard(boardnum); // 리턴값을 받지 않아도 된다. ==> 
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
//		UserVO board = boardDao.readBoard(boardnum);
//		board.setText(text);
//		board.setTitle(title);
//		
//		int result = boardDao.updateBoard(board);
//		System.out.printf("** %d 명 수정이 완료되었습니다.",result);
//		
//	}
//
//	private void search(UserVO vo) {
//		List<UserVO> list = boardDao.listBoard();
//		Iterator<UserVO> iter = list.iterator();
//		Board board = new Board();
//		keyin.nextLine();
//		System.out.print("> 내용중 찾을 단어를 입력하세요 : ");
//		String a = keyin.nextLine();
//		map.put(a, board);
//		while(iter.hasNext())
//			System.out.println(iter.next());
//		
//		boardDao.searchBoard(map);
//		System.out.println(map.size());
//	}
//
//	private void mainMenu() {
//		System.out.println();
//		System.out.println("============[오픈 게시판]===============");
//		System.out.println("      1) 게시글 쓰기");
//		System.out.println("      2) 전체 목록 조회");
//		System.out.println("      3) 게시글 읽기");
//		System.out.println("      4) 게시글 삭제");
//		System.out.println("      5) 게시글 수정");
//		System.out.println("      6) 게시글 검색");
//		System.out.println("      7) 총 게시글 수");
//		System.out.println("      0) 프로그램 종료");
//		System.out.println("==================================");
//		System.out.print	("                선택 > ");
//	}
//}
