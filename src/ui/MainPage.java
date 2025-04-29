package ui;

import java.awt.*;

import javax.swing.*;

public class MainPage extends JFrame {
	CardLayout cardLayout;
	JPanel cardPanel;

	public MainPage() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1280, 720);

		cardLayout = new CardLayout();
		cardPanel  = new JPanel(cardLayout);

		// Create Panel, logic button move
		MainPanel main = new MainPanel(
			e -> cardLayout.show(cardPanel, "Review"),
			e -> cardLayout.show(cardPanel, "Add")
		);

		ReviewPanel review = new ReviewPanel(
			e -> cardLayout.show(cardPanel, "Main")
		);

		AddingPanel addition = new AddingPanel(
			e -> cardLayout.show(cardPanel, "Main")
		);

		// 카드패널에 붙이기
		cardPanel.add(main, "Main");
		cardPanel.add(review, "Review");
		cardPanel.add(addition, "Add");

		add(cardPanel);
		setVisible(true);
	}
}
