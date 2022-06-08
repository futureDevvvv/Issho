package net.scit.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class memberListUI extends JFrame {

	UserDAO userDao = new UserDAO();

	public memberListUI(UserVO vo) {
		
		String teamnum = vo.getTeamnum();
		
		List<UserVO> memList = userDao.userReply(vo); 
		Vector<String> memVector = new Vector<String>();
		
		for(int i = 0; i < memList.size(); i++) {
			memVector.add(memList.get(i).getUsrid() + "    " + memList.get(i).getUsrname() + "\n");
		}
		

		// 컨테이너 선언 & 레이아웃 설정 없음.
		Container c = getContentPane();
		c.setLayout(null);

		// 창 닫기 버튼 클릭 시 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String teamname = null;

		switch (teamnum) {
		case "10":
			teamname = "기획부";
			break;
		case "20":
			teamname = "영업부";
			break;
		case "30":
			teamname = "인사부";
			break;
		case "40":
			teamname = "개발부";
			break;
		}
		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel title = new JLabel("팀원 목록  ");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

		JLabel info = new JLabel(teamname);
		info.setBounds(150, 10, 150, 30);
		c.add(info);

		JTextArea jtx1 = new JTextArea();
		JScrollPane jsp1 = new JScrollPane(jtx1);
		jsp1.setBounds(20, 100, 400, 500);
		c.add(jsp1);
		
		for(int i = 0; i < memVector.size(); i++) {
			jtx1.append(memVector.get(i));
		}

		JLabel lb1 = new JLabel("팀원 아이디");
		lb1.setBounds(20, 630, 100, 30);
		c.add(lb1);

		JTextField tf1 = new JTextField();
		tf1.setBounds(110, 630, 180, 30);
		c.add(tf1);

		JButton btn1 = new JButton("1:1 채팅 시작");
		btn1.setBounds(320, 630, 100, 30);
		c.add(btn1);
		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				
				String memId = tf1.getText();
				
				if(memId.length() == 0 || memId.equals("")) {
					JOptionPane.showMessageDialog(null, "팀원 아이디가 입력되지 않았습니다.", "에러", JOptionPane.ERROR_MESSAGE);
					
					return;
				}
				
				
				*/
				//new MessengerServer();
				
			}
		});
		
		JButton btn2 = new JButton("메신저 확인");
		btn2.setBounds(320, 690, 100, 30);
		c.add(btn2);
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

			//	new MessengerClient();
			}
		});


		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}
}
