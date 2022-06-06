package net.scit.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import net.scit.dao.NoticeDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.NoticeVO;
import net.scit.vo.UserVO;


public class NoticeUI {
	Scanner sc = new Scanner(System.in);
	Map<String, Object> map= new HashMap<>();
	UserDAO dao = new UserDAO();
	NoticeDAO ndao = new NoticeDAO();
	
	public NoticeUI() {
		meme();
	}
	
	
	public void meme() {
		System.out.println("========공 지 사 항========");
		list();
	}
	
	
	
	public NoticeUI(UserVO vo) {
			String choice;
			while(true) {
				mainMenu();
				choice = sc.nextLine();
				
				switch (choice) {
				case "1": input(vo); break;			//공지글쓰기
				case "2": list(); break;				//공지글 전체 조회
				case "3": delete(vo); break;			//공지글 삭제
				case "4": update(vo); break;		//공지글 수정
				case "0": 
					return;
				default:
					System.out.println("err) 메뉴를 다시 선택해 주세요");
				}
			}
	}
	private void input(UserVO vo) {
		String n_title;
		String n_content;
		int n_hitcount =0;
		
		System.out.print(">제목을 입력하세요 : ");
		n_title = sc.nextLine();
		if(n_title.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		
		System.out.println(">내용을 입력하세요 : ");
		n_content = sc.nextLine();
		if(n_content.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		NoticeVO notice = new NoticeVO(n_title, n_content,n_hitcount);
		
		ndao.weiterBoards(notice);
		map.put(notice.getN_content(), notice);
		System.out.println("** 게시글작성이 완료되었습니다.");
	}
	
	private void list() {
			List<NoticeVO> list = ndao.listBoards();
			Iterator<NoticeVO> iter = list.iterator();
			while(iter.hasNext()) {
				System.out.println(iter.next());
			}
			System.out.println();	
	}

	private void delete(UserVO vo) {
		String answer;
		String n_num;
		System.out.print("삭제할 글번호는 무엇입니까 : ");
		n_num = sc.nextLine();
		if(n_num.equals("")) { //  
			System.out.println("잘못입력하셧습니다.");
			return;
		}
			System.out.print("** 정말로 글을 지우시겠습니까? (y/n)");
			answer = sc.nextLine();
					if (answer.equals("y")) {
						ndao.deletBoards(n_num); 
						System.out.println("** 삭제 완료\n");
						return;
					} else {
						System.out.println("** 삭제 작업이 취소되었습니다.");
					}
	}

	private void update(UserVO vo) {
		String n_num;
		String n_title;
		String n_content;
		list();
		System.out.print(">수정할 번호를 입력하세요 : ");
		n_num = sc.nextLine();
		if(vo.equals(n_num)) {
			System.out.println("** 해당 번호가 없습니다.");
			return;
		}
		System.out.println("====수  정====");
		System.out.print("> 제목은 무엇인가요 : ");
		n_title = sc.nextLine();
		if(n_title.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		System.out.println("> 내용을 입력하세요 : ");
		n_content = sc.nextLine();
		if(n_content.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}
		NoticeVO notice = ndao.readNotices(n_num);
		notice.setN_title(n_title);;
		notice.setN_content(n_content);
		
		ndao.updateBoards(notice);
	}
	private void mainMenu() {
		System.out.println();
		System.out.println("============[공지 사항]===============");
		System.out.println("      1) 공지글 쓰기");
		System.out.println("      2) 공지글 전체 조회");
		System.out.println("      3) 공지글 삭제");
		System.out.println("      4) 공지글 수정");
		System.out.println("      0) 프로그램 종료");
		System.out.println("==================================");
		System.out.print	("                선택 > ");
	}
}
