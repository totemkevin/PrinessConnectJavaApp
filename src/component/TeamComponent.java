package component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dao.Role;
import entity.TeamEntity;
import loader.ImgLoader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamComponent extends JPanel {
	private Image backgroundImage;
	private ImgLoader imgLoader;
	private List<Role> roleList;
	private TeamEntity teamEntity;

	/**
	 * Create the panel.
	 * @param teamEntity 
	 */
	public TeamComponent(TeamEntity teamEntity, List<Role> roleList) {
		this.roleList = roleList;
		this.teamEntity = teamEntity;
		setPreferredSize(new Dimension(900, 170));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(teamEntity.getType());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(652, 13, 234, 104);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("³Æµù");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, teamEntity.getNote(), "³Æµù", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(738, 130, 99, 27);
		add(btnNewButton);
		imgLoader = ImgLoader.getInstance();
		imgLoader.load();
		Image background = imgLoader.getImageByName("teamlabel.png");
		backgroundImage = background.getScaledInstance(900, 160, Image.SCALE_SMOOTH);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 10, this); // see javadoc for more info on the parameters 
        int i = 0;
        for(Role role:roleList) {
        	Image img = imgLoader.getImageByName(role.getImg());
           
        	img =  img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        	g.drawImage(img, 20+i*115, 40, this);
        	i++;
        }
    }
}
