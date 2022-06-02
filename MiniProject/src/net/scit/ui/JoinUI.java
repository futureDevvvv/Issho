package net.scit.ui;

import java.util.Scanner;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class JoinUI {
	// 회원가입ui
	Scanner sc = new Scanner(System.in);
	UserDAO dao = new UserDAO();

	public JoinUI() {
		input();
	}

	private void input() {
		String usrid, pw, usrname, teamnum;
		System.out.println(" *** 회  원  가  입 *** ");
		System.out.println();
		System.out.println(">10자 이내로 작성해주세요.");
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
		
		
		System.out.println(">15자 이내로 비밀번호를 작성해주세요.");
		System.out.print("> 비밀번호를 입력하세요 : ");
		pw = sc.nextLine();
		
		if (pw.length() > 15) {
			System.out.println("길이를 15자 이내로 맞춰주세요.");
			return;
		}
		System.out.println(">이름을 30자 이내로 만들어주세요.");
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
		
		if(Integer.parseInt(teamnum) == 0) {
				if(dao.findByTeamnum(teamnum) == 1) {
					System.out.println("더이상 생성하실수 없습니다.");
					return;
				}
		}else {
			System.out.println("더이상 생성하실수 없습니다.");
			return;
		}
			
		
		
		System.out.println();
		int aa = Integer.parseInt(teamnum);
		 //값 비교하기 위한
		if(aa != 10 && aa != 20&& aa != 30&& aa != 40&& aa != 0) {
			//추가할부분 0직업의 값은 1개 이상이면 생성불가.
			
			System.out.println("팀번호를 잘못 입력하셧습니다. 다시 입력해 주세요.");
			return;
		}
		
		UserVO uservo = new UserVO(usrid, pw, usrname, teamnum);

		dao.joinBoard(uservo);
		
		System.out.printf("회원가입이 완료되었습니다.");
		new LoginUI();
	}

}
