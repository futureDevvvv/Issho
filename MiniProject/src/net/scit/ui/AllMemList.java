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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class AllMemList extends JFrame {

	UserDAO userDao = new UserDAO();
	List<UserVO> list = userDao.AllMemList();

	public AllMemList(UserVO vo) {

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel main = new JLabel("구성원 관리");
		main.setFont(forTitle);
		main.setBounds(20, 10, 300, 30);
		c.add(main);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 650, 400);
		c.add(scrollPane);

		String[] colNames = new String[] { "아이디", "이름", "소속 부서" };
		Object[][] rowDatas = new Object[list.size()][colNames.length];

		// JTable
		JTable table = new JTable();

		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = new Object[] { list.get(i).getUsrid(), list.get(i).getUsrname(),
					teamName(list.get(i).getTeamnum()) };
		}

		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		scrollPane.setViewportView(table);
		

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

	public String teamName(String teamnum) {
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

		return teamname;

	}
}
