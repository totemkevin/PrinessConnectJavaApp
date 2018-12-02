package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import component.TeamComponent;
import dao.Role;

public class RoleMouseListener implements MouseListener {
	private Role role;
	private List<Role> selectList;
	private TeamComponent teamComponent;
	private boolean removeAction;

	public RoleMouseListener(TeamComponent teamComponent, List<Role> selectList, Role role,Boolean removeAction) {
		this.role = role;
		this.selectList = selectList;
		this.teamComponent = teamComponent;
		this.removeAction = removeAction;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(selectList.size() == 5) {
			JOptionPane.showMessageDialog(null, "隊伍不可超過五人", "Error", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(removeAction) {
			System.out.println("remove role");
			selectList.remove(role);
		}else {
			System.out.println("add role");
			selectList.add(role);
		}
		teamComponent.repaint();
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
