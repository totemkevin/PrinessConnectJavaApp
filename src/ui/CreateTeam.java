package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
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
import javax.swing.DefaultListModel;
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
import logic.TeamLogic;
import service.RoleService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JList;

public class CreateTeam extends JFrame {

	private int NUM_OF_ROW = 10;
	public JPanel contentPane;
	public List<Role> selectList = new ArrayList<Role>();
	public JPanel panel;
	private JList jlist;
	private JTextArea textArea;
	

	/**
	 * Create the frame.
	 */
	public CreateTeam() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 418, 900, 160);
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0, 0, 900, 160);
		Icon icon1 = ImgLoader.getInstance().getIconByName("teamlabel.png");
		
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
		button.setBounds(924, 635, 99, 27);
		contentPane.add(button);
		lblNewLabel1.setIcon(icon1);
		panel.add(lblNewLabel1);
		contentPane.add(panel);
		
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TeamLogic teamLogic = new TeamLogic();
				try {
					teamLogic.save(textArea.getText(), jlist.getSelectedValue().toString(), selectList);
					JOptionPane.showMessageDialog(null, "成功", "info", JOptionPane.INFORMATION_MESSAGE);
					TeamList teamList = new TeamList();
					teamList.setVisible(true);
					dispose();
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "失敗", "info", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(1037, 635, 99, 27);
		contentPane.add(btnNewButton);
		
		RoleService roleService = new RoleService();
		List<Role> list = roleService.findAll();
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
		scrollPane.setBounds(10, 5, 1238, 400);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_1 = new JLabel("備註");
		lblNewLabel_1.setBounds(10, 591, 57, 19);
		contentPane.add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setBounds(81, 591, 829, 71);
		contentPane.add(textArea);
		
		DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("1 v 1");
        listModel.addElement("活動王H");
        listModel.addElement("活動王VH");

        //Create the list and put it in a scroll pane.
        jlist = new JList(listModel);
        jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlist.setSelectedIndex(0);
        jlist.setVisibleRowCount(5);
        
        JScrollPane listScrollPane = new JScrollPane(jlist);
        listScrollPane.setBounds(924, 436, 324, 142);
        contentPane.add(listScrollPane);
        
        JButton btnNewButton_1 = new JButton("返回");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		TeamList teamList = new TeamList();
				teamList.setVisible(true);
				dispose();
        	}
        });
        btnNewButton_1.setBounds(1149, 635, 99, 27);
        contentPane.add(btnNewButton_1);
	}
}
