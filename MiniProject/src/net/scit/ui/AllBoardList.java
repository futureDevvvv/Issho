package net.scit.ui;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.BoardDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BoardVO;
import net.scit.vo.UserVO;

public class AllBoardList extends JFrame {

	BoardDAO boardDao = new BoardDAO();
	UserDAO userDao = new UserDAO();

	List<BoardVO> list = boardDao.listBoardAll();

	public AllBoardList(UserVO vo) {

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel main = new JLabel("게시판 관리");
		main.setFont(forTitle);
		main.setBounds(20, 10, 300, 30);
		c.add(main);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 150, 650, 400);
		c.add(scrollPane);


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

				new BoardDetailUI(vo, list.get(rowNum).getB_num());
				setVisible(false);
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
