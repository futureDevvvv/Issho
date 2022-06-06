package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.scit.vo.UserVO;

public class MenuUI extends JFrame {

	ImageIcon sMsg = new ImageIcon("mail.png");
	Image sndMsg = sMsg.getImage();
	Image sendMsg = sndMsg.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	ImageIcon sendMsgIcon = new ImageIcon(sendMsg);

	ImageIcon shMsg = new ImageIcon("post.png");
	Image shoMsg = shMsg.getImage();
	Image showMsg = shoMsg.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	ImageIcon showMsgIcon = new ImageIcon(showMsg);

	public MenuUI(UserVO vo) {

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font welcomeFont = new Font("굴림", Font.PLAIN, 20);
		JLabel welcome = new JLabel("*** " + vo.getUsrname() + "님 환영합니다! ***");
		welcome.setFont(welcomeFont);
		welcome.setBounds(200, 100, 300, 50);
		c.add(welcome);

		JPanel menuPanel = new JPanel(new GridLayout(5, 0, 20, 20));
		menuPanel.setBounds(250, 200, 200, 300);
		// menuPanel.setBackground(Color.black);
		c.add(menuPanel);

		if (!vo.getTeamnum().equals("0")) {
			JButton memInfo = new JButton("멤버 정보");
			menuPanel.add(memInfo);

			JButton viewBoard = new JButton("팀원 게시판");
			menuPanel.add(viewBoard);

			JButton todo = new JButton("업무관리");
			menuPanel.add(todo);

			todo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new TodoUI(vo);
					setVisible(false);
				}
			});

			JButton schedule = new JButton("일정관리");
			menuPanel.add(schedule);

			schedule.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new SchedulerUI(vo);
					setVisible(false);
				}
			});

			JButton logout = new JButton("로그아웃");
			menuPanel.add(logout);

			logout.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new Login();
					setVisible(false);
				}
			});

			JButton sendMsg = new JButton(sendMsgIcon);
			sendMsg.setBorderPainted(false);
			sendMsg.setContentAreaFilled(false);
			sendMsg.setFocusPainted(false);
			sendMsg.setBounds(250, 600, 50, 50);
			c.add(sendMsg);

			sendMsg.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new MessengerServer(vo);
					setVisible(false);
				}
			});

			JLabel sendMsgLb = new JLabel("메시지 보내기");
			sendMsgLb.setBounds(320, 600, 100, 30);
			c.add(sendMsgLb);

			JButton showMsg = new JButton(showMsgIcon);
			showMsg.setBorderPainted(false);
			showMsg.setContentAreaFilled(false);
			showMsg.setFocusPainted(false);
			showMsg.setBounds(250, 660, 50, 50);
			c.add(showMsg);

			showMsg.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new MessengerClient(vo);
					setVisible(false);
				}
			});

			JLabel showMsgLb = new JLabel("메시지함 확인");
			showMsgLb.setBounds(320, 660, 100, 30);
			c.add(showMsgLb);

			JPanel chatBorder = new JPanel();
			chatBorder.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), "메신저"));
			chatBorder.setBounds(200, 580, 300, 150);
			c.add(chatBorder);
		} else {
			//관리자 메뉴
			
			
			
		}

		JPanel menuBorder = new JPanel();
		menuBorder.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), "회원 메뉴"));
		menuBorder.setBounds(200, 150, 300, 400);
		c.add(menuBorder);

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

}
