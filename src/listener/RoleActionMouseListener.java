package listener;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import component.TeamActionComponent;
import dao.Role;
import loader.ImgLoader;
import ui.CreateTeam;

public class RoleActionMouseListener implements MouseListener  {
	private CreateTeam createTeam;
	private Role role;

	public RoleActionMouseListener(CreateTeam createTeam,Role role) {
		this.createTeam = createTeam;
		this.role = role;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		List<Role> selectList = createTeam.selectList;
		if(selectList .contains(role)) {
			selectList.remove(role);
		}else {
			if(selectList.size()>=5) {
				JOptionPane.showMessageDialog(null, "隊伍不可超過五人", "Error", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			selectList.add(role);
		}
	
		Container panel = createTeam.panel;
		panel.removeAll();
		panel.setLayout(null);
		
		int i=0;
		for(Role role:selectList) {
			Icon icon = ImgLoader.getInstance().getIconByNameResize(role.getImg(),100,100);
			JLabel lblNewLabel = new JLabel();

			lblNewLabel.setBounds(20+105*i, 30, 100, 100);
			lblNewLabel.setIcon(icon);
			lblNewLabel.addMouseListener(new RoleActionMouseListener(createTeam, role));
			panel.add(lblNewLabel);
			i++;
		}
		
		JLabel lblNewLabel1 = new JLabel();
		lblNewLabel1.setBounds(0, 0, 900, 160);
		Icon icon1 = ImgLoader.getInstance().getIconByName("teamlabel.png");
		lblNewLabel1.setIcon(icon1);
		panel.add(lblNewLabel1);

		createTeam.contentPane.revalidate();
		createTeam.contentPane.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
