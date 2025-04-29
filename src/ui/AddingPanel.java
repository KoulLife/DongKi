package ui;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.QuestionDao;

public class AddingPanel extends JPanel {
	public AddingPanel(ActionListener onBack) {
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

		JTextArea qArea = new JTextArea(7, 20);
		JTextArea aArea = new JTextArea(7, 20);
		formPanel.add(new JLabel("문제를 입력하여 주세요"));
		formPanel.add(new JScrollPane(qArea));
		formPanel.add(Box.createVerticalStrut(10));
		formPanel.add(new JLabel("답안을 입력하여 주세요"));
		formPanel.add(new JScrollPane(aArea));

		JButton resetBtn = new JButton("초기화하기");
		resetBtn.addActionListener(e -> {
			qArea.setText("");
			aArea.setText("");
		});
		JButton submitBtn = new JButton("등록하기");
		submitBtn.addActionListener(e -> {
			new QuestionDao().saveQuestionAndAnswer(qArea.getText(), aArea.getText());
			qArea.setText("");
			aArea.setText("");
		});

		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
		btnPanel.add(resetBtn);
		btnPanel.add(submitBtn);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		center.add(formPanel);
		center.add(Box.createVerticalStrut(20));
		center.add(btnPanel);

		add(center, BorderLayout.CENTER);
	}
}
