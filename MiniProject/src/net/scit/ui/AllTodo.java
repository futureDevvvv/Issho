package net.scit.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.scit.dao.TodoDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.TodoVO;
import net.scit.vo.UserVO;

public class AllTodo extends JFrame {

	TodoDAO todoDao = new TodoDAO();
	UserDAO userDao = new UserDAO();

	public AllTodo(UserVO vo) {

		Container c = getContentPane();
		c.setLayout(null);

		// 창 닫기 버튼 클릭 시 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel title = new JLabel("업무 관리");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

		Font fortextArea = new Font("KoPubWorld돋움체 Light", Font.BOLD, 15);

		JLabel before = new JLabel("진행전");
		before.setFont(fortextArea);
		before.setBounds(100, 140, 200, 30);
		c.add(before);

		JTextArea jtx1 = new JTextArea();
		JScrollPane jsp1 = new JScrollPane(jtx1);
		jsp1.setBounds(20, 170, 200, 270);
		c.add(jsp1);

		// textArea / 진행중
		JLabel proceed = new JLabel("진행중");
		proceed.setFont(fortextArea);
		proceed.setBounds(300, 140, 200, 30);
		c.add(proceed);

		JTextArea jtx2 = new JTextArea();
		JScrollPane jsp2 = new JScrollPane(jtx2);
		jsp2.setBounds(230, 170, 200, 270);
		c.add(jsp2);

		// textArea / 진행완료
		JLabel finish = new JLabel("완료");
		finish.setFont(fortextArea);
		finish.setBounds(520, 140, 200, 30);
		c.add(finish);

		JTextArea jtx3 = new JTextArea();
		JScrollPane jsp3 = new JScrollPane(jtx3);
		jsp3.setBounds(440, 170, 200, 270);
		c.add(jsp3);

		List<TodoVO> list = todoDao.listAll();

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).getT_state() == 0) {
				jtx1.append(list.get(i).getT_num() + "\n" + list.get(i).getT_content() + "\n"
						+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getT_regdate() + "\n");
				jtx1.append("------------------------------------\n");
			} else if (list.get(i).getT_state() == 1) {
				jtx2.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid()) + "\n"
						+ list.get(i).getT_regdate() + "\n");
				jtx2.append("------------------------------------\n");
			}
		}
		
		// 이전 버튼
				JButton menu = new JButton("<< 이전");
				menu.setBounds(20, 570, 150, 30);
				c.add(menu);

				menu.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						new MenuUI(vo);
						setVisible(false);
					}
				});
		
		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

}
