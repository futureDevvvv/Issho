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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;

public class BoardAllUI extends JFrame {

	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();

	List<BoardVO> list = boardDao.listBoardAll();

	String searchOption[] = { "제목", "내용", "작성자" };

	String[] colNames = new String[] { "글번호", "제목", "작성자", "작성일", "조회수" };
	Object[][] rowDatas = new Object[list.size()][colNames.length];

	public BoardAllUI() {

		/*
		 * 
		 * String teamnum = vo.getTeamnum();
		 * 
		 * String teamname = null;
		 * 
		 * switch (teamnum) { case "10": teamname = "기획부"; break; case "20": teamname =
		 * "영업부"; break; case "30": teamname = "인사부"; break; case "40": teamname =
		 * "개발부"; break; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("굴림", Font.BOLD, 25);

		JLabel title = new JLabel("팀원 게시판");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

		/*
		 * 
		 * JLabel lb1 = new JLabel("조회 옵션"); lb1.setBounds(20, 50, 100, 30); c.add(lb1);
		 * 
		 * // 라디오버튼 생성 JRadioButton rd1 = new JRadioButton("최신순"); JRadioButton rd2 =
		 * new JRadioButton("인기순");
		 * 
		 * rd1.setBounds(100, 50, 70, 30); rd2.setBounds(170, 50, 80, 30);
		 * 
		 * // 1번 라디오 버튼 눌려져 있도록 rd1.setSelected(true);
		 * 
		 * // 라디오 버튼을 그룹화하기 위한 객체 생성 ButtonGroup groupRd = new ButtonGroup();
		 * 
		 * // 그룹에 라디오 버튼 포함 groupRd.add(rd1); groupRd.add(rd2);
		 * 
		 * c.add(rd1); c.add(rd2);
		 * 
		 */

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

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(550);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
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

				new BoardDetailUI(list.get(rowNum).getB_num());
				setVisible(false);
			}

		});

		JButton writeNew = new JButton("게시글 작성");
		writeNew.setBounds(500, 570, 150, 30);
		c.add(writeNew);

		writeNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BoardInsertUI();
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

				list = boardDao.searchBoard(map);

			}
		});

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

	public static void main(String[] args) {
		new BoardAllUI();
	}
}
