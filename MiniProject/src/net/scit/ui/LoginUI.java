package net.scit.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class LoginUI {
	
	/*
	
	UserVO vo = new UserVO();
	Scanner sc = new Scanner(System.in);
	UserDAO dao = new UserDAO();

	// 로그인ui
	public LoginUI() {

		String choice;
		while (true) {
			Menu();
			choice = sc.nextLine();

			if (Integer.parseInt(choice) == 3) {
				new JoinUI();
			}

			String usrid;
			System.out.print("> ID를 입력하세요 : ");
			usrid = sc.nextLine();
			vo = dao.findById(usrid);

			if (usrid.trim().equals("")) {
				System.out.println("문자로 입력해주세요");
				return;
			}
			if (dao.findById(usrid) == null) {
				System.out.println("아이디가 존재하지 않습니다.");
				return;
			}
			if (Integer.parseInt(vo.getTeamnum()) != 0) {// 팀번호가 0이 아니면
				switch (choice) {
				case "1":
					login(vo);
					break;
				case "2":
					pwsken(vo);
					break;
				case "3":
					new JoinUI();
					break;

				case "0":
					System.out.println("** 프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("err) 메뉴를 다시 선택해 주세요");
				}
			} else {// 팀번호가 0이면
				switch (choice) {
				case "1":
					ganLogin(vo);
					break;
				case "2":
					pwsken(vo);
					break;
				case "3":
					new JoinUI();
					break;

				case "0":
					System.out.println("** 프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("err) 메뉴를 다시 선택해 주세요");
				}
			}
		}
	}

	private void login(UserVO vo) { // 일반 회원 로그인
		String pw;
		System.out.print("> PW 를 입력하세요 : ");
		pw = sc.nextLine();
		if (dao.findById(pw) == null) {
			System.out.println("비밀번호가 틀립니다.");
			return;
		}
		vo = dao.findById(vo.getUsrid());
		dao.Logins(vo);
		System.out.printf(" *** %s님 환영합니다!*** ", vo.getUsrname());

		loginMenu(vo);

	}

	private void ganLogin(UserVO vo) { // 관리자 로그인
		String pw;
		System.out.print("> PW 를 입력하세요 : ");
		pw = sc.nextLine();
		if (dao.findById(pw) == null) {
			System.out.println("비밀번호가 틀립니다.");
			return;
		}
		vo = dao.findById(vo.getUsrid());
		dao.Logins(vo);
		System.out.printf(" *** %s님 환영합니다!*** ", vo.getUsrname());

		ganginMenu(vo);

	}

	private void pwsken(UserVO vo) {

		if (dao.findById(vo.getUsrid()) != null) {
			vo = dao.findById(vo.getUsrid());
			System.out.println("비밀번호는 : " + vo.getPw());
			return;
		} else {
			System.out.println("해당 아이디가 존재하지 않습니다.");
			return;
		}
	}

	// 유저 메뉴
	private void loginMenu(UserVO vo) {
		String choice;
		while (true) {
			loMenu();
			choice = sc.nextLine();

			switch (choice) {
			case "1":
				new memberListUI(vo);
				break;
			case "2":
				new BoardUI(vo);
				break;
			case "3":
				new TodoUI(vo);
				break;
			case "4":
				new SchedulerUI(vo);
				break;
			case "5" : 
				new MessengerServer(vo);
				break;
			case "6" :
				new MessengerClient(vo);
				break;
			case "0":
				return;
			default:
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}

		}

	}

	// 관리자 메뉴
	private void ganginMenu(UserVO vo) {
		String choice;
		while (true) {
			ganMenu();
			choice = sc.nextLine();

			switch (choice) {
			case "1":
				allReply(vo);
				break;
			case "2":
				new BoardUI(vo);
				break;
//			case "3":  break;
//			case "3":  break;

			case "0":
				return;
			default:
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}
		}
	}

	private void userReply(UserVO vo) {
		List<UserVO> list = dao.userReply(vo);
		Iterator<UserVO> iter = list.iterator();
		System.out.println(vo.getTeamnum() + "번의 팀 맴버 목록 입니다.");

		while (iter.hasNext())
			System.out.println(iter.next());
		System.out.println();
	}

	private void allReply(UserVO vo) {
		List<UserVO> list = dao.allReply(vo);
		Iterator<UserVO> iter = list.iterator();

		while (iter.hasNext())
			System.out.println(iter.next());
		System.out.println();
	}

	private void Menu() {
		System.out.println();
		System.out.println("============[로  그  인]===============");
		System.out.println("      1) 로  그  인");
		System.out.println("      2) 비밀번호 찾기");
		System.out.println("      3) 회원가입 하기");
		System.out.println("      4) 채팅 하기");
		System.out.println("==================================");
		System.out.print("                선택 > ");
	}

	private void loMenu() {
		System.out.println();
		System.out.println("============[로  그  인]===============");
		System.out.println("      1) 팀원 조회");
		System.out.println("      2) 게시판 조회");
		System.out.println("      3) 할일 등록");
		System.out.println("      4) 할일 조회");
		System.out.println("      5) 팀원 일정 조회");
		System.out.println("      0) 로그아웃");
		System.out.println("==================================");
		System.out.print("                선택 > ");
	}

	private void ganMenu() {
		System.out.println();
		System.out.println("============[로  그  인]===============");
		System.out.println("      1) 인원 조회");
		System.out.println("      2) 게시판 조회");
		System.out.println("      3) 공지사항 조회");
		System.out.println("      4) 전체 할일 조회");
		System.out.println("      5) 전체 팀원 일정 조회");
		System.out.println("      0) 로그아웃");
		System.out.println("==================================");
		System.out.print("                선택 > ");
	}
	
	
	*/

}
