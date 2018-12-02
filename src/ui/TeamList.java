package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import component.TeamComponent;
import loader.ImgLoader;
import util.HibernateUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class TeamList extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamList frame = new TeamList();
					frame.setVisible(true);
					ImgLoader.getInstance().load();
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamList() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		
		JScrollPane scrollPane = new JScrollPane(listPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 5, 930, 665);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		JButton button = new JButton("·s¼W¶¤¥î");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTeam createTeam = new CreateTeam();
				createTeam.setVisible(true);
			}
		});
		button.setBounds(1149, 5, 99, 27);
		contentPane.add(button);
	}

}
