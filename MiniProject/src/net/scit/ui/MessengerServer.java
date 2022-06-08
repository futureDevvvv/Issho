package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class MessengerServer extends JFrame {

	int setComplete = 0;

	JTextField textField; // 입력창
	JTextArea textArea; // 채팅창
	JLabel lblStatus;

	private ServerSocket serverSocket;
	private Socket socket;

	private DataInputStream dis;
	private DataOutputStream dos;

	private boolean connectStatus; // 클라이언트 접속 여부
	private boolean stopSignal; // 스레드 종료 신호 저장

	UserDAO userDao = new UserDAO();
	List<UserVO> list = userDao.AllMemList();

	String myName = null;
	String memName = null;
	String memId = null;

	private String password;

	public MessengerServer(UserVO vo) {
		// UserVO 받기
		showFrame(vo); // gui 화면 출력

	}

	private void showFrame(UserVO vo) {

		myName = vo.getUsrname();
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

		List<UserVO> memList = userDao.userReply(vo);
		Vector<String> memVector = new Vector<String>();

		for (int i = 0; i < memList.size(); i++) {
			memVector.add(memList.get(i).getUsrid() + "    " + memList.get(i).getUsrname() + "\n");
		}

		setTitle("1:1 채팅 서버");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(400, 500, 500, 300);

		Container c = getContentPane();
		c.setLayout(null);

		Font forTitle = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);

		JLabel title = new JLabel("1:1 채팅 시작하기");
		title.setFont(forTitle);
		title.setBounds(20, 20, 200, 30);
		c.add(title);

		/*
		 * 
		 * JLabel memListLb = new JLabel("구성원 목록"); memListLb.setBounds(20, 20, 200,
		 * 30); c.add(memListLb);
		 * 
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 80, 470, 200);
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

		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);

		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.getSelectedRow();

				memId = list.get(rowNum).getUsrid();
				memName = list.get(rowNum).getUsrname();

				int answer = JOptionPane.showConfirmDialog(null, memName + "님과 1:1 채팅을 시작하겠습니까?", "일정 삭제",
						JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION) {

					JOptionPane.showMessageDialog(null, memName + " 님과 연결합니다 . . .");

					startService();
				}
			}

		});

		/*
		 * 
		 * JTextArea jtx1 = new JTextArea(); JScrollPane jsp1 = new JScrollPane(jtx1);
		 * jsp1.setBounds(20, 130, 200, 180); c.add(jsp1);
		 * 
		 * for (int i = 0; i < memVector.size(); i++) { jtx1.append(memVector.get(i)); }
		 * 
		 */

		/*
		 * 
		 * JLabel lb1 = new JLabel("팀원 아이디"); lb1.setBounds(250, 150, 100, 30);
		 * c.add(lb1);
		 * 
		 * JTextField tf1 = new JTextField(); tf1.setBounds(250, 190, 170, 30);
		 * c.add(tf1);
		 * 
		 * JButton btn1 = new JButton("1:1 채팅 시작"); btn1.setBounds(250, 230, 170, 30);
		 * c.add(btn1);
		 * 
		 */

		/*
		 * btn1.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * memId = tf1.getText();
		 * 
		 * if (memId.length() == 0 || memId.equals("")) {
		 * JOptionPane.showMessageDialog(null, "팀원 아이디가 입력되지 않았습니다.", "에러",
		 * JOptionPane.ERROR_MESSAGE);
		 * 
		 * return; } else {
		 * 
		 * memName = userDao.findById(memId).getUsrname();
		 * 
		 * JOptionPane.showMessageDialog(null, memName + " 님과의 채팅을 시도합니다.");
		 * 
		 * startService(); } } });
		 */
		textArea = new JTextArea();
		textArea.setEditable(false);
		c.add(textArea);

		textField = new JTextField();
		textField.setBounds(20, 670, 380, 31);
		c.add(textField);
		textField.setColumns(10);

		JButton btnInput = new JButton("입력");
		btnInput.setBounds(420, 670, 100, 31);
		c.add(btnInput);

		JPanel panel = new JPanel();
		panel.setBounds(0, 320, 484, 30);
		c.add(panel);

		lblStatus = new JLabel("상대방 : 연결 없음");
		panel.add(lblStatus);

		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(20, 350, 500, 300);
		c.add(jsp);
		jsp.setViewportView(textArea);

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});

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

		setSize(700, 900);
		setVisible(true);

		textField.requestFocus();

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

	private void startService() {
		try {
			serverSocket = new ServerSocket(7777);

			System.out.println("서비스 준비 완료");

			password = memId;

			// client로부터 접속이 성공할 때까지 무한 대기
			connectStatus = false;
			while (!connectStatus) {
				// serverSocket 객체의 accept() 메서드를 호출하여 연결 대기
				// 연결 완료 시 socket 객체 리턴
				socket = serverSocket.accept();
				textArea.append(memName + " 님이 접속하였습니다. (" + socket.getInetAddress() + ")\n");

				dis = new DataInputStream(socket.getInputStream());

				String clientPass = dis.readUTF();

				dos = new DataOutputStream(socket.getOutputStream());

				// 입력받은 패스워드와 저장된 패스워드 비교
				if (!clientPass.equals(password)) {// 일치하지 않는 경우
					dos.writeBoolean(false);
					textArea.append("사용자 불일치로 접속 해제됨\n");
				} else {
					connectStatus = true;
					textArea.append("사용자 일치 확인 완료\n\n\n\n");
					lblStatus.setText("상대방 연결 상태 : 연결됨");
					dos.writeBoolean(true);
				}

				// 메시지를 실시간으로 주고 받기 위한 Runnable - Thread 객체 전달. start() 메서드 호출 + 쓰레드 실행
				new Thread(new Runnable() {

					@Override
					public void run() {
						receiveMessage();
					}

				}).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessage() {
		try {
			String text = textField.getText();
			textArea.append(text + "\n");

			if (text.equals("/exit")) {
				// 종료 시그널 전송 시
				stopSignal = true;

				dos.close();
				socket.close();

				// 프로그램 종료
				System.exit(0);
			} else {
				// 그 외의 경우 client에게 메시지 전송
				dos.writeUTF(myName + " : " + text);

				// 초기화,커서 포커스
				textField.setText("");
				textField.requestFocus();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receiveMessage() {
		// stopSignal이 false일 때까지 반복하여 메시지 수신

		try {
			while (!stopSignal) {
				textArea.append(memName + " : " + dis.readUTF() + "\n");
			}

			// stopSignal이 수신되면 소켓 정리 후 종료.
			dis.close();
			socket.close();
		} catch (EOFException e) {
			// 상대방의 접속 해제 시 소켓 제거
			textArea.append("상대방 접속이 해제되었습니다.\n");
			lblStatus.setText("상대방 연결 상태 : 미연결");
			connectStatus = false;

		} catch (SocketException e) {
			textArea.append("서버 접속이 해제되었습니다.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) { new MessengerServer(); }
	 */
}
