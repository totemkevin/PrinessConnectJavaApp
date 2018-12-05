package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import component.TeamComponent;
import loader.ImgLoader;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Icon;
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
		setBounds(100, 100, 798, 505);
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
		btnNewButton.setBounds(620, 13, 151, 27);
		btnNewButton.setEnabled(false);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("常用隊伍查詢");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamList teamList = new TeamList();
				teamList.setVisible(true);
			}
		});
		button.setBounds(620, 53, 151, 27);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(15, 15, 591, 402);
		Icon icon = ImgLoader.getInstance().getIconByNameResize("party.png", 591, 402);
		lblNewLabel.setIcon(icon);
		contentPane.add(lblNewLabel);
		
	}
}
