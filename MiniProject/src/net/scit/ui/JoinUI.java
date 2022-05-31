package net.scit.ui;

import java.util.Scanner;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class JoinUI {
	// 회원가입ui
	Scanner sc = new Scanner(System.in);
	UserDAO dao = new UserDAO();

	public JoinUI() {
		String choice;
		while (true) {
			mainMenu();
			choice = sc.nextLine();

			switch (choice) {
			case "1":
				input();
				break;
//			case "2": list(); break;
//			case "3": read(); break;
//			case "4": delete(); break;
//			case "5": update(); break;
//			case "6": search(); break;
//			case "7": count(); break;
			case "0":
				System.out.println("** 프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("err) 메뉴를 다시 선택해 주세요");
			}

		}

	}

	private void input() {
		String usrid, pw, usrname, teamnum;

		System.out.print("> ID를 입력하세요 : ");
		usrid = sc.nextLine();
		if (usrid.trim().equals("")) {
			System.out.println("문자로 입력해주세요");
			return;
		}

		if (dao.findById(usrid) != null) {
			System.out.println("아이디가 중복됩니다.");
			return;
		}

		if (usrid.length() > 10) {
			System.out.println("길이를 10자 이내로 맞춰주세요.");
			return;
		}
		
		

		System.out.print("> 비밀번호를 입력하세요 : ");
		pw = sc.nextLine();
		
		if (pw.length() > 15) {
			System.out.println("길이를 15자 이내로 맞춰주세요.");
			return;
		}

		System.out.println("> 이름을 입력하세요 : ");
		usrname = sc.nextLine();
		if (usrname.trim().equals("")) { // 글자만 입력할수있게 다른게 들어오면 입력안되게
			System.out.println("문자로 입력해주세요");
			return;
		}
		if (usrname.length() > 30) {
			System.out.println("길이를 30자 이내로 맞춰주세요.");
			return;
		}
		System.out.println(" 10) 기획    20) 영업   30) 인사   40) 개발");
		System.out.print("> 팀번호를 입력하세요 :  ");
		teamnum = sc.nextLine();
		
		System.out.println();
		
		 int aa = Integer.parseInt(teamnum);//값 비교하기 위한
		if(aa != 10 && aa != 20&& aa != 30&& aa != 40&& aa != 0) {
			//추가할부분 0직업의 값은 1개 이상이면 생성불가.
			
			System.out.println("팀번호를 잘못 입력하셧습니다. 다시 입력해 주세요.");
			return;
		}
		
		UserVO uservo = new UserVO(usrid, pw, usrname, teamnum);

		int result = dao.joinBoard(uservo);
		System.out.printf("회원가입이 완료되었습니다.");

	}

	private void mainMenu() {
		System.out.println();
		System.out.println("============[회원가입]===============");
		System.out.println("      1) 회원 가입");
		System.out.println("      2) 팀원 조회");
		System.out.println("      3) 게시판으로 이동");
		System.out.println("      4) 채팅 하기");
		System.out.println("==================================");
		System.out.print("                선택 > ");
	}

}
