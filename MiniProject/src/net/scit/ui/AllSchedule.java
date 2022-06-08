package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.scit.dao.ScheduleDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.ScheduleVO;
import net.scit.vo.UserVO;

public class AllSchedule extends JFrame {
	UserDAO userDao = new UserDAO();
	ScheduleDAO schDao = new ScheduleDAO();

	List<ScheduleVO> list = schDao.AllSchList();

	int calendarWindowTest = 0; // calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int clickCheck = 0;
	int dayClicked = 0;
	int msg = 0;

	JTextField startField = new JTextField();
	JTextField endField = new JTextField();
	JTextField forDay = new JTextField();

	Container c = getContentPane();

	JPanel panelForDay = new JPanel(new GridLayout(0, 7));

	JTextArea selectResult = new JTextArea();

	JComboBox<Integer> yearCombo = new JComboBox<Integer>();
	JComboBox<Integer> monthCombo = new JComboBox<Integer>();

	public AllSchedule(UserVO vo) {
		// 요일
		String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

		// 캘린더 객체
		Calendar now = Calendar.getInstance();
		int year, month, date;

		year = now.get(Calendar.YEAR);// 2021년
		month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
		date = now.get(Calendar.DATE);

		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel title = new JLabel("일정 관리   ");
		title.setFont(forTitle);
		title.setBounds(20, 10, 150, 30);
		c.add(title);

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
		
		JScrollPane sJsp = new JScrollPane(selectResult);
		sJsp.setBounds(430, 150, 210, 300);
		c.add(sJsp);

		for (int i = 0; i < list.size(); i++) {
			selectResult.append(list.get(i).getS_num() + " " + list.get(i).getS_content() + "\n"
					+ userDao.findById(list.get(i).getUsrid()) + "\n" + list.get(i).getStartdate() + " ~ "
					+ list.get(i).getEnddate() + "\n");
			selectResult.append("--------------------------------------------------\n");
		}
		//c.add(selectResult);

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

				createDayStart();
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

				createDayStart();
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

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

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
						createDayStart();
						forDay.setText("");
						dayClicked = 0;
					}

					forDay.setText(str);
				}
			});
		}
	}

	public void createDayStart() {

		panelForDay.setVisible(false);
		panelForDay.removeAll();
		dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
		panelForDay.setVisible(true);

	}

}
