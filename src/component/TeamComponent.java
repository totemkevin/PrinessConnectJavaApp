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
import loader.ImgLoader;

public class TeamComponent extends JPanel {
	private Image backgroundImage;
	private ImgLoader imgLoader;
	private List<Role> roleList;

	/**
	 * Create the panel.
	 */
	public TeamComponent(List<Role> roleList) {
		this.roleList = roleList;
		setPreferredSize(new Dimension(900, 170));
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
