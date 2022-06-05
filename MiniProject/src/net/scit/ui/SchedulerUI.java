package net.scit.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import net.scit.dao.ScheduleDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.ScheduleVO;
import net.scit.vo.UserVO;

public class SchedulerUI extends JFrame {

	UserDAO userDao = new UserDAO();
	ScheduleDAO schDao = new ScheduleDAO();

	int calendarWindowTest = 0; // calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int clickCheck = 0;
	int dayClicked = 0;
	int msg = 0;

	JTextField startField = new JTextField();
	JTextField endField = new JTextField();
	JTextField forDay = new JTextField();

	Container c = getContentPane();

	ImageIcon icon = new ImageIcon("calendar.png");
	Image im = icon.getImage();
	Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon icon2 = new ImageIcon(im2);

	JButton btn5 = new JButton("정보 로드");
	JButton btn6 = new JButton("초기화");

	JPanel panelForDay = new JPanel(new GridLayout(0, 7));

	// 윈도우캘린더
	JPanel ceNorth = new JPanel();
	JPanel forDayWindow = new JPanel(new GridLayout(0, 7));
	JPanel forDateWindow = new JPanel(new GridLayout(0, 7));
	JPanel calendarWindow = new JPanel(new BorderLayout());

	JTextArea selectResult = new JTextArea();

	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();

	JComboBox<Integer> yearComboWindow = new JComboBox<Integer>();
	JComboBox<Integer> monthComboWindow = new JComboBox<Integer>();

	public SchedulerUI(UserVO vo) {

		// CalendarWindow panel setting
		calendarWindow.setBounds(380, 370, 270, 270);
		calendarWindow.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		calendarWindow.setBackground(Color.white);
		forDayWindow.setBackground(Color.white);
		forDateWindow.setBackground(Color.white);
		c.add(calendarWindow);

		ceNorth.setBounds(380, 340, 270, 30);
		ceNorth.setBackground(Color.DARK_GRAY);
		c.add(ceNorth);

		JButton lastMonthWindow = new JButton("◀");
		ceNorth.add(lastMonthWindow);

		DefaultComboBoxModel<Integer> yearModelWindow = new DefaultComboBoxModel<Integer>();
		yearComboWindow.setBounds(380, 450, 50, 50);
		ceNorth.add(yearComboWindow);

		JLabel yLblWindow = new JLabel("년 ");
		yLblWindow.setForeground(Color.white);
		ceNorth.add(yLblWindow);

		DefaultComboBoxModel<Integer> monthModelWindow = new DefaultComboBoxModel<Integer>();
		ceNorth.add(monthComboWindow);

		JLabel mLblWindow = new JLabel("월");
		mLblWindow.setForeground(Color.white);
		ceNorth.add(mLblWindow);

		JButton nextMonthWindow = new JButton("▶");
		ceNorth.add(nextMonthWindow);

		// 요일
		String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

		// 캘린더 객체
		Calendar now = Calendar.getInstance();
		int year, month, date;

		year = now.get(Calendar.YEAR);// 2021년
		month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
		date = now.get(Calendar.DATE);
		for (int i = year; i <= year + 50; i++) {
			yearModelWindow.addElement(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthModelWindow.addElement(i);
		}

		yearComboWindow.setModel(yearModelWindow);
		yearComboWindow.setSelectedItem(year);

		monthComboWindow.setModel(monthModelWindow);
		monthComboWindow.setSelectedItem(month);

		// 윈도우달력 버튼이벤트
		lastMonthWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int yy = (Integer) yearComboWindow.getSelectedItem();
				int mm = (Integer) monthComboWindow.getSelectedItem();

				if (mm == 1 && yy == year) {
				} else if (mm == 1) {
					yy--;
					mm = 12;
				} else {
					mm--;
				}

				yearComboWindow.setSelectedItem(yy);
				monthComboWindow.setSelectedItem(mm);

				createDayStart(1);
			}
		});

		nextMonthWindow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectResult.setText("");

				int yy = (Integer) yearComboWindow.getSelectedItem();
				int mm = (Integer) monthComboWindow.getSelectedItem();

				if (mm == 12) {
					yy++;
					mm = 1;
				} else {
					mm++;
				}

				yearComboWindow.setSelectedItem(yy);
				monthComboWindow.setSelectedItem(mm);

				createDayStart(1);
			}
		});

		// 요일 출력
		calendarWindow.add("North", forDayWindow);

		for (int i = 0; i < dw.length; i++) {
			JLabel dayOfWeekWindow = new JLabel(dw[i]);

			if (i == 0) {
				dayOfWeekWindow.setForeground(Color.red);
			} else if (i == 6) {
				dayOfWeekWindow.setForeground(Color.blue);
			}

			forDayWindow.add(dayOfWeekWindow);
		}

		calendarWindow.add("Center", forDateWindow);
		dayPrintWindow(year, month);

		// 서브캘린더 끝 & 로그인 정보

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

		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("굴림", Font.BOLD, 25);

		JLabel title = new JLabel("일정 관리   ");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

		JLabel info = new JLabel(teamname + " " + name + " 님");
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

		// 일자 받기
		forDay.setBounds(270, 40, 50, 30);
		// c.add(forDay);

		// 조회 버튼
		JButton select = new JButton("조회");
		select.setBounds(300, 50, 100, 30);
		c.add(select);

		// 달력
		JButton lastMonth = new JButton("◀");
		lastMonth.setBounds(20, 100, 50, 30);
		c.add(lastMonth);

		DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
		yearCombo.setBounds(90, 100, 80, 30);
		c.add(yearCombo);

		JLabel yLbl = new JLabel("년 ");
		yLbl.setBounds(180, 100, 50, 30);
		c.add(yLbl);

		DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
		monthCombo.setBounds(220, 100, 80, 30);
		c.add(monthCombo);

		JLabel mLbl = new JLabel("월");
		mLbl.setBounds(310, 100, 50, 30);
		c.add(mLbl);

		JButton nextMonth = new JButton("▶");
		nextMonth.setBounds(340, 100, 50, 30);
		c.add(nextMonth);

		for (int i = year; i <= year + 50; i++) {
			yearModel.addElement(i);
		}
		for (int i = 1; i <= 12; i++) {
			monthModel.addElement(i);
		}

		yearCombo.setModel(yearModel);
		yearCombo.setSelectedItem(year);

		monthCombo.setModel(monthModel);
		monthCombo.setSelectedItem(month);

		// 버튼 이벤트 처리
		lastMonth.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectResult.setText("");

				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();

				if (mm == 1 && yy == year) {
				} else if (mm == 1) {
					yy--;
					mm = 12;
				} else {
					mm--;
				}

				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);

				createDayStart(2);
			}
		});

		nextMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectResult.setText("");

				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();

				if (mm == 12) {
					yy++;
					mm = 1;
				} else {
					mm++;
				}

				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);

				createDayStart(2);
			}
		});

		JPanel dayOfWeekPanel = new JPanel();
		dayOfWeekPanel.setBackground(new Color(222, 222, 239));
		dayOfWeekPanel.setBounds(10, 90, 400, 50);
		c.add(dayOfWeekPanel);

		// 요일 출력
		for (int i = 0; i < dw.length; i++) {
			JLabel dayOfWeek = new JLabel(dw[i]);
			dayOfWeekPanel.add(dayOfWeek);

			if (i == 0) {
				dayOfWeek.setForeground(Color.red);
			} else if (i == 6) {
				dayOfWeek.setForeground(Color.blue);
			}

			dayOfWeek.setBounds(30 + i * 60, 150, 30, 40);
			c.add(dayOfWeek);
		}

		// 일자 출력
		int yy = (Integer) yearCombo.getSelectedItem();
		int mm = (Integer) monthCombo.getSelectedItem();

		panelForDay.setBounds(10, 150, 400, 300);
		panelForDay.setBackground(Color.white);
		c.add(panelForDay);

		dayPrint(yy, mm);

		// 셀렉트 결과 표출
		JScrollPane sJsp = new JScrollPane(selectResult);
		sJsp.setBounds(430, 150, 210, 300);
		c.add(sJsp);

		// C,U,D layout
		// 일정 번호 호출
		JLabel lb5 = new JLabel("일정 번호");
		lb5.setBounds(20, 480, 100, 30);
		c.add(lb5);

		JTextArea jtx4 = new JTextArea();
		JScrollPane jsp4 = new JScrollPane(jtx4);
		jsp4.setBounds(100, 480, 100, 30);
		c.add(jsp4);

		btn5.setBounds(400, 480, 100, 30);
		c.add(btn5);

		btn6.setBounds(530, 480, 100, 30);
		c.add(btn6);

		// 등록
		JLabel lb6 = new JLabel("일 정");
		lb6.setBounds(20, 530, 100, 30);
		c.add(lb6);

		JTextField tf2 = new JTextField();
		tf2.setBounds(100, 530, 530, 30);
		c.add(tf2);

		JLabel lb7 = new JLabel("담당자");
		lb7.setBounds(20, 580, 100, 30);
		c.add(lb7);

		for (int i = 0; i < memberList.size(); i++) {
			memVector.add(memberList.get(i).getUsrname());
		}

		JComboBox<String> memCombo = new JComboBox<String>(memVector);

		memCombo.setBounds(100, 580, 100, 30);
		c.add(memCombo);

		JLabel lb8 = new JLabel("시작일");
		lb8.setBounds(20, 650, 100, 30);
		c.add(lb8);

		startField.setBounds(100, 650, 150, 30);
		c.add(startField);

		JLabel lb9 = new JLabel("종료일");
		lb9.setBounds(300, 650, 100, 30);
		c.add(lb9);

		endField.setBounds(380, 650, 150, 30);
		c.add(endField);

		JButton btn1 = new JButton(icon2);
		btn1.setBorderPainted(false);
		btn1.setContentAreaFilled(false);
		btn1.setFocusPainted(false);
		btn1.setBounds(550, 650, 30, 30);
		c.add(btn1);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (calendarWindowTest == 0) {
					ceNorth.setVisible(true);
					calendarWindow.setVisible(true);
					btn5.setVisible(false);
					btn6.setVisible(false);
					calendarWindowTest = 1;
				} else {
					ceNorth.setVisible(false);
					calendarWindow.setVisible(false);
					btn5.setVisible(true);
					btn6.setVisible(true);
					calendarWindowTest = 0;
				}
			}
		});

		JButton btn2 = new JButton("입력");
		btn2.setBounds(150, 700, 100, 30);
		c.add(btn2);

		JButton btn3 = new JButton("수정");
		btn3.setBounds(300, 700, 100, 30);
		c.add(btn3);

		JButton btn4 = new JButton("삭제");
		btn4.setBounds(450, 700, 100, 30);
		c.add(btn4);

		// select 버튼 이벤트
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectResult.setText("");

				int yy = (Integer) yearCombo.getSelectedItem();
				int mm = (Integer) monthCombo.getSelectedItem();
				String dd = forDay.getText();

				String mmToStr = Integer.toString(mm);

				if (mm < 10) {
					mmToStr = "0" + mmToStr;
				}

				String yymm = Integer.toString(yy) + "/" + mmToStr;
				String yymmdd = Integer.toString(yy) + "/" + mmToStr + dd;

				Map<String, String> map = new HashMap<>();

				// 개인별 조회(연, 월)
				if (rd1.isSelected()) {

					if (dd.equals("") || dd.length() == 0) {
						map.put("usrid", vo.getUsrid());
						map.put("s_date", yymm);

						List<ScheduleVO> list = schDao.listByPersonMonth(map);

						for (int i = 0; i < list.size(); i++) {

							selectResult.append(list.get(i).getS_num() + " " + list.get(i).getS_content() + "\n"
									+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getStartdate()
									+ " ~ " + list.get(i).getEnddate() + "\n");
							selectResult.append("--------------------------------------------------\n");
						}
					} else {
						// 개인별 조회(연 일 월)

						map.put("usrid", vo.getUsrid());
						map.put("s_date", yymmdd);

						List<ScheduleVO> list = schDao.listByPersonDay(map);

						for (int i = 0; i < list.size(); i++) {

							selectResult.append(list.get(i).getS_num() + " " + list.get(i).getS_content() + "\n"
									+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getStartdate()
									+ " ~ " + list.get(i).getEnddate() + "\n");
							selectResult.append("--------------------------------------------------\n");
						}

					}

				} else {
					// 팀별 조회
					map.put("teamnum", teamnum);
					map.put("s_date", yymm);

					List<ScheduleVO> list = schDao.listByTeamMonth(map);

					for (int i = 0; i < list.size(); i++) {
						selectResult.append(list.get(i).getS_num() + " " + list.get(i).getS_content() + "\n"
								+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getStartdate() + " ~ "
								+ list.get(i).getEnddate() + "\n");
						selectResult.append("----------------------------------------------\n");
					}

				}

			}
		});

		// 정보 로드 버튼 이벤트
		btn5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String s_num = jtx4.getText();

				if (s_num.length() == 0 || s_num.equals("")) {

					return;
				}

				ScheduleVO sch = schDao.findByNum(s_num);

				if (sch == null) {
					JOptionPane.showMessageDialog(null, "해당 할 일이 존재하지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);

					return;
				}

				// 일정
				tf2.setText(sch.getS_content());

				// 시작일
				startField.setText(sch.getStartdate());

				// 종료일
				endField.setText(sch.getEnddate());

			}
		});

		// 초기화 버튼 이벤트
		btn6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtx4.setText("");
				tf2.setText("");
				startField.setText("");
				endField.setText("");
			}
		});

		// 입력 이벤트
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String content = tf2.getText();

				String selectedMem = memCombo.getSelectedItem().toString();

				// 이름으로 user객체 찾기
				UserVO user = userDao.findByName(selectedMem);
				String memId = user.getUsrid();
				String teamnum = user.getTeamnum();

				String startdate = startField.getText();
				String enddate = endField.getText();

				int insertResult = schDao.insertSchedule(new ScheduleVO(content, memId, startdate, enddate, teamnum));

				if (insertResult == 1) {
					JOptionPane.showMessageDialog(null, "일정이 등록되었습니다.");

				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				}

				jtx4.setText("");
				tf2.setText("");
				startField.setText("");
				endField.setText("");
			}
		});

		// 수정 이벤트
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String s_num = jtx4.getText();

				ScheduleVO sch = schDao.findByNum(s_num);

				String content = tf2.getText();

				String selectedMem = memCombo.getSelectedItem().toString();

				// 이름으로 user객체 찾기
				UserVO user = userDao.findByName(selectedMem);
				String memId = user.getUsrid();
				String teamnum = user.getTeamnum();

				String startdate = startField.getText();
				String enddate = endField.getText();

				sch.setS_content(content);
				sch.setUsrid(memId);
				sch.setTeamnum(teamnum);
				sch.setStartdate(startdate);
				sch.setEnddate(enddate);

				int updateResult = schDao.updateSchedule(sch);

				if (updateResult == 1) {
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
				}

				jtx4.setText("");
				tf2.setText("");
				startField.setText("");
				endField.setText("");
			}
		});

		// 삭제 이벤트
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s_num = jtx4.getText();

				if (s_num.length() == 0 || s_num.equals("")) {

					return;
				}

				ScheduleVO sch = schDao.findByNum(s_num);

				if (sch == null) {
					JOptionPane.showMessageDialog(null, "해당 일정이 존재하지 않습니다.", "에러", JOptionPane.ERROR_MESSAGE);

					return;
				}

				int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "일정 삭제", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION) {
					int deleteResult = schDao.deleteSchedule(s_num);

					if (deleteResult == 1) {
						JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");

					} else {
						JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
					}

					jtx4.setText("");
					tf2.setText("");
					startField.setText("");
					endField.setText("");
				}
			}
		});

		calendarWindow.setVisible(false);
		ceNorth.setVisible(false);

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}

	// 서브 캘린더 날짜 출력 메소드
	private void dayPrintWindow(int yy, int mm) {

		Calendar cal = Calendar.getInstance();
		cal.set(yy, mm - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일

		for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
			forDateWindow.add(new JLabel(""));
		}

		for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
			JLabel day = new JLabel();
			day.setHorizontalAlignment(JLabel.CENTER);
			if ((week + i) % 7 == 0) {
				forDateWindow.add(day).setForeground(Color.blue);

				day.setText(1 + i + "");
			} else if ((week + i) % 7 == 1) {
				forDateWindow.add(day).setForeground(Color.red);

				day.setText(1 + i + "");
			} else {
				forDateWindow.add(day);
				day.setText(1 + i + "");
			}

			day.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					JLabel mouseClick = (JLabel) me.getSource();
					String str = mouseClick.getText();
					String yy = "" + yearCombo.getSelectedItem();
					String mm = "" + monthCombo.getSelectedItem();

					// 받은 "요일"이 1자리면 0을 붙여라
					if (str.equals(""))
						;
					else if (str.length() == 1)
						str = "0" + str;

					// 받은 "월"이 1자리면 0을 붙여라
					if (mm.length() == 1)
						mm = "0" + mm;

					if (clickCheck == 0) {
						startField.setText(yy + "/" + mm + "/" + str);
						startField.setEnabled(false);
						clickCheck++;
					} else if (clickCheck == 1) {
						endField.setText(yy + "/" + mm + "/" + str);
						endField.setEnabled(false);
						clickCheck--;

						if (dayMinusCheck() == 1) {
							JOptionPane.showMessageDialog(null, "종료일이 시작일보다 이전일 수 없습니다.", "에러",
									JOptionPane.ERROR_MESSAGE);
							startField.setText("");
							endField.setText("");
						}

						calendarWindow.setVisible(false);
						ceNorth.setVisible(false);

						btn5.setVisible(true);
						btn6.setVisible(true);

					}
				}
			});
		}
	}

	// 달력 초기화
	public void createDayStart(int msg) {

		if (msg == 2) {
			panelForDay.setVisible(false);
			panelForDay.removeAll();
			dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
			panelForDay.setVisible(true);
		} else {
			forDateWindow.setVisible(false);
			forDateWindow.removeAll();
			dayPrintWindow((Integer) yearComboWindow.getSelectedItem(), (Integer) monthComboWindow.getSelectedItem());
			forDateWindow.setVisible(true);
		}
	}

	// 시작일, 종료일 체크
	public int dayMinusCheck() {
		int result = 0;
		int start = Integer.valueOf(startField.getText().replace("/", ""));
		int end = Integer.valueOf(endField.getText().replace("/", ""));
		int minusCheck = end - start;

		if (minusCheck < 0) {
			result = 1;
		}

		return result;
	}

	// 메인 캘린더 날짜 출력 메소드
	public void dayPrint(int y, int m) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, 1);
		int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
		int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일

		for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
			panelForDay.add(new JLabel(""));
		}

		for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
			JLabel day = new JLabel();
			day.setHorizontalAlignment(JLabel.CENTER);
			if ((week + i) % 7 == 0) {
				panelForDay.add(day).setForeground(Color.blue);

				day.setText(1 + i + "");
			} else if ((week + i) % 7 == 1) {
				panelForDay.add(day).setForeground(Color.red);

				day.setText(1 + i + "");
			} else {
				panelForDay.add(day);
				day.setText(1 + i + "");
			}

			day.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					JLabel mouseClick = (JLabel) me.getSource();
					String str = mouseClick.getText();

					// 받은 "요일"이 1자리면 0을 붙여라
					if (str.equals(""))
						;
					else if (str.length() == 1)
						str = "0" + str;

					if (dayClicked == 0) {
						day.setOpaque(true);
						day.setBackground(Color.YELLOW);

						dayClicked = 1;
					} else if (dayClicked == 1) {
						createDayStart(2);
						forDay.setText("");
						dayClicked = 0;
					}

					forDay.setText(str);
				}
			});
		}
	}
}
