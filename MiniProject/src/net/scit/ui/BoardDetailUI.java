package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;

public class BoardDetailUI extends JFrame {

	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();
	
	public BoardDetailUI(String b_num) {

		BoardVO board = boardDao.readBoard(b_num);
		
		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("굴림", Font.BOLD, 25);

		JLabel title = new JLabel(board.getB_title());
		title.setFont(forTitle);
		title.setBounds(120, 130, 300, 50);
		c.add(title);

		Font forContent = new Font("굴림", Font.PLAIN, 15);

		JLabel writer = new JLabel("작성자   " + userDao.findById(board.getUsrid()).getUsrname());
		// writer.setFont(forElse);
		writer.setBounds(120, 180, 150, 30);
		c.add(writer);

		JLabel regdate = new JLabel("작성일   " + board.getB_regdate());
		// regdate.setFont(forElse);
		regdate.setBounds(230, 180, 180, 30);
		c.add(regdate);

		JLabel hitcount = new JLabel("조회수   " + board.getB_hitcount());
		// regdate.setFont(forElse);
		hitcount.setBounds(400, 180, 180, 30);
		c.add(hitcount);

		JTextArea content = new JTextArea();
		content.setFont(forContent);
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane jsp1 = new JScrollPane(content);
		jsp1.setBounds(100, 220, 500, 280);
		c.add(jsp1);

		content.append(board.getB_content());

		//본인이 작성자인 경우 : 수정, 삭제 버튼
		JButton update = new JButton("수정");
		update.setBounds(370, 520, 100, 30);
		c.add(update);
		
		JButton delete = new JButton("삭제");
		delete.setBounds(500, 520, 100, 30);
		c.add(delete);
		
		//본인이 작성자가 아닐 경우 : 추천 버튼
		JButton likeit = new JButton("추천");
		likeit.setBounds(100, 520, 100, 30);
		c.add(likeit);
		
		JLabel reply = new JLabel("댓글");
		reply.setBounds(100, 580, 100, 30);
		c.add(reply);
		
		JTable table = new JTable();
		
		
		
		//본문 테두리
		JPanel boardBorder = new JPanel();
		boardBorder.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		boardBorder.setBounds(100, 100, 500, 400);
		c.add(boardBorder);

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);
	}
}
