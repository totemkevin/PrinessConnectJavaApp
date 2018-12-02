package loader;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import lombok.NoArgsConstructor;

public class ImgLoader {
	private static ImgLoader imageLoader = new ImgLoader();
	
	public static ImgLoader getInstance() {
		return imageLoader;
	}
	String PATH = "resource/img";
	public Map<String, File> fileMap = new HashMap<String, File>();
	public void load() {
		File f = new File(PATH);
		String[] n = f.list();
		System.out.println("len"+n.length);
	    for (int i=0; i<n.length; i++){
	    	File child = new File(PATH+File.separator+n[i]);
	        System.out.println(child.getName());
	        if(!child.isDirectory()){
	        	fileMap.put(child.getName(), child);
	        }
	    }
	}
	
	public File getFileByName(String name) {
		return fileMap.get(name);
	}
	
	public Image getImageByName(String name) {
		Image background = null;
		try {
			background = ImageIO.read(fileMap.get(name));
		} catch (Exception e) {
			System.out.println("img name: "+ name+" not found");
		}
		Image backgroundImage = background.getScaledInstance(900, 160, Image.SCALE_SMOOTH);
		return backgroundImage;
	}
	
	public Icon getIconByName(String name) {
		Image img = getImageByName(name);
		Icon icon = new ImageIcon(img);
		return icon;
	}
	
	public Icon getIconByNameResize(String name,int x,int y) {
		Image img = getImageByName(name);
		img = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		Icon icon = new ImageIcon(img);
		return icon;
	}
	
	
}
