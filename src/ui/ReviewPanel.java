package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.*;

import dao.QuestionDao;

public class ReviewPanel extends JPanel {

	private int qnaIdx = -1;
	private int lastQnaIdx = 0;
	private boolean showAnswer = false;
	private boolean isFinishied = false;

	public ReviewPanel(ActionListener onBack) {
		// TODO : 여기에 작성하는 것이 맞는가?
		QuestionDao questionDao = new QuestionDao();
		String[][] qnaList = questionDao.getTodayTotalQuestionDetail();
		lastQnaIdx = questionDao.getTodayTotalQuestionCnt();

		setLayout(new BorderLayout());

		// Topbar
		JPanel topbar = new JPanel(new BorderLayout());
		JButton backBtn = new JButton("<-");
		backBtn.setPreferredSize(new Dimension(50, 20));
		backBtn.addActionListener(onBack);
		topbar.add(backBtn, BorderLayout.WEST);

		JLabel title = new JLabel("DongKi", SwingConstants.CENTER);
		title.setPreferredSize(new Dimension(500, 20));
		topbar.add(title, BorderLayout.CENTER);
		add(topbar, BorderLayout.NORTH);

		// Content
		var formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel question = new JLabel("환영합니다. O 혹은 X 를 눌러주세요.");
		question.setAlignmentX(Component.CENTER_ALIGNMENT);
		formPanel.add(question);

		JLabel answer = new JLabel("");
		answer.setAlignmentX(Component.CENTER_ALIGNMENT);
		answer.setVisible(false);
		formPanel.add(answer);

		// 정답보기 버튼
		JButton answerBtn = new JButton("정답보기");
		answerBtn.setVisible(false);

		answerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				answer.setText(qnaList[qnaIdx][1]);

				if (showAnswer == false) {
					showAnswer = true;
				} else {
					showAnswer = false;
				}
				answer.setVisible(showAnswer);
			}
		});

		// O 버튼
		JButton rightBtn = new JButton("O");

		// X 버튼
		JButton wrongBtn = new JButton("X");

		rightBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerBtn.setVisible(true);
				if (qnaIdx == lastQnaIdx - 1) {
					isFinishied = true;
				}

				if (isFinishied) {
					rightBtn.setEnabled(false);
					wrongBtn.setEnabled(false);
					question.setText("오늘의 복습이 끝났습니다.");
					answerBtn.setEnabled(false);
				}

				++qnaIdx;
				showAnswer = false;
				answer.setVisible(showAnswer);
				question.setText(qnaList[qnaIdx][0]);
			}
		});


		wrongBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				answerBtn.setVisible(true);
				if (qnaIdx == lastQnaIdx - 1) {
					isFinishied = true;
				}

				if (isFinishied) {
					rightBtn.setEnabled(false);
					wrongBtn.setEnabled(false);
					question.setText("오늘의 복습이 끝났습니다.");
					answerBtn.setEnabled(false);
				}

				++qnaIdx;
				showAnswer = false;
				answer.setVisible(showAnswer);
				question.setText(qnaList[qnaIdx][0]);
				answerBtn.setVisible(true);
			}
		});

		JPanel bPanel = new JPanel();
		bPanel.setLayout(new BoxLayout(bPanel, BoxLayout.Y_AXIS));

		JPanel answerBtnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		answerBtnPanel.add(answerBtn);

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		btnPanel.add(rightBtn);
		btnPanel.add(wrongBtn);

		bPanel.add(btnPanel);
		bPanel.add(answerBtnPanel);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		center.add(formPanel);
		center.add(Box.createVerticalStrut(20));
		center.add(bPanel);

		add(center, BorderLayout.CENTER);
	}
}
