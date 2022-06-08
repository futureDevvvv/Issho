package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class BoardUpdateUI extends JFrame {

	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();

	public BoardUpdateUI(UserVO vo, String b_num) {
		String teamnum = vo.getTeamnum();

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

		BoardVO board = boardDao.readBoard(b_num);

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel main = new JLabel(teamname + " 팀원 게시판");
		main.setFont(forTitle);
		main.setBounds(20, 10, 300, 30);
		c.add(main);

		Font forContent = new Font("KoPubWorld돋움체 Light", Font.PLAIN, 15);

		// 제목 입력창
		JLabel title = new JLabel("제목");
		title.setFont(forContent);
		title.setBounds(120, 130, 100, 30);
		c.add(title);

		JTextField inputTitle = new JTextField();
		inputTitle.setBounds(120, 180, 450, 30);
		inputTitle.setFont(forContent);
		c.add(inputTitle);

		inputTitle.setText(board.getB_title());

		// 본문 입력창
		JLabel content = new JLabel("내용");
		content.setFont(forContent);
		content.setBounds(120, 230, 100, 30);
		c.add(content);

		JTextField inputContent = new JTextField();
		inputContent.setBounds(120, 280, 450, 250);
		inputContent.setFont(forContent);
		c.add(inputContent);

		inputContent.setText(board.getB_content());

		// 입력 & 뒤로 가기
		JButton update = new JButton("수정");
		update.setBounds(230, 550, 100, 30);
		c.add(update);

		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String title = inputTitle.getText();
				String content = inputContent.getText();

				if (title.length() == 0 || title.equals("")) {
					JOptionPane.showMessageDialog(null, "제목이 입력되지 않았습니다.");
					return;
				}

				if (content.length() == 0 || content.equals("")) {
					JOptionPane.showMessageDialog(null, "내용이 입력되지 않았습니다.");
					return;
				}

				board.setB_title(title);
				board.setB_content(content);

				int result = boardDao.updateBoard(board);

				if (result == 1) {
					new BoardDetailUI(vo, board.getB_num());
					setVisible(false);
				}
			}
		});

		JButton delete = new JButton("취소");
		delete.setBounds(350, 550, 100, 30);
		c.add(delete);

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BoardAllUI(vo);
				setVisible(false);
			}
		});

		JPanel boardBorder = new JPanel();
		boardBorder.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		boardBorder.setBounds(100, 100, 500, 500);
		c.add(boardBorder);

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);
	}
}
