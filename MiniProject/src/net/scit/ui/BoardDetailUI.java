package net.scit.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import net.scit.dao.BlikeDAO;
import net.scit.dao.BoardDAO;
import net.scit.dao.ReplyDAO;
import net.scit.dao.UserDAO;
import net.scit.vo.BlikeVO;
import net.scit.vo.BoardVO;
import net.scit.vo.ReplyVO;
import net.scit.vo.UserVO;

public class BoardDetailUI extends JFrame {

	ImageIcon l = new ImageIcon("love.png");
	Image like = l.getImage();
	Image likePush = like.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
	ImageIcon likePushIcon = new ImageIcon(likePush);
	
	ImageIcon c = new ImageIcon("heart.png");
	Image cancel = c.getImage();
	Image cancelLike = cancel.getScaledInstance(45, 45, Image.SCALE_DEFAULT);
	ImageIcon cancelLikeIcon = new ImageIcon(cancelLike);
	
	UserDAO userDao = new UserDAO();
	BoardDAO boardDao = new BoardDAO();
	ReplyDAO replyDao = new ReplyDAO();
	BlikeDAO blikeDao = new BlikeDAO();

	public BoardDetailUI(UserVO vo, String b_num) {

		Font forContent = new Font("KoPubWorld돋움체 Light", Font.PLAIN, 15);
		Font forReply = new Font("KoPubWorld돋움체 Light", Font.BOLD, 25);
		
		int likeCountNum = blikeDao.likeCount(b_num);
		String likeCountString = Integer.toString(likeCountNum);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("b_num", b_num);
		map.put("usrid", vo.getUsrid());

		

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

		JLabel main = new JLabel(teamname + "  팀원 게시판");
		main.setFont(forTitle);
		main.setBounds(20, 10, 250, 30);
		c.add(main);

		BoardVO board = boardDao.readBoard(b_num);

		List<ReplyVO> replyList = replyDao.listReply(b_num);

		JLabel title = new JLabel(board.getB_title());
		title.setFont(forContent);
		title.setBounds(120, 130, 300, 50);
		c.add(title);

		JLabel writer = new JLabel("작성자   " + userDao.findById(board.getUsrid()).getUsrname());
		// writer.setFont(forElse);
		writer.setBounds(120, 180, 150, 30);
		c.add(writer);

		JLabel regdate = new JLabel("작성일   " + board.getB_regdate());
		// regdate.setFont(forElse);
		regdate.setBounds(230, 180, 180, 30);
		c.add(regdate);

		JLabel hitcount = new JLabel("조회수   " + board.getB_hitcount());
		// regdate.setFont(forElse);
		hitcount.setBounds(450, 180, 180, 30);
		c.add(hitcount);

		JTextArea content = new JTextArea();
		content.setFont(forContent);
		content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane jsp1 = new JScrollPane(content);
		jsp1.setBounds(100, 220, 500, 180);
		c.add(jsp1);

		content.append(board.getB_content());

		if (vo.getUsrid().equals(board.getUsrid())) {
			// 본인이 작성자인 경우 : 수정, 삭제 버튼
			JButton update = new JButton("수정");
			update.setBounds(370, 420, 100, 30);
			c.add(update);

			JButton delete = new JButton("삭제");
			delete.setBounds(500, 420, 100, 30);
			c.add(delete);

			update.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new BoardUpdateUI(vo, b_num);
					setVisible(false);
				}
			});

			delete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					int answer = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "일정 삭제",
							JOptionPane.YES_NO_OPTION);

					if (answer == JOptionPane.YES_OPTION) {
						int deleteResult = boardDao.deletBoard(b_num);

						if (deleteResult == 1) {
							JOptionPane.showMessageDialog(null, "삭제가 완료되었습니다.");
							new BoardAllUI(vo);
							setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "오류가 발생했습니다.", "에러", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});

		} else {
			// 본인이 작성자가 아닐 경우 : 추천 버튼
			
			BlikeVO blike = blikeDao.ifLike(map);
			
			if (blike == null) {
				JButton likeit = new JButton(likePushIcon);
				likeit.setBorderPainted(false);
				likeit.setContentAreaFilled(false);
				likeit.setFocusPainted(false);
				likeit.setBounds(100, 410, 50, 50);
				c.add(likeit);
				
				JLabel likeCount = new JLabel("추천수 " + likeCountString);
				likeCount.setBounds(150, 420, 100, 30);
				likeCount.setFont(forContent);
				c.add(likeCount);

				likeit.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						blikeDao.Up(new BlikeVO(b_num, vo.getUsrid()));
						
						JOptionPane.showMessageDialog(null, "게시글을 추천했습니다");
						
						new BoardDetailUI(vo, b_num);
						setVisible(false);

					}
				});
			} else {

				JButton cancelLike = new JButton(cancelLikeIcon);
				cancelLike.setBorderPainted(false);
				cancelLike.setContentAreaFilled(false);
				cancelLike.setFocusPainted(false);
				cancelLike.setBounds(100, 410, 50, 50);
				c.add(cancelLike);
				
				JLabel likeCount = new JLabel("추천수 " + likeCountString);
				likeCount.setBounds(150, 420, 100, 30);
				likeCount.setFont(forContent);
				c.add(likeCount);

				cancelLike.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						blikeDao.deleteLike(map);
						
						JOptionPane.showMessageDialog(null, "게시글 추천을 취소했습니다");
						
						new BoardDetailUI(vo, b_num);
						setVisible(false);

					}
				});

			}
		}

		JLabel reply = new JLabel("댓글");
		reply.setBounds(100, 460, 100, 30);
		reply.setFont(forContent);
		c.add(reply);

		JTable table = new JTable();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 490, 500, 200);
		c.add(scrollPane);

		String[] colNames = new String[] { "댓글번호", "내용", "작성자" };
		Object[][] rowDatas = new Object[replyList.size()][colNames.length];

		for (int i = 0; i < replyList.size(); i++) {
			rowDatas[i] = new Object[] { replyList.get(i).getR_num(), replyList.get(i).getR_content(),
					userDao.findById(replyList.get(i).getUsrid()) };
		}

		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

		table.setRowHeight(25);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(550);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);

		scrollPane.setViewportView(table);

		// 댓글 수정 및 삭제
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int rowNum = table.getSelectedRow();

				// 본인이 쓴 댓글인지 여부 확인
				if (replyList.get(rowNum).getUsrid().equals(vo.getUsrid())) {

					new ReplyUpdateUI(vo, replyList.get(rowNum).getR_num());
					
				}
			}

		});

		// 댓글 입력창
		JTextField replyInput = new JTextField();
		replyInput.setBounds(100, 700, 380, 30);
		c.add(replyInput);

		JButton replyEnter = new JButton("입력");
		replyEnter.setBounds(500, 700, 100, 30);
		c.add(replyEnter);

		replyEnter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String replyContent = replyInput.getText();

				if (replyContent.length() == 0 || replyContent.equals("")) {
					JOptionPane.showMessageDialog(null, "내용이 입력되지 않았습니다.");
					return;
				}

				replyDao.writeReply(new ReplyVO(b_num, replyContent, vo.getUsrid()));
				new BoardDetailUI(vo, b_num);
			}
		});

		// 본문 테두리
		JPanel boardBorder = new JPanel();
		boardBorder.setBorder(new LineBorder(Color.DARK_GRAY, 1));
		boardBorder.setBounds(100, 100, 500, 300);
		c.add(boardBorder);

		JButton backToList = new JButton("<< 목록");
		backToList.setBounds(20, 750, 100, 30);
		c.add(backToList);

		backToList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new BoardAllUI(vo);
				setVisible(false);
			}
		});

		// 윈도우 창 크기 설정
		setSize(700, 900);

		// 윈도우 창 나타나게.
		setVisible(true);
	}
}
