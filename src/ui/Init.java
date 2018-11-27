package ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import controller.InitController;
import loader.imgLoader;
import logic.InitLogic;
import util.HibernateUtil;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import org.hibernate.Session;
import javax.swing.JPanel;

public class Init {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Init window = new Init();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Init() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblConnectDatabase = new JLabel("connect database");
		lblConnectDatabase.setBounds(14, 13, 103, 19);
		frame.getContentPane().add(lblConnectDatabase);
		
		JLabel lblStatus = new JLabel("status");
		lblStatus.setBounds(131, 13, 57, 19);
		frame.getContentPane().add(lblStatus);
		
		InitLogic logic = new InitLogic();
		String status = logic.checkDataBaseStatus();
		lblStatus.setText(status);
		
		JLabel label = new JLabel("");
		label.setBounds(14, 45, 404, 180);
		frame.getContentPane().add(label);
		
		imgLoader imgLoader = new imgLoader();
		imgLoader.load();
		
		Image image;
		try {
			image = ImageIO.read(imgLoader.getFileByName("001.jpg"));
			Icon icon = new ImageIcon(image);
			label.setIcon(icon);
			
			JButton btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.dispose();
					IndexUI indexUI = new IndexUI();
					indexUI.setVisible(true);
				}
			});
			btnNewButton.setBounds(170, 228, 99, 27);
			frame.getContentPane().add(btnNewButton);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public JFrame getFrame(){
		return frame;
	}
}
