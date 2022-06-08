package net.scit.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.scit.dao.BoardDAO;
import net.scit.dao.ReplyDAO;
import net.scit.vo.ReplyVO;
import net.scit.vo.UserVO;

public class ReplyUpdateUI extends JFrame {

	ReplyDAO replyDao = new ReplyDAO();
	BoardDAO boardDao = new BoardDAO();
	
	public ReplyUpdateUI(UserVO vo, String r_num) {

		ReplyVO reply = replyDao.selectReply(r_num);
		
		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("댓글");

		JTextField tf1 = new JTextField();
		tf1.setBounds(20, 50, 420, 150);
		c.add(tf1);
		
		tf1.setText(reply.getR_content());
		
		JButton update = new JButton("수정");
		update.setBounds(130, 200, 100, 30);
		c.add(update);
		
		update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reply.setR_content(tf1.getText());
				
				replyDao.updateReply(reply);
				setVisible(false);
				new BoardDetailUI(vo, reply.getB_num());
				
			}
		});

		JButton delete = new JButton("삭제");
		delete.setBounds(240, 200, 100, 30);
		c.add(delete);
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				replyDao.deleteReply(r_num);
				setVisible(false);
				new BoardDetailUI(vo, reply.getB_num());
				
			}
		});
		
		// 윈도우 창 크기 설정
		setSize(500, 300);

		// 윈도우 창 나타나게.
		setVisible(true);

	}
}
