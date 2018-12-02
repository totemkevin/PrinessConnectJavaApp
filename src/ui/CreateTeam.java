package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import component.TeamActionComponent;
import component.TeamComponent;
import dao.Role;
import listener.RoleActionMouseListener;
import listener.RoleMouseListener;
import loader.ImgLoader;
import service.RoleService;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class CreateTeam extends JFrame {

	private int NUM_OF_ROW = 10;
	public JPanel contentPane;
	public List<Role> selectList = new ArrayList<Role>();
	public JPanel panel;
	

	/**
	 * Create the frame.
	 */
	public CreateTeam() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 498, 900, 160);
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0, 0, 900, 160);
		Icon icon1 = ImgLoader.getInstance().getIconByName("teamlabel.png");
		lblNewLabel1.setIcon(icon1);
		panel.add(lblNewLabel1);
		contentPane.add(panel);
		
		
		JButton btnNewButton = new JButton("┤гец");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setBounds(1149, 635, 99, 27);
		contentPane.add(btnNewButton);
		
		RoleService roleService = new RoleService();
		List<Role> list = roleService.findAll();
		System.out.println(list.size());
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		int rowNum = 0;
		JPanel row = null;
		for(int i=0;i<list.size()/NUM_OF_ROW+1;i++) {
			row = new JPanel();
			row.setBounds(0, 100*rowNum, 1000, 100);
			for(int j=0;j<NUM_OF_ROW;j++) {
				if(rowNum*NUM_OF_ROW+j>=list.size()) {
					continue;
				}
				Role role = list.get(rowNum*NUM_OF_ROW+j);
				
				Image image = ImgLoader.getInstance().getImageByName(role.getImg());
				Image newImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				Icon icon = new ImageIcon(newImage);
				
				JLabel lblNewLabel = new JLabel();

				lblNewLabel.setBounds(100*i, 0, 100, 100);
				lblNewLabel.setIcon(icon);
				lblNewLabel.addMouseListener(new RoleActionMouseListener(this,role));
				row.add(lblNewLabel);
			}
			listPanel.add(row);
			rowNum ++;
		}
		
		
		JScrollPane scrollPane = new JScrollPane(listPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 5, 1238, 480);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		JButton button = new JButton("\u6E05\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectList = new ArrayList<Role>();
				panel.removeAll();
				JLabel lblNewLabel1 = new JLabel();
				lblNewLabel1.setBounds(0, 0, 900, 160);
				Icon icon1 = ImgLoader.getInstance().getIconByName("teamlabel.png");
				lblNewLabel1.setIcon(icon1);
				panel.add(lblNewLabel1);
				contentPane.invalidate();
				contentPane.repaint();
			}
		});
		button.setBounds(924, 518, 99, 27);
		contentPane.add(button);
	}
}
