package net.scit.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.TodoDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.TodoVO;
import net.scit.vo.UserVO;

public class TodoUI extends JFrame {

	TodoDAO todoDao = new TodoDAO();
	UserDAO userDao = new UserDAO();
	Vector<String> row1 = new Vector<String>();

	/**
	 * serialVersion
	 */
	private static final long serialVersionUID = 2721396482567223817L;

	TodoUI(UserVO vo) {

		String name = vo.getUsrname();
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

		List<UserVO> memberList = userDao.userReply(vo);
		Vector<String> memVector = new Vector<String>();

		Container c = getContentPane();
		c.setLayout(null);

		// 창 닫기 버튼 클릭 시 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("굴림", Font.BOLD, 25);

		JLabel title = new JLabel("업무 관리   ");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

		JLabel info = new JLabel(teamname + " " + name);
		info.setBounds(130, 10, 150, 30);
		c.add(info);

		JLabel lb1 = new JLabel("조회 범위");
		lb1.setBounds(20, 50, 100, 30);
		c.add(lb1);

		// 라디오버튼 생성
		JRadioButton rd1 = new JRadioButton("개인별");
		JRadioButton rd2 = new JRadioButton("팀별");

		rd1.setBounds(100, 50, 70, 30);
		rd2.setBounds(170, 50, 80, 30);

		// 1번 라디오 버튼 눌려져 있도록
		rd1.setSelected(true);

		// 라디오 버튼을 그룹화하기 위한 객체 생성
		ButtonGroup groupRd = new ButtonGroup();

		// 그룹에 라디오 버튼 포함
		groupRd.add(rd1);
		groupRd.add(rd2);

		c.add(rd1);
		c.add(rd2);

		// 할 일 검색
		JLabel lb3 = new JLabel("검색어");
		lb3.setBounds(20, 90, 100, 30);
		c.add(lb3);

		// 검색창
		JTextField tf1 = new JTextField();
		tf1.setBounds(100, 90, 170, 30);
		c.add(tf1);

		// 조회 버튼
		JButton btn1 = new JButton("조회");
		btn1.setBounds(300, 90, 100, 30);
		c.add(btn1);

		// textArea / 진행전

		Font fortextArea = new Font("굴림", Font.BOLD, 15);

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

		// 버튼 입력 이벤트 : 조회 조건 설정 및 출력
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (rd1.isSelected()) {

					if (tf1.getText().length() == 0 || tf1.getText().equals("")) {

						List<TodoVO> list = todoDao.listByPerson(vo.getUsrid());

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_num() + "\n" + list.get(i).getT_content() + "\n"
										+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getT_regdate()
										+ "\n");
								jtx1.append("------------------------------------\n");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------------\n");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------------\n");
							}
						}
					} else {
						String searchWord = tf1.getText();

						Map<String, String> map = new HashMap<>();

						map.put("usrid", vo.getUsrid());
						map.put("searchWord", searchWord);

						List<TodoVO> list = todoDao.listByPersonSearch(map);

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								row1.add(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								// jtx1.append("------------------------------------\n");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------------\n");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------------\n");
							}
						}
					}
				}

				if (rd2.isSelected()) {

					if (tf1.getText().length() == 0 || tf1.getText().equals("")) {

						List<TodoVO> list = todoDao.listByTeam(vo.getTeamnum());

						for (int i = 0; i < list.size(); i++) {

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");

								jtx1.append("------------------------------------\n");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------------\n");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------------\n");
							}
						}

					} else {
						String searchWord = tf1.getText();

						Map<String, String> map = new HashMap<>();

						map.put("teamnum", vo.getTeamnum());
						map.put("searchWord", searchWord);

						List<TodoVO> list = todoDao.listByTeamSearch(map);

						for (int i = 0; i < list.size(); i++) {

							System.out.println("2번");

							if (list.get(i).getT_state() == 0) {
								jtx1.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx1.append("------------------------------------\n");
							} else if (list.get(i).getT_state() == 1) {
								jtx2.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx2.append("------------------------------------\n");
							} else {
								jtx3.append(list.get(i).getT_content() + "\n" + userDao.findById(list.get(i).getUsrid())
										+ "\n" + list.get(i).getT_regdate() + "\n");
								jtx3.append("------------------------------------\n");
							}
						}
					}
				}
			}
		});

		// 업무 번호 호출
		JLabel lb7 = new JLabel("업무 번호");
		lb7.setBounds(20, 470, 100, 30);
		c.add(lb7);

		JTextArea jtx4 = new JTextArea();
		JScrollPane jsp4 = new JScrollPane(jtx4);
		jsp4.setBounds(100, 470, 100, 30);
		c.add(jsp4);

		JButton btn5 = new JButton("정보 로드");
		btn5.setBounds(400, 470, 100, 30);
		c.add(btn5);

		JButton btn6 = new JButton("초기화");
		btn6.setBounds(530, 470, 100, 30);
		c.add(btn6);

		// 삽입 : 할 일, 담당자, 현재 상태
		// 할 일 입력 테스트
		JLabel lb4 = new JLabel("할 일");
		lb4.setBounds(20, 520, 100, 30);
		c.add(lb4);

		JTextField tf2 = new JTextField();
		tf2.setBounds(100, 520, 530, 30);
		c.add(tf2);

		JLabel lb5 = new JLabel("담당자");
		lb5.setBounds(20, 570, 100, 30);
		c.add(lb5);

		for (int i = 0; i < memberList.size(); i++) {
			memVector.add(memberList.get(i).getUsrname());
		}

		JComboBox<String> memCombo = new JComboBox<String>(memVector);

		// JList<String> memberNameList = new JList<String>(memVector);
		// JScrollPane jsp4 = new JScrollPane(memCombo);
		memCombo.setBounds(100, 570, 100, 30);
		c.add(memCombo);

		JLabel lb6 = new JLabel("상태");
		lb6.setBounds(20, 650, 100, 30);
		c.add(lb6);
		JRadioButton rd3 = new JRadioButton("진행전");
		JRadioButton rd4 = new JRadioButton("진행중");
		JRadioButton rd5 = new JRadioButton("완료");

		// 1번 라디오 버튼 눌려져 있도록
		rd3.setSelected(true);

		// 라디오 버튼을 그룹화하기 위한 객체 생성
		ButtonGroup groupRd1 = new ButtonGroup();

		groupRd1.add(rd3);
		groupRd1.add(rd4);
		groupRd1.add(rd5);

		rd3.setBounds(100, 650, 70, 30);
		rd4.setBounds(200, 650, 70, 30);
		rd5.setBounds(300, 650, 100, 30);

		c.add(rd3);
		c.add(rd4);
		c.add(rd5);

		JButton btn2 = new JButton("입력");
		btn2.setBounds(150, 700, 100, 30);
		c.add(btn2);

		JButton btn3 = new JButton("수정");
		btn3.setBounds(300, 700, 100, 30);
		c.add(btn3);

		JButton btn4 = new JButton("삭제");
		btn4.setBounds(450, 700, 100, 30);
		c.add(btn4);

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String todo = tf2.getText();

				String selectedMem = memCombo.getSelectedItem().toString();

				// 이름으로 user객체 찾기
				UserVO user = userDao.findByName(selectedMem);
				String memId = user.getUsrid();
				String teamnum = user.getTeamnum();

				int state;

				if (rd3.isSelected()) {
					state = 0;
				} else if (rd4.isSelected()) {
					state = 1;
				} else {
					state = 2;
				}

				int InsertResult = todoDao.insertTodo(new TodoVO(todo, memId, teamnum, state));

				tf2.setText("");
				rd3.setSelected(true);

				if (InsertResult == 1) {
					JOptionPane.showMessageDialog(null, "할 일이 등록되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		// 정보 로드
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String t_num = jtx4.getText();

				System.out.println(t_num);

				TodoVO todo = todoDao.findByTnum(t_num);

				String content = todo.getT_content();
				tf2.setText(content);

				int selectedState = todo.getT_state();
				if (selectedState == 0) {
					rd3.setSelected(true);
				} else if (selectedState == 1) {
					rd4.setSelected(true);
				} else {
					rd5.setSelected(true);
				}

			}
		});

		// 수정
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String content = tf2.getText();

				String selectedMem = memCombo.getSelectedItem().toString();
				UserVO user = userDao.findByName(selectedMem);
				String memId = user.getUsrid();

				int state;

				if (rd3.isSelected()) {
					state = 0;
				} else if (rd4.isSelected()) {
					state = 1;
				} else {
					state = 2;
				}

				//todo.setT_content(content);
				//todo.setT_state(state);
				//todo.setUsrid(memId);

				//int updateResult = todoDao.updateTodo(todo);

				/*
				if (updateResult == 1) {
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				}
				*/
			}
		});

		// 삭제
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String t_num = jtx4.getText();

				TodoVO todo = todoDao.findByTnum(t_num);

				if (todo == null) {

					JOptionPane.showMessageDialog(null, "해당 할 일이 존재하지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);

					return;
				}

				int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "일정 삭제", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION) {
					int deleteResult = todoDao.deleteTodo(t_num);

					if (deleteResult == 1) {
						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");

					} else {
						JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

}
