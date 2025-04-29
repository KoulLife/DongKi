package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dao.QuestionDao;

public class MainPage2 extends JFrame {
	CardLayout cardLayout;
	JPanel cardPanel;

	public MainPage2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		// == [UI] Main ==
		JPanel mainPage = new JPanel();
		mainPage.setLayout(new BoxLayout(mainPage, BoxLayout.Y_AXIS));
		{
			JLabel textLabel = new JLabel("DongKi", SwingConstants.CENTER);
			textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			textLabel.setPreferredSize(new Dimension(400, 300));
			textLabel.setMaximumSize(new Dimension(400, 300));
			textLabel.setFont(new Font("Serif", Font.PLAIN, 50));

			JButton reviewButton = new JButton("복습하기");
			reviewButton.setPreferredSize(new Dimension(400, 80));
			reviewButton.setMaximumSize(new Dimension(400, 80));
			reviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			JButton addButton = new JButton("추가하기");
			addButton.setPreferredSize(new Dimension(400, 80));
			addButton.setMaximumSize(new Dimension(400, 80));
			addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			mainPage.add(Box.createRigidArea(new Dimension(0, 20)));
			mainPage.add(textLabel);
			mainPage.add(Box.createRigidArea(new Dimension(0, 20)));
			mainPage.add(reviewButton);
			mainPage.add(Box.createRigidArea(new Dimension(0, 10)));
			mainPage.add(addButton);

			// 버튼 클릭 시 카드 전환
			reviewButton.addActionListener(e -> cardLayout.show(cardPanel, "Review"));
			addButton.addActionListener(e -> cardLayout.show(cardPanel, "Add"));
		}

		// == [UI] Review ==
		JPanel reviewPage = new JPanel();
		reviewPage.setLayout(new BoxLayout(reviewPage, BoxLayout.Y_AXIS));
		{
			JPanel reviewTopbar = new JPanel(new BorderLayout());
			JButton reviewBackButton = new JButton("<-");
			reviewBackButton.setPreferredSize(new Dimension(50, 20));
			reviewBackButton.setMaximumSize(new Dimension(50, 20));

			JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
			backButtonPanel.add(reviewBackButton);
			backButtonPanel.setOpaque(false);

			JLabel reviewTitle = new JLabel("DongKi", SwingConstants.CENTER);
			reviewTitle.setPreferredSize(new Dimension(500, 20));
			reviewTitle.setMaximumSize(new Dimension(500, 20));
			JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			titlePanel.add(reviewTitle);
			titlePanel.setOpaque(false);

			reviewTopbar.add(backButtonPanel, BorderLayout.WEST);
			reviewTopbar.add(titlePanel, BorderLayout.CENTER);

			reviewPage.add(Box.createRigidArea(new Dimension(0, 20)));
			reviewPage.add(reviewTopbar);

			reviewBackButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));
		}

		// == [UI] Add ==
		JPanel addPage = new JPanel(new BorderLayout());

		// 1) Topbar (뒤로가기 + 제목)
		JPanel addTopbar = new JPanel(new BorderLayout());
		{
			JButton addBackButton = new JButton("<-");
			addBackButton.setPreferredSize(new Dimension(50, 20));
			addBackButton.setMaximumSize(new Dimension(50, 20));
			JPanel addBackButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
			addBackButtonPanel.add(addBackButton);
			addBackButtonPanel.setOpaque(false);

			JLabel addTitle = new JLabel("DongKi", SwingConstants.CENTER);
			addTitle.setPreferredSize(new Dimension(500, 20));
			addTitle.setMaximumSize(new Dimension(500, 20));
			JPanel addTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			addTitlePanel.add(addTitle);
			addTitlePanel.setOpaque(false);

			addTopbar.add(addBackButtonPanel, BorderLayout.WEST);
			addTopbar.add(addTitlePanel, BorderLayout.CENTER);

			addBackButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));
		}
		addPage.add(addTopbar, BorderLayout.NORTH);

		// 2) Content Panel (폼 + 버튼)
		//  2-1) 폼 영역
		JPanel addContentPanel = new JPanel();
		addContentPanel.setLayout(new BoxLayout(addContentPanel, BoxLayout.Y_AXIS));
		addContentPanel.setMaximumSize(new Dimension(600, 400));
		addContentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		{
			JLabel questionLabel = new JLabel("문제를 입력하여 주세요", SwingConstants.CENTER);
			questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			addContentPanel.add(questionLabel);

			JPanel questionWrapper = new JPanel(new BorderLayout());
			questionWrapper.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
			JTextArea questionArea = new JTextArea(7, 20);
			questionWrapper.add(questionArea, BorderLayout.CENTER);
			addContentPanel.add(questionWrapper);

			JLabel answerLabel = new JLabel("답안을 입력하여 주세요", SwingConstants.CENTER);
			answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			addContentPanel.add(answerLabel);

			JPanel answerWrapper = new JPanel(new BorderLayout());
			answerWrapper.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
			JTextArea answerArea = new JTextArea(7, 20);
			answerWrapper.add(answerArea, BorderLayout.CENTER);
			addContentPanel.add(answerWrapper);

			//  2-2) 버튼 영역
			JPanel addButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
			addButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

			JButton resetButton = new JButton("초기화하기");
			JButton submitButton = new JButton("등록하기");
			addButtonPanel.add(resetButton);
			addButtonPanel.add(submitButton);

			resetButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					questionArea.setText("");
					answerArea.setText("");
				}
			});

			submitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					QuestionDao qd = new QuestionDao();
					qd.saveQuestionAndAnswer(questionArea.getText(), answerArea.getText());

					questionArea.setText("");
					answerArea.setText("");
				}
			});

			//  2-3) 중앙 래퍼: 위쪽 여백을 50px 주고 폼+버튼 순으로 붙임
			JPanel centerWrapper = new JPanel();
			centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
			centerWrapper.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
			centerWrapper.add(addContentPanel);
			centerWrapper.add(Box.createRigidArea(new Dimension(0, 20)));
			centerWrapper.add(addButtonPanel);

			addPage.add(centerWrapper, BorderLayout.CENTER);

			// 카드패널에 붙이기
			cardPanel.add(mainPage, "Main");
			cardPanel.add(reviewPage, "Review");
			cardPanel.add(addPage, "Add");

			add(cardPanel);
			setVisible(true);
		}
	}

	public static void main(String[] args) {
		new MainPage2();
	}
}
