package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class Join extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	String departments[] = { "기획팀", "영업팀", "인사팀", "개발팀" };

	String usrid, usrname, pw, teamname, teamnum;
	int idCheck = 0;

	UserDAO usrDao = new UserDAO();

	/**
	 * Create the frame.
	 */
	public Join() {

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font join = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);
		JLabel lblNewLabel = new JLabel("신규 사원 등록");
		lblNewLabel.setFont(join);
		lblNewLabel.setBounds(270, 100, 300, 50);
		c.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("아이디");
		lblNewLabel_1.setBounds(180, 200, 100, 30);
		c.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(250, 200, 250, 30);
		c.add(textField);

		JButton btnNewButton = new JButton("아이디 중복 체크");
		btnNewButton.setBounds(180, 250, 180, 30);
		c.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				usrid = textField.getText();

				if (usrid.length() == 0 || usrid.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력하세요.", "에러", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (usrDao.findById(usrid) == null) {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "알림", JOptionPane.PLAIN_MESSAGE);
					idCheck = 1;
				} else {
					JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.", "알림", JOptionPane.ERROR_MESSAGE);
					idCheck = 2;
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("비밀번호 ");
		lblNewLabel_2.setBounds(180, 300, 100, 30);
		c.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(250, 300, 250, 30);
		c.add(textField_1);

		JLabel lblNewLabel_3 = new JLabel("이름 ");
		lblNewLabel_3.setBounds(180, 360, 100, 30);
		c.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setBounds(250, 360, 250, 30);
		c.add(textField_2);

		JLabel lblNewLabel_4 = new JLabel("팀 번호 ");
		lblNewLabel_4.setBounds(180, 420, 100, 30);
		c.add(lblNewLabel_4);

		JComboBox<String> combo = new JComboBox<String>(departments);
		combo.setBounds(250, 420, 100, 30);
		c.add(combo);

		JButton btnNewButton_1 = new JButton("등록");
		btnNewButton_1.setBounds(240, 550, 100, 30);
		c.add(btnNewButton_1);

		// 멤버 Insert 이벤트
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				usrid = textField.getText();
				pw = textField_1.getText();
				teamname = combo.getSelectedItem().toString();

				switch (teamname) {
				case "기획팀":
					teamnum = "10";
					break;

				case "영업팀":
					teamnum = "20";
					break;

				case "인사팀":
					teamnum = "30";
					break;

				case "개발팀":
					teamnum = "40";
					break;

				}

				usrname = textField_2.getText();

				if (usrid.length() == 0 || usrid.equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (pw.length() == 0 || usrid.equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (usrname.length() == 0 || usrid.equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요", "", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// 중복 체크 여부
				if (idCheck == 0) {
					JOptionPane.showMessageDialog(null, "아이디 중복 체크가 완료되지 않았습니다", "", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (idCheck == 2) {
					JOptionPane.showMessageDialog(null, "중복되는 아이디는 사용할 수 없습니다", "", JOptionPane.ERROR_MESSAGE);
					idCheck = 0;

					return;
				}

				int result = usrDao.joinBoard(new UserVO(usrid, pw, usrname, teamnum));

				if (result == 1) {
					JOptionPane.showMessageDialog(null, "성공적으로 등록되었습니다!", "알림", JOptionPane.PLAIN_MESSAGE);
					new Login();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton btnNewButton_2 = new JButton("뒤로 가기");
		btnNewButton_2.setBounds(370, 550, 100, 30);
		c.add(btnNewButton_2);

		// 첫 화면으로 이동
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				setVisible(false);
			}
		});

		JPanel joinBorder = new JPanel();
		joinBorder.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		joinBorder.setBounds(150, 150, 400, 450);
		c.add(joinBorder);

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}
}
