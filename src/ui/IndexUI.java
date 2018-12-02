package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.TeamComponent;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class IndexUI extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public IndexUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("新增角色");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRole addRole = new AddRole();
				addRole.setVisible(true);
			}
		});
		btnNewButton.setBounds(1095, 13, 151, 27);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("常用隊伍查詢");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamList teamList = new TeamList();
				teamList.setVisible(true);
			}
		});
		button.setBounds(1095, 53, 151, 27);
		contentPane.add(button);
		
	}
}
