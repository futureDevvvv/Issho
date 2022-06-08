package net.scit.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class BoardSearchUI extends JFrame {

	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();

	String searchOption[] = { "제목", "내용", "작성자" };

	public BoardSearchUI(UserVO vo, Map<String, String> map) {

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

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel main = new JLabel(teamname + " 팀원 게시판");
		main.setFont(forTitle);
		main.setBounds(20, 10, 300, 30);
		c.add(main);

		// 게시글 검색
		// 검색옵션
		JComboBox<String> combo = new JComboBox<String>(searchOption);
		combo.setBounds(20, 90, 100, 30);
		c.add(combo);

		// 검색창
		JTextField tf1 = new JTextField();
		tf1.setBounds(130, 90, 170, 30);
		c.add(tf1);

		// 조회 버튼
		JButton btn1 = new JButton("검색");
		btn1.setBounds(320, 90, 100, 30);
		c.add(btn1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 650, 400);
		c.add(scrollPane);

		List<BoardVO> list = boardDao.searchBoard(map);

		String[] colNames = new String[] { "글번호", "제목", "작성자", "작성일", "조회수" };
		Object[][] rowDatas = new Object[list.size()][colNames.length];

		// JTable
		JTable table = new JTable();

		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = new Object[] { list.get(i).getB_num(), list.get(i).getB_title(),
					userDao.findById(list.get(i).getUsrid()), list.get(i).getB_regdate(), list.get(i).getB_hitcount() };
		}

		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(470);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);

		scrollPane.setViewportView(table);

		// 게시글 상세조회
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.getSelectedRow();

				new BoardDetailUI(vo, list.get(rowNum).getB_num());
				setVisible(false);
			}

		});

		JButton writeNew = new JButton("게시글 작성");
		writeNew.setBounds(500, 570, 150, 30);
		c.add(writeNew);

		writeNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BoardInsertUI(vo);
				setVisible(false);
			}
		});

		// 검색 기능
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Map<String, String> map = new HashMap<String, String>();

				String searchItem = null;

				String option = combo.getSelectedItem().toString();

				switch (option) {

				case "제목":
					searchItem = "b_title";
					break;

				case "내용":
					searchItem = "b_title";
					break;

				case "작성자":
					searchItem = "usrid";
					break;

				}

				map.put("searchItem", searchItem);

				String keyword = tf1.getText();

				if (option == "작성자") {
					keyword = userDao.findByName(keyword).getUsrid();
				}

				map.put("searchWord", keyword);

				new BoardSearchUI(vo, map);
				setVisible(false);

				// list = boardDao.searchBoard(map);

			}
		});

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
