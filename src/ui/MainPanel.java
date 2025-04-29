package ui;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPanel extends JPanel {
	public MainPanel(ActionListener onReview, ActionListener onAdding) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JLabel title = new JLabel("DongKi", SwingConstants.CENTER);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setPreferredSize(new Dimension(400, 300));
		title.setMaximumSize(new Dimension(400, 300));
		title.setFont(new Font("Serif", Font.PLAIN, 50));

		JButton reviewBtn = new JButton("복습하기");
		reviewBtn.setPreferredSize(new Dimension(400, 80));
		reviewBtn.setMaximumSize(new Dimension(400, 80));
		reviewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		reviewBtn.addActionListener(onReview);

		JButton addingBtn = new JButton("추가하기");
		addingBtn.setPreferredSize(new Dimension(400, 80));
		addingBtn.setMaximumSize(new Dimension(400, 80));
		addingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		addingBtn.addActionListener(onAdding);

		add(Box.createRigidArea(new Dimension(0, 20)));
		add(title);
		add(Box.createRigidArea(new Dimension(0, 20)));
		add(reviewBtn);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(addingBtn);
	}

}
