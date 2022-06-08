package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class MyInfoUI extends JFrame {

	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();

	JPanel myInfoPanel;
	JPanel memInfoPanel;
	JPanel myPostPanel;

	public MyInfoUI(UserVO vo) {

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

		JLabel main = new JLabel("마이페이지");
		main.setFont(forTitle);
		main.setBounds(20, 10, 300, 30);
		c.add(main);

		Font forContent = new Font("KoPubWorld돋움체 Light", Font.PLAIN, 20);

		String myInfo = "내 정보";
		String memInfo = "팀원 목록";
		String myPost = "내 게시글";

		// 내 정보
		JLabel myId = new JLabel("아이디 : " + vo.getUsrid());
		myId.setBounds(150, 150, 150, 30);
		c.add(myId);

		JLabel myName = new JLabel("이름 : " + vo.getUsrname());
		myName.setBounds(150, 200, 100, 30);
		 c.add(myName);

		JLabel myTeam = new JLabel("소속 부서 : " + teamname);
		myTeam.setBounds(150, 250, 150, 30);
		c.add(myTeam);

		// 팀원 목록
		JLabel memInfoPanel = new JLabel(memInfo);
		memInfoPanel.setFont(forContent);
		memInfoPanel.setBounds(350, 100, 100, 30);
		c.add(memInfoPanel);

		JScrollPane scrollPaneMem = new JScrollPane();
		scrollPaneMem.setBounds(350, 135, 250, 210);
		c.add(scrollPaneMem);

		List<UserVO> memList = userDao.userReply(vo);

		String[] colNamesMem = new String[] { "아이디", "이름" };
		Object[][] rowDatasMem = new Object[memList.size()][colNamesMem.length];

		// JTable
		JTable tableMem = new JTable();

		for (int i = 0; i < memList.size(); i++) {
			rowDatasMem[i] = new Object[] { memList.get(i).getUsrid(), memList.get(i).getUsrname() };
		}

		tableMem.setModel(new DefaultTableModel(rowDatasMem, colNamesMem) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

		tableMem.setRowHeight(25);
		tableMem.getColumnModel().getColumn(0).setResizable(false);
		tableMem.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableMem.getColumnModel().getColumn(1).setResizable(false);
		tableMem.getColumnModel().getColumn(1).setPreferredWidth(200);

		scrollPaneMem.setViewportView(tableMem);

		// 내 게시글
		JLabel myPostPanel = new JLabel(myPost);
		myPostPanel.setFont(forContent);
		myPostPanel.setBounds(80, 390, 100, 30);
		c.add(myPostPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(80, 420, 530, 250);
		c.add(scrollPane);

		List<BoardVO> boardList = boardDao.selectById(vo.getUsrid());

		String[] colNames = new String[] { "글번호", "제목", "작성자", "작성일", "조회수" };
		Object[][] rowDatas = new Object[boardList.size()][colNames.length];

		// JTable
		JTable table = new JTable();

		for (int i = 0; i < boardList.size(); i++) {
			rowDatas[i] = new Object[] { boardList.get(i).getB_num(), boardList.get(i).getB_title(),
					userDao.findById(boardList.get(i).getUsrid()), boardList.get(i).getB_regdate(),
					boardList.get(i).getB_hitcount() };
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

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.getSelectedRow();

				new BoardDetailUI(vo, boardList.get(rowNum).getB_num());
				setVisible(false);
			}

		});

		// 내 정보 패널
		myInfoPanel = new JPanel();
		myInfoPanel.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), myInfo, 0, 0, forContent));
		myInfoPanel.setBounds(80, 100, 250, 250);
		c.add(myInfoPanel);

		JButton menu = new JButton("<< 이전");
		menu.setBounds(20, 750, 100, 30);
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
