package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import net.scit.dao.UserDAO;
import net.scit.vo.UserVO;

public class Login extends JFrame {

	public static void setUIFont(FontUIResource f) {
		Enumeration keys = UIManager.getDefaults().keys();

		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);

			if (value instanceof FontUIResource) {
				FontUIResource orig = (FontUIResource) value;
				Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
				UIManager.put(key, new FontUIResource(font));
			}
		}
	}

	private JPanel forIntro;
	private JPanel LoginBorder;
	private JTextField idField;
	private JTextField pwField;

	/**
	 * Create the frame.
	 */
	public Login() {

		setUIFont(new FontUIResource(new Font("KoPubWorld돋움체 Light", 0, 15)));

		UserDAO usrDao = new UserDAO();

		Container c = getContentPane();
		c.setLayout(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font = new Font("창원단감아삭 Bold", Font.PLAIN, 120);
		Font font2 = new Font("Yu Gothic", Font.BOLD, 40);
		Font font3 = new Font("한컴산뜻돋움", Font.PLAIN, 40);

		JLabel issho = new JLabel("issho");
		issho.setBounds(100, 100, 500, 200);
		issho.setFont(font);
		issho.setForeground(new Color(51, 102, 255));
		c.add(issho);

		JLabel japan = new JLabel("一緒に、一生懸命");
		japan.setBounds(100, 250, 500, 100);
		japan.setFont(font2);
		japan.setForeground(new Color(51, 102, 255));
		c.add(japan);

		/*
		 * 
		 * forIntro = new JPanel(); forIntro.setBackground(Color.white);
		 * forIntro.setBounds(30, 50, 600, 250); c.add(forIntro);
		 * 
		 */
		// contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// setContentPane(contentPane);
		// contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("프로그램 이름 공간");
		lblNewLabel.setBounds(60, 80, 400, 200);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("KoPubWorld돋움체 Light", Font.PLAIN, 30));
		// forIntro.add(lblNewLabel);

		JLabel idLabel = new JLabel("ID ");
		idLabel.setBounds(90, 420, 100, 30);
		// idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// idLabel.setFont(new Font("궁서", Font.PLAIN, 30));
		c.add(idLabel);

		idField = new JTextField();
		idField.setBounds(150, 420, 300, 30);
		// textField.setFont(new Font("궁서", Font.PLAIN, 30));
		c.add(idField);
		// textField.setColumns(10);

		JLabel pwLabel = new JLabel("PW ");
		pwLabel.setBounds(90, 470, 100, 30);
		// lblNewLabel_2.setFont(new Font("궁서", Font.PLAIN, 30));
		// lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		c.add(pwLabel);

		pwField = new JTextField();
		pwField.setBounds(150, 470, 300, 30);
		// pwField.setFont(new Font("궁서", Font.PLAIN, 30));
		// pwField.setColumns(10);
		c.add(pwField);

		JButton btnNewButton = new JButton("로그인");
		// btnNewButton.setFont(new Font("궁서", Font.PLAIN, 30));
		btnNewButton.setBounds(470, 420, 80, 80);
		c.add(btnNewButton);

		// 로그인 로직 처리
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = idField.getText();
				String pw = pwField.getText();

				// 아이디 입력 x
				if (id.length() == 0 || id.equals("")) {

					JOptionPane.showMessageDialog(null, "아이디를 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
					return;
					// 비밀번호 입력 x
				} else if (pw.length() == 0 || pw.equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "에러", JOptionPane.ERROR_MESSAGE);
					return;

				} else if (!usrDao.findById(id).getPw().equals(pw)) {
					JOptionPane.showMessageDialog(null, "아이디와 비밀번호가 일치하지 않습니다", "에러", JOptionPane.ERROR_MESSAGE);

					idField.setText("");
					pwField.setText("");

					return;
				} else {
					UserVO vo = usrDao.findById(id);
					new MenuUI(vo);
					setVisible(false);
				}
			}
		});

		LoginBorder = new JPanel();
		LoginBorder.setBorder(new TitledBorder(new LineBorder(Color.DARK_GRAY, 1), "회원 로그인"));
		LoginBorder.setBounds(50, 370, 530, 170);
		c.add(LoginBorder);

		/*
		 * 
		 * JLabel lbl1 = new JLabel("비밀번호를 잊어버리셨나요?"); lbl1.setBounds(150, 570, 200,
		 * 30); c.add(lbl1);
		 * 
		 * JButton btnNewButton_1 = new JButton("비밀번호 찾기"); //
		 * btnNewButton_1.setFont(new Font("궁서", Font.PLAIN, 30));
		 * btnNewButton_1.setBounds(350, 570, 150, 30); c.add(btnNewButton_1);
		 * 
		 */

		JLabel lbl2 = new JLabel("아직 회원이 아니신가요?");
		lbl2.setBounds(150, 570, 200, 30);
		c.add(lbl2);

		JButton btnNewButton_2 = new JButton("회원가입");
		// btnNewButton_2.setFont(new Font("궁서", Font.PLAIN, 30));
		btnNewButton_2.setBounds(350, 570, 150, 30);
		c.add(btnNewButton_2);

		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Join();
				setVisible(false);
			}
		});

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);

	}
}
