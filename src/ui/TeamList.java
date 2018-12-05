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
import dao.Role;
import entity.TeamEntity;
import entity.TeamMemberEntity;
import loader.ImgLoader;
import logic.TeamLogic;
import util.HibernateUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class TeamList extends JFrame {

	private JPanel contentPane;
	private int page = 0;
	private int limit = 4;
	private JPanel listPanel;
	private JScrollPane scrollPane;

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
		
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		TeamLogic teamLogic = new TeamLogic();
		List<TeamEntity> list = teamLogic.getTeams(page*4,limit);
		for(TeamEntity teamEntity :list) {
			List<Role> roleList = new ArrayList<Role>();
			List<TeamMemberEntity> tmList = teamEntity.getTeamMemberEntitys();
			for(TeamMemberEntity tm : tmList) {
				roleList.add(tm.getRole());
			}
			TeamComponent tc = new TeamComponent(teamEntity,roleList);
			listPanel.add(tc);
		}
		
		scrollPane = new JScrollPane(listPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 5, 930, 665);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.setLayout(null);
		contentPane.add(scrollPane);
		
		JButton button = new JButton("新增隊伍");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTeam createTeam = new CreateTeam();
				createTeam.setVisible(true);
				dispose();
			}
		});
		button.setBounds(1149, 5, 99, 27);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("上一頁");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(page == 0) {
					JOptionPane.showMessageDialog(null, "目前是第一頁", "Error", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				page --;
				
				contentPane.remove(listPanel);
				contentPane.remove(scrollPane);
				listPanel = new JPanel();
				listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
				
				TeamLogic teamLogic = new TeamLogic();
				List<TeamEntity> list = teamLogic.getTeams(page*4,limit);
				for(TeamEntity teamEntity :list) {
					List<Role> roleList = new ArrayList<Role>();
					List<TeamMemberEntity> tmList = teamEntity.getTeamMemberEntitys();
					for(TeamMemberEntity tm : tmList) {
						roleList.add(tm.getRole());
					}
					TeamComponent tc = new TeamComponent(teamEntity,roleList);
					listPanel.add(tc);
				}
				scrollPane = new JScrollPane(listPanel,
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(10, 5, 930, 665);
				scrollPane.getVerticalScrollBar().setUnitIncrement(16);
				contentPane.setLayout(null);
				contentPane.add(scrollPane);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnNewButton.setBounds(954, 5, 99, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("下一頁");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				page ++;
				List<TeamEntity> list = teamLogic.getTeams(page*4,limit);
				if(list.size() == 0) {
					JOptionPane.showMessageDialog(null, "目前是最後一頁", "Error", JOptionPane.INFORMATION_MESSAGE);
					page --;
					return ;
				}
				contentPane.remove(listPanel);
				contentPane.remove(scrollPane);
				listPanel = new JPanel();
				listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
				
				TeamLogic teamLogic = new TeamLogic();
				
				
				for(TeamEntity teamEntity :list) {
					List<Role> roleList = new ArrayList<Role>();
					List<TeamMemberEntity> tmList = teamEntity.getTeamMemberEntitys();
					for(TeamMemberEntity tm : tmList) {
						roleList.add(tm.getRole());
					}
					TeamComponent tc = new TeamComponent(teamEntity,roleList);
					listPanel.add(tc);
				}
				scrollPane = new JScrollPane(listPanel,
						JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(10, 5, 930, 665);
				scrollPane.getVerticalScrollBar().setUnitIncrement(16);
				contentPane.setLayout(null);
				contentPane.add(scrollPane);
				
				contentPane.revalidate();
				contentPane.repaint();
			}
		});
		btnNewButton_1.setBounds(954, 45, 99, 27);
		contentPane.add(btnNewButton_1);
	}

}
