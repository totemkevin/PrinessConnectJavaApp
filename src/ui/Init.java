package ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;

import controller.InitController;
import entity.RoleEntity;
import loader.ImgLoader;
import logic.InitLogic;
import service.json.RoleJsonService;
import thread.DBConnectThread;
import util.HibernateUtil;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.hibernate.Session;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Init implements Observer {

	private JFrame frame;
	private JLabel lblStatus;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

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
		ImgLoader.getInstance().load();
		initialize();
		DBConnectThread run = new DBConnectThread();
		run.addObserver(this);
		new Thread(run).start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 412);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Image login = ImgLoader.getInstance().getImageByName("login.png");
		login = login.getScaledInstance(820, 312, Image.SCALE_SMOOTH);
		Icon icon = new ImageIcon(login);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, 822, 317);
		lblNewLabel.setIcon(icon);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblConnectDatabase = new JLabel("connect database");
		lblConnectDatabase.setBounds(10, 334, 103, 19);
		frame.getContentPane().add(lblConnectDatabase);
		
		lblStatus = new JLabel("status");
		lblStatus.setBounds(122, 334, 57, 19);
		frame.getContentPane().add(lblStatus);
		
		btnNewButton = new JButton("New button");
		btnNewButton.setEnabled(false);
		btnNewButton.setBounds(709, 330, 99, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IndexUI indexUI = new IndexUI();
				indexUI.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		
		
		WindowListener exitListener = new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "Are You Sure to Close Application?", 
		             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	HibernateUtil.shutdown();
		           System.exit(0);
		        }
		    }
		};
		frame.addWindowListener(exitListener);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		String status = ((DBConnectThread) arg0).getStatus();
		lblStatus.setText(status);
		btnNewButton.setEnabled(true);
		SwingUtilities.updateComponentTreeUI(frame);
	}
}
