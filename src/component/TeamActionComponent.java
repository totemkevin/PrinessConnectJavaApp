package component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.Role;
import loader.ImgLoader;

public class TeamActionComponent  extends JPanel {
	private Image backgroundImage;
	private ImgLoader imgLoader;
	private List<Role> roleList;

	/**
	 * Create the panel.
	 */
	public TeamActionComponent(List<Role> roleList) {
		this.roleList = roleList;
		setPreferredSize(new Dimension(900, 170));
		setLayout(null);
		imgLoader = ImgLoader.getInstance();
		imgLoader.load();
		Image background = imgLoader.getImageByName("teamlabel.png");
		backgroundImage = background.getScaledInstance(900, 160, Image.SCALE_SMOOTH);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 10, this); // see javadoc for more info on the parameters 
    }
	
	public void addRole(Role role) {
		roleList.add(role);
		JLabel label = new JLabel();
		label.setBounds(5, 5, 100, 100);
		Icon icon = imgLoader.getIconByNameResize(role.getImg(), 100, 100);
		label.setIcon(icon);
		add(label);
	}
}
