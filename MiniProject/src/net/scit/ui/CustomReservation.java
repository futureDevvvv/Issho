package net.scit.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.JTextField;

public class CustomReservation extends JPanel implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/*Font fnt = new Font("굴림체",Font.BOLD,14);
	
	JPanel changePane = new JPanel();
	JLabel startLbl = new JLabel("출발지");
		static String start[] = {"인천","런던","바르셀로나","발리","방콕","부산","시드니","싱가포르","서울/김포"
				,"제주","파리","호놀룰루"};
		static DefaultComboBoxModel<String> startModel = new DefaultComboBoxModel<String>(start);
		static JComboBox<String> startCombo = new JComboBox<String>(startModel);
	
	JLabel arriveLbl = new JLabel("도착지");
		static String arrive[] = {"런던","바르셀로나","발리","방콕","부산","시드니","싱가포르","서울/김포","인천"
				,"제주","파리","호놀룰루"};
		static DefaultComboBoxModel<String> arriveModel = new DefaultComboBoxModel<String>(arrive);
		static JComboBox<String> arriveCombo = new JComboBox<String>(arriveModel);

	JLabel startDateLbl = new JLabel("출발 날짜");
		static JTextField startDateField = new JTextField();
		ImageIcon icon = new ImageIcon("img/calendar.png");
		Image im = icon.getImage();
		Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(im2);
		JLabel startCalendar = new JLabel(icon2);
	JLabel arriveDateLbl = new JLabel("도착 날짜");
		static JTextField arriveDateField = new JTextField();
	JLabel roundDateLbl = new JLabel("종류");
		JRadioButton roundBtn = new JRadioButton("왕복");
		JRadioButton onewayBtn = new JRadioButton("편도");	
		ButtonGroup groupRd = new ButtonGroup();
		static String rdb ="왕복"; // 라디오버튼 눌린게 무엇인지 구별
		
	JLabel humanLbl = new JLabel("인원수");
		JLabel audultLbl = new JLabel("어른");
		static Integer audult[] = {1,2,3,4,5};
		static DefaultComboBoxModel<Integer> adultModel = new DefaultComboBoxModel<Integer>(audult);
		static JComboBox<Integer> audultAge = new JComboBox<Integer>(adultModel);
		JLabel babyLbl = new JLabel("소아");
		static Integer baby[] = {0,1,2,3,4,5};
		static DefaultComboBoxModel<Integer> babyModel = new DefaultComboBoxModel<Integer>(baby);
		static JComboBox<Integer> babyAge = new JComboBox<Integer>(babyModel);
		
		
	
	JButton cancelBtn = new JButton("예약취소");
	JButton nextBtn = new JButton("다음단계");
	
	int calendarWindowTest = 0; // calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int clickCheck = 0; // 달력에서 클릭한 횟수를 통해 출발날짜, 도착날짜가 체크되는 순서를 절한다.
	static int humanCount; // 사람 명수
	public CustomReservation() {
		setLayout(new BorderLayout());
		
		
		
		// 전환 페이지
		add("Center",changePane);
		changePane.setLayout(null);
		changePane.setBackground(Color.white);
		int x=300;
		int x1=380;
		changePane.add(startLbl).setBounds(x,150,70,30); changePane.add(startCombo).setBounds(x1,150,150,30); 
		startLbl.setFont(fnt);
		startCombo.setFont(fnt);
		startCombo.setBackground(Color.white);
		
		changePane.add(arriveLbl).setBounds(x,200,70,30); changePane.add(arriveCombo).setBounds(x1,200,150,30); 
		arriveLbl.setFont(fnt);
		arriveCombo.setFont(fnt);
		arriveCombo.setBackground(Color.white);
		
		changePane.add(startDateLbl).setBounds(x,250,70,30); changePane.add(startDateField).setBounds(x1, 250, 150, 30); changePane.add(startCalendar).setBounds(550,250,30,30);
		startDateLbl.setFont(fnt);
		startDateField.setFont(fnt);
		
		changePane.add(arriveDateLbl).setBounds(x,300,70,30); changePane.add(arriveDateField).setBounds(x1,300,150,30); 
		arriveDateLbl.setFont(fnt);
		arriveDateField.setFont(fnt);
		
		changePane.add(roundDateLbl).setBounds(x,350,70,30); changePane.add(roundBtn).setBounds(x1,350,70,30); changePane.add(onewayBtn).setBounds(520,350,70,30);
		roundDateLbl.setFont(fnt);
		roundBtn.setSelected(true);
		roundBtn.setBackground(Color.white);
		onewayBtn.setBackground(Color.white);
		// 라디오 버튼 그룹화
		groupRd.add(roundBtn);
		groupRd.add(onewayBtn);
		
		changePane.add(humanLbl).setBounds(x,400,70,30); changePane.add(audultLbl).setBounds(x1,400,50,30); changePane.add(audultAge).setBounds(450,400,70,30); changePane.add(babyLbl).setBounds(550,400,50,30);
		changePane.add(babyAge).setBounds(610,400,70,30);
		audultAge.setBackground(Color.white);
		babyAge.setBackground(Color.white);
		humanLbl.setFont(fnt);
		audultLbl.setFont(fnt);
		audultAge.setFont(fnt);
		babyLbl.setFont(fnt);
		babyAge.setFont(fnt);
		
		changePane.add(cancelBtn).setBounds(400,500,100,30); changePane.add(nextBtn).setBounds(530,500,100,30); 
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(0,130,255));
		cancelBtn.setForeground(Color.white);
		nextBtn.setFont(fnt);
		nextBtn.setBackground(new Color(0,130,255));
		nextBtn.setForeground(Color.white);
			
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		startCalendar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
		roundBtn.addActionListener(this);
		onewayBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			
			
			if(btn.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("다음단계")) {
				if(startCombo.getSelectedItem().equals(arriveCombo.getSelectedItem())) {
					JOptionPane.showMessageDialog(this, "동일 지역이 선택 될 수 없습니다");
				} else if(startDateField.getText().equals("") || arriveDateField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "날짜를 선택해 주시기바랍니다");
				} else if(startDateField.getText().equals(arriveDateField.getText())) {
					JOptionPane.showMessageDialog(this, "같은 날짜 선택은 불가능합니다.");
				} else if(errorCheck() ==1) {
					JOptionPane.showMessageDialog(this, "당일보다 이전일은 항공일정이 없습니다");
				} else if(dayMinusCheck()==1) {
					JOptionPane.showMessageDialog(this, "출발 날짜보다 도착 날짜가 이전일 수는 없습니다");
				} else if(humanCount() == 1){
					JOptionPane.showMessageDialog(this, "본인을 포함, 5명을 초과하여 예약 할 수 없습니다 \n단체 예약을 원 할경우 관리자에게 문의하시기 바랍니다.");
				}else {
					this.setVisible(false);
					CustomFrame.reservation2.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.reservation2);
				}
			}
		} else if(obj instanceof JRadioButton) {
			rdb = (String)ae.getActionCommand();
		}
	}
	public int humanCount() {
		int result = 0;
		humanCount = (int) audultAge.getSelectedItem() +  (int)babyAge.getSelectedItem();
		if(humanCount >5) result = 1;
		
		return result;
	}
	
	
	/// 출발 날짜가 도착 날짜보다 뒤로 설정해보는 엉뚱한 사람을 체크해라!
	public int dayMinusCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/", ""));
		int arrive = Integer.valueOf(arriveDateField.getText().replace("/", ""));
		int minusCheck = arrive-start;
		if(minusCheck<0) {
			result = 1;
		}
		return result;
	}
	// 출발날짜 선택을 당일보다 전일로 설정 할 경우 걸러낸다
	public int errorCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/",""));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		int fmt = Integer.valueOf(format.format(date));
		int dateCheck = fmt - start;
		if(dateCheck > 0){
			result = 1;
		}
		
		return result;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////            달                   력      ////////////////////////////////
	class CustomCalendar extends JFrame implements ActionListener, WindowListener{
		// 상단 지역
		JPanel bar = new JPanel();
			JButton lastMonth = new JButton("◀");
		
			JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
			
			JLabel yLbl = new JLabel("년 ");
			
			JComboBox<Integer> monthCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
			JLabel mLbl = new JLabel("월");
			JButton nextMonth = new JButton("▶");
		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
			// 중앙 상단 지역
			JPanel cntNorth = new JPanel(new GridLayout(0,7));
			// 중앙 중앙 지역
			JPanel cntCenter = new JPanel(new GridLayout(0,7));
		
		// 요일 입력
		String dw[] = {"일","월","화","수","목","금","토"};
		
		
		Calendar now = Calendar.getInstance();
		int year, month, date;
		
		public CustomCalendar() {
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH)+1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for(int i=year; i<=year+50; i++){yearModel.addElement(i);}
			for(int i=1; i<=12; i++) { monthModel.addElement(i); }
			//////////////////////////프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
				bar.setLayout(new FlowLayout());
				bar.setSize(300,400);
				bar.add(lastMonth);
				//////////////////////////달력/////////////////////////////////////////////
				bar.add(yearCombo);
					yearCombo.setModel(yearModel);
					yearCombo.setSelectedItem(year);

				bar.add(yLbl);
				bar.add(monthCombo);
					monthCombo.setModel(monthModel);
					monthCombo.setSelectedItem(month);

				bar.add(mLbl);
				bar.add(nextMonth);
				bar.setBackground(new Color(0,210,180));

			// 중앙 지역
			add("Center", center);
				// 중앙 상단 지역
				center.add("North",cntNorth);
				for(int i=0; i<dw.length; i++) {
					JLabel dayOfWeek = new JLabel(dw[i],JLabel.CENTER);
					if(i==0) dayOfWeek.setForeground(Color.red);
					else if(i==6) dayOfWeek.setForeground(Color.blue);
					cntNorth.add(dayOfWeek);
				}

				// 중앙 중앙 지역
				center.add("Center",cntCenter);
				dayPrint(year,month);
				
				
			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);
			
			// frame 기본 셋팅
			setSize(400,300);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}	


		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton){
				JButton eventBtn = (JButton)obj;
				int yy = (Integer)yearCombo.getSelectedItem();
				int mm = (Integer)monthCombo.getSelectedItem();
				if(eventBtn.equals(lastMonth)){	//전달
					if(mm == 1 && yy == year ) {
					} else if(mm == 1){
						yy--; mm = 12;
					} else {
						mm--;
					}
				}else if(eventBtn.equals(nextMonth)){	//다음달
					if(mm == 12){
						yy++; mm = 1;
					}else{
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}else if(obj instanceof JComboBox){	//콤보박스 이벤트 발생시
				createDayStart();
			}
		}


		private void createDayStart() {
			cntCenter.setVisible(false);	//패널 숨기기
			cntCenter.removeAll();	//날짜 출력한 라벨 지우기
			dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			cntCenter.setVisible(true);	//패널 재출력	
		}


		// 날짜 출력
		public void dayPrint(int y,int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m-1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for(int i =1; i<week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}
			
			for(int i =0;i<=lastDate-1;i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if((week+i)%7==0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1+i+"");
				} else if((week+i)%7==1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1+i+"");
				} else {
					cntCenter.add(day);
					day.setText(1+i+"");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel)me.getSource();
						String str= mouseClick.getText();
						String y = ""+yearCombo.getSelectedItem();
						String m = ""+monthCombo.getSelectedItem();
						
						// 받은 "요일"이 1자리면 0을 붙여라
						if(str.equals("")) ;
						else if(str.length()==1) str = "0"+str; 
						
						// 받은 "월"이 1자리면 0을 붙여라
						if(m.length()==1) m = "0"+m;
						
						if(clickCheck==0) {
							startDateField.setText(y+"/"+m+"/"+str);
							startDateField.setEnabled(false);
							clickCheck++;
						} else if(clickCheck==1) {
							arriveDateField.setText(y+"/"+m+"/"+str);
							arriveDateField.setEnabled(false);
							clickCheck--;
						}
					}
				});
			}
			

			
		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}
		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}	
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}*/
}